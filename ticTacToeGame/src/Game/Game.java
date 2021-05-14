package Game;

import Game.gui.GameWindow;
import Game.gui.Window;

import javax.swing.*;

public abstract class Game {
    private Window window;
    protected GameWindow gameWindow;

    public static final int PORT = 2502;

    public static final int WIDTH = 600, HEIGHT = 600;
    public static final String title = "Tic Tac Toe Game";
    public static final int FIELD_WIDTH = WIDTH / 3, FIELD_HEIGHT = HEIGHT / 3;

    protected int[][] fields;
    protected int currentPlay;
    protected int thisPLayer;

    public static final int blank = 0, player_1 = 1, player_2 = 2;

    public Game(int thisPlayer) {
        this.thisPLayer = thisPlayer;
        window = new Window(this,title, WIDTH, HEIGHT);
        gameWindow = new GameWindow(this);
        fields = new int[3][3];
        window.add(gameWindow);
        window.setVisible(true);
        currentPlay = Game.player_1;
    }

    protected void showWinner(int winner) {
        if(winner == blank) {
            JOptionPane.showMessageDialog(null, "Game's draw");
        } else {
            JOptionPane.showMessageDialog(null, "The winner is player " + winner + '!');
        }

    }
    protected boolean myTurn () {
        if(thisPLayer == currentPlay) {
            return true;
        }
        return false;
    }

    public abstract void inputReceived(int x, int y);
    public abstract void close();
    public abstract void packetReceived(Object object);

    public int[][] getFields() {
        return fields;
    }
}
