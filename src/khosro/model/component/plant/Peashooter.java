package khosro.model.component.plant;

import khosro.model.component.Bullet.Bullet;
import khosro.model.component.Bullet.PeaBullet;
import khosro.model.map.MapHome;

import javax.swing.*;
import java.util.ArrayList;

public class Peashooter extends Plants {
    private Bullet peaBullet;
    private Boolean shoot=false;
    /**
     * @param bulletArr     Array of bullet that plant can shoot it. If plant can't shoot a bullet, it equal to null.
     * @param image         Image of plants.
     * @param life          Each plants how many life time when zombies eat it.
     * @param locX          X location in visual map
     * @param locY          Y location in visual map
     * @param bornTime      Time has left a plants card refresh.
     * @param x             X location in map
     * @param y             Y location in map
     */
    public Peashooter(ArrayList<Bullet> bulletArr,
                      ImageIcon image,
                      int life,
                      MapHome locX,
                      MapHome locY,
                      long bornTime,
                      int x,
                      int y) {
        super(bulletArr, image, life, bornTime, x, y);
        cost = 100;
    }

    public Peashooter(){
        setImage(new ImageIcon("./src/khosro/model/res/pea.png"));
        setGif(new ImageIcon("./src/khosro/model/res/PeaShooter.gif"));
        setCost(100);
        peaBullet=new PeaBullet();
        setLife(70);

    }


    /**
     * Shoot the bullet to zombie.
     * Create a bullet and add its to array of bullet.
     */
    public Bullet shoot() {
        peaBullet.setRun(true);
        peaBullet.setBornTime(System.currentTimeMillis());
        return peaBullet;
    }

    public Bullet getPeaBullet() {
        return peaBullet;
    }
}
