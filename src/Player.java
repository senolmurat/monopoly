public class Player {

    private int numberOfTurn; //Number of turns each player played
    private int id;
    private String name;
    private boolean bankrupt = false; //Is player bankrupt or not.
    private Money money;
    private int doubleDiceCounter = 0; //Number of double dices in a row
    private int[] tossedFaces;
    private Piece piece;
    private int firstRoll;

    private Player() {

    }

    public Player(int id, String name, int startingMoney, Dice dice) {
        this.id = id;
        this.name = name;
        this.money = new Money(startingMoney);
        piece = new Piece();
        firstRoll = tossDie(dice, false, null);
    }

    public int tossDie(Dice dice, boolean isItForMoving, Board board) {
        dice.setDouble(false); //Set the isDouble value to "false" for every dice before tossing
        tossedFaces = dice.getFaces();

        if (dice.isDouble()) {
            doubleDiceCounter++;
        }

        int sumOfFaces = dice.getTotalFaces();

        if(isItForMoving)
            money.addMoney(piece.move(sumOfFaces, board));

        return sumOfFaces;
    }

    public int getNumberOfTurn() {
        return numberOfTurn;
    }

    public void setNumberOfTurn(int numberOfTurn) {
        this.numberOfTurn = numberOfTurn;
    }

    public void setPosition(int position) {
        piece.setPosition(position);
    }

    public int getPosition() {
        return piece.getPosition();
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

    public void setId(int id) {this.id = id; }

    public int getFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(int firstRoll) {
        this.firstRoll = firstRoll;
    }
}
