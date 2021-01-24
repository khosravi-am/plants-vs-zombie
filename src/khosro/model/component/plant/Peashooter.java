package khosro.model.component.plant;

import khosro.model.component.Bullet.Bullet;
import khosro.model.component.Bullet.PeaBullet;
import khosro.model.map.MapHome;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Peashooter extends Pea {

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
    public Peashooter(ArrayList<Bullet> bulletArr,
                      BufferedImage img,
                      ImageIcon image,
                      int life,
                      MapHome locX,
                      MapHome locY,
                      long bornTime,
                      int x,
                      int y) {
        super(bulletArr, img, image, life, locX, locY, bornTime, x, y);
        cost = 100;
    }

    /**
     * Shoot the bullet to zombie.
     * Create a bullet and add its to array of bullet.
     */
    @Override
    public void shoot() {
//        Bullet bullet = new PeaBullet("");
//        bulletArr.add(bullet);
    }
}
