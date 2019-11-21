package Player;

import GameElements.Board;
import IO.Reader;

public class Piece {
    private int position = 0;
    boolean isPassedFromStart;
    private Reader reader = new Reader();

    public Piece() {

    }

    public int move(int sumOfFaces, Board board) {

        isPassedFromStart = false;

        if ((sumOfFaces + position) / board.getSize() == 1) {
            isPassedFromStart = true;
        }

        position = (position + sumOfFaces) % board.getSize();

        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public boolean isPassedFromStart() {
        return isPassedFromStart;
    }

    public void setPassedFromStart(boolean passedFromStart) {
        isPassedFromStart = passedFromStart;
    }
}
