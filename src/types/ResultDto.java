package src.types;

import java.awt.*;
import java.io.Serializable;

public class ResultDto implements Serializable {
    String gameName;
    int result;
    long durationInMillis;
    long startedAt;
    Dimension selectedBoardSize;

    public ResultDto(String gameName, int result, long durationInMillis, long startedAt, Dimension selectedBoardSize) {
        this.gameName = gameName;
        this.result = result;
        this.durationInMillis = durationInMillis;
        this.startedAt = startedAt;
        this.selectedBoardSize = selectedBoardSize;
    }

    public int getResult() {
        return result;
    }

    public Dimension getSelectedBoardSize() {
        return selectedBoardSize;
    }

    public long getDurationInMillis() {
        return durationInMillis;
    }

    public long getStartedAt() {
        return startedAt;
    }

    public String getGameName() {
        return gameName;
    }
}
