package Game.packets;

import java.io.Serializable;

public class ClientPlayPacket implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;

    private int x;
    private int y;

    public ClientPlayPacket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
