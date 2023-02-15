package org.game;

import org.game.entity.Player;
import org.game.object.SuperObject;
import org.game.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 16;  //16x16 tile (плитка, NPC, персонаж и т.д.)
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //масштабирование 48х48
    public final int maxScreenCol = 32; //32
    public final int maxScreenRow = 20; //20
    public final int screenWidth = tileSize * maxScreenCol; // 48 * 16 =  1 536 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 48  * 20 = 960 pixels

    //WOLRD SETTINGS
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 79;

    //FPS
    int FPS = 60;
    //System
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music  = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //Entity and object
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

       aSetter.setObject();

       playMusic(0);
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

        //Tile
        tileM.draw(g2);

        //Object
        for (int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //Player
        player.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();
    }
    public void playMusic(int i) {
        music.setFIle(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        soundEffect.setFIle(i);
        soundEffect.play();
    }
}
