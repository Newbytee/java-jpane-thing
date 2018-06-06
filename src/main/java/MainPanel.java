import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MainPanel extends javax.swing.JPanel {

    private static ArrayList<Dot> dots = new ArrayList<>();
    private static ArrayList<Bullet> bullets = new ArrayList<>();
    private static Random randomGen = new Random();
    private int spawnTimer;
    private int flashCounter = 0;
    private int playerHp = 100;
    private int score = 0;
    private double gameSpeed = 1.0;
    private boolean shouldRun = true;
    private boolean displayDmg = false;

    MainPanel() {
        initComponents();
        Player.setPlayerX(0);
        Player.setPlayerY(350);

        final long timeInterval = 1;
        spawnTimer = 0;

        Runnable runnable = () -> {
            while (shouldRun) {
                if (spawnTimer >= 1000 * gameSpeed) {
                    dots.add(new Dot(randomGen.nextInt(490), 0, randomGen.nextInt(5) + 5));
                    spawnTimer = 0;
                    gameSpeed -= 0.001;
                } else {
                    spawnTimer++;
                }

                if ((spawnTimer % 100) == 0) {
                    for (Dot dot : dots) {
                        dot.modifyCoords(0, 1);
                        if (dot.getY() > 350 - dot.getRadius()) {
                            playerHp--;
                            flashCounter = 50;
                            System.out.println(playerHp);
                        }
                    }
                }

                for (Bullet bullet : bullets) {
                    bullet.modifyY(-2);
                    for (Dot dot : dots) {
                        if ((dot.getY() >= bullet.getY()) && ((dot.getX() <= bullet.getX()) && (dot.getX() + 20 >= bullet.getX()))) {
                            dot.setY(500);
                            bullet.setY(-10);
                            score += dot.getRadius();
                        }
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
        for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext();) {
            Bullet bullet = iterator.next();
            if (bullet.getY() < 0) {
                iterator.remove();
            }
            gfx.fillRect(bullet.getX(), bullet.getY(), 4, 20);
            System.out.println(bullet.getX() + "\t" + bullet.getY());
        }

        gfx.setColor(Color.WHITE);
        for (Iterator<Dot> iterator = dots.iterator(); iterator.hasNext();) {
            Dot dot = iterator.next();
            if (dot.getY() > 350) {
                iterator.remove();
            }
            gfx.fillOval(dot.getX(), dot.getY(), dot.getRadius() * 2, dot.getRadius() * 2);
        }

        gfx.setColor(Color.RED);
        gfx.fillOval(Player.getPlayerX(), Player.getPlayerY(), 200, 200);

        gfx.drawString(Integer.toString(playerHp) + " HP", 10, 20);
        gfx.drawString(Integer.toString(score) + " points", 10, 40);

        if (playerHp < 0) {
            gfx.drawString("GAME OVER", 220, 150);
            shouldRun = false;
        }

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