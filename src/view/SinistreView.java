package view;

import dao.ContratDAO;
import model.entities.Contrat;
import model.entities.Sinistre;
import model.enums.TypeSinistre;
import service.ContratService;
import service.SinistreService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SinistreView {
    private ContratService sinistreService ;

    private static Scanner scanner = new Scanner(System.in) ;

    public SinistreView() {
        this.sinistreService = new ContratService();

    }
    public void afficherMenu() {
        int choix;
        do {
            System.out.println("\n===== Gestion des Sinistre =====");
            System.out.println("1. Ajouter un Sinistre");
            System.out.println("2. Supprimer un Sinistre");
            System.out.println("3. Lister tous les Sinistre");
            System.out.println("4. recherche un Sinistre par son id");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // consommer le retour

            switch (choix) {
                case 1:
                    ajouterSinistre();
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
        } while (choix != 5);
    }
    private static void ajouterSinistre() {
        System.out.println("=== Ajout d’un nouveau sinistre ===");

        // --- Type de sinistre ---
        System.out.print("Type de sinistre (Accident_de_travaille,accident_de_maison,MALADIE ) : ");
        String typeInput = scanner.nextLine().toUpperCase();
        TypeSinistre typeSinistre = TypeSinistre.valueOf(typeInput);

        // --- Dates ---
        System.out.print("Date début (yyyy-MM-dd) : ");
        LocalDate dateDebut = LocalDate.parse(scanner.nextLine());

        System.out.print("Date fin (yyyy-MM-dd) : ");
        LocalDate dateFin = LocalDate.parse(scanner.nextLine());

        // --- Montant ---
        System.out.print("Montant : ");
        double montant = Double.parseDouble(scanner.nextLine());

        // --- Description ---
        System.out.print("Description : ");
        String description = scanner.nextLine();

        // --- Lister les contrats existants ---
        ContratDAO contratDAO = new ContratDAO();
        List<Contrat> contrats = contratDAO.ListerContrat();

        System.out.println("=== Liste des contrats ===");
        for (Contrat c : contrats) {
            System.out.println("ID: " + c.getId() +
                    ", Type: " + c.getTypeContrat());

        }

        if (contrats.isEmpty()) {
            System.out.println("⚠️ Aucun contrat trouvé !");
            return;
        }

        System.out.print("ID du contrat concerné : ");
        int contratId = Integer.parseInt(scanner.nextLine());

        Contrat contrat = null;
        for (Contrat c : contrats) {
            if (c.getId() == contratId) {
                contrat = c;
                break;
            }
        }

        if (contrat == null) {
            System.out.println("⚠️ Contrat introuvable !");
            return;
        }

        // --- Création du sinistre ---
        Sinistre sinistre = new Sinistre(0, typeSinistre, dateDebut, dateFin, montant, description, contrat);

        SinistreService.ajouterSinistre(sinistre);
        System.out.println("✅ Sinistre ajouté !");
    }

}
