import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;

public class LevelPanel {
    JPanel LevelPanel;
    Player Player;
    JLabel[] LevelBlock;
    int x = 30;

    public LevelPanel() {
        LevelPanel = new JPanel();
        Player = new Player();
        LevelBlock = new JLabel[100];

        LevelPanel.setBounds(0,0,1280,480);
        LevelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        LevelPanel.setLayout(null);

        LevelPanel.add(Player.Player);
        GenerateLevelBlock();
        LevelPanel.setVisible(true);
    }

    public void GenerateLevelBlock() {
        int posX = 150;
        for (int i = 0; i < 100; i++) {
            LevelBlock[i] = new JLabel("B");
            LevelBlock[i].setBounds(posX + x,470,10,10);
            LevelBlock[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            LevelBlock[i].setLayout(null);
            LevelBlock[i].setVisible(true);
            x += 50;
            LevelPanel.add(LevelBlock[i]);
        }
    }
}
