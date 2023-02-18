package org.game.entity;

import org.game.main.GamePanel;
import org.game.main.KeyHandler;
import org.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter = 0;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
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

        down1 = setup("down1");
        downMid = setup("down_mid");
        down2 = setup("down2");
        up1 = setup("up1");
        upMid = setup("up_mid");
        up2 = setup("up2");
        left1 = setup("left1");
        leftMid = setup("left_mid");
        left2 = setup("left2");
        right1 = setup("right1");
        rightMid = setup("right_mid");
        right2 = setup("right2");
    }

    public BufferedImage setup(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName +  ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
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

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Ты подобрал ключ!");
                    break;
                case "Door":
                    gp.playSE(1);
                    if(hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("Дверь открыта!");
                    } else {
                        gp.ui.showMessage("Барсик, тебе нужен ключ!");
                    }
                    break;
                case "Wings":
                    gp.playSE(1);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Крылья дают тебе скорость!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(2);
                    break;
            }
        }
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






