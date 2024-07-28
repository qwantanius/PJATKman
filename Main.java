import src.models.Pacman;
import src.views.MainMenu;

public class Main {
    public static void main(String[] args) {
        Pacman p = new Pacman();
        System.out.println(p);
        new MainMenu();
    }

}
