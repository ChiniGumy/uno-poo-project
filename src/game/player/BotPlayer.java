package game.player;

import game.Deck;
import game.GameEngine;
import game.UI;
import game.card.Card;
import game.card.SpecialCard;
import java.util.List;

public class BotPlayer extends Player {

    UI ui = new UI();

    @Override
    public String toString() {
        return "Bot";
    }

    @Override
    public Card playTurn(Card topCard, Player opponent, Deck deck) {
        List<Card> playable = hand.stream()
            .filter(card -> card.isPlayable(topCard, GameEngine.currentColor))
            .toList();

        if (playable.isEmpty()) return null;

        Card selected = playable.get(0);

        if (selected instanceof SpecialCard specialCard) {
            specialCard.playEffect(specialCard.getEffect(), opponent, deck, null);
            ui.showCardEffect(specialCard, opponent);
        }
        int index = hand.indexOf(selected);
        removeCard(index);
        return selected;
    }
}