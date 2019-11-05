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
        for(int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(i, reader.getNames()[i], reader.getStartingMoney());
            players[i].getMoney().setMoney(players[i].tossDie(dice));
            System.out.println(players[i].getName() + " " + players[i].getMoney().getMoney());
        }

        //TODO : Dice Sorting
        //selectionSort(players);

        for(int i = 0; i < numberOfPlayers; i++) {
            players[i].getMoney().setMoney(reader.getStartingMoney());
            players[i].setId(i + 1);
            System.out.println(players[i].getName() + " " + players[i].getId());
        }


        while(remainingPlayers > 1) {
            String squareType = "";
            System.out.println("\nCYCLE " + cycleCounter);
            for(int i = 0; i < numberOfPlayers; i++) {
                if(!players[i].isBankrupt()) {
                    if(players[i].getDoubleDiceCounter() < 3) {
                        int size = board.getSize();

                        info.infoMessageBeforeTossDie(players[i], squareType);

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

        //TODO i will add a function to sort players by rolling a dice
        //TODO This might be added in second iteration

    }
    protected static Game instance() {
        return new Game();
    }

}
