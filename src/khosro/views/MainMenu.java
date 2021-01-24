package khosro.views;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class MainMenu {
    MainPage mainPage;
    public static final String NEWGAME = "New Game";
    public static final String LOADGAME = "Load game";
    public static final String SCOREBOARD = "Score Board";
    public static final String SETTINGS = "Settings";
    Graphics2D graphics2D;

    public MainMenu(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        graphics2D.drawImage(mainPage.getMainImage(), 0, 0, 1080, 770, null);
        Font font = new Font("Time New Roman", Font.BOLD, 55);
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(7), 0, 0);
        Font driverFont = font.deriveFont(transform);
        graphics2D.setFont(driverFont);
    }

    public void drawStrings() {
        drawMenu(NEWGAME, 600, 195);
        drawMenu(LOADGAME, 585, 305);
        drawMenu(SCOREBOARD, 575, 400);
        drawMenu(SETTINGS, 630, 500);
    }

    public void changeColor(Color color) {
        graphics2D.setColor(color);
    }

    public void drawMenu(String newGame, int i, int i2) {
        graphics2D.drawString(newGame, i, i2);
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
                e.getY() < 385 &&
                e.getY() > 236;
    }
}
