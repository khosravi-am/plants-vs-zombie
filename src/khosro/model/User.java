package khosro.model;

import khosro.model.component.zombie.Zombies;
import khosro.model.map.Map;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private Zombies zombie;
    private Map map;

    public User(String userName) {
        this.userName = userName;

    }
}
