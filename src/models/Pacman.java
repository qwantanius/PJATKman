package src.models;

public class Pacman implements BoardEntity {

    private Location location = new Location();

    public Pacman() {}

    public Location getLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        int x = this.location.getX();
        int y = this.location.getY();
        return String.format("Pacman location x,y: %d,%d", x, y);
    }
}