package service;

import dao.ClientDAO;
import dao.ContratDAO;
import model.entities.Client;
import model.entities.Conseiller;
import model.entities.Contrat;

import java.util.List;
import java.util.stream.Collectors;

import static dao.ContratDAO.ListerContratt;

public class ContratService {

    private static ContratDAO contratDAO;
    public ContratService() {
      contratDAO = new ContratDAO();
    }
    public static void ajouterContrat(Contrat contrat) {
        if (contrat != null) {
            ContratDAO.ajouterContrat(contrat);
            System.out.println("Contrat ajouté de id : " + contrat.getId());
        } else {
            System.out.println("Impossible d'ajouter une Contrat nul !");
        }
    }
    public static void supprimerContrat(Contrat contrat) {
        if (contrat != null) {
            contratDAO.supprimerContrat(contrat);
            System.out.println("Contrat suprimer : " + contrat.getId());
        } else {
            System.out.println("Impossible de supprimer un conseiller  !");
        }
    }
   public static void ListerContrat() {
        List<Contrat> contrats = ContratDAO.ListerContrat();

        if (contrats.isEmpty()) {
            System.out.println("Aucun contrat trouvé !");
            return ;
        }
       contrats.stream()
               .forEach(c -> {

                   System.out.println(
                           "ID Contrat: " + c.getId() +
                                   ", Type: " + c.getTypeContrat() +
                                   ", Début: " + c.getDateDebut() +
                                   ", Fin: " + c.getDateFin()

                   );
               });
    }
    public static List<Contrat> ListerContratParClient(int clientId) {
        List<Contrat> contrats = ListerContratt(); // tous les contrats avec clients

        return contrats.stream()
                .filter(c -> c.getClient() != null && c.getClient().getId() == clientId)
                .collect(Collectors.toList());
    }





}
