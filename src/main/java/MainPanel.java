import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MainPanel extends javax.swing.JPanel {

    private static ArrayList<Dot> dots = new ArrayList<>();
    private static ArrayList<Bullet> bullets = new ArrayList<>();
    private static Random randomGen = new Random();
    private int spawnTimer;
    private int flashCounter = 0;
    private int playerHp = 3;
    private boolean shouldRun = true;
    private boolean displayDmg = false;

    MainPanel() {
        initComponents();
        Player.setPlayerX(0);
        Player.setPlayerY(350);

        final long timeInterval = 10;
        spawnTimer = 0;

        Runnable runnable = () -> {
            while (shouldRun) {
                if (spawnTimer >= 300) {
                    dots.add(new Dot(randomGen.nextInt(500), 0, randomGen.nextInt(5) + 5));
                    spawnTimer = 0;
                } else {
                    spawnTimer++;
                }

                if ((spawnTimer % 10) == 0) {
                    for (int i = 0; i < dots.size(); i++) {
                        dots.get(i).modifyCoords(0, 1);
                        if (dots.get(i).getY() > 400 - dots.get(i).getRadius()) {
                            playerHp--;
                            dots.remove(i);
                            flashCounter = 50;
                            System.out.println(playerHp);
                        }
                    }
                }

                if ((spawnTimer % 10) == 0) {
                    for (Bullet bullet : bullets) {
                        bullet.modifyY(-1);
                    }
                }

                repaint();

                if (flashCounter > 0) {
                    flashCounter--;
                } else {
                    displayDmg = false;
                }

                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    static void shoot() {
        bullets.add(new Bullet(Player.getPlayerX()));
    }

    @Override
    protected void paintComponent(Graphics gfx) {
        super.paintComponent(gfx);

        gfx.setColor(Color.CYAN);
        for (Bullet bullet : bullets) {
            gfx.fillRect(bullet.getX(), bullet.getY(), 10, 20);
            System.out.println(bullet.getX() + "\t" + bullet.getY());
        }

        gfx.setColor(Color.WHITE);
        for (Dot dot : dots) {
            gfx.fillOval(dot.getX(), dot.getY(), dot.getRadius() * 2, dot.getRadius() * 2);
        }

        gfx.setColor(Color.RED);
        gfx.fillOval(Player.getPlayerX(), Player.getPlayerY(), 200, 200);

        if (displayDmg) {
            gfx.fillRect(400, 0, 500, 400);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }
}
