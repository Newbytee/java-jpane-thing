public class Player extends MainPanel {
    private static int playerX, playerY;

    public Player(int x, int y) {
        playerX = x;
        playerY = y;
    }

    static int getPlayerX() {
        return playerX;
    }

    static int getPlayerY() {
        return playerY;
    }

    static void setPlayerX(int playerX) {
        Player.playerX = playerX;
    }

    static void setPlayerY(int playerY) {
        Player.playerY = playerY;
    }

    static void modPlayerX(int playerX) {
        Player.playerX += playerX;
    }
}
