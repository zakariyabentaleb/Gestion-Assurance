package view;

import dao.ClientDAO;
import model.entities.Client;
import model.entities.Conseiller;
import model.entities.Contrat;
import model.enums.TypeContrat;
import service.ClientService;
import service.ContratService;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ContratView {

    private ContratService contratService ;

    private static Scanner scanner = new Scanner(System.in) ;

    public ContratView() {
        this.contratService = new ContratService();

    }

    public static void afficherMenu() {
        int choix;
        do {
            System.out.println("\n===== Gestion des Contrats =====");
            System.out.println("1. Ajouter une Contrat ");
            System.out.println("2. Supprimer une Contrat par id ");
            System.out.println("3. Afficher une contrat par id");
            System.out.println("4. Afficher les contrats souscrits d’un client donné par l’id client ");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterContrat();
                    break;
                case 2:
                    supprimerContrat();
                    break;
                case 3:
                    ListerContrat();
                    break;

                case 4:
                    ListerContratParClientView() ;
                    break;

                case 5 :
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 5);
    }

    private static void ajouterContrat() {
        System.out.println("=== Ajout d’un nouveau contrat ===");

        // --- Type de contrat ---
        System.out.print("Type de contrat (AUTOMOBILE / IMMOBILIER / MALADIE) : ");
        String typeInput = scanner.nextLine().toUpperCase();
        TypeContrat typeContrat = TypeContrat.valueOf(typeInput);

        // --- Dates ---
        System.out.print("Date début (yyyy-MM-dd) : ");
        LocalDate dateDebut = LocalDate.parse(scanner.nextLine());

        System.out.print("Date fin (yyyy-MM-dd) : ");
        LocalDate dateFin = LocalDate.parse(scanner.nextLine());

        ClientDAO clientDAO = new ClientDAO();

        System.out.println("=== Liste des clients ===");
        List<Client> clients = clientDAO.ListerClient();
        for (Client c : clients) {
            System.out.println("ID: " + c.getId() +
                    ", Nom: " + c.getNom() +
                    ", Prénom: " + c.getPrenom() +
                    ", Email: " + c.getEmail());
        }
        if (clients.isEmpty()) {
            System.out.println("⚠️ Aucun client trouvé !");
            return;
        }

        System.out.print("ID du client : ");
        int clientId = Integer.parseInt(scanner.nextLine());

        Client client = null;
        for (Client c : clients) {
            if (c.getId() == clientId) {
                client = c;
                break;
            }
        }

        if (client == null) {
            System.out.println("⚠️ Client introuvable !");
            return;
        }

        // --- Création du contrat ---
        Contrat contrat = new Contrat(0, typeContrat, dateDebut, dateFin, client);

        ContratService.ajouterContrat(contrat);
        System.out.println("✅ Contrat ajouté !");
    }
    private static void supprimerContrat() {
        System.out.print("Entrez l'Id du Client :  ");
        int  id = scanner.nextInt();
        Contrat contrat = new Contrat();
        contrat.setId(id);
        ContratService.supprimerContrat(contrat);
    }
    private static void ListerContrat() {
        System.out.print("Voila tous les Contrats :  ");
        ContratService.ListerContrat();

    }
    public static void afficherContratsParClient() {
        System.out.println("=== Affichage des contrats d’un client ===");

        // Demande de l’ID du client
        System.out.print("Entrez l’ID du client : ");
        int clientId;
        try {
            clientId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ ID invalide !");
            return;
        }

        // Récupération et affichage des contrats
        List<Contrat> contrats = ContratService.ListerContratParClient(clientId);

        // Si la liste est vide, un message a déjà été affiché dans le service
        if (!contrats.isEmpty()) {
            System.out.println("\nNombre de contrats trouvés : " + contrats.size());
        }
    }
    private static void ListerContratParClientView() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'ID du client : ");
        int clientId = Integer.parseInt(scanner.nextLine());

        List<Contrat> contrats = ContratService.ListerContratParClient(clientId);

        if (contrats.isEmpty()) {
            System.out.println("Aucun contrat trouvé pour ce client.");
        } else {
            System.out.println("Liste des contrats du client ID " + clientId + " :");
            contrats.forEach(c -> {
                System.out.println(
                        "ID Contrat: " + c.getId() +
                                ", Type: " + c.getTypeContrat() +
                                ", Début: " + c.getDateDebut() +
                                ", Fin: " + c.getDateFin()
                );
            });
        }
    }










}


