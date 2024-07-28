package src.configs;

import javax.swing.*;
import java.awt.*;

public class Fonts {

    public static void setup() {
        UIManager.put("Button.font", Fonts.DEFAULT_FONT);
        UIManager.put("Label.font", Fonts.DEFAULT_FONT);
        UIManager.put("TextField.font", Fonts.DEFAULT_FONT);
        UIManager.put("TextArea.font", Fonts.DEFAULT_FONT);
        UIManager.put("OptionPane.foreground", Color.WHITE);
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Panel.foreground", Color.WHITE);
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Label.background", Color.BLACK);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("TextField.background", Color.BLACK);
        UIManager.put("TextField.foreground", Color.WHITE);
    }

    public static Font DEFAULT_FONT = new Font("NSimSun", Font.BOLD, 15);

}