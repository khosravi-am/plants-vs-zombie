package khosro.handler;

import khosro.map.Map;
import khosro.pages.GamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class GameHandler {
    public boolean gameOver;
    private Map map;
    private Graphics2D g2d;
    private GamePage gamePage;

    /*private BufferedImage image;*/

    private BufferStrategy bufferStrategy;

    public GameHandler(Map map, GamePage gamePage) {
        this.map = map;
        this.gamePage = gamePage;
        setImage();
       // initBufferStrategy();

    }


    /**
     * This must be called once after the JFrame is shown:
     *    frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        gamePage.createBufferStrategy(3);
        bufferStrategy = gamePage.getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(){
        gamePage.paint2();

    }
    public void render2(){
        gamePage.paint3();
    }
    private void setImage() {
        gamePage.setBackground(map.getBackground().getImage());
        gamePage.setMapRow(map.getMapRows());
        gamePage.setCardFlower(map.getCardFlower().getImage());
        //gamePage.setVisible(true);
    }


    public void update() {
    }
}
