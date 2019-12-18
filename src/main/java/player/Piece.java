package player;

import game_elements.Board;
import lombok.Getter;
import lombok.Setter;
import square.Square;

@Getter
@Setter
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

}
