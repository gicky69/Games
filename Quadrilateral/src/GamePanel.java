import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel implements KeyListener {
    JPanel GamePanel;
    boolean isPaused = false;
    Player Player;

    List<JLabel> Walls = new ArrayList<>();
    List<JLabel> WODs = new ArrayList<>();

    // 1 = Wall, 0 = None
    int Map1[] = {
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
    };

    JPanel CoinsPanel;
    JLabel CoinsLabel;
    JLabel HealthLabel;

    JPanel MapPanel;


    public GamePanel() {
        GamePanel = new JPanel();
        Player = new Player(this);
        MapPanel = new JPanel();
        CoinsPanel = new JPanel();
        CoinsLabel = new JLabel();
        HealthLabel = new JLabel();

        GamePanel.setBounds(430,100,500,500);
        GamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GamePanel.setBackground(Color.BLACK);
        GamePanel.setLayout(null);

        GamePanel.add(Player.Player);
        GamePanel.add(Player.Melee.Melee);
        GamePanel.addKeyListener(this);
        GamePanel.addKeyListener(Player);
        GamePanel.add(Player.Enemy.Enemy);
        GamePanel.add(Player.EMelee.Melee);
        GamePanel.add(Player.Shooters.Shooters);

        // CoinsPanel
        CoinsPanel.setBounds(0,0,100,50);
        CoinsPanel.setVisible(true);

        CoinsLabel.setBounds(0,30,100,30);
        CoinsLabel.setText("Coins : " + Player.Coins);

        HealthLabel.setBounds(0,0,100,30);
        HealthLabel.setText("Health : " + Player.Health);
        CoinsPanel.add(HealthLabel);
        CoinsPanel.add(CoinsLabel);

        generate();
//        generateWOD();
        GamePanel.setFocusable(true);
        GamePanel.requestFocusInWindow();
        GamePanel.setVisible(true);
    }

    public void update() {
        Player.update(this);

    }
    public void generate() {
        for (int i=0;i<Map1.length;i++){
            if (Map1[i] == 1) {
                JLabel Wall = new JLabel();
                Wall.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                Wall.setBackground(Color.WHITE);
                Wall.setBounds((i%25)*20,(i/25)*20,20,20);
                Wall.setVisible(true);
                GamePanel.add(Wall);
                Walls.add(Wall);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            isPaused = !isPaused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){

    }

    @Override
    public void keyTyped(KeyEvent e){

    }
}
