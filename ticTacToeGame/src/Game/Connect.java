package Game;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Connect implements Runnable{
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private boolean isRunning;

    private Game game;

    public Connect(Game game, Socket socket) {
        this.game = game;

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        isRunning = true;

        while (isRunning) {
            try {
                Object object = inputStream.readObject();
                game.packetReceived(object);

            } catch (EOFException | SocketException e) {
                isRunning = false;
            } catch (ClassNotFoundException |  IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendPacket(Object object) {
        try {
            outputStream.reset();
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
    }
}
