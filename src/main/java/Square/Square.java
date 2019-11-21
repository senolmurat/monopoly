package Square;

import Player.Player;

public abstract class Square{
    private String name;
    private int position;

    public Square (){
    }

    public abstract void squareAction(Player player);

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String landedOn();
}
