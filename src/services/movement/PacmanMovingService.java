package src.services.movement;

import src.controllers.GameBoardFrameKeyListener;
import src.services.animation.PacmanAnimationService;
import src.types.Bonus;
import src.types.Pair;
import java.util.ArrayList;
import java.util.Random;


// WHY with right border and down border issues
public class PacmanMovingService extends AbstractMovingService {
    PacmanAnimationService pacmanAnimationService;
    private boolean isDoubleBonus = false;
    private int doubleBonusMaxPoints = 10;
    private int doubleBonusPoints = 0;

    public void enableDoubleBonus(Bonus bonusEnum) {
        doubleBonusPoints = 0;
        isDoubleBonus = true;
    }

    public boolean isDoubleBonus() {
        return isDoubleBonus;
    }

    public PacmanMovingService(GameBoardFrameKeyListener gameBoardFrameKeyListener) {
        this.gbkl = gameBoardFrameKeyListener;
        this.jTable = this.gbkl.getTableMap();
    }

    public void setPacmanAnimationService(PacmanAnimationService pacmanAnimationService) {
        this.pacmanAnimationService = pacmanAnimationService;
    }

    @Override
    int getStepRateLimit() {
        return 300;
    }

    @Override
    void actionOnStep() {
        applyMovementSelection();
        applyBonuses();
        applyHealthCheck(new Pair(getCoordX(), getCoordY()));
        applyScoreCounter();
    }

    private void applyHealthCheck(Pair pair) {
        if (this.gbkl.getGhostsAnimationManager().matchAny(pair)) {
            this.gbkl.currentHealth--;
        }
    }

    private void applyMovementSelection() {
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
    }

    private void applyScoreCounter() {
        GameBoardFrameKeyListener.AVAILABLE_COORDS.forEach(pair -> {
            if (pair.x == getCoordX() && pair.y == getCoordY()) {
                if (!pair.isTouched) {
                    pair.isTouched = true;
                    if (doubleBonusPoints == doubleBonusMaxPoints) {
                        this.isDoubleBonus = false;
                    }
                    if (isDoubleBonus) {
                        this.gbkl.score++;
                        this.doubleBonusPoints++;
                    }
                    this.gbkl.score++;
                }
            }
        });
    }

    private void applyBonuses() {
        ArrayList<Bonus> toRemove = new ArrayList<>();
        GameBoardFrameKeyListener.RENDERED_BONUSES.forEach(bonus -> {
            Pair bonusLocation = bonus.getLocation();
            if (bonusLocation.x == getCoordX() && bonusLocation.y == getCoordY()) {
                switch (bonus.getType()) {
                    case double_score_bonus -> enableDoubleBonus(bonus);
                    case freeze_bonus -> this.gbkl.getGhostsAnimationManager().applyFreeze();
                    case extra_live_slot_bonus -> this.gbkl.fullHealth++;
                    case heal_one_live_bonus -> {
                        if (this.gbkl.currentHealth != this.gbkl.fullHealth) {
                            this.gbkl.currentHealth++;
                        }
                    }
                    case mistery_bonus -> {
                        Random random = new Random();
                        int randomNumber = random.nextInt(4);
                        switch (randomNumber) {
                            case 0 -> enableDoubleBonus(bonus);
                            case 1 -> this.gbkl.getGhostsAnimationManager().applyFreeze();
                            case 2 -> this.gbkl.fullHealth++;
                            case 3 -> this.gbkl.currentHealth++;
                        }
                    }
                };
                toRemove.add(bonus);
            }
        });
        toRemove.forEach(bonus -> {
            GameBoardFrameKeyListener.RENDERED_BONUSES.remove(bonus);
        });
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
}
