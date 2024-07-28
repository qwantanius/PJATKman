package src.views;

import src.services.images.ImageService;
import src.types.DirectionEnum;

import javax.swing.*;


public class PacmanView extends BasicView {
    public static final String images = BasicView.getPacmanImagePath() + "\\pacman";

    public static final ImageIcon lhsRight = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_opened_%s_LHS.png", BasicView.getCwd(), images, DirectionEnum.RIGHT)));
    public static final ImageIcon lhsLeft = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_opened_%s_LHS.png",  BasicView.getCwd(), images, DirectionEnum.LEFT)));
    public static final ImageIcon lhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_opened_%s_LHS.png",    BasicView.getCwd(), images, DirectionEnum.UP)));
    public static final ImageIcon lhsDown = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_opened_%s_LHS.png",  BasicView.getCwd(), images, DirectionEnum.DOWN)));

    public static final ImageIcon rhsRight = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_opened_%s_RHS.png", BasicView.getCwd(), images, DirectionEnum.RIGHT)));
    public static final ImageIcon rhsLeft = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_opened_%s_RHS.png",  BasicView.getCwd(), images, DirectionEnum.LEFT)));
    public static final ImageIcon rhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_opened_%s_RHS.png",    BasicView.getCwd(), images, DirectionEnum.UP)));
    public static final ImageIcon rhsDown = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_opened_%s_RHS.png",  BasicView.getCwd(), images, DirectionEnum.DOWN)));

    public static final ImageIcon closedRight = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_closed_%s.png", BasicView.getCwd(), images, DirectionEnum.RIGHT)));
    public static final ImageIcon closedLeft = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_closed_%s.png",  BasicView.getCwd(), images, DirectionEnum.LEFT)));
    public static final ImageIcon closedUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_closed_%s.png",    BasicView.getCwd(), images, DirectionEnum.UP)));
    public static final ImageIcon closedDown = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\pacman_closed_%s.png",  BasicView.getCwd(), images, DirectionEnum.DOWN)));
}
