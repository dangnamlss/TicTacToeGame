package Game.packets;

import java.io.Serializable;

public class UpdatePacket implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;

    private int[][] newField;

    private int currentPlay;

    public UpdatePacket(int[][] newField, int currentPlay) {
        this.newField = newField;
        this.currentPlay = currentPlay;
    }

    public int[][] getNewField() {
        return newField;
    }


    public int getCurrentPlay() {
        return currentPlay;
    }


}
