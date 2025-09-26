package service;

import dao.ClientDAO;
import dao.ContratDAO;
import model.entities.Conseiller;
import model.entities.Contrat;

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
}
