package org.game.entity;

import org.game.main.GamePanel;
import org.game.main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {


    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCounter = 0;


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 15;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 20;



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

        down1 = setup("/player/down1");
        downMid = setup("/player/down_mid");
        down2 = setup("/player/down2");
        up1 = setup("/player/up1");
        upMid = setup("/player/up_mid");
        up2 = setup("/player/up2");
        left1 = setup("/player/left1");
        leftMid = setup("/player/left_mid");
        left2 = setup("/player/left2");
        right1 = setup("/player/right1");
        rightMid = setup("/player/right_mid");
        right2 = setup("/player/right2");
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

            //Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

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
        } else {
             standCounter++;
             if(standCounter == 20) {
                 spriteNum = 2;
                 standCounter = 0;
             }
        }
    }


    public void pickUpObject(int i) {

        if(i != 999) {
        }
    }

    public void interactNPC(int i) {
        if(i != 999) {

             if(gp.keyH.enterPressed == true) {
                 gp.gameState = gp.dialogueState;
                 gp.npc[i].speak();
             }
        }
        gp.keyH.enterPressed = false;
    }


    public void draw(Graphics2D g2) {
     /*   g2.setColor(Color.blue);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);*/


        BufferedImage image = null;

        switch (direction) {
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
        g2.drawImage(image, screenX, screenY,null);


    }


}






