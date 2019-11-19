public class GoToJail extends Square {
    public GoToJail(String name, int position) {
        super.setName(name);
        super.setPosition(position);
    }

    @Override
    public String landedOn() {
        return "(Go To Jail)";
    }
}
