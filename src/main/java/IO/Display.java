package IO;

import Player.Player;
import GameElements.*;
import Square.PropertySquare;

public class Display {

    public Display() {

    }

    public void infoMessageBeforeTossDie(Player player, String squareType) {
        System.out.println("-----------------------");
        System.out.println("Turn: " + (player.getNumberOfTurn() + 1) + " | " + player.getName() + " will play"
                + " | Position: " + (player.getPosition() + 1) + " " +  squareType + " | Money: " + player.getMoney().getMoney());
    }

    public void infoMessageTossedDice(Player player, Dice dice) {
        System.out.println(player.getName() + " tossing dice... Faces are " + player.getTossedFaces()[0] + " - " + player.getTossedFaces()[1]
                + " | Total faces: " + dice.getTotalFaces() + " | Double: "
                + dice.isDouble());
    }

    public void infoMessageAfterTossDie(Player player, String squareType) {
        System.out.println("New position: " + (player.getPosition() + 1) + " " + squareType);
    }

    public void infoMessageBasedOnBalance(Player[] players, Board board) {
        System.out.println("-----------------------");
        Player[] tempPlayers = new Player[players.length];
        tempPlayers = players.clone();

        selectionSort(tempPlayers);

        for (int i = 0; i < tempPlayers.length; i++) {
            if (tempPlayers[i].isBankrupt()) {
                System.out.println(tempPlayers[i].getName() + " | BANKRUPT! ");
            } else {
                System.out.println(tempPlayers[i].getName() + " | Location: " + (tempPlayers[i].getPosition() + 1) + " "
                        + board.getBoard()[tempPlayers[i].getPosition()].getName()
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
}
