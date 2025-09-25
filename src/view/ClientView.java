package view;

import model.entities.Client;
import model.entities.Conseiller;
import service.ClientService;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientView {

    private ClientService clientService ;

    private Scanner scanner = new Scanner(System.in) ;

    public ClientView() {
        this.clientService = new ClientService();

    }

    public void afficherMenu() {
        int choix;
        do {
            System.out.println("\n===== Gestion des clients =====");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Supprimer un client");
            System.out.println("3. Lister tous les clients");
            System.out.println("4. recherche un client par son id");
            System.out.println("5. recherche un client par son nom");
            System.out.println("6. tri par ordre alphabetique");
            System.out.println("7. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterClient();
                    break;
                case 2:
                    supprimerClient();
                    break;
                case 3:
                    ListerClient();
                    break;

                case 4:
                    RechercheClientParId();
                    break;

                case 5:
                    RechercheClientParNom();
                    break;

                case 6:
                    TrierParOrdreAlphab();
                    break;

                case 7 :
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 7);
    }

    private void ajouterClient() {

       System.out.print("Nom : ");
        String nom = scanner.nextLine();
       System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
       System.out.print("Email : ");
        String email = scanner.nextLine();

        Client client = new Client(0, nom, prenom, email);

        ClientService.ajouterClient(client);
    }
    private void supprimerClient() {
        System.out.print("Entrez l'Id du Client :  ");
        int  id = scanner.nextInt();
        Client client = new Client();
        client.setId(id);
        ClientService.supprimerClient(client);
    }
    private void RechercheClientParId() {
        System.out.print("Entrez l'ID du client à rechercher : ");
        int id = scanner.nextInt();
        Optional<Client> client = ClientService.findClientById(id);

        if (client.isPresent()) {
            System.out.println("Conseiller trouvé : " + client.get().getNom() + " " + client.get().getPrenom());
        } else {
            System.out.println("Aucun conseiller trouvé avec cet ID.");
        }

    }
    private void RechercheClientParNom() {
        System.out.print("Entrez le nom du client à rechercher : ");
        String nom = scanner.nextLine();
        Optional<Client> client = ClientService.findClientByNom(nom);

        if (client.isPresent()) {
            System.out.println("Conseiller trouvé : " + client.get().getNom() + " " + client.get().getPrenom());
        } else {
            System.out.println("Aucun conseiller trouvé avec cet ID.");
        }
    }
    private void TrierParOrdreAlphab() {
        List<Client> sorted = ClientService.TrierParOrdreAlph();
        sorted.forEach(c -> System.out.println(c.getNom() + " " + c.getPrenom()));
    }
    private void ListerClient() {
        System.out.print("Voila tous les Client :  ");
        ClientService.ListerClient();
    }


}

