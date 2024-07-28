package src.configs;

import java.awt.Dimension;

public class GameBoard {
    private int gameBoardHeight;
    private int gameBoardWidth;
    public static final int WIDTH_ADJUSTMENT_CONSTANT = 5;

    public Dimension getGameBoardSize() {
        return new Dimension(this.gameBoardWidth, this.gameBoardHeight);
    }

    public void setGameBoardHeight(int height) {
        this.gameBoardHeight = height;
    }

    public void setGameBoardWidth(int width) {
        this.gameBoardWidth = width;
    }
}
