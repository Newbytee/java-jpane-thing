package jpanelthingy;

class Bullet {

    private int x;
    private int y;

    Bullet(int x) {
        this.x = x + 100;
        y = 350;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    void modifyY(int y) {
        this.y += y;
    }
}
