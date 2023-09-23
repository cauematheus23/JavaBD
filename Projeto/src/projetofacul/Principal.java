package projetofacul;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Principal 
{
	public static Connection connection;

	public static void main(String[] args) {
// 0 - adicionar o arquivo .jar	da biblioteca SQLite JDBC
		// 1 - identificar qual que é o caminho do arquivo de banco de dados
			String jdbcUrl = "jdbc:sqlite:/C:\\Users\\cauem\\eclipse-workspace\\Projeto\\BDHOTEL.db";
			
	try {
		// 2 - conectar com o banco de dados
		Connection connection = DriverManager.getConnection(jdbcUrl);
		while (true) {
			Scanner scanner = new Scanner(System.in); // criar um objeto da classe Scanner
			System.out.println("------------------------------");
			System.out.println("BANCO DE DADOS HOTEL");
			System.out.println("------------------------------");
			System.out.println("[1] Clientes\n[2] Quartos\n[3] Reservas\n[4] Serviços" );
			while (true) { 
				int escolhatabela = scanner.nextInt();
				if (escolhatabela == 1) {
					System.out.println("TABELA CLIENTES");
					System.out.println("------------------------------");
					System.out.println("[1] Consultar Dados\n[2] Inserir Dados\n[3] Deletar Dados\n[4] Atualizar Dados\n [5] Voltar" );
					int escolha = scanner.nextInt();
					if (escolha == 1) {
						Clientes.consultarDadosclientes(connection);
					} else if (escolha == 2) {
						Clientes.cadastrarDados(connection);
					} else if (escolha == 3) {
						Clientes.deletarDados(connection);
					} else if (escolha == 4) {
						Clientes.atualizarDados(connection);
					} else if (escolha == 5) {
						break;
					} else {
						System.out.println("DIGITE UM VALOR VALIDO");
					}

			}
			}
			System.out.println("[1] Consultar Dados\n[2] Inserir Dados\n[3] Deletar Dados\n[4] Finalizar programa" );
			System.out.println("------------------------------");

			int escolha = scanner.nextInt();
			if (escolha == 1) {
			} else if (escolha == 2) {
				Clientes.cadastrarDados(connection);
			} else if (escolha == 3) {
				Clientes.deletarDados(connection);
			} else if (escolha == 4) {
				break ;
			} else {
				System.out.println("DIGITE UM VALOR VALIDO");
			}
			
		}
	}  catch (SQLException e) {
		System.out.println("ERRO" + e);
		e.printStackTrace();
		}
}
	
	
	
	
		
}
