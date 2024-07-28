package src.views;
import javax.swing.*;

import src.services.SettingsService;
import src.services.ScaleService;

import java.awt.*;

public class MainMenu extends JFrame {

    private SettingsService settingsService;

    public MainMenu() {

        this.settingsService = new SettingsService();
        setTitle("PACMAN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        // Center the window on the screen
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);

        // Create new game button
        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setPreferredSize(new Dimension(200, 50));
        newGameButton.addActionListener(e -> {
            new BoardSizeDialog(this);
            System.out.println("CONVERTED FROM: " + this.settingsService.getGameBoardSize().width + "|" + this.settingsService.getGameBoardSize().height);
            Dimension sc = ScaleService.scale(this.settingsService.getGameBoardSize());
            System.out.println("CONVERTED RESULT: " + sc.getWidth() + "|" + sc.getHeight());
            new GameBoardFrame(sc);

        });
        // Center the button on the panel and set the text to uppercase
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(newGameButton, gbc);

        // Create scores button
        JButton scoresButton = new JButton("SCORES");
        scoresButton.setPreferredSize(new Dimension(200, 50));
        scoresButton.addActionListener(e -> {
            // TODO: Add scores logic here
        });
        // Center the button on the panel and set the text to uppercase
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(scoresButton, gbc);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public SettingsService getSettingsService() {
        return this.settingsService;
    }

}
