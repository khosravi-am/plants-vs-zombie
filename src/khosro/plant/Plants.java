package khosro.plant;

import khosro.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    protected int locX;

    /**
     * Y location in visual map
     */
    protected int locY;

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

    public Plants(ArrayList<Bullet> bulletArr,
                  BufferedImage img,
                  Image image,
                  int cost,
                  int life,
                  int preparingTime,
                  int locX,
                  int locY,
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
}
