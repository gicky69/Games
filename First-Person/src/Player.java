import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener{
    JLabel Player;
    int PlayerPosX = 100;
    int PlayerPosY = 470;
    int Gravity = 0;

    public Player() {
        Player = new JLabel("P");
        Player.setBounds(PlayerPosX,PlayerPosY,10,10);
        Player.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Player.setLayout(null);

        Player.setVisible(true);
    }

    public void update(){
        PlayerPosY += Gravity;
        Player.setBounds(PlayerPosX,PlayerPosY,10,10);
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Gravity = -10;
            System.out.println("Jump");
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Gravity = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){

    }
}
