import GameElements.Board;
import GameElements.Cards.Card;
import GameElements.Cards.Chance.*;
import GameElements.Cards.CommunityChest.*;
import GameElements.Dice;
import IO.*;
import Player.Player;
import Square.*;
import java.util.Iterator;
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

        Board board = new Board( reader.getPassingStartPrize());
        Display display = new Display();
        Dice dice = new Dice();

        //Card[] chance = new Card[6];
        //Card[] communityChest = new Card[15];
        //crateChanceCards(chance , communityChest);

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
                    if (!players[i].isInJail()){
                        if(players[i].getDoubleDiceCounter() < 3) {

                            squareType = getTheSquareType(players[i], board); //Type of the square which the player is currently at.

                            display.infoMessageBeforeTossDie(players[i], squareType);

                            int sumOfFaces = players[i].tossDie(dice); //Player rolls the dice
                            display.infoMessageTossedDice(players[i], dice);

                            if(dice.isDouble()) {
                                players[i].setDoubleDiceCounter(players[i].getDoubleDiceCounter() + 1);
                                if( players[i].getDoubleDiceCounter() == 3){ //If current player rolls double dice three times in a row
                                    players[i].setDoubleDiceCounter(0);
                                    players[i].goToJail(board.getJailIndex());
                                    display.infoMessageDoubleDiceThreeTimesInARow(players[i]);
                                    continue;
                                }
                            }
                            else { //If dice is not double, resets the doubleDiceCounter
                                players[i].setDoubleDiceCounter(0);
                            }

                            players[i].getPiece().move(sumOfFaces, board); //Moves its piece based on the faces, returns new position

                            if(players[i].getPiece().isPassedFromStart()) {
                                players[i].getMoney().addMoney(((StartSquare)(board.getBoard()[0])).getPassMoney());
                            }

                            squareType = getTheSquareType(players[i], board); //Type of the square which the player is moved.
                            display.infoMessageAfterTossDie(players[i], squareType);

                            board.getBoard()[players[i].getPosition()].squareAction(players[i]);

                            if(players[i].isBankrupt()) {
                                players[i].setBankrupt(true);
                                Iterator iter = players[i].getProperties().iterator();
                                while (iter.hasNext()) {
                                    ((Purchasable)iter.next()).setOwner(null);
                                }
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

                            if (dice.isDouble()) //Current player will toss dice again
                                i--;
                        }
                    }
                    else {
                        ((JailSquare)board.getBoard()[board.getJailIndex()]).squareAction(players[i], players[i].getJailCounter(), dice);
                    }
                }
            }
            display.infoMessageBasedOnBalance(players, board);
            cycleCounter++;
        }

        int i;

        for(i = 0; i < numberOfPlayers; i++)
            if(!players[i].isBankrupt()) break;

        System.out.println("\nWinner is " + players[i].getName());

    }

    private String getTheSquareType(Player player, Board board) {
        int position = player.getPosition();
        String squareType = board.getBoard()[position].landedOn();

        return squareType;
    }

    public static Game instance() {
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
                    players[i].setFirstRoll(players[i].tossDie(dice));
                    players[i + 1].setFirstRoll(players[i].tossDie(dice));

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

                if(players[i].getNumberOfTurn() >= brokeOnes[j])
                    players[i].setNumberOfTurn(players[i].getNumberOfTurn() - 1);
            }
        }
    }


    private void crateChanceCards(Card[] chance , Card[] communityChest){

        chance[0] = new Card1();
        chance[1] = new Card2();
        chance[2] = new Card3();
        chance[3] = new Card4();
        chance[4] = new Card5();
        chance[5] = new Card6();

        communityChest[0] = new commCard1();
        communityChest[1] = new commCard2();
        communityChest[2] = new commCard3();
        communityChest[3] = new commCard4();
        communityChest[4] = new commCard5();
        communityChest[5] = new commCard6();
        communityChest[6] = new commCard7();
        communityChest[7] = new commCard8();
        communityChest[8] = new commCard9();
        communityChest[9] = new commCard10();
        communityChest[10] = new commCard11();
        communityChest[11] = new commCard12();
        communityChest[12] = new commCard13();
        communityChest[13] = new commCard14();
        communityChest[14] = new commCard15();

        return;
    }
}
