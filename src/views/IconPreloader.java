package src.views;

import src.services.images.ImageService;
import src.types.GhostEnum;

import javax.swing.*;

public class IconPreloader {
    public static final String images = BasicView.getPacmanImagePath() + "\\ghosts";
    public static final String red = GhostEnum.RED.name().toLowerCase();
    public static final String orange = GhostEnum.ORANGE.name().toLowerCase();
    public static final String pink = GhostEnum.PINK.name().toLowerCase();
    public static final String blue = GhostEnum.BLUE.name().toLowerCase();

    public static final ImageIcon redFull = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s.png", BasicView.getCwd(), images, red, red)));
    public static final ImageIcon redLhs = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_LHS.png", BasicView.getCwd(), images, red, red)));
    public static final ImageIcon redRhs = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_RHS.png", BasicView.getCwd(), images, red, red)));
    public static final ImageIcon redLhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_LHS_UP.png", BasicView.getCwd(), images, red, red)));
    public static final ImageIcon redRhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_RHS_UP.png", BasicView.getCwd(), images, red, red)));

    public static final ImageIcon orangeFull = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s.png", BasicView.getCwd(), images, orange, orange)));
    public static final ImageIcon orangeLhs = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_LHS.png", BasicView.getCwd(), images, orange, orange)));
    public static final ImageIcon orangeRhs = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_RHS.png", BasicView.getCwd(), images, orange, orange)));
    public static final ImageIcon orangeLhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_LHS_UP.png", BasicView.getCwd(), images, orange, orange)));
    public static final ImageIcon orangeRhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_RHS_UP.png", BasicView.getCwd(), images, orange, orange)));

    public static final ImageIcon pinkFull = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s.png", BasicView.getCwd(), images, pink, pink)));
    public static final ImageIcon pinkLhs = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_LHS.png", BasicView.getCwd(), images, pink, pink)));
    public static final ImageIcon pinkRhs = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_RHS.png", BasicView.getCwd(), images, pink, pink)));
    public static final ImageIcon pinkLhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_LHS_UP.png", BasicView.getCwd(), images, pink, pink)));
    public static final ImageIcon pinkRhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_RHS_UP.png", BasicView.getCwd(), images, pink, pink)));

    public static final ImageIcon blueFull = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s.png", BasicView.getCwd(), images, blue, blue)));
    public static final ImageIcon blueLhs = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_LHS.png", BasicView.getCwd(), images, blue, blue)));
    public static final ImageIcon blueRhs = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_RHS.png", BasicView.getCwd(), images, blue, blue)));
    public static final ImageIcon blueLhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_LHS_UP.png", BasicView.getCwd(), images, blue, blue)));
    public static final ImageIcon blueRhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_RHS_UP.png", BasicView.getCwd(), images, blue, blue)));
}
