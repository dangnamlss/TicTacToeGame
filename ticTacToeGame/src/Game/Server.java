package Game;

import Game.packets.ClientPlayPacket;
import Game.packets.EndGamePacket;
import Game.packets.UpdatePacket;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class Server extends Game{

    private ServerSocket serverSocket;
    private Socket socket;

    private Connect connect;

    public Server() {
        super(player_1);
        try{
            serverSocket = new ServerSocket(Game.PORT);
            socket = serverSocket.accept();
            connect = new Connect(this, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void update(int x, int y) {
        if(fields[x][y] == Game.blank) {
            fields[x][y] = currentPlay;

            if(currentPlay == Game.player_1) {
                currentPlay = Game.player_2;
            } else if(currentPlay == Game.player_2) {
                currentPlay = Game.player_1;
            }

            connect.sendPacket(new UpdatePacket(fields, currentPlay));
            gameWindow.repaint();

            int winner = checkWin();
            System.out.println(winner);

            if(winner != Game.blank) {
                endGame(winner);
            }
            else if(winner == Game.blank){
                int draw_count = 0;
                for (int a = 0; a < 3; a++) {
                    for(int b = 0; b < 3; b++) {
                        if(fields[a][b] == Game.blank) {
                            draw_count++;
                        }
                    }
                }
                if (draw_count == 9){
                    endGame(winner);
                }
            }
        }

    }

    private int checkWin() {
        for(int winner = Game.player_1; winner <= Game.player_2; winner++) {
            for(int y = 0; y < 3; y++) {
                int player_count = 0;
                for(int x = 0; x < 3; x++) {
                    if(fields[x][y] == winner) {
                        player_count++;
                    }
                }
                if(player_count == 3) {
                    return winner;
                }
            }

            for(int x = 0; x < 3; x++) {
                int player_count = 0;
                for(int y = 0; y < 3; y++) {
                    if(fields[x][y] == winner) {
                        player_count++;
                    }
                }
                if(player_count == 3) return winner;
            }

            int player_count = 0;
            for(int coordinate = 0; coordinate < 3; coordinate++) {
                if(fields[coordinate][coordinate] == winner) {
                    player_count++;                                                //Check theo đường chéo
                }
            }

            if(player_count == 3) return winner;

            player_count = 0;
            for(int coordinate = 0; coordinate < 3; coordinate++) {
                if(fields[2-coordinate][coordinate] == winner) {
                    player_count ++;
                }
            }
            if(player_count == 3) return winner;
        }
        return Game.blank;
    }

    private void endGame (int winner) {
        showWinner(winner);

        connect.sendPacket(new EndGamePacket(winner));
    }

    @Override
    public void inputReceived(int x, int y) {
        if(myTurn()) {
            update(x, y);
        }
    }

    @Override
    public void packetReceived(Object object) {
        if(object instanceof ClientPlayPacket) {
            ClientPlayPacket clientPlayPacket = (ClientPlayPacket)object;
            update(clientPlayPacket.getX(), clientPlayPacket.getY());
        }

    }

    @Override
    public void close() {
        try {
            connect.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
