package GameElements;

import Square.*;
import IO.ReadSquares;
import IO.Reader;

public class Board {
    private Square[] board;
    private int size = 40;
    private int jailIndex = 10;
    Reader reader = new Reader();

    public Board() {
        board = new Square[size];
        createRegularSquares();
        createStartSquare(reader.getPassingStartPrize());
        createExactJailSquare(jailIndex);
        generatePropertySquares(board);
        generateTaxSquare(board);
        generateUtilitySquares(board);
        generateRandomGoToJailSquareIndex(reader.getNumberOfGoToJailSquare());

    }

    public Square[] getBoard() {
        return board;
    }

    public void createRegularSquares(){
        for (int i = 0; i<size ; i++) {
            board[i] = new RegularSquare("Square ", + i+1);
        }
    }

    public void createStartSquare(int passingStartPrize){
        board[0] = new StartSquare("Start", 1, passingStartPrize);
    }
    public void createExactJailSquare(int jailSquareIndex){
        board[jailSquareIndex] = new JailSquare("Jail", jailSquareIndex + 1);
    }

    public int getSize() {
        return size;
    }

    public Square[] goToJailSquareInitialization(int goToJailSquareIndex, Square[] board) {
        if ((board[goToJailSquareIndex] instanceof Purchasable)) {
            board[goToJailSquareIndex] = new GoToJail("Go To Jail Square", goToJailSquareIndex + 1, board[jailIndex]);
        } else {
            while (!(board[goToJailSquareIndex] instanceof Purchasable)) {
                goToJailSquareIndex++;
                if (goToJailSquareIndex == size)
                    goToJailSquareIndex = 1;
            }
            board[goToJailSquareIndex] = new GoToJail("Go To Jail Square", goToJailSquareIndex + 1, board[jailIndex]);
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
        board[38] = new TaxSquare("Super Tax", 39, 100);
    }

    public void generatePropertySquares(Square[] board) {
        ReadSquares readSquares = new ReadSquares();
        for (int i = 0; i < readSquares.getPropertySquaresList().size(); i++) {
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
