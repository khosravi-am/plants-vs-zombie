package khosro.model.component.zombie;

import javax.swing.*;

public class ConeHeadZombie extends Zombie {

    public ConeHeadZombie(){
        setGif(new ImageIcon("./src/khosro/model/res/z3.gif"));
        setPower(2);
        setLife(560);
        setHeadLife(200);
        setSpeed(3);
    }
}
