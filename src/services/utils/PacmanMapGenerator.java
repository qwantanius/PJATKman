package src.services.utils;

import src.types.Pair;

import java.util.ArrayList;
import java.util.Random;

public class PacmanMapGenerator {
    public static ArrayList<Pair> generateLines(int height, int width) {
        height--;
        width--;
    Random rand = new Random();
    ArrayList<Pair> pairs = new ArrayList<>();

    for(int i = 0; i < width; i += 1 + 1) {
        int lineLength = 1 + rand.nextInt(height/4);
        int startHeight = rand.nextInt(height - lineLength + 1);
        for(int j = 0; j < lineLength; j++) {
            pairs.add(new Pair(startHeight + j, i));
        }
    }
    return pairs;
}
}
