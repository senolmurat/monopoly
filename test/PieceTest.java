import org.junit.Test;

import static org.junit.Assert.*;


public class PieceTest {

    Piece testPiece = new Piece();
    Dice testDice = new Dice();
    Reader testReader = new Reader();
    Board testBoard = new Board(testReader.getNumberOfTaxSquare(), testReader.getTaxAmount());


    int[] diceTestArray = testDice.getFaces();
    int sumOfFaces = diceTestArray[0] + diceTestArray[1];

    @Test
    public int move(int sumOfFaces, Board testBoard) {
        int returnVal = testPiece.move(sumOfFaces, testBoard);
        assertTrue(testPiece.getPosition() <= 40 && testPiece.getPosition() >= 1);

        return returnVal;
    }


}
