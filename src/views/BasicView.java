package src.views;

public class BasicView {
    private static final String cwd = System.getProperty("user.dir");

    public static String getCwd() {
        return cwd;
    }

    public static String getPacmanImagePath() {
        return "pacman\\src\\resources\\images";
    }
}
