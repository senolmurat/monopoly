import java.util.Arrays;
import java.util.Comparator;
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
        Dice dice = new Dice();
        Player[] players = new Player[numberOfPlayers];

        for(int i = 0; i < numberOfPlayers; i++)
            players[i] = new Player(i, reader.getNames()[i], reader.getStartingMoney());

        while(remainingPlayers > 1) {
            String squareType = "";
            System.out.println("\nCYCLE " + cycleCounter);
            for(int i = 0; i < numberOfPlayers; i++) {
                if(!players[i].isBankrupt()) {
                    if(players[i].getDoubleDiceCounter() < 3) {
                        int size = board.getSize();

                        infoMessageBeforeTossDie(players[i], squareType);

                        int sumOfFaces = players[i].tossDie(dice);

                        //Add money adding function to here for passing from start
                        if((sumOfFaces + players[i].getPosition()) / size == 1){
                            players[i].getMoney().addMoney(reader.getGoSquare_money());
                        }

                        //positions starts from 1 we need to change it to 0
                        players[i].setPosition((players[i].getPosition() + sumOfFaces) % size);
                        int position = players[i].getPosition();
                        if(board.getBoard()[position] instanceof TaxSquare){
                            players[i].getMoney().subtractMoney(((TaxSquare)(board.getBoard()[position])).getTax());
                            squareType = "(Tax Square)";
                        }
                        else if(board.getBoard()[position] instanceof StartSquare){
                            squareType = "(Start Square)";
                        }
                        else {
                            squareType = "";
                        }

                        infoMessageAfterTossDie(players[i], dice, squareType);
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
            infoBasedOnBalance(players, board);
            cycleCounter++;
        }

        int i;

        for(i = 0; i < numberOfPlayers; i++)
            if(!players[i].isBankrupt()) break;

        System.out.println("\nWinner is " + players[i].getName());

        //TODO i will add a function to sort players by rolling a dice
        //TODO This might be added in second iteration

    }

    protected static Game instance() {
        return new Game();
    }

    private void infoMessageBeforeTossDie(Player player, String squareType) {
        System.out.println("Turn: " + (player.getNumberOfTurn() + 1) + " | " + player.getName() + " will play"
                + " | Position: " + (player.getPosition() + 1) + squareType + " | Money: " + player.getMoney().getMoney());
    }

    private void infoMessageAfterTossDie(Player player, Dice dice, String squareType) {
        System.out.println(player.getName() + " tossing dice... Faces are " + player.getTossedFaces()[0] + " - " + player.getTossedFaces()[1]
                + " | Total faces: " + dice.getTotalFaces() + " | Double: "
                + dice.isDouble() + " | New position: " + (player.getPosition() + 1) + squareType
                + " | Money: " + player.getMoney().getMoney());
        System.out.println("-----------------------");
    }

    private void infoBasedOnBalance(Player[] players, Board board) {
        Player[] tempPlayers = players;
        selectionSort(tempPlayers);

        for(int i = 0; i < tempPlayers.length; i++){
            System.out.println(tempPlayers[i].getName() + " | Location: " + (tempPlayers[i].getPosition() + 1) + " "
                    + board.getBoard()[tempPlayers[i].getPosition()].getName()
                    + " | Money: " + tempPlayers[i].getMoney().getMoney());
        }
    }

    private void selectionSort(Player[] players) {
        int length = players.length;
        int maxValIndex;
        for (int i = 0; i < length - 1; i++) {
            maxValIndex = i;
            for(int j = i + 1; j < length; j++) {
                if(players[j].getMoney().getMoney() >= players[maxValIndex].getMoney().getMoney())
                    maxValIndex = j;
            }
            Player temp = players[maxValIndex];
            players[maxValIndex] = players[i];
            players[i] = temp;
        }
    }
}
