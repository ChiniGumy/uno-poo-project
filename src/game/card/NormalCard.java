package game.card;

public class NormalCard extends Card {
    final private int denomination;

    public NormalCard(int denomination, String color) {
        super(color);
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
    
    @Override
    public String toString() {
        return color.toUpperCase() + denomination;
    }
    
    @Override
    public boolean isPlayable(Card topCard, String currentColor) {
        boolean sameColor = this.color.equals(currentColor);
        boolean sameDenomination = topCard instanceof NormalCard && this.denomination == ((NormalCard) topCard).getDenomination();

        return sameColor || sameDenomination;
    }
}