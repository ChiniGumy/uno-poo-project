package game;

import game.player.BotPlayer;
import game.player.HumanPlayer;
import game.player.Player;

public class GameEngine {

    final int HandSize = 7;

    public GameEngine() {
        dealFirstHand(player, bot);
    }

    private Deck deck = new Deck();
    private final HumanPlayer player = new HumanPlayer();
    private final BotPlayer bot = new BotPlayer();
    
    public void dealFirstHand(Player player, BotPlayer bot){
        for (int i = 0; i < HandSize; i++) {
            player.drawCard(deck);
        }

        for (int i = 0; i < HandSize; i++) {
            bot.drawCard(deck);
        }
    }

    public HumanPlayer getPlayer() {
        return player;
    }

    public BotPlayer getBot() {
        return bot;
    }

    public Deck getDeck() {
        return deck;
    }

    
    
}
