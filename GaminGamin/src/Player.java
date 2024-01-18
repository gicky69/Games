import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Player implements KeyListener {
    JLabel Player;

    int PlayerPosX = 300;
    int PlayerPosY = 300;
    int directionX;
    int directionY;

    public Player() {
        Player = new JLabel("P");

        Player.setBounds(PlayerPosX, PlayerPosY, 10, 10);
        Player.setLayout(null);
        Player.setVisible(true);
    }

    public void update() {
        PlayerPosX += directionX;
        Player.setBounds(PlayerPosX, PlayerPosY, 10, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        if (KeyCode == KeyEvent.VK_A) {
            directionX = -2;
        }
        if (KeyCode == KeyEvent.VK_D) {
            directionX = 2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        if (KeyCode == KeyEvent.VK_A || KeyCode == KeyEvent.VK_D) {
            directionX = 0;
        }
    }
}