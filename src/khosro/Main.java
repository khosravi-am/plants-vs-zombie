package khosro;

import khosro.controller.GameController;
import khosro.controller.Save;
import khosro.model.User;
import khosro.model.component.zombie.Zombies;
import khosro.model.map.Map;
import khosro.views.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {
    public static final int FPS = 99;
    private static int cf = 85;
    private static int cc = 85;
    private static int cp = 85;
    private static int f = 85;
    private static int p = 85;
    public static Boolean mainP = true, game = false;
    private static String username = "khosro";
    public static String filename = "";
    public static Boolean b = false;
    private static int l = 0, type = 1;
    public static Boolean card[] = {false, false, false, false, false}, game2 = false, main2 = false;
    private static long start7;
    private static long start[] = {System.currentTimeMillis(), System.currentTimeMillis(), System.currentTimeMillis(), System.currentTimeMillis(), System.currentTimeMillis()};


    public static void main(String[] args) {

        MainPage mainPage = null;
        mainPage = new MainPage();
        while (!mainPage.getRunGame()) {
            try {
                long start = System.currentTimeMillis();
                mainPage.render();
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);

            } catch (InterruptedException ex) {
            }
        }
        mainP = false;
        // Initialize the global thread-pool
        // ThreadPool.init();

        // Show the game menu ...

        // After the player clicks 'PLAY' ...
        GamePage gamePage = null;
        GameController gameHandler = null;

        User user = null;
        /*gamePage = new GamePage();
        if (b)
            user = Save.loadGame(filename);
        else
            user = new User(username);

        if (game = true) {
            gameHandler = new GameController(gamePage, user);
            game = false;
            game2 = true;
        }*/
        start7 = System.currentTimeMillis();
        while (true) {
            try {
                long start = System.currentTimeMillis();
                if (game) {
                    mainPage.dispose();
                    mainPage = null;
                    game = false;
                    gamePage = new GamePage();
                    if (b) {
                        user = Save.loadGame(filename);
                        b=false;
                    }
                    else
                        user = new User(username);
                    gameHandler = new GameController(gamePage, user);

                    game2 = true;
                    main2 = false;
                    mainP=false;
                }

                //else mainPage.setVisible(false);
                if (game2) {
                    gameHandler.render(cf, cc, cp, f, p, l);
                    if (!gameHandler.getMenu()) {

                        check2(gameHandler);
                        check();
                        level();
                    }
                }

                if (mainP) {
                    gamePage.dispose();
                    gameHandler = null;
                    user = null;
                    mainPage = new MainPage();
                    mainP = false;
                    main2 = true;
                    game2 = false;
                }
                if (main2) {
                    mainPage.render();
                   // game2=false;
                }


                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);

                if (gameHandler.getGameOver())
                    break;

            } catch (InterruptedException ex) {

            } catch (NullPointerException e) {

            }
        }


        gamePage.dispose();
        System.out.println("tamam2");
        System.exit(0);
    }

    private static int check4(GameController gameHandler, String str, int i, int j, int k) {
        if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals(str) && card[i]) {
            card[i] = false;
            if (k == 0)
                start[j] = System.currentTimeMillis();
            k = 85;
            gameHandler.setPlant(null);
        }
        return k;
    }

    private static void check2(GameController gameHandler) {
        try {

            cf = check4(gameHandler, "SunFlower", 0, 0, cf);
            cp = check4(gameHandler, "Peashooter", 1, 1, cp);
            f = check4(gameHandler, "SnowPea", 2, 2, f);
            cc = check4(gameHandler, "Cherry", 3, 3, cc);
            p = check4(gameHandler, "Potato", 4, 4, p);

        } catch (NullPointerException e) {

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
