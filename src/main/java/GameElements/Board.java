package GameElements;

import Square.*;
import IO.ReadSquares;

public class Board {

    private Square[] board;
    private int size = 40;
    private int taxAmount;


    public Board(int goSquare_money) {
        board = new Square[size];
        this.taxAmount = taxAmount;


        //Setting positions and names for all squares
        for (int i = 0; i < size; i++) {
            board[i] = new RegularSquare("Square ", i + 1);
        }

        generatePropertySquares(board);
        // generateRandomTaxSquareIndex(taxSquareNumber);
        generateTaxSquare(board);
        generateUtilitySquares(board);


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

    /* public Square[] taxSquareInitialization(int taxSquareIndex, Square[] board) {
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
     }*/
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
