package khosro.model.component.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    protected Image image;
    protected int x;
    protected int y;
    protected int locX;
    protected int locY;
    protected BufferedImage bufferedImage;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void move() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getImage() {
        return bufferedImage;
    }
}
