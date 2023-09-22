package projetofacul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Quartos {
	public static void consultarDadosclientes(Connection connection) {
		try {	
			// 3 - definir a query SQL
			String sql = "SELECT * from clientes";
			// 4 - abrir a conexão com o banco de dados 
			Statement statement = connection.createStatement();
			// 5 - executar a query
			ResultSet result = statement.executeQuery(sql);
			// 6 - acessar e exibir o resultado
			System.out.println("---------------------------");
			System.out.println("| ID | NOME | Email | Telefone |");
			System.out.println("---------------------------");
			while(result.next()) 
		{ String idclientes = result.getString("idclientes");
				String nomecliente = result.getString("nomecliente");
				String emailcliente = result.getString("emailcliente");
				String telcliente = result.getString("telcliente");


			    System.out.printf("| %-4d | %-10s | %-15s | %-10s |\n", idclientes, nomecliente, emailcliente, telcliente);
		}
			System.out.println("----------------------");

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
			System.out.println("Digite o ID (inteiro) da pessoa que o nome vai ser atualizado: ");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Digite o novo nome da pessoa: ");
			String novonome = scanner.nextLine();
			String sql = "UPDATE pessoas SET nome = ? WHERE id = ?";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, novonome);
				preparedStatement.setInt(2, id);
				int rowsAffected = preparedStatement.executeUpdate();
				if(rowsAffected > 0) {
					System.out.println("Registro atualizado com sucesso");
				} else {
					System.out.println("Nenhum registro foi atualizado. Verifique o ID informado.");
				}
			}	catch(SQLException e) {
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
