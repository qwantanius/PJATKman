package src.services;

import java.awt.Dimension;

public class ScaleService {

    private static int MAX_HEIGHT = 700;
    private static int MAX_WIDTH = 700;


    public static Dimension scale(Dimension dimensionFrom) {
        int scaledHeight = (int)((dimensionFrom.getHeight()/100)*MAX_HEIGHT);
        int scaledWidth = (int)((dimensionFrom.getWidth()/100)*MAX_WIDTH);

        return new Dimension(scaledWidth, scaledHeight);
    }
}