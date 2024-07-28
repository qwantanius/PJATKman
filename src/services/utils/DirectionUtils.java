package src.services.utils;

import src.types.DirectionEnum;

import java.util.Random;

public class DirectionUtils {
    private static final Random random = new Random();

    public static DirectionEnum getRandomDirection() {
        DirectionEnum[] directions = DirectionEnum.values();
        int randomIndex = random.nextInt(directions.length);
        return directions[randomIndex];
    }
}
