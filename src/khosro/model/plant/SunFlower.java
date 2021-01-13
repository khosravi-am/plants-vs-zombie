package khosro.model.plant;

import khosro.model.Bullet.Bullet;
import khosro.model.Sun;
import khosro.model.map.MapHome;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * SunFlower plant
 */
public class SunFlower extends Plants {

    /**
     * Time has left to produce a sun per second by a sunflower.
     */
    public static final int PRODUCINGTIME = 20;

    /**
     * @param bulletArr Array of bullet that plant can shoot it. If plant can't shoot a bullet, it equal to null.
     * @param img       Image address.
     * @param image     Image of plants.
     * @param life      Each plants how many life time when zombies eat it.
     * @param locX      X location in visual map
     * @param locY      Y location in visual map
     * @param bornTime  Time has left a plants card refresh.
     * @param x         X location in map
     * @param y         Y location in map
     */
    public SunFlower(ArrayList<Bullet> bulletArr,
                     BufferedImage img,
                     Image image,
                     int life,
                     MapHome locX,
                     MapHome locY,
                     long bornTime,
                     int x,
                     int y) {
        super(bulletArr, img, image, life, locX, locY, bornTime, x, y);
        cost = 100;
        preparingTime = 5;
    }

    /**
     * Produce a sun
     */
    public void produceSun() {
        new Sun();
    }
}
