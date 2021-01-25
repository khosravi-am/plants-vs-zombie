package khosro.model.component.plant;

import khosro.model.component.Bullet.Bullet;
import khosro.model.map.MapHome;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Peashooter extends Plants {

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
        super(bulletArr, img, image, life, bornTime, x, y);
        cost = 100;
    }

    public Peashooter(){
        setImage(new ImageIcon("./src/khosro/model/res/pea.png"));
        setGif(new ImageIcon("./src/khosro/model/res/PeaShooter.gif"));
    }


    /**
     * Shoot the bullet to zombie.
     * Create a bullet and add its to array of bullet.
     */
    public void shoot() {
//        Bullet bullet = new PeaBullet("");
//        bulletArr.add(bullet);
    }
}
