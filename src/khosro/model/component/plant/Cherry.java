package khosro.model.component.plant;

import khosro.model.component.Bullet.Bullet;
import khosro.model.map.MapHome;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Cherry extends Plants {

    /**
     * Time has left to explode the cherry.
     */
    private final int explodedTime = 5;

    /**
     * Domain of explosion
     */
    private final int explodeDomains = 1;

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
    public Cherry(ArrayList<Bullet> bulletArr,
                  BufferedImage img,
                  Image image,
                  int life,
                  MapHome locX,
                  MapHome locY,
                  long bornTime,
                  int x,
                  int y) {
        super(bulletArr, img, image, life, locX, locY, bornTime, x, y);
        cost = 150;
        preparingTime = 7;
    }

    public void explode() {

    }
}
