package game.player;

import game.card.Card;
import java.util.List;

public class BotPlayer extends Player {

    @Override
    public String toString() {
        return "Bot";
    }

    @Override
    public Card playTurn(Card topCard) {
        List<Card> playable = hand.stream()
            .filter(card -> card.isPlayable(topCard))
            .toList();

        if (playable.isEmpty()) return null;

        Card selected = playable.get(0);
        int index = hand.indexOf(selected);
        removeCard(index);
        return selected;
    }
}