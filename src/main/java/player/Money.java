package player;

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
    public  void setMoney(int money) {this.money = money;}
}

