import GameElements.Board;
import GameElements.Dice;
import Player.*;
import org.junit.Test;

import static org.junit.Assert.*;


public class PieceTest {

    Piece testPiece = new Piece();
    Dice testDice = new Dice();
    Board testBoard = new Board(15, 150, 200);


    int[] diceTestArray = testDice.getFaces();
    int sumOfFaces = diceTestArray[0] + diceTestArray[1];


    @Test
    public int move(int sumOfFaces_M, Board testBoard_M) {
        sumOfFaces_M = sumOfFaces;
        testBoard_M = testBoard;
        int returnVal = testPiece.move(sumOfFaces_M, testBoard_M);
        assertTrue(testPiece.getPosition() <= 40 && testPiece.getPosition() >= 1);

        return returnVal;
    }


}
