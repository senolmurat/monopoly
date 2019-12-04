package square;

import player.Player;

public class StartSquare extends Square {
    private int passMoney;
    public StartSquare (String name, int position, int passMoney){
        super.setName(name);
        super.setPosition(position);
        this.passMoney = passMoney;
    }

    public void setPassMoney(int passMoney) {
        this.passMoney = passMoney;
    }

    public int getPassMoney() {
        return passMoney;
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
