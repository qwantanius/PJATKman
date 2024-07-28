package src.services.movement;

import src.controllers.GameBoardFrameKeyListener;
import src.services.bonuses.BonusService;
import src.services.utils.DirectionUtils;
import src.types.Bonus;
import src.types.Pair;

import javax.swing.*;

public class GhostMovingService extends AbstractMovingService {
    int stepsCounter = 0;
    int minSteps = 5;
    public boolean isFreezeBonusApplied = false;
    public int freezeBonusMaxApplyTime = 15;
    public int freezeBonusCurrentApplyTime = 0;

    public void applyFreeze() {
        this.isFreezeBonusApplied = true;
    }


    public GhostMovingService(GameBoardFrameKeyListener gameBoardFrameKeyListener) {
        this.gbkl = gameBoardFrameKeyListener;
        this.jTable = this.gbkl.getTableMap();
        this.minSteps = 20;
    }

    @Override
    void actionOnStep() {
        if (isFreezeBonusApplied) {
            if (freezeBonusCurrentApplyTime == freezeBonusMaxApplyTime) {
                freezeBonusCurrentApplyTime = 0;
                isFreezeBonusApplied = false;
            }
            if (isFreezeBonusApplied) {
                freezeBonusCurrentApplyTime++;
            }
            return;
        }
        boolean isBusy = true;
        switch (getCurrentDirection()) {
            case UP -> {
                if (!this.isAboveYBusy()) {
                    decrY();
                    isBusy = false;
                }
            }
            case DOWN -> {
                if (!this.isBelowYBusy()) {
                    incrY();
                    isBusy = false;
                }
            }
            case LEFT -> {
                if (!this.isBelowXBusy()) {
                    decrX();
                    isBusy = false;
                }
            }
            case RIGHT -> {
                if (!this.isAboveXBusy()) {
                    incrX();
                    isBusy = false;
                }
            }
        }
        if (isBusy || stepsCounter >= minSteps) {
            setCurrentDirection(DirectionUtils.getRandomDirection());
        }
        stepsCounter++;
    }

    public String getDescriptionAt(int x, int y) {
        ImageIcon img = (ImageIcon) this.jTable.getValueAt(x, y);
        String descr = img.getDescription();
        if (descr == null) {
            return null;
        }
        return descr;
    }

    private boolean isAboveXBusy() {
        return GameBoardFrameKeyListener.isReservedAt(getCoordX()+1, getCoordY());
    }

    private boolean isBelowXBusy() {
        return GameBoardFrameKeyListener.isReservedAt(getCoordX()-1, getCoordY());
    }

    private boolean isAboveYBusy() {
        return GameBoardFrameKeyListener.isReservedAt(getCoordX(), getCoordY()-1);
    }
    private boolean isBelowYBusy() {
        return GameBoardFrameKeyListener.isReservedAt(getCoordX(), getCoordY()+1);
    }
    // finish glitchy issue
    // bonuses

    @Override
    int getStepRateLimit() {
        return 300;
    }
}
