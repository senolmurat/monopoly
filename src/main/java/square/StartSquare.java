package square;

import lombok.Getter;
import player.Player;

@Getter
public class StartSquare extends Square {
    private int passMoney;
    public StartSquare (String name, int position, int passMoney){
        super.setName(name);
        super.setPosition(position);
        this.passMoney = passMoney;
    }

    @Override
    public void squareAction(Player player) {
        player.getMoney().addMoney(passMoney);
    }

    @Override
    public String landedOn() {
        return "Start Square";
    }
}
