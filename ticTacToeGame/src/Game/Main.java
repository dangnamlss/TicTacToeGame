package Game;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int side = Integer.parseInt(JOptionPane.showInputDialog("1 for X| 2 for O"));
        switch (side) {
            case 1:
                new Server();
                break;
            case 2:
                new Client();
                break;
        }
    }
}
