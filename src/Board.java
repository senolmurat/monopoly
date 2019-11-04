import java.util.Arrays;
import java.util.Scanner;

public class Board {

    private Square[] board;
    private int size = 40;
    private int taxSquareNumber;
    private int taxAmount;


    public Board(int taxSquareNumber, int taxAmount) {
        board = new Square[size];
        this.taxAmount = taxAmount;
        this.taxSquareNumber = taxSquareNumber;


        //Setting positions and names for all squares
        for (int i = 0; i < size; i++) {
            board[i] = new Square("Square ", i + 1);
            board[i].setName("Square");
            board[i].setPosition(i + 1);
        }

        Scanner input = new Scanner(System.in);
        taxSquareInitialization(generateRandomTaxSquareIndex(taxSquareNumber), board);


        //****************SQUARES********************************
        //Initializing start square
        board[0] = new StartSquare("Start", 1);

        //Initializing Jail Square
        board[9] = new JailSquare("Jail", 10);


        //****************SQUARES********************************
    }

    public Square[] getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }

    public Square[] taxSquareInitialization(int[] randomNumberForTaxSquareIndex, Square[] board) {
        Arrays.sort(randomNumberForTaxSquareIndex);
        for (int i = 0; i < randomNumberForTaxSquareIndex.length; i++) {
            if (randomNumberForTaxSquareIndex[i] != 9) {
                board[randomNumberForTaxSquareIndex[i]] = new TaxSquare("Tax Square", randomNumberForTaxSquareIndex[i] + 1, 100);
            }

        }
        return board;
    }

    public int[] generateRandomTaxSquareIndex(int numberOfTaxSquares) {
        int[] randomTaxSquareIndex = new int[numberOfTaxSquares];
        for (int i = 0; i < numberOfTaxSquares; i++) {
            int randomNumberForTaxSquareIndex = (int) (Math.random() * 39) + 1;
            randomTaxSquareIndex[i] = randomNumberForTaxSquareIndex;
        }
        return randomTaxSquareIndex;
    }

}
