package khosro.model.component.zombie;

import khosro.model.map.MapRow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Zombies implements Serializable {
    private ArrayList<Zombie> normZombies;
    private ArrayList<Zombie> coneHeadZombies;
    private ArrayList<Zombie> bucketHeadZombies;
    private Zombie z;

    public Zombies()  {
        createZombies();
    }

    private void createZombies() {
        normZombies = new ArrayList<>();
        coneHeadZombies = new ArrayList<>();
        bucketHeadZombies = new ArrayList<>();
        while (true) {
            for (int i = 0; i < 29; i++)
                switch (new Random().nextInt(3)) {
                    case 0:
                        normZombies.add(new NormZombie());
                        break;
                    case 1:
                        coneHeadZombies.add(new ConeHeadZombie());
                        break;
                    case 2:
                        bucketHeadZombies.add(new BucketHeadZombie());
                        break;
                }
            if (normZombies.size() > 2 && coneHeadZombies.size() > 2 && bucketHeadZombies.size() > 2)
                break;
            else {
                normZombies.clear();
                coneHeadZombies.clear();
                bucketHeadZombies.clear();
            }
        }
    }

    public Zombie checkConBede(int sunNum, ArrayList<MapRow> mapRows, int round) {
        int[] point = {0, 0, 0, 0, 0};
        //Thread thread = new Thread() {
        //public void run() {
        int max = -100;
        int max2 = -80;
        int k = 0;
        int j = 0;
        int min = checkMin(mapRows);
        int minPea = checkMinPea(mapRows);
        int minFreeze = checkMinFreeze(mapRows);
        int minPotato = checkMinPotato(mapRows);
        int maxSun = checkMaxSun(mapRows);
        System.out.println(maxSun);
        for (int i = 0; i < 5; i++) {
            if (mapRows.get(i).size() == min)
                point[i] = point[i] + min;
            else point[i] = point[i] - mapRows.get(i).size();
            if (mapRows.get(i).potatoNumber() == minPotato)
                point[i] = point[i] + minPotato;
            else point[i] = point[i] - mapRows.get(i).potatoNumber();
            if (mapRows.get(i).peaNumber() == minPea)
                point[i] = point[i] + minPea + 1;
            else point[i] = point[i] - mapRows.get(i).peaNumber();
            if (mapRows.get(i).freezeNumber() == minFreeze)
                point[i] = point[i] + minFreeze + 2;
            else point[i] = point[i] - mapRows.get(i).freezeNumber();
            if (mapRows.get(i).sunFlowerNumber() == maxSun) {
                point[i] = point[i] + maxSun + 1;
            } else {
                point[i] = point[i] + mapRows.get(i).sunFlowerNumber();
            }
            if (point[i] > max) {
                max = point[i];
                j = i;
            }
            if (point[i] < point[j] && point[i] > max2) {
                max2 = point[i];
                k = i;
            }
        }

        if (round == 1) {
            if (sunNum <= 150) {
                if (bucketHeadZombies.size() > 8)
                    return checkAndReturn(bucketHeadZombies, j, mapRows, k);

                if (coneHeadZombies.size() > 8)
                    return checkAndReturn(coneHeadZombies, j, mapRows, k);

            }
            if (sunNum <= 450)
                if (coneHeadZombies.size() > 8)
                    return checkAndReturn(coneHeadZombies, j, mapRows, k);
            if (normZombies.size() > 0)
                return checkAndReturn(normZombies, j, mapRows, k);

            if (coneHeadZombies.size() > 0)
                return checkAndReturn(coneHeadZombies, j, mapRows, k);

            if (bucketHeadZombies.size() > 0)
                return checkAndReturn(bucketHeadZombies, j, mapRows, k);

        }
        if (round == 2) {
            if (sunNum <= 200) {
                if (bucketHeadZombies.size() > 4)
                    return checkAndReturn(bucketHeadZombies, j, mapRows, k);

                if (coneHeadZombies.size() > 4)
                    return checkAndReturn(coneHeadZombies, j, mapRows, k);

            }
            if (sunNum <= 600 && coneHeadZombies.size() > 4)
                return checkAndReturn(coneHeadZombies, j, mapRows, k);
            if (normZombies.size() > 0)
                return checkAndReturn(normZombies, j, mapRows, k);

            if (coneHeadZombies.size() > 0)
                return checkAndReturn(coneHeadZombies, j, mapRows, k);

            if (bucketHeadZombies.size() > 0)
                return checkAndReturn(bucketHeadZombies, j, mapRows, k);

        }

        if (round == 3) {
            if (sunNum <= 150)
                if (bucketHeadZombies.size() > 0)
                    return checkAndReturn(bucketHeadZombies, j, mapRows, k);

            if (sunNum <= 250)
                if (coneHeadZombies.size() > 0)
                    return checkAndReturn(coneHeadZombies, j, mapRows, k);

            if (normZombies.size() > 0)
                return checkAndReturn(normZombies, j, mapRows, k);

            if (coneHeadZombies.size() > 0)
                return checkAndReturn(coneHeadZombies, j, mapRows, k);

            if (bucketHeadZombies.size() > 0)
                return checkAndReturn(bucketHeadZombies, j, mapRows, k);

        }
        return null;


        //  }
        //  };
        //   thread.start();
    }

    private Zombie checkAndReturn(ArrayList<Zombie> zombies, int j, ArrayList<MapRow> mapRows, int k) {
        z = zombies.get(zombies.size() - 1);
        if (mapRows.get(j).getMapHomes().get(8).getHaveZombie() || mapRows.get(j).getMapHomes().get(7).getHaveZombie())
            z.setRow(k);
        else
            z.setRow(j);
        zombies.remove(zombies.size() - 1);
        return z;
    }

    private int checkMin(ArrayList<MapRow> mapRows) {
        int min = 9;
        for (int i = 0; i < 5; i++)
            if (mapRows.get(i).size() < min)
                min = mapRows.get(i).size();

        return min;
    }

    private int checkMinPea(ArrayList<MapRow> mapRows) {
        int min = 9;
        for (int i = 0; i < 5; i++)
            if (mapRows.get(i).peaNumber() < min)
                min = mapRows.get(i).peaNumber();

        return min;
    }

    private int checkMinFreeze(ArrayList<MapRow> mapRows) {
        int min = 9;
        for (int i = 0; i < 5; i++)
            if (mapRows.get(i).freezeNumber() < min)
                min = mapRows.get(i).freezeNumber();

        return min;
    }

    private int checkMinPotato(ArrayList<MapRow> mapRows) {
        int min = 9;
        for (int i = 0; i < 5; i++)
            if (mapRows.get(i).potatoNumber() < min)
                min = mapRows.get(i).potatoNumber();

        return min;
    }

    private int checkMaxSun(ArrayList<MapRow> mapRows) {
        int max = 0;
        for (int i = 0; i < 5; i++)
            if (mapRows.get(i).sunFlowerNumber() > max)
                max = mapRows.get(i).sunFlowerNumber();

        return max;
    }

    @Override
    public String toString() {
        return "Zombies{" +
                "normZombies=" + normZombies +
                ", coneHeadZombies=" + coneHeadZombies +
                ", bucketHeadZombies=" + bucketHeadZombies +
                ", z=" + z +
                '}';
    }
}
