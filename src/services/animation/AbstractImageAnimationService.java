package src.services.animation;

import src.controllers.GameBoardFrameKeyListener;
import src.services.movement.AbstractMovingService;
import src.types.Animatable;

import javax.swing.*;

public abstract class AbstractImageAnimationService extends Thread implements Animatable {
    protected int FRAME_COUNTER = 0;
    protected int rowForLHS = -1;
    protected int colForLHS = -1;
    protected AbstractMovingService movingService;


    public int getColForLHS() {
        return colForLHS;
    }

    public int getRowForLHS() {
        return rowForLHS;
    }

    public void tickFrame() {
        int MAX_FRAME_SIZE = getFrameSize();
        if (FRAME_COUNTER == MAX_FRAME_SIZE) {
            FRAME_COUNTER = 0;
        }
        FRAME_COUNTER++;
    }

    public int getTicks() {
        return FRAME_COUNTER;
    }

    protected GameBoardFrameKeyListener gameBoard;
    protected boolean isRunning = false;
    protected boolean isPaused = false;
    public static boolean FORCE_STOP = false;

    public void pauseAnimation() {
        this.isPaused = true;
    }

    public void resumeAnimation() {
        this.isPaused = false;
    }

    public abstract int getAnimationRateDelayInMillis();

    abstract int getFrameSize();

    public AbstractImageAnimationService(GameBoardFrameKeyListener gameBoard) {
        this.gameBoard = gameBoard;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (this.isRunning && !FORCE_STOP) {
            if (!this.isPaused) {
                if (gameBoard.tableMap != null) {
                    try {
                        Thread.sleep(getAnimationRateDelayInMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tickFrame();
                }
            }
        }
    }

    public ImageIcon basicSmoothImageSelection(int rowIndex, int columnIndex) {
        boolean isMovableCell = rowIndex == movingService.getCoordY() && columnIndex == movingService.getCoordX();
        if (isMovableCell) {
            movingService.resumeMoving();
            boolean allAnimationsPerformed = getTicks() != 2;
            if (allAnimationsPerformed) {
                rowForLHS = rowIndex;
                colForLHS = columnIndex;
                return getClosedFullAnimationImage();
            } else {
                movingService.pauseMoving();
                return getRhsAnimationImage();
            }
        }
        if (rowForLHS == rowIndex && colForLHS == columnIndex) {
            rowForLHS = -1;
            colForLHS = -1;
            return getLhsAnimationImage();
        }
        return null;
    }
}
