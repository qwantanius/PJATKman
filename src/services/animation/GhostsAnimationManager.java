package src.services.animation;

import src.controllers.GameBoardFrameKeyListener;
import src.services.movement.GhostMovingService;
import src.types.GhostEnum;
import src.types.Pair;
import src.views.GhostView;

import javax.swing.*;

public class GhostsAnimationManager {
    GhostView blue = new GhostView(GhostEnum.BLUE);
    GhostView red = new GhostView(GhostEnum.RED);
    GhostView pink = new GhostView(GhostEnum.PINK);
    GhostView orange = new GhostView(GhostEnum.ORANGE);
    GameBoardFrameKeyListener gameBoardFrameKeyListener;

    private final GhostAnimationService ghostAnimationServiceBlue;
    private final GhostAnimationService ghostAnimationServiceRed;
    private final GhostAnimationService ghostAnimationServicePink;
    private final GhostAnimationService ghostAnimationServiceOrange;

    private final GhostMovingService ghostMovingServiceRed;
    private final GhostMovingService ghostMovingServiceOrange;
    private final GhostMovingService ghostMovingServiceBlue;
    private final GhostMovingService ghostMovingServicePink;

    public GhostsAnimationManager(GameBoardFrameKeyListener gameBoardFrameKeyListener) {
        this.gameBoardFrameKeyListener = gameBoardFrameKeyListener;
        this.ghostMovingServiceRed = new GhostMovingService(gameBoardFrameKeyListener);
        this.ghostMovingServiceOrange = new GhostMovingService(gameBoardFrameKeyListener);
        this.ghostMovingServiceBlue = new GhostMovingService(gameBoardFrameKeyListener);
        this.ghostMovingServicePink = new GhostMovingService(gameBoardFrameKeyListener);
        this.ghostAnimationServiceBlue = new GhostAnimationService(gameBoardFrameKeyListener, ghostMovingServiceBlue, blue);
        this.ghostAnimationServiceRed = new GhostAnimationService(gameBoardFrameKeyListener, ghostMovingServiceRed,red);
        this.ghostAnimationServicePink = new GhostAnimationService(gameBoardFrameKeyListener, ghostMovingServicePink,pink);
        this.ghostAnimationServiceOrange = new GhostAnimationService(gameBoardFrameKeyListener, ghostMovingServiceOrange,orange);
    }

    public boolean matchAny(Pair pair) {
        return (ghostMovingServiceBlue.getCoordX() == pair.x) && (ghostMovingServiceBlue.getCoordY() == pair.y) ||
            (ghostMovingServiceRed.getCoordX() == pair.x) && (ghostMovingServiceRed.getCoordY() == pair.y) ||
            (ghostMovingServiceOrange.getCoordX() == pair.x) && (ghostMovingServiceOrange.getCoordY() == pair.y) ||
            (ghostMovingServicePink.getCoordX() == pair.x) && (ghostMovingServicePink.getCoordY() == pair.y);
    }

    public GhostMovingService getGhostMovingService(GhostEnum ghostEnum) {
        return switch (ghostEnum) {
            case BLUE -> ghostMovingServiceBlue;
            case PINK -> ghostMovingServicePink;
            case ORANGE -> ghostMovingServiceOrange;
            case RED -> ghostMovingServiceRed;
        };
    }

    public ImageIcon performGhostSelectionForCell(int rowIndex, int columnIndex) {
        ImageIcon blue = ghostAnimationServiceBlue.basicSmoothImageSelection(rowIndex, columnIndex);
        ImageIcon orange = ghostAnimationServiceOrange.basicSmoothImageSelection(rowIndex, columnIndex);
        ImageIcon red = ghostAnimationServiceRed.basicSmoothImageSelection(rowIndex, columnIndex);
        ImageIcon pink = ghostAnimationServicePink.basicSmoothImageSelection(rowIndex, columnIndex);
        return blue != null ? blue : orange != null ? orange : red != null ? red : pink;
    }

    public void applyFreeze() {
        ghostMovingServiceBlue.applyFreeze();
        ghostMovingServiceRed.applyFreeze();
        ghostMovingServiceOrange.applyFreeze();
        ghostMovingServicePink.applyFreeze();
    }

    public void executeGhosts() {
        ghostAnimationServiceBlue.start();
        ghostMovingServiceBlue.setCoordY(11);
        ghostMovingServiceBlue.setCoordX(11);
        ghostMovingServiceBlue.start();

        ghostAnimationServiceRed.start();
        ghostMovingServiceRed.setCoordY(11);
        ghostMovingServiceBlue.setCoordX(12);
        ghostMovingServiceRed.start();

        ghostAnimationServiceOrange.start();
        ghostMovingServiceOrange.setCoordY(12);
        ghostMovingServiceBlue.setCoordX(11);
        ghostMovingServiceOrange.start();

        ghostAnimationServicePink.start();
        ghostMovingServicePink.setCoordY(12);
        ghostMovingServiceBlue.setCoordX(12);
        ghostMovingServicePink.start();
    }
}
