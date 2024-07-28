package src.services.animation;

import src.controllers.GameBoardFrameKeyListener;
import src.services.movement.AbstractMovingService;
import src.types.GhostEnum;
import src.views.GameBoardView;
import src.views.GhostView;

import javax.swing.*;

public class GhostAnimationService extends AbstractImageAnimationService {

    public GhostView ghostView;
    public GameBoardFrameKeyListener gameBoardFrameKeyListener;

    public GhostAnimationService(GameBoardFrameKeyListener gameBoardFrameKeyListener, AbstractMovingService ghostAnimationService, GhostView ghostView) {
        super(gameBoardFrameKeyListener);
        this.ghostView = ghostView;
        this.gameBoardFrameKeyListener = gameBoardFrameKeyListener;
        // use ghost manager
        this.movingService = ghostAnimationService;
        isPaused = true;
    }

    @Override
    public int getAnimationRateDelayInMillis() {
        return 150;
    }

    @Override
    public int getFrameSize() {
        return 2;
    }

    @Override
    public ImageIcon getLhsAnimationImage() {
        return GameBoardView.background;
    }

    @Override
    public ImageIcon getRhsAnimationImage() {
        return GameBoardView.background;
    }

    @Override
    public ImageIcon getClosedFullAnimationImage() {
        return switch (this.movingService.getCurrentDirection()) {
            case UP, DOWN, RIGHT, LEFT -> ghostView.full;
        };
    }
}
