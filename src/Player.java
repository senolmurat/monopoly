public class Player {

    private int numberOfTurn = 0; //Number of turns each player played
    private int cycleCounter = 0; //Number of passes through the starting square for each player
    private int position = 0; //Which square
    private int id;
    private String name;
    private boolean bankrupt = false; //Is player bankrupt or not.
    private Money money = new Money(1500);
    private int doubleDiceCounter = 0; //Number of double dices in a row

    private Player() {

    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int tossDie(Dice dice) {
        dice.setDouble(false); //Set the isDouble value to "false" for every dice before tossing
        int[] tossedFaces = dice.getFaces();

        if (dice.isDouble()) {
            doubleDiceCounter++;
        }
        System.out.println("Turn: " + (this.getNumberOfTurn() + 1) + " | Cycle: " + this.getCycleCounter() + " | "
                + this.getName() + " will play"
                + " | Position: " + this.getPosition() + " | Money: " + this.money.getMoney());
        System.out.println(getName() + " tossing dice... Faces are " + tossedFaces[0] + " - " + tossedFaces[1]
                + " | Total faces: " + dice.getTotalFaces() + " | Double: "
                + dice.isDouble() + " | New position: " + (this.getPosition() + dice.getTotalFaces()) + "\n");

        int sumOfFaces = tossedFaces[0] + tossedFaces[1];

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
        return bankrupt;
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

    public int getCycleCounter() {
        return cycleCounter;
    }

    public void setCycleCounter(int cycleCounter) {
        this.cycleCounter = cycleCounter;
    }
}
