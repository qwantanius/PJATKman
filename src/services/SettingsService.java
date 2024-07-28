package src.services;

import java.awt.Dimension;

public class SettingsService {
    private int gameBoardHeight;
    private int gameBoardWidth;

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
