package game.card;

public class NormalCard extends Card {
    final private int denomination;

    public NormalCard(int denomination, String color) {
        super(color);
        this.denomination = denomination;
    }
    
    @Override
    public String toString() {
        return color.toUpperCase() + denomination;
    }
    
}