package square;

import lombok.Getter;
import lombok.Setter;
import player.Player;

@Getter
@Setter
public class TaxSquare extends Square {
    private int tax;

    public TaxSquare(String name, int position, int tax) {
        super.setName(name);
        super.setPosition(position);
        this.tax = tax;
    }


    @Override
    public void squareAction(Player player) {
        if (player.getMoney().getMoney() * 0.20 < tax)
            player.getMoney().subtractMoney(this.tax);
        else
            player.getMoney().subtractMoney((int) (player.getMoney().getMoney() * 0.20));
    }

    @Override
    public String landedOn() {
        return getName();
    }
}
