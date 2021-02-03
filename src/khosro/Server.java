package khosro;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

/**
 * server side. get a line in each turn , and returns all lines received to the client
 */
public class Server {
    private static HashMap<String, String> userScore = null;
    private static HashMap<InetAddress, String> userIp = null;

    public static void main(String[] args) {
        userScore = loadUsers();
        userIp = loadUserIp();
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new RunServer(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<InetAddress, String> loadUserIp() {
        HashMap<InetAddress, String> userIp = null;
        try {


            FileInputStream fi = new FileInputStream(new File("./userIp.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            userIp = (HashMap<InetAddress, String>) oi.readObject();
            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return userIp;

    }

    private static HashMap<String, String> loadUsers() {

        HashMap<String, String> userScore = null;
        try {


            FileInputStream fi = new FileInputStream(new File("./userScore.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            userScore = (HashMap<String, String>) oi.readObject();
            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return userScore;
    }

    private static class RunServer implements Runnable {

        Socket socket;

        /**
         * simple constructor.
         *
         * @param socket socket that server accepted.
         */
        public RunServer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(socket.getInputStream());
                String currentString;
                StringBuilder stringBuilder = new StringBuilder();
                System.out.println("start");
                while (!(currentString = scanner.nextLine()).equals("over")) {
                    if (currentString.equals("new"))
                        outputStream.writeBytes(userIp.get(socket.getInetAddress()));
                    else {
                        String[] str = currentString.split(",");
                        if (str.length == 1)
                            userIp.put(socket.getInetAddress(), str[0]);
                        else {
                            userScore.computeIfPresent(str[0], (key, oldValue) -> String.valueOf(Integer.valueOf(oldValue) + Integer.valueOf(str[1])));
                            userScore.computeIfAbsent(str[0], key -> str[1]);
                            save();
                            stringBuilder.append(currentString);
                            System.out.println("recived");
                            outputStream.writeBytes(stringBuilder.toString() + "\n");
                            System.out.println("send");
                        }
                    }
                }
                System.out.println("end");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void save() {
            try {
                File file = new File("./userScore.txt");

                FileOutputStream f = new FileOutputStream(file, false);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(userScore);
                System.out.println("File already exists.");

                o.close();
                f.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public Boolean check(String str) {
            if (userScore.containsKey(str))
                return false;
            else return true;
        }

        public void setNewUser(String userName) {
            userScore.put(userName, "0");
        }

        public HashMap<String, String> getUsers() {
            return userScore;
        }

    }
}
