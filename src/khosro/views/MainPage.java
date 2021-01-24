/*** In The Name of Allah ***/
package khosro.views;

import khosro.model.res.AddressStore;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import javax.swing.*;

/**
 * The window on which the rendering is performed.
 * This example uses the modern BufferStrategy approach for double-buffering,
 * actually it performs triple-buffering!
 * For more information on BufferStrategy check out:
 * http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 * http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class MainPage extends JFrame implements MouseMotionListener, MouseListener {

    //uncomment all /*...*/ in the class for using Tank icon instead of a simple circle
    /*private BufferedImage image;*/
    private ImageIcon icon;
    private Image mainImage;
    private Boolean login;
    private Boolean setting;
    private Boolean rank;
    private Boolean runGame;
    private Boolean loadGame;
    private Graphics2D graphics;
    private BufferStrategy bufferStrategy;

    MainMenu mainMenu;

    public MainPage() {
        super("Plants vs. Zombie");
        icon = new ImageIcon(AddressStore.GAMEICONE);
        super.setIconImage(icon.getImage());
        super.setSize(1080, 770);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setLayout(null);
        super.setResizable(false);
        super.setVisible(true);
        mainMenu = new MainMenu(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        login = false;
        setting = false;
        rank = false;
        runGame = false;
        loadGame = false;

        initBufferStrategy();
        mainImage = new ImageIcon(AddressStore.MAINPAGE).getImage();
        graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        mainMenu.setGraphics2D(graphics);
    }

    /**
     * This must be called once after the JFrame is shown:
     * frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        this.createBufferStrategy(3);
        bufferStrategy = this.getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render() {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    doRendering();
                } finally {
                    // Dispose the graphics
                    graphics.dispose();
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

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering() {
        mainMenu.drawStrings();
    }

    public Image getMainImage() {
        return mainImage;
    }

    @Override
    public Graphics2D getGraphics() {
        return graphics;
    }

    public Boolean getRunGame() {
        return runGame;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("(" + e.getX() + " , " + e.getY() + ")");

        if (mainMenu.isLoadGameMoved(e)) {
            System.out.println("Load Game clicked");
        } else if (mainMenu.isNewGameMoved(e)) {
            System.out.println("New Game clicked");
            runGame = true;
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

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
        if (graphics != null) {
            if (mainMenu.isNewGameMoved(e)) {
                mainMenu.changeColor(Color.WHITE);
            } else if (mainMenu.isLoadGameMoved(e)) {
                mainMenu.changeColor(Color.WHITE);
            } else if (mainMenu.isScoreBoardGameMoved(e)) {

            } else if (mainMenu.isSettingGameMoved(e)) {

            } else {
                mainMenu.changeColor(Color.black);
            }
            doRendering();
        }
    }
}
