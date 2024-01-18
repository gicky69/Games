import javax.swing.*;
import java.util.Random;
import java.awt.*;

public class MapGeneration {
    JPanel MapPanel;
    JPanel MapPanel2;
    JPanel MapPanel3;
    JLabel[] MapLabel;// Initial Map 1
    JLabel[] MapLabel2;// Map 2
    Random random;
    int MapPosX;
    int Gravity;
    boolean isMapLabelActive = true;

    public MapGeneration() {
        MapPanel = new JPanel();
        MapPanel2 = new JPanel();
        MapPanel3 = new JPanel();

        MapLabel = new JLabel[50];
        MapLabel2 = new JLabel[50];


        MapPanel.setBounds(0, 0, 600, 6400);
        MapPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        MapPanel.setLayout(null);
    }

    // Initial Map Generation
    public void GenerateMap(Player Player) {
        random = new Random();
        for (int i=0;i<50;i++){
            int x = (Player.Player.getX() - 300) + random.nextInt(600);
            int y = (Player.Player.getY() + 200) + random.nextInt(400);

            MapLabel[i] = new JLabel("X");
            MapLabel[i].setBounds(x, y, 10, 10);
            MapLabel[i].setLayout(null);
            MapPanel.add(MapLabel[i]);
        }

        // MapLabel2 Render
        for (int i=0;i<50;i++){
            int x = (Player.Player.getX() - 300) + random.nextInt(600);
            int y = (Player.Player.getY() + 700) + random.nextInt(400);

            MapLabel2[i] = new JLabel("X");
            MapLabel2[i].setBounds(x, y, 10, 10);
            MapLabel2[i].setLayout(null);
            MapPanel.add(MapLabel2[i]);
        }
    }

    public void update(Player Player) {
        // Move The Map Panel everytime it updates
        Gravity = 2;
        int MapPosY = MapPanel.getY() - Gravity;
        MapPanel.setBounds(MapPosX, MapPosY, 600, 6400);

        // Move The Map Label everytime it updates
        for (int i=0;i<50;i++) {
            int MapLabelPosY = MapLabel[i].getY() - Gravity;
            int MapLabel2PosY = MapLabel2[i].getY() - Gravity;
            MapLabel[i].setBounds(MapLabel[i].getX(), MapLabelPosY, 10, 10);
            MapLabel2[i].setBounds(MapLabel2[i].getX(), MapLabel2PosY, 10, 10);
        }

        System.out.println(MapPanel.getY());

        // Generate New Map 1
        if ((MapPanel.getY() % -400)==0) {
            if (isMapLabelActive) {
                for (int i=0;i<50;i++){
                    int x = (Player.Player.getX() - 300) + random.nextInt(600);
                    int y = (Player.Player.getY() + 1000) + random.nextInt(400);

                    MapLabel[i].setBounds(x, y, 10, 10);
                }
                isMapLabelActive = false;
            }
            else {
                for (int i=0;i<50;i++){
                    int x = (Player.Player.getX() - 300) + random.nextInt(600);
                    int y = (Player.Player.getY() + 1600) + random.nextInt(400);

                    MapLabel2[i].setBounds(x, y, 10, 10);
                }
                isMapLabelActive = true;
            }
        }
    }
}
