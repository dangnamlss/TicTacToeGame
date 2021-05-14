package Game.packets;

import java.io.Serializable;

public class EndGamePacket implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;

    private int winner;

    public EndGamePacket(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }
}
