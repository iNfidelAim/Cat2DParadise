package org.game.entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage down1, down2, downMid, up1, up2, upMid, left1, left2, leftMid, right1, right2, rightMid;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
