package service;

import dao.ConseillerDAO;
import model.Conseiller;

public class ConseillerService {

    private ConseillerDAO conseillerDAO;

    public ConseillerService() {
        this.conseillerDAO = new ConseillerDAO();
    }

    public void ajouterConseiller(Conseiller conseiller) {
        if (conseiller != null) {
            conseillerDAO.ajouterConseiller(conseiller);
            System.out.println("Conseiller ajouté : " + conseiller.getNom() + " " + conseiller.getPrenom());
        } else {
            System.out.println("Impossible d'ajouter un conseiller nul !");
        }
    }
    public void supprimerConseiller(Conseiller conseiller) {
        if (conseiller != null) {
            conseillerDAO.supprimerConseiller(conseiller);
            System.out.println("Conseiller suprimer : " + conseiller.getId());
        } else {
            System.out.println("Impossible de supprimer un conseiller  !");
        }
    }


}
