package khosro.model.component.Bullet;


import javax.swing.*;

public class PeaBullet extends Bullet {
    public PeaBullet(String address, int x, int y) {
        super(x, y);
    }

    public PeaBullet(){
        setImage(new ImageIcon("./src/khosro/model/res/bullet.png"));
        setPower(30);
    }
}
