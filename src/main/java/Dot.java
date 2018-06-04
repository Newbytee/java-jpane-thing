public class Dot {
    private int x, y, radius;

    public Dot() {
    }

    public Dot(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getRadius() {
        return this.radius;
    }

    public void modifyCoords(int modX, int modY) {
        this.x += modX;
        this.y += modY;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
