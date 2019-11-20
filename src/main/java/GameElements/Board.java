package GameElements;

import Square.*;
import IO.ReadPropertySquare;
import org.json.simple.JSONObject;

public class Board {

    private Square[] board;
    private int size = 40;
    private int taxSquareNumber;
    private int taxAmount;


    public Board(int taxSquareNumber, int taxAmount, int goSquare_money) {
        board = new Square[size];
        this.taxAmount = taxAmount;
        this.taxSquareNumber = taxSquareNumber;


        //Setting positions and names for all squares
        for (int i = 0; i < size; i++) {
            board[i] = new RegularSquare("Square ", i + 1);
        }

        generatePropertySquares(board);
        generateRandomTaxSquareIndex(taxSquareNumber);


        //****************SQUARES********************************
        //Initializing start square
        board[0] = new StartSquare("Start", 1, goSquare_money);

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

    public Square[] taxSquareInitialization(int taxSquareIndex, Square[] board) {
        if (!(board[taxSquareIndex] instanceof TaxSquare)) {
            board[taxSquareIndex] = new TaxSquare("Tax Square", taxSquareIndex + 1, taxAmount);
        } else {

            while ((board[taxSquareIndex] instanceof TaxSquare)) {
                taxSquareIndex++;
                if (taxSquareIndex == size)
                    taxSquareIndex = 1;
            }
            board[taxSquareIndex] = new TaxSquare("Tax Square", taxSquareIndex + 1, taxAmount);
        }
        return board;
    }

    public void generateRandomTaxSquareIndex(int numberOfTaxSquares) {
        for (int i = 0; i < numberOfTaxSquares; i++) {
            int randomNumberForTaxSquareIndex = (int) (Math.random() * 39) + 1;
            taxSquareInitialization(randomNumberForTaxSquareIndex, board);
        }
    }

    public void generatePropertySquares(Square[] board) {
        ReadPropertySquare readPropertySquare = new ReadPropertySquare();
        for (int i = 0; i < readPropertySquare.getPropertySquaresList().size() - 1; i++) {
            board[readPropertySquare.getPropertySquaresList().get(i).getPosition()] = readPropertySquare.getPropertySquaresList().get(i);
        }


    }

}
