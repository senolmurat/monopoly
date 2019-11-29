package GameElements;

import Square.*;
import IO.ReadSquares;
import IO.Reader;

public class Board {

    private Square[] board;
    private int size = 40;
    private int taxAmount;
    private int jailIndex = 9;
    Reader reader = new Reader();

    public Board(int passingStartPrize) {
        board = new Square[size];
        this.taxAmount = taxAmount;


        //Setting positions and names for all squares
        for (int i = 0; i < size; i++) {
            board[i] = new RegularSquare("Square ", i + 1);
        }

        generatePropertySquares(board);
        generateRandomGoToJailSquareIndex(reader.getNumberOfGoToJailSquare());
        generateTaxSquare(board);
        generateUtilitySquares(board);


        //****************SQUARES********************************
        //Initializing start square
        board[0] = new StartSquare("Start", 1, passingStartPrize);

        //Initializing Jail Square
        board[jailIndex] = new JailSquare("Jail", jailIndex + 1);

        //****************SQUARES********************************
    }

    public Square[] getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }

     public Square[] goToJailSquareInitialization(int goToJailSquareIndex, Square[] board) {
         if (!(board[goToJailSquareIndex] instanceof Purchasable)) {
             board[goToJailSquareIndex] = new GoToJail("Go To Jail Square", goToJailSquareIndex + 1);
         } else {

             while (!(board[goToJailSquareIndex] instanceof Purchasable)) {
                 goToJailSquareIndex++;
                 if (goToJailSquareIndex == size)
                     goToJailSquareIndex = 1;
             }
             board[goToJailSquareIndex] = new GoToJail("Go To Jail Square", goToJailSquareIndex + 1);
         }
         return board;
     }

     public void generateRandomGoToJailSquareIndex(int numberOfGoToJailSquares) {
         for (int i = 0; i < numberOfGoToJailSquares; i++) {
             int randomNumberForGoToSquareIndex = (int) (Math.random() * 39) + 1;
             goToJailSquareInitialization(randomNumberForGoToSquareIndex, board);
         }
     }
    public void generateTaxSquare(Square[] board) {
        board[4] = new TaxSquare("Income Tax", 5, 200);
        board[37] = new TaxSquare("Super Tax", 38, 100);
    }

    public void generatePropertySquares(Square[] board) {
        ReadSquares readSquares = new ReadSquares();
        for (int i = 0; i < readSquares.getPropertySquaresList().size() - 1; i++) {
            board[readSquares.getPropertySquaresList().get(i).getPosition() - 1] = readSquares.getPropertySquaresList().get(i);
        }
    }

    public void generateUtilitySquares(Square[] board) {
        ReadSquares readSquares = new ReadSquares();
        for (int i = 0; i < readSquares.getUtilitySquaresList().size() - 1; i++) {
            board[readSquares.getUtilitySquaresList().get(i).getPosition() - 1] = readSquares.getUtilitySquaresList().get(i);
        }
    }

    public int getJailIndex() {
        return jailIndex;
    }

}
