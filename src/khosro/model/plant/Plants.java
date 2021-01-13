package khosro.model.plant;

import khosro.model.Bullet.Bullet;
import khosro.model.map.MapHome;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * SuperClass of all other plants.
 * Keep fields of plants.
 */
public class Plants {

    /**
     * Array of bullet that plant can shoot it.
     * If plant can't shoot a bullet, it equal to null.
     */
    protected ArrayList<Bullet> bulletArr;

    /**
     * Image address.
     */
    protected BufferedImage img;

    /**
     * Image of plants.
     */
    protected Image image;

    /**
     * Cost of each plants per sun
     */
    protected int cost;

    /**
     * Each plants how many life time when zombies eat it.
     */
    protected int life;

    /**
     * The time each plants produce its product (sunflower produce a sun and shooters shoot a bullet)
     */
    protected int preparingTime;

    /**
     * X location in visual map
     */
    protected MapHome locX;

    /**
     * Y location in visual map
     */
    protected MapHome locY;

    /**
     * Time has left a plants card refresh.
     */
    protected long bornTime;

    /**
     * X location in map
     */
    protected int x;

    /**
     * Y location in map
     */
    protected int y;

    protected boolean live;

    /**
     * @param bulletArr     Array of bullet that plant can shoot it. If plant can't shoot a bullet, it equal to null.
     * @param img           Image address.
     * @param image         Image of plants.
     * @param life          Each plants how many life time when zombies eat it.
     * @param locX          X location in visual map
     * @param locY          Y location in visual map
     * @param bornTime      Time has left a plants card refresh.
     * @param x             X location in map
     * @param y             Y location in map
     */
    public Plants(ArrayList<Bullet> bulletArr,
                  BufferedImage img,
                  Image image,
                  int life,
                  MapHome locX,
                  MapHome locY,
                  long bornTime,
                  int x,
                  int y) {
        this.bulletArr = bulletArr;
        this.img = img;
        this.image = image;
        this.life = life;
        this.locX = locX;
        this.locY = locY;
        this.bornTime = bornTime;
        this.x = x;
        this.y = y;
        live = true;

        try {
            img = ImageIO.read(new File(" input.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        image = new ImageIcon("input.gif").getImage();
    }

    public MapHome getLocX() {
        return locX;
    }

    public MapHome getLocY() {
        return locY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void dead() {
        //TODO remove plant form screen.
        live = false;
    }
}
