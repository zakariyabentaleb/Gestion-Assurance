package dao;

import model.entities.Contrat;

import java.sql.*;

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
            System.out.println("✅ Contrat ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
