package Game;

import Game.packets.ClientPlayPacket;
import Game.packets.EndGamePacket;
import Game.packets.UpdatePacket;

import java.io.IOException;
import java.net.Socket;

public class Client extends Game{

    private Socket socket;
    private Connect connect;

    public Client()  {
        super(Game.player_2);
        try {
            socket = new Socket("localhost", Game.PORT);
            connect = new Connect(this, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inputReceived(int x, int y) {
        if(myTurn()) {
            connect.sendPacket(new ClientPlayPacket(x, y));
        }
    }

    @Override
    public void packetReceived(Object object) {

        if(object instanceof UpdatePacket) {
            UpdatePacket updatePacket = (UpdatePacket) object;
            fields = updatePacket.getNewField();
            currentPlay = updatePacket.getCurrentPlay();
        } else if(object instanceof EndGamePacket) {
            EndGamePacket endGamePacket = (EndGamePacket) object;
            showWinner(endGamePacket.getWinner());
        }
        gameWindow.repaint();
    }


    @Override
    public void close() {
        try{
            connect.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
