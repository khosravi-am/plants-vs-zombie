package khosro.controller;

import khosro.Main;
import khosro.model.component.plant.*;
import khosro.model.map.Map;
import khosro.model.map.MapHome;
import khosro.views.GamePage;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class GameController implements MouseListener, MouseMotionListener {
    public boolean gameOver;

    private Boolean clickPea = false, clickSunflower = false, clickFreeze = false, clickPotato = false, clickCherry = false,cherry=false,cherry1=false;
    private Map map;
    private Plants plant;
    private Graphics2D g2d;
    private GamePage gamePage;
    Long start;
    private int mouseX, mouseY;
    /*private BufferedImage image;*/

    private BufferStrategy bufferStrategy;

    public GameController(Map map, GamePage gamePage) {
        this.map = map;
        this.gamePage = gamePage;
        start = System.currentTimeMillis();
        gamePage.addMouseListener(this);
        gamePage.addMouseMotionListener(this);
        setImage();
        initBufferStrategy();
        //(addMouseListener();
    }

    private void addMouseListener() {
      /*  for (int i=0;i<5;i++)
            for (int j=0;j<9;j++)
      */          //map.getMapRows().get(i).getMapHomes().get(j).addMouseListener(this);
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
                    gamePage.paintBack(g2d);
                    gamePage.setRows(g2d, map.getMapRows());
                    gamePage.paintMenu(g2d);
                    gamePage.delayCardFlower(g2d, cf);
                    gamePage.delayCardCherry(g2d, cc);
                    gamePage.delayCardPea(g2d, cp);
                    gamePage.delayCardFreeze(g2d, f);
                    gamePage.delayCardPotato(g2d, p);
                    gamePage.paintLevel(g2d, l);
                    if (clickSunflower) {
                        paintPlants(new SunFlower());
                        clickSunflower = false;
                    }
                    if (clickPea) {
                        paintPlants(new Peashooter());
                        clickPea = false;
                    }
                    if (clickFreeze){
                        paintPlants(new SnowPea());
                        clickFreeze=false;
                    }
                    if (clickCherry){
                        paintPlants(new Cherry());
                        clickCherry=false;
                    }
                    if (clickPotato){
                        paintPlants(new Potato());
                        clickPotato=false;
                    }

                    if (plant != null && plant.isUse())
                        gamePage.paintMouseImg(g2d, plant.getImage().getImage(), mouseX - 50, mouseY - 80);

                    for (int i = 0; i < 5; i++)
                        for (int j = 0; j < 9; j++)
                            if (map.getMapRows().get(i).getMapHomes().get(j).isPor())
                                checkAndInsert(map.getMapRows().get(i).getMapHomes().get(j));





                } catch (NullPointerException e) {

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

    private void paintPlants(Plants plant) {
        plant.setUse(true);
        this.plant = plant;
        gamePage.paintMouseImg(g2d, plant.getImage().getImage(), mouseX - 50, mouseY - 80);
    }

    public void setPlant(Plants plant) {
        this.plant = plant;
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

    private void checkAndInsert(MapHome mapHome) {
        if (!mapHome.getPlant().isLive()){
            if (mapHome.getPlant().getClass().getSimpleName().equals("Cherry"))
                cherry=false;
            mapHome.setPlant(null);
            return;
        }
        if (mapHome.getPlant().getClass().getSimpleName().equals("Cherry")) {
            gamePage.paintHomeImage(g2d, mapHome.getPlant().getGif().getImage(), mapHome.getX() - 130, mapHome.getY() - 130, 350, 350);
            if (!cherry) {
                mapHome.getPlant().setBornTime(System.currentTimeMillis());
                ((Cherry) mapHome.getPlant()).explode();
                cherry=true;
            }

        }
        else gamePage.paintHomeImage(g2d, mapHome.getPlant().getGif().getImage(), mapHome.getX(), mapHome.getY());


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
        System.out.println("x:" + e.getX() + " y:" + e.getY());

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() > 132 && e.getY() > 40 && e.getX() < 196 && e.getY() < 125 && Main.card1)
            clickSunflower = true;
        if (e.getX() > 210 && e.getY() > 40 && e.getX() < 275 && e.getY() < 125 && Main.card2)
            clickPea = true;
        if (e.getX() > 290 && e.getY() > 40 && e.getX() < 360 && e.getY() < 125 && Main.card3)
            clickFreeze = true;
        if (e.getX() > 370 && e.getY() > 40 && e.getX() < 440 && e.getY() < 125 && Main.card4)
            clickCherry = true;
        if (e.getX() > 450 && e.getY() > 40 && e.getX() < 515 && e.getY() < 125 && Main.card5)
            clickPotato = true;
        if (e.getX() > 45 && e.getY() > 137 && e.getX() < 1042 && e.getY() < 734)
            checkHome(e);

    }

    private void checkHome(MouseEvent e) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 9; j++)
                if (e.getX() > map.getMapRows().get(i).getMapHomes().get(j).getX() && e.getY() > map.getMapRows().get(i).getMapHomes().get(j).getY() && e.getX() < map.getMapRows().get(i).getMapHomes().get(j).getX2() && e.getY() < map.getMapRows().get(i).getMapHomes().get(j).getY2()) {
                    if (!map.getMapRows().get(i).getMapHomes().get(j).isPor()) {
                        if (plant.isUse()) {
                            plant.setUse(false);
                            map.getMapRows().get(i).getMapHomes().get(j).setPlant(plant);
                        }
                    }
                    break;
                }
    }

    public Boolean getClickSunflower() {
        return clickSunflower;
    }


    public Plants getPlant() {
        return plant;
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //checkHome(e);
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
        mouseX = e.getX();
        mouseY = e.getY();

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
