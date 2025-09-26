package service;

import dao.ClientDAO;
import dao.ContratDAO;
import dao.SinistreDAO;
import model.entities.Client;
import model.entities.Contrat;
import model.entities.Sinistre;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static dao.SinistreDAO.ListerSinistres;

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
    public static void supprimerSinistre(Sinistre sinistre) {
        if (sinistre  != null) {
            SinistreDAO.supprimerSinistre(sinistre);
            System.out.println("Conseiller suprimer : " + sinistre.getId());
        } else {
            System.out.println("Impossible de supprimer un conseiller  !");
        }
    }
    public static Optional<Sinistre> findSinistreById(int id) {
        return ListerSinistres()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
    public static List<Sinistre> ListerSinistresParContrat(int contratId) {
        // On récupère tous les sinistres
        List<Sinistre> tousLesSinistres = ListerSinistres();

        // Filtrer par contratId avec Stream API
        return tousLesSinistres.stream()
                .filter(s -> s.getContrat() != null && s.getContrat().getId() == contratId)
                .toList(); // ou .collect(Collectors.toList()) si Java < 16
    }
    public static List<Sinistre> listerSinistresParMontantDesc() {
        // Récupérer tous les sinistres
        List<Sinistre> sinistres = ListerSinistres();

        // Utiliser Stream API pour trier par montant décroissant
        return sinistres.stream()
                .sorted(Comparator.comparingDouble(Sinistre::getMontant).reversed())
                .toList();
    }


}
