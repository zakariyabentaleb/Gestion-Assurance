package view;

import model.entities.Client;
import model.entities.Conseiller;
import service.ClientService;

import java.sql.SQLOutput;
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
            System.out.println("2. Supprimer un conseiller");
            System.out.println("3. Lister tous les conseillers");
            System.out.println("4. recherche un Conseiller");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // consommer le retour

            switch (choix) {
                case 1:
                    ajouterClient();
                    break;
                case 2:

                    break;
                case 3:

                    break;

                case 4:

                    break;

                case 5 :
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 2);
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
}

