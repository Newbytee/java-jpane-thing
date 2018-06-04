import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MainPanel extends javax.swing.JPanel {

    private static ArrayList<Dot> dots = new ArrayList<>();
    private Random randomGen = new Random();
    private boolean shouldRun = true;
    private int spawnTimer;

    MainPanel() {
        initComponents();
        Player.setPlayerX(100);
        Player.setPlayerY(50);

        final long timeInterval = 10;
        spawnTimer = 0;

        Runnable runnable = () -> {
            while (shouldRun) {
                if (spawnTimer >= 100) {
                    dots.add(new Dot(randomGen.nextInt(500), randomGen.nextInt(400), randomGen.nextInt(5) + 5));
                    spawnTimer = 0;
                } else {
                    spawnTimer++;
                }

                repaint();

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

    @Override
    protected void paintComponent(Graphics gfx) {
        super.paintComponent(gfx);

        gfx.setColor(Color.WHITE);
        for (int i = 0; i < dots.size(); i++) {
            gfx.fillOval(dots.get(i).getX(), dots.get(i).getY(), dots.get(i).getRadius() * 2, dots.get(i).getRadius() * 2);
        }

        gfx.setColor(Color.RED);
        gfx.fillOval(Player.getPlayerX(), Player.getPlayerY(), 200, 200);
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
