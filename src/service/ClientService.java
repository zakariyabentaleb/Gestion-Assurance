package service;

import dao.ClientDAO;
import dao.ConseillerDAO;
import model.entities.Client;
import model.entities.Conseiller;

import java.util.Optional;

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
    public static void supprimerClient(Client client) {
        if (client  != null) {
            ClientDAO.supprimerClient(client);
            System.out.println("Conseiller suprimer : " + client.getId());
        } else {
            System.out.println("Impossible de supprimer un conseiller  !");
        }
    }
    public static Optional<Client> findClientById(int id) {
        return ClientDAO.ListerClient()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
}
