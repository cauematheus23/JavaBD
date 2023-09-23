package projetofacul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Quartos {
    // Métodos para a tabela "Quartos"...

    public static void consultarDadosQuartos(Connection connection) {
        try {
            String sql = "SELECT * FROM quartos";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("|                                    QUARTOS                                    |");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("| %-3s | %-15s | %-10s | %-15s |\n", "ID", "TIPO", "DIÁRIA", "DISPONIBILIDADE");
            System.out.println("---------------------------------------------------------------------------------");

            while (result.next()) {
                int idQuarto = result.getInt("idquarto");
                String tipoQuarto = result.getString("tipoquarto");
                double diaria = result.getDouble("diaria");
                boolean disponibilidade = result.getBoolean("disponibilidade");

                System.out.printf("| %-3d | %-15s | %-10.2f | %-15s |\n", idQuarto, tipoQuarto, diaria, disponibilidade ? "Disponível" : "Indisponível");
            }

            System.out.println("---------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
            e.printStackTrace();
        }
    }

    public static void cadastrarDadosQuarto(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o ID (inteiro e único) do quarto que deseja inserir no banco: ");
        int idQuarto = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Informe o tipo de quarto: ");
        String tipoQuarto = scanner.nextLine();
        System.out.println("Informe a diária: ");
        double diaria = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Informe a disponibilidade (True para disponivel ou false para indisponivel): ");
        boolean disponibilidade = scanner.nextBoolean();

        String sql = "INSERT INTO quartos (idquarto, tipoquarto, diaria, disponibilidade) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idQuarto);
            preparedStatement.setString(2, tipoQuarto);
            preparedStatement.setDouble(3, diaria);
            preparedStatement.setBoolean(4, disponibilidade);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Registro inserido com sucesso!");
            } else {
                System.out.println("Falha ao inserir o registro");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

    // Outros métodos para atualizar e deletar quartos...

    public static void exibirDetalhesQuarto(Connection connection, int idQuarto) {
        String query = "SELECT idquarto, tipoquarto, diaria, disponibilidade FROM quartos WHERE idquarto = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idQuarto);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("idquarto");
                String tipoQuarto = resultSet.getString("tipoquarto");
                double diaria = resultSet.getDouble("diaria");
                boolean disponibilidade = resultSet.getBoolean("disponibilidade");

                System.out.println("Detalhes do Quarto:");
                System.out.println("ID: " + id);
                System.out.println("Tipo: " + tipoQuarto);
                System.out.println("Diária: " + diaria);
                System.out.println("Disponibilidade: " + (disponibilidade ? "Disponível" : "Indisponível"));
            } else {
                System.out.println("Quarto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar detalhes do quarto: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
