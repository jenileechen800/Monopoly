package BoardSquares;

import MainGameObjects.Pawn;

import java.util.ArrayList;

public class Go extends BoardSquare {
    private int numTimesPassed;

    public Go() {
        super();
        resetGo();

        setName("Go");
    }

    public void passGo() {
        numTimesPassed++;
    }

    public void resetGo() {
        numTimesPassed = 0;
    }

    public int getNumTimesPassed(ArrayList<Pawn> pawns) {
        return numTimesPassed/pawns.size();
    }

    public void setNumTimesPassed(int numTimesPassed) {
        this.numTimesPassed = numTimesPassed;
    }
}
