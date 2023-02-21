package org.game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    //Debug
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.titleState) {

            if(gp.ui.titleScreenState == 0) {

                if (code == KeyEvent.VK_UP) {
                    gp.ui.commandNumb--;
                    if(gp.ui.commandNumb < 0) { gp.ui.commandNumb = 2; }
                }
                if (code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNumb++;
                    if(gp.ui.commandNumb > 2) { gp.ui.commandNumb = 0; }
                }
                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNumb == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commandNumb == 1) { //TODO later
                    }
                    if(gp.ui.commandNumb == 2) {
                        System.exit(0);
                    }
                }
            }

           else if(gp.ui.titleScreenState == 1) {

                if (code == KeyEvent.VK_UP) {
                    gp.ui.commandNumb--;
                    if(gp.ui.commandNumb < 0) { gp.ui.commandNumb = 2; }
                }
                if (code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNumb++;
                    if(gp.ui.commandNumb > 2) { gp.ui.commandNumb = 0; }
                }
                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNumb == 0) {
                        System.out.println("тут выбирается моделька и какие-то спец. способности т.е. сам гл.герой (мяукать, царапаться и т.д.");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNumb == 1) {
                        System.out.println("тут выбирается моделька и какие-то спец. способности т.е. сам гл.герой(гавкать, кусаться и т.д.");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNumb == 2) {
                        gp.ui.titleScreenState = 0;
                    }
                }
            }

        }

        //PLAY STATE
        if(gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_UP) { upPressed = true; }
            if (code == KeyEvent.VK_DOWN) { downPressed = true; }
            if (code == KeyEvent.VK_LEFT) { leftPressed = true; }
            if (code == KeyEvent.VK_RIGHT) { rightPressed = true; }
            if (code == KeyEvent.VK_P) { gp.gameState = gp.pauseState; }
            if (code == KeyEvent.VK_ENTER) { enterPressed = true; }

            //Debug
            if (code == KeyEvent.VK_T)  {
                if (checkDrawTime == false) { checkDrawTime = true; }
                else if(checkDrawTime = true) { checkDrawTime = false; }
            }
        }

        //PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) { gp.gameState = gp.playState; }
        }
        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_ENTER) { gp.gameState = gp.playState; }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) { upPressed = false; }
        if (code == KeyEvent.VK_DOWN) { downPressed = false; }
        if (code == KeyEvent.VK_LEFT) { leftPressed = false; }
        if (code == KeyEvent.VK_RIGHT) { rightPressed = false; }
    }
}
