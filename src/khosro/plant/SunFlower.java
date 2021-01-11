package khosro.plant;

import khosro.Bullet.Bullet;
import khosro.Sun;
import khosro.map.MapHome;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SunFlower extends Plants {

    /**
     * The sun sunflower produced.
     */
    private Sun sun;

    /**
     * Time has left to produce a sun by a sunflower.
     */
    private int producingTime;

    /**
     * @param bulletArr     Array of bullet that plant can shoot it. If plant can't shoot a bullet, it equal to null.
     * @param img           Image address.
     * @param image         Image of plants.
     * @param cost          Cost of each plants per sun
     * @param life          Each plants how many life time when zombies eat it.
     * @param preparingTime The time each plants produce its product (sunflower produce a sun and shooters shoot a bullet)
     * @param locX          X location in visual map
     * @param locY          Y location in visual map
     * @param bornTime      Time has left a plants card refresh.
     * @param x             X location in map
     * @param y             Y location in map
     */
    public SunFlower(ArrayList<Bullet> bulletArr,
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
        super(bulletArr, img, image, cost, life, preparingTime, locX, locY, bornTime, x, y);
    }
}
