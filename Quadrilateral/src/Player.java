// Todo Proper Movement
// TODO Fix Shoot
// TODO Add more Enemies

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Player implements KeyListener {
    JLabel Player;

    // Player Attributes
    int PosX = 1280/2;
    int PosY = 720/2;
    int DirX;
    int DirY;
    static int Coins = 0;
    static int Health = 100;
    boolean isAttacking;
    boolean isDodge;
    boolean isSpacebarDown = false;
    boolean isSpacebarSpammed = false;
    int EnemysKilled = 0;
    Timer DodgeTime;
    //

    // Enemies
    Enemy Enemy;
    Shooters Shooters;

    int EPosX;
    int EPosY;
    GamePanel gamePanel;
    //

    // Attacks
    Melee EMelee;
    Melee Melee;
    Shoot Shoot;
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<JLabel> Enemies = new ArrayList<>();
    Timer shootCooldown;
    boolean canShoot = true;
    boolean canAttack = true;
    //
    Random random;
    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        Player = new JLabel();
        Enemy = new Enemy();
        EMelee = new Melee();
        Melee = new Melee();
        random = new Random();
        Shooters = new Shooters();
        Shoot = new Shoot();

        Player.setBounds(500/2,500/2,10,10);
        Player.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        isAttacking = false;
        isDodge = false;

        Player.setLayout(null);
        Player.setVisible(true);


    }

    public void update(GamePanel GamePanel){
//        if (EnemysKilled == 10){
//            // Pause
//            GamePanel.isPaused = true;
//            // Open Shop
//            Shop Shop = new Shop(GamePanel);
//        }

        int oldPosX = PosX;
        int oldPosY = PosY;

        PosX = Player.getX();
        PosY = Player.getY();

        PosX += DirX;
        PosY += DirY;

        Player.setBounds(PosX,PosY,10,10);

        // Player Collides with Walls
        for (int i=0;i< GamePanel.Walls.size();i++) {
            if (GamePanel.Walls.get(i).getBounds().intersects(Player.getBounds())) {
                PosX = oldPosX;
                PosY = oldPosY;
            }
        }

        Player.setBounds(PosX,PosY,10,10);

        // Enemy 1
        int LastEPosX = Enemy.Enemy.getX();
        int LastEPosY = Enemy.Enemy.getY();

        int LastSPosX = Shooters.Shooters.getX();
        int LastSPosY = Shooters.Shooters.getY();

        {
            Enemy.Enemy.setVisible(true);

            EPosX = Enemy.Enemy.getX();
            EPosY = Enemy.Enemy.getY();

            if (PosX < EPosX) {
                EPosX -= 1;
            }
            if (PosX > EPosX) {
                EPosX += 1;
            }
            if (PosY < EPosY) {
                EPosY -= 1;
            }
            if (PosY > EPosY) {
                EPosY += 1;
            }

            Enemy.Enemy.setBounds(EPosX, EPosY, 10, 10);
            // Enemy Dies
            if (Melee.Melee.getBounds().intersects(Enemy.Enemy.getBounds())) {
                Enemy.Enemy.setVisible(false);
                EnemysKilled += 1;
                Coins += 500;

                int x = random.nextInt(800);
                int y = random.nextInt(600);

                Enemy.Enemy.setBounds(x, y, 10, 10);
            }
            if (Melee.Melee.getBounds().intersects(Shooters.Shooters.getBounds())){
                Shooters.Shooters.setVisible(false);
                EnemysKilled += 1;
                Coins += 500;

                int x = random.nextInt(800);
                int y = random.nextInt(600);

                Shooters.Shooters.setBounds(x, y, 10, 10);
            }
        }

        // Enemy Melee
        {
            // If enemy close to player
            int distance = (int) Math.sqrt(Math.pow(PosX - EPosX, 2) + Math.pow(PosY - EPosY, 2));

            if (distance < 50 && canAttack) {
                canAttack = false;
                EMelee.Melee.setVisible(true);
                if (PosX > EPosX) {
                    EMelee.Melee.setBounds(EPosX + 15, EPosY - 7, 20, 25);
                }
                if (PosX < EPosX) {
                    EMelee.Melee.setBounds(EPosX - 25, EPosY - 7, 20, 25);
                }
                if (PosY > EPosY) {
                    EMelee.Melee.setBounds(EPosX - 7, EPosY + 15, 25, 20);
                }
                if (PosY < EPosY) {
                    EMelee.Melee.setBounds(EPosX - 7, EPosY - 25, 25, 20);
                }

                // Enemy Attack at distance cloes to Player

                Timer timer = new Timer(100, KeyEvent -> {
                    EMelee.Melee.setVisible(false);
                });
                timer.setRepeats(false);
                timer.start();
                Timer canAtkTimer = new Timer(1000, KeyEvent -> {
                    canAttack = true;
                });
                canAtkTimer.setRepeats(false);
                canAtkTimer.start();
            }
        }

        // Shooters Enemies
        {
            Shooters.Shooters.setVisible(true);
            int SPosX = Shooters.Shooters.getX();
            int SPosY = Shooters.Shooters.getY();

            int SDirX = 0;
            int SDirY = 0;

            if (PosX < SPosX) {
                SPosX -= 1;
            }
            if (PosX > SPosX) {
                SPosX += 1;
            }
            if (PosY < SPosY) {
                SPosY -= 1;
            }
            if (PosY > SPosY) {
                SPosY += 1;
            }

            SDirX += SPosX;
            SDirY += SPosY;

            Shooters.Shooters.setBounds(SDirX, SDirY, 10, 10);
        }

        // Shooters and Enemies Collides, Enables Collision
        if (Shooters.Shooters.getBounds().intersects(Enemy.Enemy.getBounds())){
            Enemy.Enemy.setBounds(LastSPosX,LastSPosY,10,10);
            Shooters.Shooters.setBounds(LastEPosX,LastEPosY,10,10);
        }

        // Player gets Hit
        if (EMelee.Melee.getBounds().intersects(Player.getBounds())){
            Health--;
        }
        for (Bullet bullet : bullets) {
            bullet.update();

            if (bullet.bullet.getBounds().intersects(Player.getBounds())) {
                Health--;
                bullet.bullet.setVisible(false);
                bullets.remove(bullet);
                break;
            }
        }

        // Player Dies
        if (Health <= 0) {
            Health = 0;
            Player.setVisible(false);
            System.out.println("GAME OVER NIGGA");
        }
        shoot();
    }

    // Todo Fix Shoot
    public void shoot() {
        if (!canShoot) {
            return;
        }

        int bulletX = Shooters.Shooters.getX();
        int bulletY = Shooters.Shooters.getY();
        int bulletDx = 0;
        int bulletDy = 0;

        if (PosX < bulletX) {
            bulletDx = -1;
        }
        if (PosX > bulletX){
            bulletDx = 1;
        }
        if (PosY < bulletY){
            bulletDy = -1;
        }
        if (PosY > bulletY){
            bulletDy = 1;
        }

        Bullet bullet = new Bullet(bulletX, bulletY, bulletDx, bulletDy);
        bullets.add(bullet);
        gamePanel.GamePanel.add(bullet.bullet);

        canShoot = false;
        shootCooldown = new Timer(1000, e -> canShoot = true);
        shootCooldown.setRepeats(false);
        shootCooldown.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gamePanel.isPaused) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            DirY = -2;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            DirY = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            DirX = -2;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            DirX = 2;
        }

        // Melee Attack
        if (e.getKeyCode() == KeyEvent.VK_J && !isAttacking){
            if (isDodge){
                return;
            }

            isAttacking = true;
            Melee.Melee.setVisible(true);
            if (DirX == 2){
                Melee.Melee.setBounds(PosX+15,PosY-7,20,25);
            }
            if (DirX == -2){
                Melee.Melee.setBounds(PosX-25,PosY-7,20,25);
            }
            if (DirY == 2){
                Melee.Melee.setBounds(PosX-7,PosY+15,25,20);
            }
            if (DirY == -2){
                Melee.Melee.setBounds(PosX-7,PosY-25,25,20);
            }

            // Error Checking
            if (DirX == 0 && DirY == 0){
                Melee.Melee.setBounds(PosX+15,PosY-7,20,25);
            }

            Timer timer = new Timer(100, KeyEvent -> {
                Melee.Melee.setBounds(1600,1600,20,25);
                Melee.Melee.setVisible(false);
                isAttacking = false;
            });
            timer.setRepeats(false);
            timer.start();
        }

        // Dash
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isDodge && !isSpacebarDown && isSpacebarSpammed){
            isSpacebarDown = true;
            isSpacebarSpammed = false;
            isDodge = true;
            DodgeTime = new Timer(1200, e2 ->{
                isDodge = false;
            });

            DodgeTime.start();

            int oldDirX = DirX;
            int oldDirY = DirY;

            // Direction of Dash
            if (oldDirX > 0){
                DirX = 4;
            }
            if (oldDirX < 0){
                DirX = -4;
            }
            if (oldDirY < 0){
                DirY = -4;
            }
            if (oldDirY > 0){
                DirY = 4;
            }
            if (oldDirX == 0 && oldDirY == 0) {
                DirX = 4;
            }

            Timer timer = new Timer(100, e1 -> {
                DirX = oldDirX;
                DirY = oldDirY;
            });
            timer.setRepeats(false);
            timer.start();
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W) {
            DirY = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_S) {
            DirY = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_A) {
            DirX = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
            DirX = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_J){
            isAttacking = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSpacebarDown = false;
            isSpacebarSpammed = true;
        }
    }
}
