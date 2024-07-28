package src.views;

import src.services.images.ImageService;

import javax.swing.*;
import java.awt.*;

public class GameBoardView extends BasicView {
    public static final ImageIcon background = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\background.png", BasicView.getCwd(), getPacmanImagePath())));
    public static final ImageIcon backgroundScorePoint = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\background_score_point.png", BasicView.getCwd(), getPacmanImagePath())));
    public static final ImageIcon border = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\border.png", BasicView.getCwd(), getPacmanImagePath())));
    public static final ImageIcon newGameButton = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\new_game_button.png", BasicView.getCwd(), getPacmanImagePath()), 200, 50));
    public static final ImageIcon scoresButton = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\scores_button.png", BasicView.getCwd(), getPacmanImagePath()), 200, 50));
    public static final ImageIcon exitButton = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\exit_button.png", BasicView.getCwd(), getPacmanImagePath()), 200, 50));
    public static final ImageIcon mainMenuBackground = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\main_menu_background.png", BasicView.getCwd(), getPacmanImagePath()), 500, 465));
    public static final ImageIcon dimensionSelectorBackground = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\background.png", BasicView.getCwd(), getPacmanImagePath()), 500, 465));
    public static final ImageIcon cancelButton = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\cancel_button.png", BasicView.getCwd(), getPacmanImagePath()), 90, 30));
    public static final ImageIcon okButton = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\ok_button.png", BasicView.getCwd(), getPacmanImagePath()), 60, 30));
    public static final ImageIcon heightLabel = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\height_label.png", BasicView.getCwd(), getPacmanImagePath()), 70, 40));
    public static final ImageIcon widthLabel = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\width_label.png", BasicView.getCwd(), getPacmanImagePath()), 70, 40));

}
