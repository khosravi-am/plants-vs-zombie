package khosro.plant;

import khosro.Bullet.Bullet;
import khosro.map.MapHome;

import java.awt.*;
import java.awt.image.BufferedImage;
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
    private Image image;

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

    /**
     * @param bulletArr Array of bullet that plant can shoot it. If plant can't shoot a bullet, it equal to null.
     * @param img Image address.
     * @param image Image of plants.
     * @param cost Cost of each plants per sun
     * @param life Each plants how many life time when zombies eat it.
     * @param preparingTime The time each plants produce its product (sunflower produce a sun and shooters shoot a bullet)
     * @param locX X location in visual map
     * @param locY Y location in visual map
     * @param bornTime Time has left a plants card refresh.
     * @param x X location in map
     * @param y Y location in map
     */
    public Plants(ArrayList<Bullet> bulletArr,
                  BufferedImage img,
                  Image image,
                  int cost,
                  int life,
                  int preparingTime,
                  MapHome locX,
                  MapHome locY,
                  long bornTime,
                  int x,
                  int y) {
        this.bulletArr = bulletArr;
        this.img = img;
        this.image = image;
        this.cost = cost;
        this.life = life;
        this.preparingTime = preparingTime;
        this.locX = locX;
        this.locY = locY;
        this.bornTime = bornTime;
        this.x = x;
        this.y = y;
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
}
