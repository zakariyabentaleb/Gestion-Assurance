package dao;

import model.entities.Client;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private static Connection connection;

    public ClientDAO() {

        connection = DatabaseConnection.getInstance().getConnection();
    }
    public  void ajouterClient(Client client) {
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





}
