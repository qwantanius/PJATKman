package src.services.movement;

import src.controllers.GameBoardFrameKeyListener;
import src.types.DirectionEnum;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractMovingService extends Thread {

    protected GameBoardFrameKeyListener gbkl;
    protected JTable jTable;

    public boolean isBusyAt(int x, int y) {
        ImageIcon imageIcon = (ImageIcon) this.jTable.getValueAt(x,y);
        String descr = imageIcon.getDescription();
        return descr == null || (descr.equals("BORDER"));
    }

    private int coordX = 5;
    private int coordY = 5;
    private DirectionEnum currentDirection = DirectionEnum.RIGHT;


    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void incrX() {
        this.coordX++;
    }

    public void incrY() {
        this.coordY++;
    }

    public void decrX() {
        this.coordX--;
    }

    public void decrY() {
        this.coordY--;
    }

    public static boolean FORCE_STOP = false;
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public DirectionEnum getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(DirectionEnum currentDirection) {
        this.currentDirection = currentDirection;
    }

    protected boolean isPaused = false;
    protected int DEFAULT_STEP_LIMIT = 300;

    public void pauseMoving() { this.isPaused = true; }

    public void resumeMoving() { this.isPaused = false; }

    abstract void actionOnStep();
    abstract int getStepRateLimit();

    @Override
    public void run() {
        while (!FORCE_STOP) {
            jTable.repaint();
            if (!this.isPaused) {
                try {
                    Thread.sleep(getStepRateLimit());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                actionOnStep();
            }
        }
    }
}
