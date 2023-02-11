package org.game;

import org.game.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 16;  //16x16 tile (плитка, NPC, персонаж и т.д.)
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //масштабирование 48х48
    final int maxScreenCol = 32;
    final int maxScreenRow = 20;
    final int screenWidth = tileSize * maxScreenCol; // 48 * 16 =  1 536 pixels
    final int screenHeight = tileSize * maxScreenRow; // 48  * 20 = 960 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    //set default player position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1_000_000_000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {


            //System.out.println("The game loop is running");
            //1 update обновление позиции персонажа
            update();
            //отрисовка на экране
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1_000_000;

                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();
    }
}
