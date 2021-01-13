package khosro.controller;

import khosro.model.component.plant.Plants;
import khosro.model.component.plant.SunFlower;
import khosro.model.map.Map;
import khosro.views.GamePage;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class GameController implements MouseListener,MouseMotionListener {
    public boolean gameOver;
    private ArrayList<Plants> pea,sunflower,freeze,potato,cherry;
    private Boolean clickPea=false,clickSunflower=false,clickFreeze=false,clickPotato=false,clickCherry=false;
    private Map map;
    private Graphics2D g2d;
    private GamePage gamePage;
    Long start;
    /*private BufferedImage image;*/

    private BufferStrategy bufferStrategy;

    public GameController(Map map, GamePage gamePage){
        this.map = map;
        this.gamePage = gamePage;
        start = System.currentTimeMillis();
        gamePage.addMouseListener(this);
        gamePage.addMouseMotionListener(this);
        pea=new ArrayList<>();
        sunflower=new ArrayList<>();
        freeze=new ArrayList<>();
        potato=new ArrayList<>();
        cherry=new ArrayList<>();
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
     * @param cf
     * @param cc
     * @param cp
     * @param f
     * @param p
     * @param l
     *
     */
    public void render(int cf, int cc, int cp, int f, int p, int l) {
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
                    gamePage.delayCardFlower(g2d, cf);
                    gamePage.delayCardCherry(g2d, cc);
                    gamePage.delayCardPea(g2d, cp);
                    gamePage.delayCardFreeze(g2d, f);
                    gamePage.delayCardPotato(g2d, p);
                    gamePage.paintLevel(g2d, l);
                    //if (clickSunflower)
                       // paintFlower(new SunFlower())

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

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("x:"+e.getX()+" y:"+e.getY());
        //if (e.getX()>132 && e.getY()>41 && e.getX()<196 && e.getY()<122)
           // clickSunflower=true;
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("x:"+e.getX()+" y:"+e.getY());
        if (e.getX()>132 && e.getY()>41 && e.getX()<196 && e.getY()<122)
            clickSunflower=true;

        System.out.println(clickSunflower);

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX());
    }
}
