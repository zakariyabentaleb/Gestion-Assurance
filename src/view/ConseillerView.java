package view;

import model.Conseiller;
import service.ConseillerService;

import java.util.Scanner;

public class ConseillerView {

    private ConseillerService conseillerService;
    private Scanner scanner;

    public ConseillerView() {
        this.conseillerService = new ConseillerService();
        this.scanner = new Scanner(System.in);
    }

    public void afficherMenu() {
        int choix;
        do {
            System.out.println("\n===== Gestion des Conseillers =====");
            System.out.println("1. Ajouter un conseiller");
            System.out.println("2. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // consommer le retour

            switch (choix) {
                case 1:
                    ajouterConseiller();
                    break;
                case 2:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 2);
    }

    private void ajouterConseiller() {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();

        Conseiller conseiller = new Conseiller(0, nom, prenom, email);
        conseillerService.ajouterConseiller(conseiller);
    }
}
