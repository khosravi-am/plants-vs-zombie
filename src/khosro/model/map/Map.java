package khosro.model.map;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
    private ArrayList<MapRow> mapRows;
    private ImageIcon background, cardCherry, cardFreeze, cardPea, cardFlower, cardPotato, shovel, level, menuBar, menu;
    private int sunNum;

    public Map() {
        setImage();
        mapRows = new ArrayList<>();
        setMapRows(mapRows);
        setSunNum(50);
    }

    private void setMapRows(ArrayList<MapRow> mapRows) {
        for (int i = 0; i < 5; i++)
            mapRows.add(new MapRow(i));
    }

    public void setImage() {
            background = new ImageIcon("./src/khosro/model/res/bg.png");
            cardCherry = new ImageIcon("./src/khosro/model/res/card_cherrybomb.png");
            cardFreeze = new ImageIcon("./src/khosro/model/res/card_freezepeashooter.png");
            cardPea = new ImageIcon("./src/khosro/model/res/card_peashooter.png");
            cardFlower = new ImageIcon("./src/khosro/model/res/card_sunflower.png");
            cardPotato = new ImageIcon("./src/khosro/model/res/card_wallnut.png");
            shovel = new ImageIcon("./src/khosro/model/res/shovel.png");
            level = new ImageIcon("./src/khosro/model/res/level.png");
            menuBar = new ImageIcon("./src/khosro/model/res/menu_bar.png");
            menu = new ImageIcon("./src/khosro/model/res/menu.png");

    }

    public ArrayList<MapRow> getMapRows() {
        return mapRows;
    }

    public int getSunNum() {
        return sunNum;
    }

    public ImageIcon getBackground() {
        return background;
    }

    public ImageIcon getShovel() {
        return shovel;
    }

    public ImageIcon getCardCherry() {
        return cardCherry;
    }

    public ImageIcon getCardFreeze() {
        return cardFreeze;
    }

    public ImageIcon getCardFlower() {
        return cardFlower;
    }

    public ImageIcon getCardPea() {
        return cardPea;
    }

    public ImageIcon getCardPotato() {
        return cardPotato;
    }

    public ImageIcon getLevel() {
        return level;
    }

    public void setShovel(ImageIcon shovel) {
        this.shovel = shovel;
    }

    public void setSunNum(int sunNum) {
        this.sunNum = sunNum;
    }

    public ImageIcon getMenu() {
        return menu;
    }

    public ImageIcon getMenuBar() {
        return menuBar;
    }

    @Override
    public String toString() {
        return "Map{" +
                "mapRows=" + mapRows +
                ", background=" + background +
                ", cardCherry=" + cardCherry +
                ", cardFreeze=" + cardFreeze +
                ", cardPea=" + cardPea +
                ", cardFlower=" + cardFlower +
                ", cardPotato=" + cardPotato +
                ", shovel=" + shovel +
                ", level=" + level +
                ", menuBar=" + menuBar +
                ", menu=" + menu +
                ", sunNum=" + sunNum +
                '}';
    }
}
