package dao;

import java.sql.Connection;

import model.entities.Conseiller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
    public void supprimerConseiller(Conseiller conseiller) {
        try {
            String sql = "DELETE FROM conseiller WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, conseiller.getId());
            stmt.executeUpdate();
            System.out.println("Conseiller supprime !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression  du conseiller : " + e.getMessage());
        }
    }

    public List<Conseiller> ListerConseiller() {
        List<Conseiller> conseillers = new ArrayList<>();
        try {
            String sql = " select * from conseiller ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Conseiller c = new Conseiller();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setEmail(rs.getString("email"));
                conseillers.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la listage  du conseiller : " + e.getMessage());
        }
        return conseillers;
    }

    public Conseiller RechercheConseiller(int id) {
        Conseiller conseiller = null;
        try {
            String sql = "SELECT * FROM conseiller WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
             ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                conseiller = new Conseiller();
                conseiller.setId(rs.getInt("id"));
                conseiller.setNom(rs.getString("nom"));
                conseiller.setPrenom(rs.getString("prenom"));
                conseiller.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du conseiller : " + e.getMessage());
        }
        return conseiller;
    }
}
