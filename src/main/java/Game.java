import GameElements.Board;
import GameElements.Dice;
import IO.*;
import Player.Player;
import Square.*;

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

        Board board = new Board( reader.getGoSquare_money());
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
                                // i--; //Couldn't make it here, compile error
                            }
                            else { //If dice is not double, resets the doubleDiceCounter
                                players[i].setDoubleDiceCounter(0);
                            }

                            players[i].getPiece().move(sumOfFaces, board); //Moves its piece based on the faces, returns new position

                            squareType = getTheSquareType(players[i], board); //Type of the square which the player is moved.
                            display.infoMessageAfterTossDie(players[i], squareType);

                            if(players[i].getPiece().isPassedFromStart()) {
                                players[i].getMoney().addMoney(((StartSquare)(board.getBoard()[0])).getPassMoney());
                            }

                            board.getBoard()[players[i].getPosition()].squareAction(players[i]);

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

                            if (dice.isDouble()) //Current player will toss dice again
                                i--;
                        }
                        /*
                        else {
                            players[i].setPosition(10); //Put it in jail
                            players[i].setDoubleDiceCounter(0);
                        }
                         */
                    }
                    else {
                        if (players[i].getJailCounter() < 3){

                                    //Tries to get out from jail
                            
                            players[i].setJailCounter(players[i].getJailCounter()+1);
                        }
                        else { //If he/she stayed in jail 3 turns, gets out
                            players[i].setInJail(false);
                            players[i].setJailCounter(0);
                        }
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
                    players[i].setFirstRoll(players[i].tossDie(dice, null));
                    players[i + 1].setFirstRoll(players[i].tossDie(dice, null));

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
}
