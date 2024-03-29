package khosro.model.component.plant;

import khosro.model.component.Bullet.Bullet;
import khosro.model.map.MapHome;

import javax.swing.*;
import java.util.ArrayList;

public class Potato extends Plants {

    /**
     * @param bulletArr Array of bullet that plant can shoot it. If plant can't shoot a bullet, it equal to null.
     * @param image     Image of plants.
     * @param life      Each plants how many life time when zombies eat it.
     * @param locX      X location in visual map
     * @param locY      Y location in visual map
     * @param bornTime  Time has left a plants card refresh.
     * @param x         X location in map
     * @param y         Y location in map
     */
    public Potato(ArrayList<Bullet> bulletArr,
                  ImageIcon image,
                  int life,
                  MapHome locX,
                  MapHome locY,
                  long bornTime,
                  int x,
                  int y) {
        super(bulletArr, image, life, bornTime, x, y);
        this.cost = 50;
        this.preparingTime = 10;
    }

    public Potato(){
        setImage(new ImageIcon("./src/khosro/model/res/walnut.png"));
        setGif(new ImageIcon("./src/khosro/model/res/walnut4.gif"));
        setCost(50);
        setLife(150);
    }
}
