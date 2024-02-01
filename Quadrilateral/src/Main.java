import javax.swing.*;

public class Main implements Runnable {
    JFrame Frame;
    GamePanel GamePanel;
    Thread GameThread;
    Shop ShopPanel;
    public Main() {
        Frame = new JFrame();
        GamePanel = new GamePanel();
        ShopPanel = new Shop();

        Frame.setTitle("Super Shooter");
        Frame.setSize(1280, 720);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLayout(null);
        Frame.setLocationRelativeTo(null);
        Frame.setResizable(false);
        Frame.setUndecorated(true);

        Frame.add(GamePanel.GamePanel);
        Frame.add(GamePanel.CoinsPanel);

        Frame.setVisible(true);

        start();
    }

    public static void main(String[] args) {
        new Main();
    }

    public void start() {
        GameThread = new Thread(this);
        GameThread.start();
    }

    public void run() {
        while(true) {
            if (!GamePanel.isPaused){
                GamePanel.update();
                GamePanel.CoinsLabel.setText("Coins: " + Player.Coins);
                GamePanel.HealthLabel.setText("Health: " + Player.Health);
                Frame.repaint();

                if ((GamePanel.Player.EnemysKilled % 15) == 1) {
                    Frame.remove(GamePanel.GamePanel);
                    Frame.add(ShopPanel.ShopPanel);
                    ShopPanel.ShopPanel.setVisible(true);
                    Frame.setVisible(true);
                }
            }
            try {
                Thread.sleep(1000/60);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}