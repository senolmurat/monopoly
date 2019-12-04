package Player;

import GameElements.Board;
import IO.Reader;
import Square.Square;

public class Piece {
    private Square position = null;
    boolean isPassedFromStart;
    private Reader reader = new Reader();

    public Piece() {

    }

    public int move(int sumOfFaces, Board board) {

        isPassedFromStart = false;

        if ((sumOfFaces + position.getPosition()) / board.getSize() == 1) {
            isPassedFromStart = true;
        }

        position = board.getBoard()[(position.getPosition() + sumOfFaces) % board.getSize()];

        return position.getPosition();
    }

    public void setPosition(Square position) {
        this.position = position;
    }

    public Square getPosition() {
        return position;
    }

    public boolean isPassedFromStart() {
        return isPassedFromStart;
    }

    public void setPassedFromStart(boolean passedFromStart) {
        isPassedFromStart = passedFromStart;
    }
}
