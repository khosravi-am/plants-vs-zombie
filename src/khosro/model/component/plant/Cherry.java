package khosro.model.component.plant;

import khosro.model.component.Bullet.Bullet;
import khosro.model.map.Map;
import khosro.model.map.MapHome;

import javax.swing.*;
import javax.swing.plaf.TableHeaderUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Cherry extends Plants {

    /**
     * Time has left to explode the cherry.
     */
    private final int explodedTime = 5;

    /**
     * Domain of explosion
     */
    private final int explodeDomains = 1;
    private Long time;

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
                  ImageIcon image,
                  int life,
                  MapHome locX,
                  MapHome locY,
                  long bornTime,
                  int x,
                  int y) {
        super(bulletArr, img, image, life, bornTime, x, y);
        cost = 150;
        preparingTime = 7;
    }

    public Cherry() {
        setImage(new ImageIcon("./src/khosro/model/res/cherry.png"));
        setCost(150);
        setLife(70);
    }

    public void explode(MapHome mapHome, Map map) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (System.currentTimeMillis() - getBornTime() > 1590) {
                        dead();
                        killZombies(mapHome, map);
                        break;
                    }

                }

                interrupt();

            }
        };
        thread.start();
    }

    private void killZombies(MapHome mapHome, Map map) {
        for (int i = -1; i < 3; i++)
            for (int j = -1; j < 3; j++)
                if (mapHome.getRow() + i > -1 && mapHome.getCount() - 1 + j > -1 && mapHome.getCount() - 1 + j < 9 && mapHome.getRow() + i < 5)
                    if (map.getMapRows().get(mapHome.getRow() + i).getMapHomes().get(mapHome.getCount() - 1 + j).getHaveZombie()) {
                        map.getMapRows().get(mapHome.getRow() + i).getMapHomes().get(mapHome.getCount() - 1 + j).getZombie().setRun(false);
                        map.getMapRows().get(mapHome.getRow() + i).getMapHomes().get(mapHome.getCount() - 1 + j).getZombie().setGif(new ImageIcon("./src/khosro/model/res/burntZombie.gif"));

                    }
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = -1; i < 3; i++)
            for (int j = -1; j < 3; j++)
                if (mapHome.getRow() + i > -1 && mapHome.getCount() -1 + j > -1 && mapHome.getCount() - 1 + j < 9 && mapHome.getRow() + i < 5)
                    if (map.getMapRows().get(mapHome.getRow() + i).getMapHomes().get(mapHome.getCount() - 1 + j).getHaveZombie())
                        map.getMapRows().get(mapHome.getRow() + i).getMapHomes().get(mapHome.getCount() - 1 + j).setZombie(null);
    }


}



