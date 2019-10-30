public class Player {

    private int numberOfTurn = 0;
    private int position = 0; //Which square
    private int id;
    private String name;
    private boolean bankrupt = false;
    private Money money = new Money(1500);
    private int doubleDiceCounter = 0;

    private Player() {

    }
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int tossDie(Dice dice) {
        dice.setDouble(false); //Set the isDouble value to "false" for every dice before tossing
        int[] tossedFaces = dice.getFaces();

        if(dice.isDouble()){
            doubleDiceCounter++;
        }

        System.out.println("Turn: " + (this.getNumberOfTurn() + 1) + "| Position: " + this.getPosition() + "| Money: " + this.getMoney());
        System.out.println(getName() + "tossing dice... Faces are " + tossedFaces[0] + " - " + tossedFaces[1] + "Double: " + dice.isDouble());

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
}
