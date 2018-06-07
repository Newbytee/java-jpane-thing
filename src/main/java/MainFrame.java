import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {

    private static MainPanel panel;
    private static boolean debug = true;

    private MainFrame(String title) {
        super(title);
        this.setSize(500, 400);
        panel = new MainPanel();
        this.add(panel);
        panel.setBackground(Color.BLACK);
        setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        MainFrame frame = new MainFrame("Octahedron Invaders");
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getExtendedKeyCode()) {
                    case 65:
                    case 37:
                        System.out.println('a');
                        Player.modPlayerX(-10);
                        System.out.println(MouseInfo.getPointerInfo().getLocation() + "\n" + frame.getLocationOnScreen());
                        break;
                    case 87:
                        System.out.println('w');
                        //panel.changeOvalCoords(0, -10);
                        break;
                    case 83:
                        System.out.println('s');
                        System.out.println(Player.getPlayerX());
                        //panel.changeOvalCoords(0, 10);
                        break;
                    case 68:
                    case 39:
                        System.out.println('d');
                        Player.modPlayerX(10);
                        break;
                    case 32:
                        MainPanel.shoot();
                        break;
                }
                if (debug) System.out.println("KeyCode: " + e.getKeyCode() + "\nKeyChar: " + e.getKeyChar() + "\nExtended KeyCode: " + e.getExtendedKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }
}