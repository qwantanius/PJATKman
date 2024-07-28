package src.services.utils;

import java.awt.Dimension;

public class ScaleService {
    public static Dimension scale(Dimension dimensionFrom) {
        int scaledHeight = (dimensionFrom.height*5)+300;
        int scaledWidth = (dimensionFrom.width*5)+300;

        return new Dimension(scaledWidth, scaledHeight);
    }
}