package src.services.bonuses;

import src.controllers.GameBoardFrameKeyListener;
import src.services.images.ImageService;
import src.services.movement.GhostMovingService;
import src.types.Bonus;
import src.types.BonusEnum;
import src.types.GhostEnum;
import src.types.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BonusService extends Thread {
    public static final int maxAmountOfBonuses = 5;
    public static Random random = new Random();
    public Dimension dim;

    public BonusService(Dimension dim) {
        this.dim = dim;
    }
    public boolean shouldBonusBeGenerated() {
        Random rand = new Random();
        int n = rand.nextInt(100);
        return n*4 < 100;
    }

    public static ImageIcon getImageIconByType(BonusEnum bonus) {
        if (bonus == BonusEnum.mistery_bonus) {
            bonus = selectRandomBonus();
        }
        return new ImageIcon(ImageService.readAndScale(Bonus.BONUSES_PATH + bonus.name() + ".png", 20, 20));
    }

    public static BonusEnum selectRandomBonus() {
        Random rand = new Random();
        int pick = rand.nextInt(BonusEnum.values().length);
        return BonusEnum.values()[pick];
    }

    @Override
    public void run() {
        while(true) {
            try {
                if(shouldBonusBeGenerated()) {
                    GameBoardFrameKeyListener.RENDERED_BONUSES.add(new Bonus(selectRandomBonus(), new Pair(
                        BonusService.random.nextInt(dim.height),
                        BonusService.random.nextInt(dim.width)
                    )));
                }
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
