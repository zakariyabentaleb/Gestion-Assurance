package view;

import model.entities.Conseiller;
import service.ConseillerService;

import java.util.Scanner;

public class MenuView {
    private Scanner scanner = new Scanner(System.in) ;
    public void afficherMenu() {
        int choix;
        do {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Gestion des Clients");
            System.out.println("2. Gestion des Conseiller");
            System.out.println("3. Gestion du Contrat");
            System.out.println("4. Gestion du Sinistre");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    gestionduclient();
                    break;
                case 2:
                    gestionduConseiller();
                    break;
                case 3:
                    gestionduContrat();
                    break;

                case 4:
                    gestionduSinistre();
                    break;

                case 5 :
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 5);
    }
     public void gestionduclient(){

         ClientView viewClient = new ClientView();
         viewClient.afficherMenu();
     }
    public void gestionduConseiller(){
        ConseillerView viewCONSEILLER = new ConseillerView();
        viewCONSEILLER.afficherMenu();
    }
    public void gestionduContrat(){
       ContratView viewContrat = new ContratView();
        ContratView.afficherMenu();
    }
    public void gestionduSinistre(){
        SinistreView viewSinistre = new SinistreView();
        viewSinistre.afficherMenu();
    }
}

