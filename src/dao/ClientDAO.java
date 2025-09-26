package dao;

import model.entities.Client;
import model.entities.Conseiller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private static Connection connection = DatabaseConnection.getInstance().getConnection();
    public static void ajouterClient(Client client) {
        try {
            String sql = "INSERT INTO client (nom, prenom, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du client : " + e.getMessage());
        }
    }
    public static void supprimerClient(Client client) {
        try {
            String sql = "DELETE FROM client WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, client.getId());
            stmt.executeUpdate();
            System.out.println("Conseiller supprime !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression  du conseiller : " + e.getMessage());
        }
    }
   /* public static List<Client> ListerClient() {
        List<Client> clients = new ArrayList<>();
        Client client = null;
        try {
            String sql = "SELECT * FROM client";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEmail(rs.getString("email"));
                clients.add(client);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du conseiller : " + e.getMessage());
        }
        return clients;
    }
*/
   public static List<Client> ListerClient() {
       List<Client> clients = new ArrayList<>();
       try {
           String sql = "SELECT * FROM client";
           PreparedStatement stmt = connection.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();

           ConseillerDAO conseillerDAO = new ConseillerDAO(); // <-- instance

           while (rs.next()) {
               Client client = new Client();
               client.setId(rs.getInt("id"));
               client.setNom(rs.getString("nom"));
               client.setPrenom(rs.getString("prenom"));
               client.setEmail(rs.getString("email"));

               // --- Récupérer le conseiller associé ---
               int conseillerId = rs.getInt("conseiller_id"); // la colonne dans la table client
               if (conseillerId != 0) { // si le client a un conseiller
                   Conseiller conseiller = conseillerDAO.RechercheConseiller(conseillerId); // <-- instance
                   client.setConseiller(conseiller);
               }

               clients.add(client);
           }

       } catch (SQLException e) {
           System.out.println("Erreur lors de la récupération des clients : " + e.getMessage());
       }
       return clients;
   }




}
