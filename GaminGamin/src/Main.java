import javax.swing.*;

public class Main implements Runnable   {
    JFrame Frame;
    Camera Camera;
    Thread GameThread;

    public Main() {
        Frame = new JFrame("Hello World");
        Camera = new Camera();

        Frame.setSize(600, 800);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLayout(null);
        Frame.setLocationRelativeTo(null);
        Frame.setResizable(false);
        Frame.add(Camera.Camera);

        Frame.setVisible(true);

        Frame.addKeyListener(Camera.Player);

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
            Camera.Player.update();
            Camera.MapG.update(Camera.Player);
            Camera.Camera.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}