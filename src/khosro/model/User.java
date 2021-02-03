package khosro.model;

import khosro.model.component.zombie.Zombies;
import khosro.model.map.Map;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class User implements Serializable {
    private String userName;
    private Map map;
    private Zombies zombies;
    private int score = 0;

    public User(String userName) {
        this.userName = userName;
        map = new Map();
        zombies = new Zombies();

    }

    public User(String userName, Map map, Zombies zombies, int score) {
        this.userName = userName;
        this.map = map;
        this.zombies = zombies;
        this.score = score;
    }

    public void connectionAndSend(String score) {

    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setZombies(Zombies zombies) {
        this.zombies = zombies;
    }


    public Map getMap() {
        return map;
    }

    public String getUserName() {
        return userName;
    }

    public Zombies getZombies() {
        return zombies;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", map=" + map +
                ", zombies=" + zombies +
                ", score=" + score +
                '}';
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */

    public Runnable run(User user) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("localhost", 5000);
                    Scanner scanner = new Scanner(socket.getInputStream());
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    System.out.println("client: start sending");
                    String strToSend = "";
                    while (!strToSend.equals("done")) {
                        outputStream.writeBytes(String.valueOf(user.getUserName() + "," + user.getScore()));
                        if (scanner.hasNext())
                            System.out.println(strToSend = scanner.nextLine());
                    }
                    System.out.println("client: end sending");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        return runnable;
    }
}
