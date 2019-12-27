package io;

import game_elements.Board;
import player.Player;
import game_elements.*;
import square.PropertySquare;
import square.Purchasable;

public class Display {

    public Display() {

    }

    public void infoMessageBeforeTossDie(Player player, String squareType) {
        System.out.println("-----------------------");
        System.out.println("Turn: " + (player.getNumberOfTurn() + 1) + " | " + player.getName() + " will play"
                + " | Position: " + (player.getPosition().getPosition()) + " " +  squareType + " | Money: " + player.getMoney().getMoney());
    }

    public void infoMessageTossedDice(Player player, Die die) {
        System.out.print(player.getName() + " tossing dice... Faces are ");

        for (int i = 0; i < die.getDieFaces().length; i++){
            System.out.print(player.getTossedFaces()[i] + " ");
        }

        System.out.println(" | Total faces: " + die.getTotalFaces() + " | Double: " + die.isDouble());
    }

    public void infoMessageAfterTossDie(Player player, String squareType) {
        System.out.println("New position: " + (player.getPosition().getPosition()) + " " + squareType);
    }

    public void infoMessageCardForSquares(String description, Player player){
        System.out.println(description + " | Money: " + player.getMoney().getMoney());
    }
    public void infoMessageBasedOnBalance(Player[] players, Board board) {
        System.out.println("-----------------------");
        Player[] tempPlayers = players.clone();

        selectionSort(tempPlayers);

        for (int i = 0; i < tempPlayers.length; i++) {
            if (tempPlayers[i].isBankrupt()) {
                System.out.println(tempPlayers[i].getName() + " | BANKRUPT! ");
            } else {
                System.out.println(tempPlayers[i].getName() + " | Location: " + (tempPlayers[i].getPosition().getPosition()) + " "
                        + board.getBoard()[tempPlayers[i].getPosition().getPosition() - 1].getName()
                        + " | Money: " + tempPlayers[i].getMoney().getMoney());
            }

        }
    }

    public void infoMessagePayingRent(Player player, Player owner, int rent) {
        System.out.println(player.getName() + " is going to pay " + rent + " to " + owner.getName()
                + ". | Money: " + player.getMoney().getMoney());
    }

    public void infoMessageDoubleDiceThreeTimesInARow(Player player) {
        System.out.println(player.getName() + " rolled double dice three times in a row, so putted in the Jail!");
    }
    public  void infoMessageBuildHouse(Player player, String squareName, int numberOfHouses, int buildingPrice){
        System.out.println(player.getName() + " built " + numberOfHouses + ". house in " + squareName + " and paid " + buildingPrice);
    }

    public void infoMessageBuildHotel(Player player, String squareName, int buildingPrice){
        System.out.println(player.getName() + " built a hotel in " + squareName + " and paid " + buildingPrice);
    }

    public void infoCommunitySquareSelling(Purchasable square, String name, Player player){
        System.out.println(player.getName() + " sold " + name + " and earned " + square.getLandValue() + " | Money: " + player.getMoney().getMoney());
    }
    public void infoMessageColorSet(Player player, String type){
        System.out.println(player.getName() + " has a color set at " + type + " region.");
    }
    private void selectionSort(Player[] players) {
        int length = players.length;
        int maxValIndex;
        for (int i = 0; i < length - 1; i++) {
            maxValIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (players[j].getMoney().getMoney() >= players[maxValIndex].getMoney().getMoney())
                    maxValIndex = j;
            }
            Player temp = players[maxValIndex];
            players[maxValIndex] = players[i];
            players[i] = temp;
        }
    }

    public void infoMessageBuying(Player player, PropertySquare propertySquare) {
        System.out.println(player.getName() + " decided to buy " + propertySquare.getName() + " and paid " + propertySquare.getLandValue()
                + ". | Money: " + player.getMoney().getMoney());
    }

    public void infoUtilitySquareSelling(Purchasable square, String name, Player player) {
        System.out.println(player.getName() + " sold " + name + " and earned " + square.getLandValue() + " | Money: " + player.getMoney().getMoney());
    }

    public void infoPropertySquareSelling(Purchasable square, String name, Player player) {
        System.out.println(player.getName() + " sold " + name + " and earned " + square.getLandValue() + " | Money: " + player.getMoney().getMoney());
    }

    public void infoHotelSelling(Purchasable square, String name, Player player) {
        System.out.println(player.getName() + " sold hotel in " + name + " and earned " + square.getLandValue() + " | Money: " + player.getMoney().getMoney());
    }

    public void infoHouseSelling(Purchasable square, String name, Player player) {
        System.out.println(player.getName() + " sold house in " + name + " and earned " + square.getLandValue() + " | Money: " + player.getMoney().getMoney());
    }
}