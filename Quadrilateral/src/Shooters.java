import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Shooters {
    JLabel Shooters;
    Random random;

    public Shooters() {
        Shooters = new JLabel();
        random = new Random();

        Shooters.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        Shooters.setBackground(Color.BLUE);

        int EX = random.nextInt(500);
        int EY = random.nextInt(500);
        Shooters.setBounds(EX, EY, 10, 10);
        Shooters.setLayout(null);
        Shooters.setVisible(true);
    }
}
