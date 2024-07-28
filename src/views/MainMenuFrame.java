package src.views;
import javax.swing.*;

import src.configs.Strings;
import src.configs.GameBoard;
import src.controllers.GameBoardFrameKeyListener;
import src.services.utils.ScaleService;
import src.services.utils.SerializationUtil;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainMenuFrame extends JFrame {

    public GameBoard settingsService;
    public static boolean blockGame = false;
    JButton newGameButton = new JButton(GameBoardView.newGameButton);
    JButton exitButton = new JButton(GameBoardView.exitButton);
    JButton scoresButton = new JButton(GameBoardView.scoresButton);

    JPanel mainPanel = new JPanel(new GridBagLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the image on the panel
            g.drawImage(GameBoardView.mainMenuBackground.getImage(), 0, 0, this);
        }
    };

    public MainMenuFrame() {
        settingsService = new GameBoard();
        setTitle(Strings.MainMenu.TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int MAIN_MENU_HEIGHT = 500;
        int MAIN_MENU_WIDTH = 500;
        setSize(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);

        // Center the window on the screen
        setLocationRelativeTo(null);
        createButtons();
        setResizable(false);
        setContentPane(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GameBoard getSettingsService() {
        return settingsService;
    }

    public void createButtons () {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);        // Create new game button
        // // // DO NOT SEPARATE
        newGameButton.setPreferredSize(new Dimension(200, 50));
        newGameButton.addActionListener(e -> {
            GameBoardFrameKeyListener.reset();
            new BoardSizeDialog(this);
            Dimension sc = ScaleService.scale(settingsService.getGameBoardSize());
            if (!blockGame) {
                new GameBoardFrameKeyListener(sc);
            }
        });
        //
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(newGameButton, gbc);

        // Create scores button
        scoresButton.setPreferredSize(new Dimension(200, 50));
        scoresButton.addActionListener(e -> {
            try {
                new ScoresFrame(Objects.requireNonNull(SerializationUtil.deserializeAll()));
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(scoresButton, gbc);

        exitButton.setPreferredSize(new Dimension(200, 50));
        exitButton.addActionListener(e -> {
            Window[] windows = getWindows();
            for (Window window: windows) {
                window.dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(exitButton, gbc);
    }
}
