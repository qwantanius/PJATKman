package src.models;

public class Location {
    private int locationX;
    private int locationY;
    private int DEFAULT_STEP = 1;

    public int getX() {
        return this.locationX;
    }

    public int getY() {
        return this.locationY;
    }

    public void moveUp() {
        this.locationY += this.DEFAULT_STEP;
    }

    public void moveDown() {
        this.locationY -= this.DEFAULT_STEP;
    }

    public void moveRight() {
        this.locationX += this.DEFAULT_STEP;
    }

    public void moveLeft() {
        this.locationX -= this.DEFAULT_STEP;
    }


}
