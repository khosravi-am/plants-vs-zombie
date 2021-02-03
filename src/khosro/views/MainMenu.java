package khosro.views;

import khosro.model.res.AddressStore;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

public class MainMenu {
    public static final String NEWGAME = "New Game";
    public static final String LOADGAME = "Load game";
    public static final String SCOREBOARD = "Score Board";
    public static final String SETTINGS = "Settings";

    private MainPage mainPage;
    private Graphics2D graphics2D;

    public MainMenu(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        draw();
        Font font = new Font("Time New Roman", Font.BOLD, 55);
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(7), 0, 0);
        Font driverFont = font.deriveFont(transform);
        graphics2D.setFont(driverFont);
    }

    public void draw() {
        graphics2D.drawImage(mainPage.getMainImage(), 0, 0, 1080, 770, null);
    }

    public void drawStrings() {
        drawMenu(NEWGAME, 600, 185, 65, 6);
        drawMenu(LOADGAME, 600, 300, 55, 8);
        drawMenu(SCOREBOARD, 575, 390, 55, 11);
        drawMenu(SETTINGS, 630, 490, 55, 12);

        drawMenu("New User", 166, 200, 30, 0);
    }

    public void drawMenu(String string, int i, int i2, int size, int radios) {
        Font font = new Font("Matura MT Script Capitals", Font.PLAIN, size);
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(radios), 0, 0);
        Font driverFont = font.deriveFont(transform);
        graphics2D.setFont(driverFont);
        graphics2D.drawString(string, i, i2);
    }

    public void drawNewUser(Image image) {
        graphics2D.drawImage(image, 300, 200, null);
    }

    public void drawLoadPage() {
        try {
            Image image = ImageIO.read(new File(AddressStore.LOADPAGE));
            graphics2D.drawImage(image, 200, 150, 600, 500, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSettingPage() {
        try {
            Image image = ImageIO.read(new File(AddressStore.SETTINGPAGE));
            graphics2D.drawImage(image, 200, 150, 600, 500, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawQuit() {
        //TODO write quit
    }

    public boolean isNewGameMoved(MouseEvent e) {
        return e.getX() > 549 &&
                e.getX() < 966 &&
                e.getY() < 262 &&
                e.getY() > 127;
    }

    public boolean isLoadGameMoved(MouseEvent e) {
        return e.getX() > 555 &&
                e.getX() < 955 &&
                e.getY() < 385 &&
                e.getY() > 236;
    }

    public boolean isScoreBoardGameMoved(MouseEvent e) {
        return e.getX() > 560 &&
                e.getX() < 926 &&
                e.getY() < 480 &&
                e.getY() > 406;
    }

    public boolean isSettingGameMoved(MouseEvent e) {
        return e.getX() > 555 &&
                e.getX() < 955 &&
                e.getY() < 500 &&
                e.getY() > 426;
    }

    public void drawFrame() {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.createBufferStrategy(3);
        BufferStrategy strategy = frame.getBufferStrategy();
        Graphics2D graphics2D = (Graphics2D) strategy.getDrawGraphics();
        try {
            Image image = ImageIO.read(new File(AddressStore.SCOREBOARDPAGE));
            graphics2D.drawImage(image, 0, 0, 400, 400, null);
            strategy.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isQuit(MouseEvent e) {
        return e.getX() > 951 &&
                e.getX() < 1050 &&
                e.getY() < 700 &&
                e.getY() > 575;
    }

    public boolean isHelp(MouseEvent e) {
        return e.getX() > 860 &&
                e.getX() < 945 &&
                e.getY() < 706 &&
                e.getY() > 625;
    }

    public boolean isNewUser(MouseEvent e) {
        return e.getX() > 50 &&
                e.getX() < 412 &&
                e.getY() < 215 &&
                e.getY() > 170;
    }

    public boolean isCancel(MouseEvent e) {
        return e.getX() > 560 &&
                e.getX() < 762 &&
                e.getY() < 467 &&
                e.getY() > 430;
    }

    public boolean isOk(MouseEvent e) {
        return e.getX() > 338 &&
                e.getX() < 530 &&
                e.getY() < 467 &&
                e.getY() > 427;
    }
}