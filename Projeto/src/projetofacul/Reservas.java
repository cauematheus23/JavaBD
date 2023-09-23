package projetofacul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Reservas {

    public static void consultarDadosReservas(Connection connection) {
        try {
            String sql = "SELECT * FROM reservas";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("| ID | ID Cliente | ID Quarto | Check-in   | Check-out  | Total Reserva | Estado Reserva|");
            System.out.println("-----------------------------------------------------------------------------------------");

            while (result.next()) {
                int idReserva = result.getInt("idreserva");
                int idCliente = result.getInt("idcliente");
                int idQuarto = result.getInt("idquarto");
                String checkin = result.getString("checkin");
                String checkout = result.getString("checkout");
                double totalReserva = result.getDouble("totalreserva");
                String estadoReserva = result.getString("estadoreserva");

                System.out.printf("| %-2d | %-10d | %-9d | %-8s | %-9s | %-13.2f | %-13s |\n",
                        idReserva, idCliente, idQuarto, checkin, checkout, totalReserva, estadoReserva);
            }

            System.out.println("-----------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void cadastrarReserva(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID do cliente: ");
        int idCliente = scanner.nextInt();

        System.out.println("Informe o ID do quarto: ");
        int idQuarto = scanner.nextInt();

        scanner.nextLine(); // Consuma a quebra de linha

        System.out.println("Informe a data de check-in (YYYY-MM-DD): ");
        String dataCheckin = scanner.nextLine();

        System.out.println("Informe a data de check-out (YYYY-MM-DD): ");
        String dataCheckout = scanner.nextLine();
        
        System.out.println("Digite o estado da reserva: ");
        String estadoreserva = scanner.nextLine();
        try {
            // Consulta o valor da diária com base no ID do quarto
            String consultaDiaria = "SELECT diaria FROM quartos WHERE idquarto = ?";
            PreparedStatement consultaDiariaStmt = connection.prepareStatement(consultaDiaria);
            consultaDiariaStmt.setInt(1, idQuarto);
            ResultSet resultadoConsultaDiaria = consultaDiariaStmt.executeQuery();

            double valorDiaria = 0.0;

            if (resultadoConsultaDiaria.next()) {
                valorDiaria = resultadoConsultaDiaria.getDouble("diaria");
            } else {
                System.out.println("Quarto não encontrado. Verifique o ID do quarto.");
                return;
            }

            // Parse das datas para objetos LocalDate
            LocalDate checkin = LocalDate.parse(dataCheckin);
            LocalDate checkout = LocalDate.parse(dataCheckout);

            // Cálculo da diferença de dias
            long dias = ChronoUnit.DAYS.between(checkin, checkout);

            // Cálculo do total da reserva
            double totalReserva = dias * valorDiaria;

            String sql = "INSERT INTO reservas (idcliente, idquarto, checkin, checkout, totalreserva, estadoreserva) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCliente);
            preparedStatement.setInt(2, idQuarto);
            preparedStatement.setString(3, dataCheckin);
            preparedStatement.setString(4, dataCheckout);
            preparedStatement.setDouble(5, totalReserva);
            preparedStatement.setString(6, estadoreserva); // Define o estado como "Ativa"

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reserva cadastrada com sucesso.");
                System.out.println("Total da reserva: R$" + totalReserva);
            } else {
                System.out.println("Falha ao cadastrar a reserva.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar a reserva: " + e.getMessage());
        }
    }

    public static void atualizarReserva(Connection connection) {
    	
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID da reserva que deseja atualizar: ");
        int idReserva = scanner.nextInt();

        scanner.nextLine(); // Consuma a quebra de linha
        System.out.println("Informe o id do novo quarto: ");
        int novoIDquarto = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Informe a nova data de Check-in (YYYY-MM-DD): ");
        String novoCheckin = scanner.nextLine();
        System.out.println("Informe a nova data de Check-out (YYYY-MM-DD): ");
        String novoCheckout = scanner.nextLine();
        System.out.println("Informe o novo estado da reserva: ");
        String novoEstadoReserva = scanner.nextLine();

        String sql = "UPDATE reservas " +
                     "SET checkin = ?, checkout = ?, totalreserva = ?, estadoreserva = ? " +
                     "WHERE idreserva = ?";

        try {
        	// Consulta o valor da diária com base no ID do quarto
            String consultaDiaria = "SELECT diaria FROM quartos WHERE idquarto = ?";
            PreparedStatement consultaDiariaStmt = connection.prepareStatement(consultaDiaria);
            consultaDiariaStmt.setInt(1, novoIDquarto);
            ResultSet resultadoConsultaDiaria = consultaDiariaStmt.executeQuery();

            double valorDiaria = 0.0;

            if (resultadoConsultaDiaria.next()) {
                valorDiaria = resultadoConsultaDiaria.getDouble("diaria");
            } else {
                System.out.println("Quarto não encontrado. Verifique o ID do quarto.");
                return;
            }

            // Parse das datas para objetos LocalDate
            LocalDate checkin = LocalDate.parse(novoCheckin);
            LocalDate checkout = LocalDate.parse(novoCheckout);

            // Cálculo da diferença de dias
            long dias = ChronoUnit.DAYS.between(checkin, checkout);

            // Cálculo do total da reserva
            double totalReserva = dias * valorDiaria;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, novoCheckin);
            preparedStatement.setString(2, novoCheckout);
            preparedStatement.setDouble(3, totalReserva);
            preparedStatement.setString(4, novoEstadoReserva);
            preparedStatement.setInt(5, idReserva);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reserva atualizada com sucesso");
            } else {
                System.out.println("Nenhuma reserva foi atualizada. Verifique o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar reserva: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deletarReserva(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID da reserva que deseja deletar: ");
        int idReserva = scanner.nextInt();

        String sql = "DELETE FROM reservas WHERE idreserva = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idReserva);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reserva deletada com sucesso");
            } else {
                System.out.println("Nenhuma reserva encontrada. Verifique o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar reserva: " + e.getMessage());
            e.printStackTrace();
        }
    }
    	
    // Implemente métodos semelhantes para atualizar e deletar reservas
}
