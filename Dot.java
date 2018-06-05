public class Dot {
    private int x, y, radius;

    Dot(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    int getRadius() {
        return this.radius;
    }

    void setY(int y) {
        this.y = y;
    }

    void modifyCoords(int modX, int modY) {
        this.x += modX;
        this.y += modY;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
