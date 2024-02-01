import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy {
    JLabel Enemy;
    Random random;

    public Enemy() {
        Enemy = new JLabel();
        random = new Random();

        Enemy.setBorder(BorderFactory.createLineBorder(Color.RED));
        Enemy.setBackground(Color.RED);

        int EX = random.nextInt(500);
        int EY = random.nextInt(500);

        Enemy.setBounds(EX, EY, 10, 10);
        Enemy.setLayout(null);
        Enemy.setVisible(true);
    }
}