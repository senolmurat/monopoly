package player;

import game.elements.Board;
import square.Square;

public class Piece {
    private Square position = null;
    private boolean isPassedFromStart;

    public Piece() {

    }

    public int move(int sumOfFaces, Board board) {

        isPassedFromStart = (sumOfFaces + position.getPosition()) / board.getSize() == 1;

        position = board.getBoard()[(position.getPosition() + sumOfFaces - 1) % board.getSize()];

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
