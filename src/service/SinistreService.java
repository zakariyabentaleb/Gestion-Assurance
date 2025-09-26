package service;

import dao.ContratDAO;
import dao.SinistreDAO;
import model.entities.Contrat;
import model.entities.Sinistre;

public class SinistreService {
    private static SinistreDAO sinistreDAO;
    public SinistreService() {
        sinistreDAO = new SinistreDAO();
    }
    public static void ajouterSinistre(Sinistre sinistre) {
        if (sinistre != null) {
            SinistreDAO sinistreDAO = new SinistreDAO();
            sinistreDAO.ajouterSinistre(sinistre);
            System.out.println("Contrat ajouté de id : " + sinistre.getId());
        } else {
            System.out.println("Impossible d'ajouter une Contrat nul !");
        }
    }
}
