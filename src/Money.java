public class Money {

    private int money;

    private Money(){

    }
    public Money(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }
    public void addMoney(int amountOfMoney){
        money += amountOfMoney;
    }
    public void subtractMoney(int amountOfMoney){
        money -= amountOfMoney;
    }
    public boolean isBankrupt(){
        return money < 0;
    }
}
