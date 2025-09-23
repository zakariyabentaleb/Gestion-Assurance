import dao.DatabaseConnection;
import model.Conseiller;
import service.ConseillerService;
import view.ConseillerView;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

         /*   DatabaseConnection db1 = DatabaseConnection.getInstance();
            DatabaseConnection db2 = DatabaseConnection.getInstance();

            // Vérifier que c'est bien le même objet (Singleton)
            System.out.println("db1 == db2 ? " + (db1 == db2));
;
            // Vérifier si la connexion n'est pas null
            Connection conn = db1.getConnection();
            if (conn != null) {
                System.out.println("Connexion réussie !");
            } else {
                System.out.println("Échec de la connexion.");
            }
    }*/
        ConseillerView view = new ConseillerView();
        view.afficherMenu();

    }
}

