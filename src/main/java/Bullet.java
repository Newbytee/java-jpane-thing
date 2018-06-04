class Bullet {

    private int x;
    private int y;

    Bullet(int x) {
        this.x = x;
        y = 350;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void modifyY(int y) {
        this.y += y;
    }
}
