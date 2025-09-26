package dao;

import model.entities.Contrat;
import model.entities.Sinistre;

import java.sql.*;

public class SinistreDAO {
    private  Connection connection;

    public SinistreDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }
    public  void ajouterSinistre(Sinistre sinistre) {
        String sql = "INSERT INTO sinistre (type_sinistre, date_debut, date_fin, montant, description,contrat_id) VALUES (?, ?, ?, ?, ? ,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, sinistre.getType().name());
            ps.setDate(2, Date.valueOf(sinistre.getDateDebut()));
            ps.setDate(3, Date.valueOf(sinistre.getDateFin()));
            ps.setDouble(4, sinistre.getMontant());
            ps.setString(5, sinistre.getDescription());
            ps.setInt(6, sinistre.getContrat().getId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    sinistre.setId(rs.getInt(1));
                }
            }
            System.out.println("Sinistre ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






}
