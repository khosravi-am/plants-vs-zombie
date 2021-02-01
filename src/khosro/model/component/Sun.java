package khosro.model.component;

import khosro.model.component.plant.Plants;

import javax.swing.*;
import java.util.Random;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;


public class Sun extends Plants implements Runnable {
    private int x;
    private int y;
    private Boolean sun=false,click=false,over=false;
    private Thread t;

    public Sun() {
        setImage(new ImageIcon("./src/khosro/model/res/sun.png"));
        setX(new Random().nextInt(900) + 100);
        setY(100);
        setPreparingTime(25000);

    }

    public Boolean getOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

    public Runnable dispose() {

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                x=getX();
                y=getY();

                while (getX() > 40 && getY() > 40) {
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setX(getX()-1);
                    setY((int) ((((double)(y-40)/(double)(x-40))*(getX()-x)) +y));


                }
                sun=true;
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setY(y);
                setX(x);
            }
        };
        return runnable;
      }

    public void setSun(Boolean sun) {
        this.sun = sun;
    }

    public Boolean getSun() {
        return sun;
    }

    public void setClick(Boolean click) {
        this.click = click;
    }

    public Boolean getClick() {
        return click;
    }

    public Thread getT() {
        return t;
    }

    public Sun(int x, int y) {
        setX(x);
        setY(y);
        setPreparingTime(25000);
        //TODO set image address to program.
        setImage(new ImageIcon("./src/khosro/model/res/sun.png"));
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        setOver(false);
        Long l = getBornTime();
        while (System.currentTimeMillis() - getBornTime() < 10000 ) {
            if (System.currentTimeMillis() - l > 20) {
                setY(getY() + 2);
                l = System.currentTimeMillis();
            }
            if (over.equals(true)) {
                interrupted();
                break;
            }
        }

        setOver(true);


    }
}
