package com.thelastbaozi;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private Thread gameThread;
    private KeyHandler keyHandler = new KeyHandler();

    // SCREEN Settings
    private final int originalTileSize = 32; // 32x32 pixels
    private final int scale = 2;
    private final int tileSize = originalTileSize * scale;

    private final int FPS = 60; // Target frame rate
    private final double frameInterval = 1_000_000_000.0 / FPS; // Frame interval in nanoseconds

    // Number of tiles based on screen size (Might need to be adjusted)
    private final int screenWidth = 896;
    private final int screenHeight = 504;
    private final int maxScreenCol = screenWidth / tileSize;
    private final int maxScreenRow = screenHeight / tileSize;

    // PLAYER Settings
    private int playerX = 100;
    private int playerY = 100;
    private int playerSpeed = 4;


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void start(){
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void stop() {
        if (gameThread != null) {
            gameThread.interrupt();
            gameThread = null;
        }
    }

    @Override
    public void run() {
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (!Thread.currentThread().isInterrupted()) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / frameInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){

        if (keyHandler.upPressed) {
            playerY -= playerSpeed;
        } else if (keyHandler.downPressed) {
            playerY += playerSpeed;
        } else if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        // Test simulation player movement
        g2d.setColor(Color.CYAN);
        g2d.fillRect(playerX, playerY, tileSize,tileSize);
        g2d.dispose();
    }
}
