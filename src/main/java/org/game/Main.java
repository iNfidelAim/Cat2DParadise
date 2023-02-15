package org.game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Game for you");

        JFrame window  = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Barsik Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}