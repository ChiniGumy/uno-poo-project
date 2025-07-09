package game.card;
import game.Deck;
import game.GameEngine;
import game.UI;
import game.player.BotPlayer;
import game.player.HumanPlayer;
import game.player.Player;
import java.util.Scanner;

public class SpecialCard extends Card {

    UI ui = new UI();
    final private String effect;
    HumanPlayer human = new HumanPlayer();

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

    public static boolean skipedTurn = false;

    public void playEffect(String effect, Player opponent, Deck deck, Scanner scanner) {
        if (this.getColor().equals("n")) {
            if (opponent instanceof HumanPlayer) {
                String randomColor = Card.getColors()[(int)(Math.random() * 4)];
                GameEngine.currentColor = randomColor;
            }
            else if (opponent instanceof BotPlayer) {
                ui.showAvailableColors();
                String newColor;
                while (true) {
                    ui.showColorInput();
                    String inputColor = scanner.nextLine().toLowerCase();
                    try {
                        if (inputColor.equals("a") || inputColor.equals("z") || inputColor.equals("r") || inputColor.equals("v")) {
                            newColor = inputColor;
                            break;
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } catch (IllegalArgumentException e) {
                        ui.showInvalidColor();
                    }
                }
                GameEngine.currentColor = newColor;
            }
        }

        switch (effect) {
            case "+2" -> {
                for (int i = 0; i < 2; i++) {
                    opponent.drawCard(deck);
                }
            }
            case "+4" -> {
                for (int i = 0; i < 4; i++) {
                    opponent.drawCard(deck);
                }
            }
            case "^", "&" -> {
               SpecialCard.skipedTurn = true;
            }
        }

    }

    public String getEffect() {
        return effect;
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

    @Override
    public boolean isPlayable(Card topCard, String currentColor) {
        boolean sameColor = this.color.equals(currentColor);
        boolean sameEffect = topCard instanceof SpecialCard && this.effect.equals(((SpecialCard) topCard).getEffect());
        boolean isBlack = this.color.equals("n");
        return sameColor || isBlack || sameEffect ;
    }

}
