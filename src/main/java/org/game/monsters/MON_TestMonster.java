package org.game.monsters;

import org.game.entity.Entity;
import org.game.main.GamePanel;

import java.util.Random;



public class MON_TestMonster extends Entity {


    public MON_TestMonster(GamePanel gp) {
                super(gp);

                name = "test_monster";
                speed = 1;
                maxLife = 4;
                life = maxLife;

                solidArea.x = 3;
                solidArea.y = 18;
                solidArea.width = 42;
                solidArea.height = 30;
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;

                getImage();
            }

    public void getImage() {

            down1 = setup("/monsters/test_monster1");
            downMid = setup("/monsters/test_monster2");
            down2 = setup("/monsters/test_monster1");
            up1 = setup("/monsters/test_monster2");
            upMid = setup("/monsters/test_monster1");
            up2 = setup("/monsters/test_monster2");
            left1 = setup("/monsters/test_monster1");
            leftMid = setup("/monsters/test_monster2");
            left2 = setup("/monsters/test_monster1");
            right1 = setup("/monsters/test_monster2");
            rightMid = setup("/monsters/test_monster1");
            right2 = setup("/monsters/test_monster2");
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
    }

