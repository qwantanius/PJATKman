package src.types;

import src.services.images.ImageService;
import src.services.utils.ScaleService;
import src.views.BasicView;

import javax.swing.*;
import java.util.Random;

public class Bonus {
    private final Pair location;
    private final BonusEnum bonus;
    public static final String BONUSES_PATH = String.format("%s\\pacman\\src\\resources\\images\\bonuses\\", BasicView.getCwd());

    public Bonus(BonusEnum bonus, Pair location) {
        this.bonus = bonus;
        this.location = location;
    }

    public BonusEnum getType() {
        return bonus;
    }

    public Pair getLocation() {
        return location;
    }

}
