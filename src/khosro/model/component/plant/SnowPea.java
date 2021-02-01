package khosro.model.component.plant;

import khosro.model.component.Bullet.Bullet;
import khosro.model.component.Bullet.SnowBullet;
import khosro.model.map.MapHome;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SnowPea extends Plants {

    private Bullet snowBullet;
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
    public SnowPea(ArrayList<Bullet> bulletArr,
                   BufferedImage img,
                   ImageIcon image,
                   int life,
                   MapHome locX,
                   MapHome locY,
                   long bornTime,
                   int x,
                   int y) {
        super(bulletArr, img, image, life, bornTime, x, y);
        cost = 175;
    }

    public SnowPea(){
        setImage(new ImageIcon("./src/khosro/model/res/icePea.png"));
        setGif(new ImageIcon("./src/khosro/model/res/SnowPea.gif"));
        setCost(175);
        setLife(100);
        snowBullet=new SnowBullet();
    }

    /**
     * Shoot the bullet to zombie.
     * Create a bullet and add its to array of bullet.
     */
    public Bullet shoot() {
        snowBullet.setRun(true);
        snowBullet.setBornTime(System.currentTimeMillis());
        return snowBullet;
    }

    public Bullet getSnowBullet() {
        return snowBullet;
    }
}