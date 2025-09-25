package view;

import model.entities.Conseiller;
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
            System.out.println("2. Supprimer un conseiller");
            System.out.println("3. Lister tous les conseillers");
            System.out.println("4. recherche un Conseiller par son id");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // consommer le retour

            switch (choix) {
                case 1:
                    ajouterConseiller();
                    break;
                case 2:
                    supprimerConseiller();
                    break;
                case 3:
                    ListerConseiller();
                    break;

                case 4:
                    RechercheConseiller();
                    break;

                case 5 :
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 5);
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
    private void supprimerConseiller() {
        System.out.print("Entrez l'Id du Conseiller :  ");
        int  id = scanner.nextInt();
        Conseiller conseiller = new Conseiller();
        conseiller.setId(id);
        conseillerService.supprimerConseiller(conseiller);
    }
    private void ListerConseiller() {
        System.out.print("Voila tous les Conseiller :  ");
        conseillerService.ListerConseiller();
    }
    private void RechercheConseiller() {
        System.out.print("Entrez l'ID du conseiller à rechercher : ");
        int id = scanner.nextInt();
        Conseiller conseiller = conseillerService.RechercheConseiller(id); // Service retourne le conseiller ou null

        if (conseiller != null) {
            System.out.println("Conseiller trouvé : " + conseiller.getNom() + " " + conseiller.getPrenom());
        } else {
            System.out.println("Aucun conseiller trouvé avec cet ID.");
        }
    }
}
