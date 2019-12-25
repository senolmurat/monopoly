package game_elements;

import lombok.Getter;
import square.*;
import io.ReadSquares;
import io.Reader;
import player.Player;

@Getter
public class Board {
    private Square[] board;
    private Player[] players;
    private int size = 40;
    private int jailIndex = 10;
    private int goToJailIndex = 30;

    public Board(Player[] playerArray) {
        Reader reader = new Reader();
        board = new Square[size];
        players = playerArray;

        createRegularSquares();
        createStartSquare(reader.getPassingStartPrize());
        createExactJailSquare(jailIndex);
        createExactGoToJailSquare(goToJailIndex);
        createCardSquares();
        generatePropertySquares(board);
        generateTaxSquare(board);
        generateUtilitySquares(board);
        generateRandomGoToJailSquareIndex(reader.getNumberOfGoToJailSquare());

    }


    private void createRegularSquares() {
        for (int i = 0; i < size; i++) {
            board[i] = new RegularSquare("Square ", +i + 1);
        }
    }

    private void createStartSquare(int passingStartPrize) {
        board[0] = new StartSquare("Start", 1, passingStartPrize);
    }

    private void createExactJailSquare(int jailSquareIndex) {
        board[jailSquareIndex] = new JailSquare("Jail", jailSquareIndex + 1);
    }

    public void createExactGoToJailSquare(int goToJailIndex) {
        board[goToJailIndex] = new GoToJail("Go To Jail Square", goToJailIndex + 1, board[jailIndex]);
    }

    public void createCardSquares(){
        board[2] = new CardSquare("Community Chest" , 3 , players  , board);
        board[17] = new CardSquare("Community Chest" , 18 , players  , board);
        board[33] = new CardSquare("Community Chest" , 34 , players  , board);

        board[7] = new CardSquare("Chance" , 8 , players , board );
        board[22] = new CardSquare("Chance" , 23 , players , board );
        board[36] = new CardSquare("Chance" , 37 , players  , board);
    }


    private void goToJailSquareInitialization(int goToJailSquareIndex, Square[] board) {
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
    }

    private void generateRandomGoToJailSquareIndex(int numberOfGoToJailSquares) {
        for (int i = 1; i < numberOfGoToJailSquares; i++) {
            int randomNumberForGoToSquareIndex = (int) (Math.random() * 39) + 1;
            goToJailSquareInitialization(randomNumberForGoToSquareIndex, board);
        }
    }

    private void generateTaxSquare(Square[] board) {
        board[4] = new TaxSquare("Income Tax", 5, 200);
        board[38] = new TaxSquare("Super Tax", 39, 100);
    }

    private void generatePropertySquares(Square[] board) {
        ReadSquares readSquares = new ReadSquares();
        for (int i = 0; i < readSquares.getPropertySquaresList().size(); i++) {
            board[readSquares.getPropertySquaresList().get(i).getPosition() - 1] = readSquares.getPropertySquaresList().get(i);
        }
    }

    private void generateUtilitySquares(Square[] board) {
        ReadSquares readSquares = new ReadSquares();
        for (int i = 0; i < readSquares.getUtilitySquaresList().size(); i++) {
            board[readSquares.getUtilitySquaresList().get(i).getPosition() - 1] = readSquares.getUtilitySquaresList().get(i);
        }
    }


}
