import game.Deck;
import game.card.Card;
import java.util.ArrayList;

public class UnoGameApp {

    public static void main(String[] args) throws Exception {
        Deck deck = new Deck();
        ArrayList<Card> hola = deck.getDeck();

        for (Card card : hola) {
            System.out.println(card);
        }
        
    }
}
