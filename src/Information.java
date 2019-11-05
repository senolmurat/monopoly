public class Information {

    public Information() {

    }

    protected void infoMessageBeforeTossDie(Player player, String squareType) {
        System.out.println("Turn: " + (player.getNumberOfTurn() + 1) + " | " + player.getName() + " will play"
                + " | Position: " + (player.getPosition() + 1) + squareType + " | Money: " + player.getMoney().getMoney());
    }

    protected void infoMessageAfterTossDie(Player player, Dice dice, String squareType) {
        System.out.println(player.getName() + " tossing dice... Faces are " + player.getTossedFaces()[0] + " - " + player.getTossedFaces()[1]
                + " | Total faces: " + dice.getTotalFaces() + " | Double: "
                + dice.isDouble() + " | New position: " + (player.getPosition() + 1) + squareType
                + " | Money: " + player.getMoney().getMoney());
        System.out.println("-----------------------");
    }

    protected void infoBasedOnBalance(Player[] players, Board board) {
        Player[] tempPlayers = players;
        selectionSort(tempPlayers);

        for (int i = 0; i < tempPlayers.length; i++) {
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
            for (int j = i + 1; j < length; j++) {
                if (players[j].getMoney().getMoney() >= players[maxValIndex].getMoney().getMoney())
                    maxValIndex = j;
            }
            Player temp = players[maxValIndex];
            players[maxValIndex] = players[i];
            players[i] = temp;
        }
    }
}
