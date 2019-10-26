public class TaxSquare extends Square {
    int tax;

    public TaxSquare(String name, int position, int tax){
        super(name, position);
        this.tax = tax;
    }

    public int getTax() {
        return tax;
    }
    public void setTax(int tax) {
        this.tax = tax;
    }
}
