package khosro;

import khosro.controller.GameController;
import khosro.views.MainPage;


public class Main {
    public static final int FPS = 99;
    private static int cf = 85;
    private static int cc = 85;
    private static int cp = 85;
    private static int f = 85;
    private static int p = 85;
    private static int l = 0, type = 1;
    public static Boolean card[] = {false, false, false, false, false};
    private static long start7;
    private static long start[] = {
            System.currentTimeMillis(),
            System.currentTimeMillis(),
            System.currentTimeMillis(),
            System.currentTimeMillis(),
            System.currentTimeMillis()};


    public static void main(String[] args) {

/*        Map map = new Map();
        GamePage gamePage = new GamePage();
        Zombies zombie = new Zombies();

        GameController gameHandler = new GameController(map, gamePage, zombie);*/

        MainPage mainPage = new MainPage();
        while (!mainPage.getRunGame()) {
            try {
                long start = System.currentTimeMillis();
                mainPage.render();
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);
            } catch (InterruptedException ignore) {
            }
        }

//        start7 = System.currentTimeMillis();
//        while (!gameHandler.getGameOver()) {
//            long start = System.currentTimeMillis();
//            gameHandler.render(cf, cc, cp, f, p, l);
//            if (!gameHandler.getMenu()) {
//                check2(gameHandler);
//                check();
//                level();
//            }
//        }


        mainPage.dispose();

//        gamePage.dispose();
        System.out.println("tamam2");
        System.exit(0);
    }

    private static void check2(GameController gameHandler) {
        try {
            if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals("SunFlower") && card[0]) {
                card[0] = false;
                if (cf == 0)
                    start[0] = System.currentTimeMillis();
                cf = 85;
                gameHandler.setPlant(null);
            }
            if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals("Peashooter") && card[1]) {
                card[1] = false;
                if (cp == 0)
                    start[1] = System.currentTimeMillis();
                cp = 85;
                gameHandler.setPlant(null);
            }
            if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals("SnowPea") && card[2]) {
                card[2] = false;
                if (f == 0)
                    start[2] = System.currentTimeMillis();
                f = 85;
                gameHandler.setPlant(null);
            }
            if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals("Cherry") && card[3]) {
                card[3] = false;
                if (cc == 0)
                    start[3] = System.currentTimeMillis();
                cc = 85;
                gameHandler.setPlant(null);
            }
            if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals("Potato") && card[4]) {
                card[4] = false;
                if (p == 0)
                    start[4] = System.currentTimeMillis();
                p = 85;
                gameHandler.setPlant(null);
            }
        } catch (NullPointerException ignore) {
        }

    }

    private static void level() {
        if (System.currentTimeMillis() - start7 > 3000 && l < 200) {
            l++;
            start7 = System.currentTimeMillis();
            System.out.println(l);
        }

    }

    private static void check() {
        cf = check3(cf, 90, 0, 0);

        cp = check3(cp, 100, 1, 1);

        if (type == 1)
            cc = check3(cc, 353, 3, 3);
        else cc = check3(cc, 530, 3, 3);

        p = check3(p, 350, 4, 4);

        if (type == 1)
            f = check3(f, 150, 2, 2);
        else f = check3(f, 350, 2, 2);
    }

    private static int check3(int i, int delay, int c, int s) {
        if (System.currentTimeMillis() - start[s] > delay && !card[c]) {
            i--;
            start[s] = System.currentTimeMillis();
        }
        if (i == 0)
            card[c] = true;
        return i;
    }
}
