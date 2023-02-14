package org.game.entity;

import org.game.GamePanel;
import org.game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 20;
        solidArea.width = 24;
        solidArea.height = 32;



        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 32;
        worldY = gp.tileSize * 32;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            downMid = ImageIO.read(getClass().getResourceAsStream("/player/down_mid.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            upMid = ImageIO.read(getClass().getResourceAsStream("/player/up_mid.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            leftMid = ImageIO.read(getClass().getResourceAsStream("/player/left_mid.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            rightMid = ImageIO.read(getClass().getResourceAsStream("/player/right_mid.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) { //сюда обернул, чтобы Барсик не двигался пока не ходит

            if(keyH.upPressed) {
                direction = "up";
            } else if(keyH.downPressed)  {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //if collision is false, player can move
            if(collisionOn == false) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 5) {
                if(spriteNum == 1) {  spriteNum = 2; }
                else if (spriteNum == 2) { spriteNum = 3; }
                else if (spriteNum == 3) { spriteNum = 1; }
                spriteCounter = 0;

            }
        }
    }

    public void draw(Graphics2D g2) {
     /*   g2.setColor(Color.blue);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);*/


        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2)  image = upMid;
                if(spriteNum == 3)  image = up2;
                break;
            case "down":
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2)  image = downMid;
                if(spriteNum == 3)  image = down2;
                break;
            case "left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2)  image = leftMid;
                if(spriteNum == 3)  image = left2;
                break;
            case "right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2)  image = rightMid;
                if(spriteNum == 3)  image = right2;
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);


    }


}






