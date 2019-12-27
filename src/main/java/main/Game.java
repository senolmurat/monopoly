package main;

import game_elements.Board;
import game_elements.Die;
import io.*;
import lombok.Getter;
import player.Player;
import square.*;

import java.util.Iterator;
import java.util.Scanner;

@Getter
public class Game {

    private int cycleCounter = 1;
    private static Game game = null;
    Board board;
    Die die;
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

        Player[] players = new Player[numberOfPlayers];

        board = new Board(players);
        Display display = new Display();
        die = new Die();


        for(int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(i, reader.getNames()[i], reader.getStartingMoney(), die);
            players[i].setPosition(board.getBoard()[0]);
        }


        sortPlayers(players, die);

        System.out.print("Order of the player to play based on first dices: ");
        for(int i = 0; i < players.length ; i++){
            players[i].setNumberOfTurn(i);
            System.out.print(players[i].getName() + " ");
        }
        System.out.println();

        String squareType;
        int brokeOnes[] = new int[numberOfPlayers];
        for(int i =  0; i < numberOfPlayers; i++) brokeOnes[i] = -1;

        while(remainingPlayers > 1) {
            turnUpdater(players, brokeOnes);
            for(int i =  0; i < numberOfPlayers; i++) brokeOnes[i] = -1;
            System.out.println("\nCYCLE " + cycleCounter);

            for(int i = 0; i < numberOfPlayers; i++) {


                if(!players[i].isBankrupt()) {
                    if (!players[i].isInJail()){
                        if(players[i].getProperties().size() > 0){
                            for (Purchasable square: players[i].getProperties()) {
                                ((Square)square).squareAction(players[i]);
                            }

                        }

                        if(players[i].getDoubleDiceCounter() < 3) {

                            squareType = getTheSquareType(players[i]); //Type of the square which the player is currently at.

                            display.infoMessageBeforeTossDie(players[i], squareType);

                            int sumOfFaces = players[i].tossDie(die); //Player rolls the dice
                            display.infoMessageTossedDice(players[i], die);

                            if(die.isDouble()) {
                                players[i].setDoubleDiceCounter(players[i].getDoubleDiceCounter() + 1);
                                if( players[i].getDoubleDiceCounter() == 3){ //If current player rolls double dice three times in a row
                                    players[i].setDoubleDiceCounter(0);
                                    players[i].goToJail(board.getBoard()[board.getJailIndex()]);
                                    display.infoMessageDoubleDiceThreeTimesInARow(players[i]);
                                    continue;
                                }
                            }
                            else { //If dice is not double, resets the doubleDiceCounter
                                players[i].setDoubleDiceCounter(0);
                            }

                            players[i].getPiece().move(sumOfFaces, board); //Moves its piece based on the faces, returns new position

                            if(players[i].getPiece().isPassedFromStart()) {
                                board.getBoard()[0].squareAction(players[i]);
                            }

                            squareType = getTheSquareType(players[i]); //Type of the square which the player is moved.
                            display.infoMessageAfterTossDie(players[i], squareType);

                            players[i].getPosition().squareAction(players[i]);

                            if(players[i].isBankrupt()) {
                                if(players[i].isMortgage())
                                    continue;
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

                            if (die.isDouble()) //Current player will toss dice again
                                i--;
                        }
                    }
                    else {
                        ((JailSquare)board.getBoard()[board.getJailIndex()]).squareAction(players[i], players[i].getJailCounter(), die);
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

    private String getTheSquareType(Player player) {

        return player.getPosition().landedOn();
    }

    public static Game instance() {
        if(game == null)
            game = new Game();
        return game;
    }

    private void sortPlayers(Player[] players, Die die) {

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
                    players[i].setFirstRoll(players[i].tossDie(die));
                    players[i + 1].setFirstRoll(players[i].tossDie(die));

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

    private void turnUpdater(Player players[], int brokeOnes[]){

        for(int i = 0; i < players.length; i++) {
            for(int j = 0; j < brokeOnes.length; j++) {
                if(brokeOnes[j] ==  -1) break;

                if(players[i].getNumberOfTurn() >= brokeOnes[j])
                    players[i].setNumberOfTurn(players[i].getNumberOfTurn() - 1);
            }
        }
    }


}
