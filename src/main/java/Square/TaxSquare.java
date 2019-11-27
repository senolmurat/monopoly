package Square;

import Player.Player;

public class TaxSquare extends Square {
    int tax;

    public TaxSquare(String name, int position, int tax){
        super.setName(name);
        super.setPosition(position);
        this.tax = tax;
    }

    public int getTax() {
        return tax;
    }
    public void setTax(int tax) {
        this.tax = tax;
    }

    @Override
    public void squareAction(Player player) {
            setTax((int) (player.getMoney().getMoney() * 0.20));
            player.getMoney().subtractMoney(this.tax);

    }

    @Override
    public String landedOn() {
        return getName();
    }
}
