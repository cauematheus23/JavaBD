package projetofacul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Clientes extends Principal{

	public static void testeInput() {
	Scanner scanner = new Scanner(System.in); // criar um objeto da classe Scanner
	int escolhatabela = scanner.nextInt();
	if (escolhatabela == 1) {
		System.out.println("TABELA CLIENTES");
		System.out.println("------------------------------");
		System.out.println("[1] Consultar Dados\n[2] Inserir Dados\n[3] Deletar Dados\n[4] Finalizar programa" );
		int escolha = scanner.nextInt();
		if (escolha == 1) {
			Clientes.consultarDadosclientes(connection);
		} else if (escolha == 2) {
			Clientes.cadastrarDados(connection);
		} else if (escolha == 3) {
			Clientes.deletarDados(Principal.connection);
		} else if (escolha == 4) {
			
		} else {
			System.out.println("DIGITE UM VALOR VALIDO");
		}
		}
	}

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
}