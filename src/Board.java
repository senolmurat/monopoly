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

        generateRandomTaxSquareIndex(taxSquareNumber);

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

}
