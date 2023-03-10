package org.game.main;

import org.game.entity.Entity;
import org.game.entity.Player;
import org.game.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity monsters[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();


    //Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonsters();
        //  playMusic(0);
        gameState = titleState;
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
                remainingTime = remainingTime / 1_000_000;

                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {


        if (gameState == playState) {
            //PLAYER
            player.update();
            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) { npc[i].update(); }
            }
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] != null) { monsters[i].update(); }
            }
        }
        if (gameState == pauseState) { /*nothing*/ }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Debug function (time)
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }

        //OTHERS
        else {
            //TILE
            tileM.draw(g2);

            //ADD ENTITIES TO THE LIST
            entityList.add(player);
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) { entityList.add(npc[i]); }
            }

            for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null) { entityList.add(obj[i]); }
            }

            for (int i = 0; i < monsters.length; i++) {
                        if (monsters[i] != null) { entityList.add(monsters[i]); }
            }
        }

        //SORT(для отприсовки либо сверху NPC либо снизу в зависимости от индекса
        Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {

                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            }
        });

        //DRAW ENTITIES
        for (int i = 0; i < entityList.size(); i++) {
            entityList.get(i).draw(g2);
        }
        //EMPTY ENTITY LIST
        for (int i = 0; i < entityList.size(); i++) {
            entityList.remove(g2);
        }


        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                entityList.add(obj[i]);
            }
        }

        //UI
        ui.draw(g2);


        //DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw time: " + passed, 10, 400);
            System.out.println("Draw time: " + passed);
        }


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
