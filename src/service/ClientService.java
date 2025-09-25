package service;

import dao.ClientDAO;
import dao.ConseillerDAO;
import model.entities.Client;
import model.entities.Conseiller;

import java.util.Comparator;
import java.util.List;
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
    public static Optional<Client> findClientByNom(String nom) {
        return ClientDAO.ListerClient()
                .stream()
                .filter(c -> c.getNom().equals(nom))
                .findFirst();
    }
    public static List<Client> TrierParOrdreAlph() {
        return ClientDAO.ListerClient()
                .stream()
                .sorted(Comparator.comparing(Client::getNom)) // tri par nom
                .toList();
    }
}
