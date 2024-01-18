import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Camera {
    JPanel Camera;
    MapGeneration MapG;
    Player Player;


    public Camera() {
        Camera = new JPanel();
        MapG = new MapGeneration();
        Player = new Player();

        Camera.setBounds(0, 0, 600, 800);
        Camera.setBackground(Color.white);
        Camera.setLayout(null);

        Camera.add(Player.Player);
        MapG.GenerateMap(Player);
        Camera.add(MapG.MapPanel);
        Camera.setLayout(null);
        Camera.setVisible(true);
    }
}
