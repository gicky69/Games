import javax.swing.*;

public class Main implements Runnable {
    JFrame Frame;
    LevelPanel LevelPanel;
    Thread GameThread;

    public Main() {
        Frame = new JFrame("Geometry Dash!");
        LevelPanel = new LevelPanel();

        Frame.setSize(1280, 720);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLayout(null);

        Frame.add(LevelPanel.LevelPanel);
        Frame.addKeyListener(LevelPanel.Player);
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
        while (true) {
            LevelPanel.Player.update();
            LevelPanel.LevelPanel.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}