package com.thelastbaozi;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    // SCREEN SETTINGS
    private final int originalTileSize = 32; // 32x32 pixels
    private final int scale = 2;
    private final int tileSize = originalTileSize * scale;

    // Number of tiles based on screen size (Might need to be adjusted)
    private final int screenWidth = 896;
    private final int screenHeight = 504;
    private final int maxScreenCol = screenWidth / tileSize;
    private final int maxScreenRow = screenHeight / tileSize;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }







}
