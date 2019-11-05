import java.util.Scanner;

public class Game {

    private int cycleCounter = 1;

    private Game() {

    }

    public void startTheGame() throws  Exception {
        Scanner input = new Scanner(System.in);
        Reader reader = new Reader();

        int numberOfPlayers = reader.getNumberOfPlayers();
        int remainingPlayers = numberOfPlayers; //Players still in the game

        while (numberOfPlayers < 2 || numberOfPlayers > 8) {
            System.out.println("Number of player is not valid, please enter a number between 2 and 8 : ");
            numberOfPlayers = input.nextInt();
        }

        Board board = new Board(reader.getNumberOfTaxSquare(), reader.getTaxAmount());
        Information info = new Information();
        Dice dice = new Dice();
        Player[] players = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++)
            players[i] = new Player(i, reader.getNames()[i], reader.getStartingMoney(), dice);

        sortPlayers(players, dice);

        while(remainingPlayers > 1) {
            String squareType = "";
            System.out.println("\nCYCLE " + cycleCounter);

            for(int i = 0; i < numberOfPlayers; i++) {
                if(!players[i].isBankrupt()) {
                    if(players[i].getDoubleDiceCounter() < 3) {

                        int size = board.getSize();
                        info.infoMessageBeforeTossDie(players[i], squareType);
                        players[i].tossDie(dice, true, board);

                        int position = players[i].getPosition();
                        if(board.getBoard()[position] instanceof TaxSquare){
                            squareType = "(Tax Square)";
                        }
                        else if(board.getBoard()[position] instanceof StartSquare){
                            squareType = "(Start Square)";
                        }
                        else {
                            squareType = "";
                        }

                        info.infoMessageAfterTossDie(players[i], dice, squareType);
                        players[i].nextTurn();

                        if(players[i].isBankrupt()) {
                            players[i].setBankrupt(true);
                            remainingPlayers--;
                            System.out.println("\n" + players[i].getName() + " is bankrupt!\n");
                            if(remainingPlayers == 1) break; //This one is for checking after a player bankrupt
                            //if there is one player left
                        }

                        if(dice.isDouble()) {
                            players[i].setDoubleDiceCounter(players[i].getDoubleDiceCounter() + 1);
                            i--;
                        }
                    }

                    else {
                        players[i].setDoubleDiceCounter(0);
                    }
                }
            }
            info.infoBasedOnBalance(players, board);
            cycleCounter++;
        }

        int i;

        for(i = 0; i < numberOfPlayers; i++)
            if(!players[i].isBankrupt()) break;

        System.out.println("\nWinner is " + players[i].getName());

    }

    protected static Game instance() {
        return new Game();
    }

    protected void sortPlayers(Player[] players, Dice dice) {

        int length = players.length;
        int maxValIndex;
        for (int i = 0; i < length - 1; i++) {
            maxValIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (players[j].getFirstRoll() >= players[maxValIndex].getFirstRoll())
                    maxValIndex = j;
            }
            Player temp = players[maxValIndex];
            players[maxValIndex] = players[i];
            players[i] = temp;
        }

        for(int i = 0; i < length - 1; i++) {
            if(players[i].getFirstRoll() == players[i + 1].getFirstRoll()) {
                while (true) {
                    players[i].setFirstRoll(players[i].tossDie(dice, false, null));
                    players[i + 1].setFirstRoll(players[i].tossDie(dice, false, null));

                    if(players[i].getFirstRoll() > players[i + 1].getFirstRoll()) break;

                    else if(players[i].getFirstRoll() < players[i + 1].getFirstRoll()) {
                        Player temp = players[i];
                        players[i] = players[i + 1];
                        players[i + 1] = temp;
                        break;
                    }
                }
            }
        }
    }

}
