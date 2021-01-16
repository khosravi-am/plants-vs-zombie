package khosro;

import khosro.controller.GameHandler;
import khosro.model.map.Map;
import khosro.views.GamePage;

import java.awt.*;

public class Main {
    public static final int FPS = 50;

    public static void main(String[] args) {
        // Initialize the global thread-pool
        // ThreadPool.init();

        // Show the game menu ...

        // After the player clicks 'PLAY' ...
        /* GameFrame frame = new GameFrame("Simple Ball !");
         frame.setLocationRelativeTo(null); // put frame at center of screen
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
         frame.initBufferStrategy();
         // Create and execute the game-loop
         GameLoop game = new GameLoop(frame);
         game.init();
        */// ThreadPool.execute(this);
// and the game starts ...

        EventQueue.invokeLater(() -> {
            Map map = new Map();
            GamePage gamePage = new GamePage();
            GameHandler gameHandler = new GameHandler(map, gamePage);
            boolean gameOver = false;
            while (!gameOver) {
                try {
                    long start = System.currentTimeMillis();
                    //
                    gameHandler.render();
                    //gameHandler.render2();
                    gameHandler.update();
                    //canvas.render(state);
                    gameOver = gameHandler.gameOver;
                    //
                    long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                    if (delay > 0)
                        Thread.sleep(delay);
                } catch (InterruptedException ignored) {
                }
            }
            //canvas.render(state);
        });


    }
}