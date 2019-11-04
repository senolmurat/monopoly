public class Player {

    private int numberOfTurn = 0; //Number of turns each player played
    private int position = 0; //Which square
    private int id;
    private String name;
    private boolean bankrupt = false; //Is player bankrupt or not.
    private Money money = new Money(1500);
    private int doubleDiceCounter = 0; //Number of double dices in a row
    private int[] tossedFaces;

    private Player() {

    }

    public Player(int id, String name, int startingMoney) {
        this.id = id;
        this.name = name;
        this.money = new Money(startingMoney);
    }

    public int tossDie(Dice dice) {
        dice.setDouble(false); //Set the isDouble value to "false" for every dice before tossing
        tossedFaces = dice.getFaces();

        if (dice.isDouble()) {
            doubleDiceCounter++;
        }

        int sumOfFaces = dice.getTotalFaces();

        return sumOfFaces;
    }

    public int getNumberOfTurn() {
        return numberOfTurn;
    }

    public void nextTurn() { //Increments the number of turns the player has taken.
        numberOfTurn++;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }

    public boolean isBankrupt() {
        return money.getMoney() <= 0;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    public int getDoubleDiceCounter() {
        return doubleDiceCounter;
    }

    public void setDoubleDiceCounter(int doubleDiceCounter) {
        this.doubleDiceCounter = doubleDiceCounter;
    }

    public int[] getTossedFaces() {
        return tossedFaces;
    }
}
