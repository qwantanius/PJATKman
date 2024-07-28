package src.services.animation;

import src.controllers.GameBoardFrameKeyListener;
import src.views.PacmanView;

import javax.swing.*;

public class PacmanAnimationService extends AbstractImageAnimationService {

    @Override
    public int getAnimationRateDelayInMillis() {
        return 150;
    }

    @Override
    public int getFrameSize() {
        return 2;
    }

    public PacmanAnimationService(GameBoardFrameKeyListener gameBoardFrameKeyListener) {
        super(gameBoardFrameKeyListener);
        this.movingService = gameBoardFrameKeyListener.getPacmanMovingService();
        this.movingService.setCoordX(5);
        this.movingService.setCoordY(5);
    }

    public ImageIcon getLhsAnimationImage() {
        return switch (this.movingService.getCurrentDirection()) {
            case UP -> PacmanView.lhsUp;
            case DOWN -> PacmanView.lhsDown;
            case RIGHT -> PacmanView.lhsRight;
            case LEFT -> PacmanView.lhsLeft;
        };
    }

    public ImageIcon getRhsAnimationImage() {
        return switch (this.movingService.getCurrentDirection()) {
            case UP -> PacmanView.rhsUp;
            case DOWN -> PacmanView.rhsDown;
            case RIGHT -> PacmanView.rhsRight;
            case LEFT -> PacmanView.rhsLeft;
        };
    }

    public ImageIcon getClosedFullAnimationImage() {
        return switch (this.movingService.getCurrentDirection()) {
            case UP -> PacmanView.closedUp;
            case DOWN -> PacmanView.closedDown;
            case RIGHT -> PacmanView.closedRight;
            case LEFT -> PacmanView.closedLeft;
        };
    }

    public ImageIcon modeSelection(int rowIndex, int columnIndex) {
        return basicSmoothImageSelection(rowIndex, columnIndex);
    }
//                if (rowForLHS == rowIndex || colForLHS == columnIndex) {
//                    return pacmanAnimationService.getLhsAnimationImage();
//                } BEAST MODE
}
