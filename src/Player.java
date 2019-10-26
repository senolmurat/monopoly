public class Player {

    private int numberOfTurn = 0;
    private int position = 0; //Which square
    private int id;
    private String name;
    private boolean bankrupt = false;
    private Money money = new Money(200000);

    private Player() {

    }
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //TODO: Zar atma fonksiyonu

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
}
