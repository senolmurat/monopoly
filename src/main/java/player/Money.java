package player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Money {

    private int money;

    private Money(){

    }
    public Money(int money) {
        this.money = money;
    }
    public void addMoney(int amountOfMoney){
        money += amountOfMoney;
    }
    public void subtractMoney(int amountOfMoney){
        money -= amountOfMoney;
    }
}

