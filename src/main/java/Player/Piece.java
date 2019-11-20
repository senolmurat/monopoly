package Player;

import GameElements.Board;
import IO.Reader;
import Square.*;
public class Piece {
    private int position = 0;
    private Reader reader = new Reader();

    public Piece() {

    }

    public int move (int sumOfFaces, Board board) {
        int returnVal = 0;

        if((sumOfFaces + position) / board.getSize() == 1){
            returnVal += ((StartSquare)(board.getBoard()[0])).getPassMoney();
        }

        position = (position + sumOfFaces) % board.getSize();

        if(board.getBoard()[position] instanceof TaxSquare)
            returnVal -= ((TaxSquare)(board.getBoard()[position])).getTax();

        return returnVal;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
