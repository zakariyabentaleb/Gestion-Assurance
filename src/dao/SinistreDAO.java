package dao;

import model.entities.Contrat;
import model.entities.Sinistre;
import model.enums.TypeSinistre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinistreDAO {
    private static Connection connection;

    public SinistreDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }
    public void ajouterSinistre(Sinistre sinistre) {
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
    public static void supprimerSinistre(Sinistre sinistre){
        try {
            String sql = "DELETE FROM sinistre WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sinistre.getId());
            stmt.executeUpdate();
            System.out.println("sinistre supprime !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression  du sinistre : " + e.getMessage());
        }
    }
    public static List<Sinistre> ListerSinistres() {
        List<Sinistre> sinistres = new ArrayList<>();

        String sql = "SELECT * FROM sinistre";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Ici, on récupère juste les colonnes de sinistre
                Sinistre sinistre = new Sinistre();
                sinistre.setId(rs.getInt("id"));
                sinistre.setType(TypeSinistre.valueOf(rs.getString("type_sinistre")));
                sinistre.setDateDebut(rs.getDate("date_debut").toLocalDate());
                sinistre.setDateFin(rs.getDate("date_fin").toLocalDate());
                sinistre.setMontant(rs.getDouble("montant"));
                sinistre.setDescription(rs.getString("description"));

                // Si tu veux récupérer le contrat lié
                Contrat contrat = new Contrat();
                contrat.setId(rs.getInt("contrat_id"));
                sinistre.setContrat(contrat);

                sinistres.add(sinistre);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors du listage des sinistres : " + e.getMessage());
        }

        return sinistres;
    }

}
