package dao;

import java.sql.Connection;

import model.Conseiller;
import java.sql.*;


public class ConseillerDAO {

    private Connection connection;

    public ConseillerDAO() {

        connection = DatabaseConnection.getInstance().getConnection();
    }
    public void ajouterConseiller(Conseiller conseiller) {
        try {
            String sql = "INSERT INTO conseiller (nom, prenom, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, conseiller.getNom());
            stmt.setString(2, conseiller.getPrenom());
            stmt.setString(3, conseiller.getEmail());
            stmt.executeUpdate();
            System.out.println("Conseiller ajouté !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du conseiller : " + e.getMessage());
        }
    }

}
