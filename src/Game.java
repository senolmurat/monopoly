import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Game {

    private int cycleCounter = 1;

    private Game() {

    }

    public void startTheGame() {
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome User!\nPlease enter the number of the player(2 to 8) : ");
        int numberOfPlayers = input.nextInt();
        int numberOfTurns = 0;//This will be incremented at the end of the last player's turn
        int remainingPlayers = numberOfPlayers; //Players still in the game

        while (numberOfPlayers < 2 || numberOfPlayers > 8) {
            System.out.println("Number of player is not valid, please enter a number between 2 and 8 : ");
            numberOfPlayers = input.nextInt();
        }

        Board board = new Board();
        Dice dice = new Dice();
        Player[] players = new Player[numberOfPlayers];

        for(int i = 0; i < numberOfPlayers; i++)
            players[i] = new Player(i, "Player" + i);

        while(remainingPlayers > 1) {
            System.out.println("\nCYCLE " + cycleCounter);
            for(int i = 0; i < numberOfPlayers; i++) {
                if(!players[i].isBankrupt()) {
                    if(players[i].getDoubleDiceCounter() < 3) {
                        int size = board.getSize();
                        int sumOfFaces = players[i].tossDie(dice);

                        infoMessages(players[i], dice);

                        //Add money adding function to here for passing from start
                        if((sumOfFaces + players[i].getPosition()) / size == 1){
                            players[i].getMoney().addMoney(200);
                        }

                        //positions starts from 1 we need to change it to 0
                        players[i].setPosition((players[i].getPosition() + sumOfFaces) % size);
                        int position = players[i].getPosition();
                        if(board.getBoard()[position] instanceof TaxSquare)
                            players[i].getMoney().subtractMoney(((TaxSquare)(board.getBoard()[position])).getTax());

                        players[i].nextTurn();

                        if(players[i].isBankrupt()) {
                            players[i].setBankrupt(true);
                            remainingPlayers--;
                            System.out.println(players[i].getName() + " is bankrupt!");
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
            infoBasedOnBalance(players);
            cycleCounter++;
            numberOfTurns++;
        }

        int i;

        for(i = 0; i < numberOfPlayers; i++)
            if(!players[i].isBankrupt()) break;

        System.out.println("Winner is " + players[i].getName());

        //TODO i will add a function to sort players by rolling a dice
        //TODO This might be added in second iteration

    }

    protected static Game instance() {
        return new Game();
    }

    private static void infoMessages(Player player, Dice dice){
        System.out.println("Turn: " + (player.getNumberOfTurn() + 1) + " | " + player.getName() + " will play"
                + " | Position: " + player.getPosition() + " | Money: " + player.getMoney().getMoney());
        System.out.println(player.getName() + " tossing dice... Faces are " + dice.getFaces()[0] + " - " + dice.getFaces()[1]
                + " | Total faces: " + dice.getTotalFaces() + " | Double: "
                + dice.isDouble() + " | New position: " + (player.getPosition() + dice.getTotalFaces()));
        System.out.println("-----------------------");
    }

    private void infoBasedOnBalance(Player[] players) {

    }
}
