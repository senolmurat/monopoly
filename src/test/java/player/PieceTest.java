package player;

import game_elements.Board;
import game_elements.Die;
import org.junit.Test;
import player.Player;

import static org.junit.Assert.*;


public class PieceTest {

    private Player[] players;
    Piece testPiece = new Piece();
    Die testDie = new Die();
    Board testBoard = new Board(players);


    int[] diceTestArray = testDie.getFaces();
    int sumOfFaces = diceTestArray[0] + diceTestArray[1];


    @Test
    public int move(int sumOfFaces_M, Board testBoard_M) {
        sumOfFaces_M = sumOfFaces;
        testBoard_M = testBoard;
        int returnVal = testPiece.move(sumOfFaces_M, testBoard_M);
        assertTrue(testPiece.getPosition().getPosition() <= 40 && testPiece.getPosition().getPosition() >= 1);

        return returnVal;
    }


}
