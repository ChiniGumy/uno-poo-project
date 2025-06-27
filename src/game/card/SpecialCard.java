package game.card;

public class SpecialCard extends Card {

    final private String effect;
    
    private static final String[] effects = {"^", "&"};
    private static final String[] blackEffects = {"%", "+2", "+4"};
   
    public SpecialCard(String effect, String color) {
        super(color);
        this.effect = effect;
    }

        public SpecialCard(String effect) {
        this.color = "n";
        this.effect = effect;
    }

    public static String[] getEffects() {
        return effects;
    }

    public static String[] getBlackeffects() {
        return blackEffects;
    }

    @Override
    public String toString() {
        return color.toUpperCase() + effect;
    }

}
