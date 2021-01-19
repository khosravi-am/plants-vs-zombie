package khosro.controller;

import khosro.map.Map;
import khosro.views.GamePage;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameHandler {
    public boolean gameOver;
    private Map map;
    private Graphics2D g2d;
    private GamePage gamePage;
    Long start;
    /*private BufferedImage image;*/

    private BufferStrategy bufferStrategy;

    public GameHandler(Map map, GamePage gamePage) {
        this.map = map;
        this.gamePage = gamePage;
        start = System.currentTimeMillis();
        setImage();
        initBufferStrategy();

    }


    /**
     * This must be called once after the JFrame is shown:
     * frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        gamePage.createBufferStrategy(3);
        bufferStrategy = gamePage.getBufferStrategy();
        g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     *
     * @
     * @param cf
     * @param cc
     * @param cp
     * @param f
     * @param p
     */
    public void render(int cf, int cc, int cp, int f, int p,int l) {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    //doRendering(graphics, state);
                    gamePage.paintBack(g2d);
                    gamePage.setRows(g2d, map.getMapRows());
                    gamePage.paintMenu(g2d);
                    gamePage.delayCardFlower(g2d,cf);
                    gamePage.delayCardCherry(g2d,cc);
                    gamePage.delayCardPea(g2d,cp);
                    gamePage.delayCardFreeze(g2d,f);
                    gamePage.delayCardPotato(g2d,p);
                    gamePage.paintLevel(g2d,l);

                } finally {
                    // Dispose the graphics
                    g2d.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }

    private void setImage() {
        gamePage.setBackground(map.getBackground().getImage());
        gamePage.setMenu(map.getMenu().getImage());
        gamePage.setMenuBar(map.getMenuBar().getImage());
        gamePage.setShovel(map.getShovel().getImage());
        gamePage.setCardFlower(map.getCardFlower().getImage());
        gamePage.setLevel(map.getLevel().getImage());
        gamePage.setCardCherry(map.getCardCherry().getImage());
        gamePage.setCardFreeze(map.getCardFreeze().getImage());
        gamePage.setCardPea(map.getCardPea().getImage());
        gamePage.setCardPotato(map.getCardPotato().getImage());
        //gamePage.setVisible(true);
    }


    public void update() {
    }
}
