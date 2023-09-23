package projetofacul;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlite:/C:\\Users\\cauem\\eclipse-workspace\\Projeto\\BDHOTEL.db";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("------------------------------");
                System.out.println("BANCO DE DADOS HOTEL");
                System.out.println("------------------------------");
                System.out.println("[1] Clientes\n[2] Quartos\n[3] Reservas\n[4] Serviços\n[5] Encerrar Programa");
                int escolhaTabela = scanner.nextInt();

                switch (escolhaTabela) {
                case 1:
                    menuClientes(connection);
                    break;
                case 2:
                    menuQuartos(connection);
                    break;
                case 3:
                    menuReservas(connection);
                    break;
                case 4:
                    menuServicos(connection);
                    break;
                case 5:
                    System.out.println("Encerrando o programa.");
                    System.exit(0); // Encerra o programa
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void menuClientes(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("TABELA CLIENTES");
            System.out.println("------------------------------");
            System.out.println("[1] Consultar Dados\n[2] Inserir Dados\n[3] Deletar Dados\n[4] Atualizar Dados\n[5] Detalhes do Cliente\n[6] Voltar");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    Clientes.consultarDadosclientes(connection);
                    break;
                case 2:
                    Clientes.cadastrarDados(connection);
                    break;
                case 3:
                    Clientes.deletarDados(connection);
                    break;
                case 4:
                    Clientes.atualizarDados(connection);
                    break;
                case 5:
                    System.out.println("Digite o ID do Cliente:");
                    int idCliente = scanner.nextInt();
                    Clientes.exibirDetalhesCliente(connection, idCliente);
                    break;
                case 6:
                    return; // Retorna ao menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public static void menuQuartos(Connection connection) {
    	Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("TABELA QUARTOS");
            System.out.println("------------------------------");
            System.out.println("[1] Consultar Dados\n[2] Cadastrar Dados\n[3] Detalhes do Quarto\n[4] Voltar");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    Quartos.consultarDadosQuartos(connection);
                    break;
                case 2:
                    Quartos.cadastrarDadosQuarto(connection);
                    break;
       
                case 3:
                    System.out.println("Digite o ID do Quarto:");
                    int idCliente = scanner.nextInt();
                    Quartos.exibirDetalhesQuarto(connection, idCliente);
                    break;
                case 4:
                    return; // Retorna ao menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public static void menuReservas(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("TABELA RESERVAS");
            System.out.println("------------------------------");
            System.out.println("[1] Consultar Reservas\n[2] Cadastrar Reserva\n[3] Atualizar Reserva\n[4] Remover Reserva\n[5] Voltar");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    Reservas.consultarDadosReservas(connection);
                    break;
                case 2:
                    Reservas.cadastrarReserva(connection);
                    break;
                case 3:
                    Reservas.atualizarReserva(connection);
                    break;
                case 4:
                    Reservas.deletarReserva(connection);
                    break;
                case 5:
                    return; // Retorna ao menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    

    public static void menuServicos(Connection connection) {
    	Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("TABELA SERVIÇOS");
            System.out.println("------------------------------");
            System.out.println("[1] Consultar Serviços\n[2] Cadastrar Serviço\n[3] Atualizar Serviço\n[4] Excluir Serviço\n[5] Voltar");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    Serviços.consultarDadosServiços(connection);
                    break;
                case 2:
                    Serviços.cadastrarServiço(connection);
                    break;
       
                case 3:
                	Serviços.atualizarServiço(connection);
                    break;
                case 4:
                   	Serviços.deletarServiço(connection);
                case 5:
                	return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
