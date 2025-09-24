package service;

import dao.ClientDAO;
import dao.ConseillerDAO;
import model.entities.Client;
import model.entities.Conseiller;

public class ClientService {
    private static ClientDAO ClientDAO;
    public ClientService() {
        this.ClientDAO = new ClientDAO();
    }

    public static void ajouterClient(Client client) {
        if (client != null) {
            ClientDAO.ajouterClient(client);
            System.out.println("Client ajouté : " + client.getNom() + " " + client.getPrenom());
        } else {
            System.out.println("Impossible d'ajouter un conseiller nul !");
        }
    }
}
