import java.util.Scanner;

public class Game {

    private Game() {

    }

    public void startTheGame() {
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome User!\nPlease enter the number of the player(2 to 8) : ");
        int numberOfPlayer = input.nextInt();
        int numberOfTurns = 0;//This will be incremented at the end of the last player's turn

        while (numberOfPlayer < 2 || numberOfPlayer > 8) {
            System.out.println("Number of player is not valid, please enter a number between 2 and 8 : ");
            numberOfPlayer = input.nextInt();
        }

        Player[] players = new Player[numberOfPlayer];

        for(int i = 0; i < numberOfPlayer; i++)
            players[i] = new Player(i, "Player" + i);

        //TODO i will add a function to sort players by rolling a dice
        //TODO This might be added in second iteration

    }

    protected static Game instance() {
        return new Game();
    }

}
