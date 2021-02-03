/*** In The Name of Allah ***/
package khosro.views;

import khosro.model.res.AddressStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

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
public class MainPage extends JFrame implements MouseMotionListener, MouseListener, KeyListener {

    /*private BufferedImage image;*/
    private ImageIcon icon;
    private Image mainImage;
    private Image newUserField;

    private Boolean login;
    private Boolean settingPanel;
    private Boolean rank;
    private Boolean runGame;
    private Boolean loadGameOption;

    private boolean newGame;
    private boolean loadGame;
    private boolean scoreBoard;
    private boolean setting;
    private boolean newUser;
    private boolean userOk;
    private boolean userCancel;
    private boolean help;

    private Graphics2D graphics;
    private BufferStrategy bufferStrategy;

    private MainMenu mainMenu;

    public MainPage() {
        super("Plants vs. Zombie");
        icon = new ImageIcon(AddressStore.GAMEICONE);
        super.setIconImage(icon.getImage());
        super.setSize(1080, 770);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
        initBufferStrategy();

        mainMenu = new MainMenu(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        login = false;
        settingPanel = false;
        rank = false;
        runGame = false;
        loadGameOption = false;

        newGame = false;
        loadGame = false;
        setting = false;
        scoreBoard = false;

        newUser = false;

        mainImage = new ImageIcon(AddressStore.MAINPAGE).getImage();
        newUserField = new ImageIcon(AddressStore.NEWUSER).getImage();

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
        bufferStrategy = super.getBufferStrategy();
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
                    mainMenu.setGraphics2D(graphics);
                    doRendering();
                    writeMenu();
                    if (newUser) {
                        mainMenu.drawNewUser(newUserField);
                        okChecker();
                    } else if (settingPanel) {
                        mainMenu.drawSettingPage();
                        if (help) {
                            mainMenu.drawHelp();
                        }
                    } else if (help) {
                        mainMenu.drawHelp();
                    } else {
                        init();
                    }
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

    public void init() {
        mainMenu.draw();
        graphics.setColor(Color.black);
        doRendering();
        writeMenu();
    }

    public void okChecker() {
        if (userOk) {
            userOk = false;
            newUser = false;
        } else if (userCancel) {
            graphics.setColor(Color.black);
            mainMenu.draw();
            mainMenu.drawStrings();
            userCancel = false;
            newUser = false;
        }
    }

    public void writeMenu() {
        if (newGame) {
            graphics.setColor(Color.white);
            mainMenu.drawMenu(MainMenu.NEWGAME, 600, 185, 65, 6);
        } else if (loadGame) {
            graphics.setColor(Color.white);
            mainMenu.drawMenu(MainMenu.LOADGAME, 600, 300, 55, 8);
        } else if (scoreBoard) {
            graphics.setColor(Color.white);
            mainMenu.drawMenu(MainMenu.SCOREBOARD, 575, 390, 55, 11);
        } else if (setting) {
            graphics.setColor(Color.white);
            mainMenu.drawMenu(MainMenu.SETTINGS, 630, 490, 55, 12);
        } else {
            graphics.setColor(Color.black);
            mainMenu.drawStrings();
        }
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

    public BufferStrategy getBufferStrategy() {
        return bufferStrategy;
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

        if (mainMenu.isNewGameMoved(e)) {
            runGame = true;
        } else if (mainMenu.isLoadGameMoved(e)) {
            loadGameOption = true;
            mainMenu.drawLoadPage();
        } else if (mainMenu.isScoreBoardGameMoved(e)) {
            rank = true;
        } else if (mainMenu.isSettingGameMoved(e)) {
            settingPanel = !mainMenu.isSettingOK(e);
            System.out.println("OK pressed.");
        } else if (mainMenu.isQuit(e)) {
            System.exit(0);
        } else if (mainMenu.isHelp(e)) {
            help = !help;
        } else if (mainMenu.isNewUser(e)) {
            newUser = true;
            System.out.println("New User Clicked.");
        }
        if (newUser) {
            if (mainMenu.isCancel(e)) {
                userCancel = true;
                System.out.println("User Cancel is clicked");
            } else if (mainMenu.isUserOk(e)) {
                userOk = true;
                System.out.println("User OK is clicked");
            }
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
                loadGame = false;
                setting = false;
                scoreBoard = false;
                newGame = true;
            } else if (mainMenu.isLoadGameMoved(e)) {
                newGame = false;
                setting = false;
                scoreBoard = false;
                loadGame = true;
            } else if (mainMenu.isScoreBoardGameMoved(e)) {
                newGame = false;
                loadGame = false;
                setting = false;
                scoreBoard = true;
            } else if (mainMenu.isSettingGameMoved(e)) {
                newGame = false;
                loadGame = false;
                scoreBoard = false;
                setting = true;
            } else if (mainMenu.isQuit(e)) {
                mainMenu.drawQuit();
            } else if (mainMenu.isHelp(e)) {
                //TODO complete this field.
            } else {
                graphics.setColor(Color.black);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        System.out.println(keyEvent.getKeyChar() + " Typed.");
        if (newUser) {
            String input = Character.toString(keyEvent.getKeyChar());
            mainMenu.drawMenu(input, 200, 200, 30, 0);
            System.out.println(input + " Typed.");
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
