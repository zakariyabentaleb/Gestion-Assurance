package service;

import dao.ConseillerDAO;
import model.entities.Conseiller;

import java.util.List;

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
    public void ListerConseiller() {
        List<Conseiller> conseillers = conseillerDAO.ListerConseiller();

        if (conseillers.isEmpty()) {
            System.out.println("Aucun conseiller trouvé !");
        } else {
            for (Conseiller c : conseillers) {
                System.out.println("ID: " + c.getId() + ", Nom: " + c.getNom() + ", Prénom: " + c.getPrenom() + ", Email: " + c.getEmail());
            }
        }
    }

    public Conseiller RechercheConseiller(int id) {

        return conseillerDAO.RechercheConseiller(id);
    }


}
