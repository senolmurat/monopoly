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

        Board board = new Board(reader.getNumberOfTaxSquare(), reader.getTaxAmount(), reader.getGoSquare_money());
        Display display = new Display();
        Dice dice = new Dice();
        Player[] players = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++)
            players[i] = new Player(i, reader.getNames()[i], reader.getStartingMoney(), dice);

        sortPlayers(players, dice);

        System.out.print("Order of the player to play based on first dices: ");
        for(int i = 0; i < players.length ; i++){
            players[i].setNumberOfTurn(i);
            System.out.print(players[i].getName() + " ");
        }
        System.out.println();

        String squareType = "";
        int brokeOnes[] = new int[numberOfPlayers];
        for(int i =  0; i < numberOfPlayers; i++) brokeOnes[i] = -1;

        while(remainingPlayers > 1) {
            turnUpdater(players, brokeOnes);
            for(int i =  0; i < numberOfPlayers; i++) brokeOnes[i] = -1;
            System.out.println("\nCYCLE " + cycleCounter);

            for(int i = 0; i < numberOfPlayers; i++) {
                if(!players[i].isBankrupt()) {
                    if(players[i].getDoubleDiceCounter() < 3) {

                        squareType = getTheSquareType(players[i], board); //Type of the square which the player is moved.

                        display.infoMessageBeforeTossDie(players[i], squareType);

                        players[i].tossDie(dice, true, board);

                        squareType = getTheSquareType(players[i], board); //Type of the square which the player is moved.

                        display.infoMessageAfterTossDie(players[i], dice, squareType);

                        if(players[i].isBankrupt()) {
                            players[i].setBankrupt(true);
                            remainingPlayers--;
                            System.out.println("\n" + players[i].getName() + " is bankrupt!\n");
                            for(int j =  0; j < numberOfPlayers; j++)
                                if(brokeOnes[j] == -1){
                                    brokeOnes[j] = players[i].getNumberOfTurn();
                                    break;
                                }
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
            display.infoBasedOnBalance(players, board);
            cycleCounter++;
        }

        int i;

        for(i = 0; i < numberOfPlayers; i++)
            if(!players[i].isBankrupt()) break;

        System.out.println("\nWinner is " + players[i].getName());

    }

    private String getTheSquareType(Player player, Board board) {
        int position = player.getPosition();
        String squareType;
        if(board.getBoard()[position] instanceof TaxSquare){
            squareType = "(Tax Square)";
        }
        else if(board.getBoard()[position] instanceof StartSquare){
            squareType = "(Start Square)";
        }
        else {
            squareType = "(Regular Square)";
        }
        return squareType;
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

    protected void turnUpdater(Player players[], int brokeOnes[]){

        for(int i = 0; i < players.length; i++) {
            for(int j = 0; j < brokeOnes.length; j++) {
                if(brokeOnes[j] ==  -1) break;

                if(players[i].getNumberOfTurn() > brokeOnes[j])
                    players[i].setNumberOfTurn(players[i].getNumberOfTurn() - 1);
            }
        }
    }
}
