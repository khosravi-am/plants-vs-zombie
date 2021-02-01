package khosro.model.component.zombie;

import javax.swing.*;

public class NormZombie extends Zombie{
    public NormZombie(){
        setGif(new ImageIcon("./src/khosro/model/res/z1.gif"));
        setPower(1);
        setLife(200);
        setHeadLife(0);
    }
}
