package dao;

import model.entities.Client;
import model.entities.Conseiller;
import model.entities.Contrat;
import model.enums.TypeContrat;
import service.ClientService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContratDAO {

    private static Connection connection;

    public ContratDAO() {

        connection = DatabaseConnection.getInstance().getConnection();
    }
    public static void ajouterContrat(Contrat contrat) {
        String sql = "INSERT INTO contrat (type_contrat, date_debut, date_fin, client_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, contrat.getTypeContrat().name());
            ps.setDate(2, Date.valueOf(contrat.getDateDebut()));
            ps.setDate(3, Date.valueOf(contrat.getDateFin()));
            ps.setInt(4, contrat.getClient().getId());
            ps.executeUpdate();


            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    contrat.setId(rs.getInt(1));
                }
            }
            System.out.println(" Contrat ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void supprimerContrat(Contrat contrat) {
        try {
            String sql = "DELETE FROM contrat WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, contrat.getId());
            stmt.executeUpdate();
            System.out.println("Client supprime !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression  du client : " + e.getMessage());
        }
    }
    public static List<Contrat> ListerContrat() {
        List<Contrat> contratss = new ArrayList<>();
        try {
            String sql = "SELECT * FROM contrat";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int clientId = rs.getInt("client_id");


             //   Optional<Client> clientOpt = ClientService.findClientById(clientId);
              Client client = null;

                Contrat contrat = new Contrat(
                        rs.getInt("id"),
                        TypeContrat.valueOf(rs.getString("type_contrat")),
                        rs.getDate("date_debut").toLocalDate(),
                        rs.getDate("date_fin").toLocalDate(),
                        client
                );
                contratss.add(contrat);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la listage des contrats : " + e.getMessage());
        }

        return contratss;
    }


}
