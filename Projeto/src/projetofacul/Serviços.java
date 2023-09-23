package projetofacul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Serviços {

    public static void consultarDadosServiços(Connection connection) {
        try {
            String sql = "SELECT * FROM servicos";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            System.out.println("-------------------------------------------------");
            System.out.println("| ID | NOME DO SERVIÇO         | VALOR DO SERVIÇO |");
            System.out.println("-------------------------------------------------");

            while (result.next()) {
                int idServiço = result.getInt("idservico");
                String nomeServiço = result.getString("nomeservico");
                double valorServiço = result.getDouble("valorservico");

                System.out.printf("| %-2d | %-23s | %-16.2f |\n", idServiço, nomeServiço, valorServiço);
            }

            System.out.println("-------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void cadastrarServiço(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome do serviço: ");
        String nomeServiço = scanner.nextLine();

        System.out.println("Informe o valor do serviço: ");
        double valorServiço = scanner.nextDouble();

        String sql = "INSERT INTO servicos (nomeservico, valorservico) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeServiço);
            preparedStatement.setDouble(2, valorServiço);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Serviço cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o serviço.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar serviço: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void atualizarServiço(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do serviço que deseja atualizar: ");
        int idServiço = scanner.nextInt();

        scanner.nextLine(); // Consuma a quebra de linha

        System.out.println("Informe o novo nome do serviço: ");
        String novoNomeServiço = scanner.nextLine();

        System.out.println("Informe o novo valor do serviço: ");
        double novoValorServiço = scanner.nextDouble();

        String sql = "UPDATE servicos SET nomeservico = ?, valorservico = ? WHERE idservico = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, novoNomeServiço);
            preparedStatement.setDouble(2, novoValorServiço);
            preparedStatement.setInt(3, idServiço);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Serviço atualizado com sucesso!");
            } else {
                System.out.println("Nenhum serviço foi atualizado. Verifique o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar serviço: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deletarServiço(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do serviço que deseja deletar: ");
        int idServiço = scanner.nextInt();

        String sql = "DELETE FROM servicos WHERE idservico = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idServiço);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Serviço deletado com sucesso!");
            } else {
                System.out.println("Nenhum serviço foi encontrado. Verifique o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar serviço: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
