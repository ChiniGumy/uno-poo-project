package game.card;

public abstract class Card {
    protected static final String[] colors = {"a", "z", "r", "v"};
    protected String color;

    public Card(String color) {
        this.color = color;
    }

    // For special black cards
    public Card() {
        this.color = "n";
    }

    public static String[] getColors() {
        return colors;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean isPlayable(Card topCard);
}