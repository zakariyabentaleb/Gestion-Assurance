import view.ClientView;
import view.ConseillerView;
import view.MenuView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       // ConseillerView viewConseiller = new ConseillerView();
        MenuView menuView = new MenuView();
       // viewConseiller.afficherMenu();
        menuView.afficherMenu();
    }
}
