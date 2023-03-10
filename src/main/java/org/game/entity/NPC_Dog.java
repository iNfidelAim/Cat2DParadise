package org.game.entity;

import org.game.main.GamePanel;

import java.util.Random;

public class NPC_Dog extends Entity {


    public NPC_Dog(GamePanel gp) {
        super(gp);

        name = "Dog";
        speed = 1;
        getImage();
        setDialogue();


    }
    public void getImage() {

        down1 = setup("/npc/dog");
        downMid = setup("/npc/dog");
        down2 = setup("/npc/dog");
        up1 = setup("/npc/dog");
        upMid = setup("/npc/dog");
        up2 = setup("/npc/dog");
        left1 = setup("/npc/dog");
        leftMid = setup("/npc/dog");
        left2 = setup("/npc/dog");
        right1 = setup("/npc/dog");
        rightMid = setup("/npc/dog");
        right2 = setup("/npc/dog");
    }

    public void setDialogue() {

        dialogues[0] = "Who like the dog said?";
        dialogues[1] = "Gav gav gav Gav gav gav Gav gav gav Gav gav gav Gav gav gav Gav \n gav gav Gav gav gav Gav gav gav";
        dialogues[2] = "Hello, Barsik";
        dialogues[3] = "gav gav, Barsik, gav gav";

    }

    @Override
    public void setAction() {

        actionLockCounter ++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //pick up number from 1 to 100

            if( i <= 25) { direction = "up"; }
            if( i > 25 && i <=50) { direction = "down"; }
            if( i > 50 && i <= 75) { direction = "left"; }
            if(i > 75) { direction = "right"; }
            actionLockCounter = 0;
        }
    }
    public void speak() {
        //This method for specific character stuff
        super.speak();
    }
}
