package khosro.model.component.zombie;

import javax.swing.*;

public class BucketHeadZombie extends Zombie{
    public BucketHeadZombie(){
        setGif(new ImageIcon("./src/khosro/model/res/z2.gif"));
        setPower(4);
        setLife(1300);
        setHeadLife(200);
        setSpeed(3);

    }
}
