package projetofacul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Clientes {



public static void consultarDadosclientes(Connection connection) {
	try {	
		// 3 - definir a query SQL
		String sql = "SELECT * from clientes";
		// 4 - abrir a conexão com o banco de dados 
		Statement statement = connection.createStatement();
		// 5 - executar a query
		ResultSet result = statement.executeQuery(sql);
		// 6 - acessar e exibir o resultado
		System.out.println("---------------------------------------------------------------------------------");
    	System.out.println("|                                   CLIENTES                                    |");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-3s | %-20s | %-30s | %-15s |\n", "ID", "NOME", "EMAIL", "TELEFONE");
        System.out.println("---------------------------------------------------------------------------------");
		while(result.next()) 
	{ String idclientes = result.getString("idclientes");
			String nomecliente = result.getString("nomecliente");
			String emailcliente = result.getString("emailcliente");
			String telcliente = result.getString("telcliente");

			System.out.printf("| %-3s | %-20s | %-30s | %-15s |\n", idclientes, nomecliente, emailcliente, telcliente);

	}
		System.out.println("---------------------------------------------------------------------------------");


		}	catch (SQLException e) 
			{	System.out.println("ERRO" + e);
			e.printStackTrace();
			}
	
	}
	
	public static void cadastrarDados(Connection connection) {
		Scanner scanner = new Scanner(System.in); // criar um objeto da classe Scanner
		System.out.println("Informe o ID(inteiro e unico) da pessoa que deseja inserir no bd:  ");
		int idclientes = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Informe o nome do cliente que deseja inserir no bd:  ");
		String nomecliente = scanner.nextLine();
		System.out.println("Informe o email do cliente que deseja inserir no bd:  ");
		String emailcliente = scanner.nextLine();
		System.out.println("Informe o Telefone do cliente que deseja inserir no bd:  ");
		String telcliente = scanner.nextLine();
		String sql = "INSERT INTO clientes (idclientes,nomecliente,emailcliente,telcliente) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,idclientes);
			preparedStatement.setString(2, nomecliente);
			preparedStatement.setString(3, emailcliente);
			preparedStatement.setString(4, telcliente);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Registro inserido com sucesso!");
			} else {
				System.out.println("Falha ao inserir o registro");
			}
		}catch (SQLException e) {
			System.out.println("Erro ao inserir dados: " + e.getMessage());
		}
}
	public static void atualizarDados(Connection connection) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Digite o ID (inteiro) da pessoa que deseja atualizar: ");
	    int id = scanner.nextInt();
	    scanner.nextLine(); // Consuma a quebra de linha
	    System.out.println("Digite o novo nome da pessoa: ");
	    String novoNome = scanner.nextLine();
	    System.out.println("Digite o novo email da pessoa: ");
	    String novoEmail = scanner.nextLine();
	    System.out.println("Digite o novo telefone da pessoa: ");
	    String novoTelefone = scanner.nextLine();

	    String sql = "UPDATE clientes SET nomecliente = ?, emailcliente = ?, telcliente = ? WHERE idclientes = ?";

	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, novoNome);
	        preparedStatement.setString(2, novoEmail);
	        preparedStatement.setString(3, novoTelefone);
	        preparedStatement.setInt(4, id);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Registro atualizado com sucesso");
	        } else {
	            System.out.println("Nenhum registro foi atualizado. Verifique o ID informado.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar registro: " + e.getMessage());
	    }
	}

	public static void deletarDados(Connection connection) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o ID (inteiro) da pessoa que deseja deletar do banco: ");
		int idcliente = scanner.nextInt();
		String sql = "DELETE FROM clientes WHERE idclientes = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idcliente);
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Registro deletado com sucesso");
			} else {
				System.out.println("Nenhum registro encontrado. Verifique o ID informado.");
			}
		}	catch(SQLException e) {
			System.out.println("Erro ao deletar registro: " + e.getMessage());
					
		}
		}
	
	public static void exibirDetalhesCliente(Connection connection, int idCliente) {
        String query = "SELECT idclientes, nomecliente, emailcliente, telcliente FROM Clientes WHERE idclientes = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCliente);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("idclientes");
                String nome = resultSet.getString("nomecliente");
                String email = resultSet.getString("emailcliente");
                String telefone = resultSet.getString("telcliente");

                System.out.println("Detalhes do Cliente:");
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Email: " + email);
                System.out.println("Telefone: " + telefone);
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar detalhes do cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}