package view;

import dao.ContratDAO;
import dao.SinistreDAO;
import model.entities.Client;
import model.entities.Contrat;
import model.entities.Sinistre;
import model.enums.TypeSinistre;
import service.ClientService;
import service.ContratService;
import service.SinistreService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SinistreView {
    private SinistreService sinistreService ;

    private static Scanner scanner = new Scanner(System.in) ;

    public SinistreView() {
        this.sinistreService = new SinistreService();

    }
    public void afficherMenu() {
        int choix;
        do {
            System.out.println("\n===== Gestion des Sinistre =====");
            System.out.println("1. Ajouter un Sinistre");
            System.out.println("2. Supprimer un Sinistre");
            System.out.println("3. Lister les sinistres d'un contrat");
            System.out.println("4. recherche un Sinistre par son id");
            System.out.println("5. afficher Sinistres Par Montant Desc ");
            System.out.println("6. afficher Sinistres Avant Date Donnee");
            System.out.println("7. afficher Sinistres Superieurs Montant Donnee");
            System.out.println("8. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // consommer le retour

            switch (choix) {
                case 1:
                    ajouterSinistre();
                    break;
                case 2:
                    supprimerSinistre();
                    break;
                case 3:
                    listerSinistresParContrat();
                    break;

                case 4:
                    RechercheSinistreParId();
                    break;

                case 5:
                    afficherSinistresParMontantDesc();
                    break;

                case 6:
                    afficherSinistresAvantDate();
                    break;

                case 7:
                    afficherSinistresSuperieursMontant();
                    break;

                    case 8:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 8);
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
    private void supprimerSinistre() {
        System.out.print("Entrez l'Id du Sinistre :  ");
        int  id = scanner.nextInt();
        Sinistre sinistre  = new Sinistre();
        sinistre.setId(id);
        SinistreService.supprimerSinistre(sinistre);
    }
    private void RechercheSinistreParId() {
        System.out.print("Entrez l'ID du client à rechercher : ");
        int id = scanner.nextInt();
        Optional<Sinistre> sinistre = SinistreService.findSinistreById(id);

        if (sinistre.isPresent()) {
            System.out.println("Sinistre trouvé : " + sinistre.get().getType() + " " + sinistre.get().getDescription());
        } else {
            System.out.println("Aucun sinistre trouvé avec cet ID.");
        }
    }
    private void listerSinistresParContrat() {
        System.out.print("ID du contrat : ");
        int contratId = Integer.parseInt(scanner.nextLine());

        List<Sinistre> sinistres = sinistreService.ListerSinistresParContrat(contratId);
        System.out.println("\n=== Sinistres du contrat " + contratId + " ===");
        sinistres.forEach(s -> System.out.println(
                "ID: " + s.getId() +
                        ", Type: " + s.getType() +
                        ", Début: " + s.getDateDebut() +
                        ", Fin: " + s.getDateFin() +
                        ", Montant: " + s.getMontant() +
                        ", Description: " + s.getDescription()
        ));
    }
    private void afficherSinistresParMontantDesc() {
        List<Sinistre> sinistresTries = SinistreService.listerSinistresParMontantDesc();

        System.out.println("\n--- Liste des sinistres triés par montant décroissant ---");
        sinistresTries.forEach(s ->
                System.out.println("ID: " + s.getId() +
                        " | Type: " + s.getType() +
                        " | Montant: " + s.getMontant() +
                        " | Contrat ID: " + s.getContrat().getId())
        );
    }
    private static void afficherSinistresAvantDate() {
        System.out.print("👉 Entrez une date (yyyy-MM-dd) : ");
        LocalDate dateLimite = LocalDate.parse(scanner.nextLine());

        List<Sinistre> sinistres = SinistreDAO.ListerSinistres();

        System.out.println("=== Sinistres avant " + dateLimite + " ===");

        sinistres.stream()
                .filter(s -> s.getDateDebut().isBefore(dateLimite)) // ou getDateFin()
                .forEach(s -> System.out.println(
                        "ID: " + s.getId() +
                                ", Type: " + s.getType() +
                                ", Début: " + s.getDateDebut() +
                                ", Fin: " + s.getDateFin() +
                                ", Montant: " + s.getMontant() +
                                ", Description: " + s.getDescription()
                ));
    }
    private static void afficherSinistresSuperieursMontant() {
        System.out.print("👉 Entrez le montant minimum : ");
        double montantMin = Double.parseDouble(scanner.nextLine());

        List<Sinistre> sinistres = SinistreDAO.ListerSinistres();

        System.out.println("=== Sinistres dont le coût est supérieur à " + montantMin + " ===");

        sinistres.stream()
                .filter(s -> s.getMontant() > montantMin)
                .forEach(s -> System.out.println(
                        "ID: " + s.getId() +
                                ", Type: " + s.getType() +
                                ", Début: " + s.getDateDebut() +
                                ", Fin: " + s.getDateFin() +
                                ", Montant: " + s.getMontant() +
                                ", Description: " + s.getDescription()
                ));
    }

}
