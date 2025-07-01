package game;
import game.card.Card;
import game.player.Player;
import java.util.ArrayList;

public class UI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void showStartup(){
        System.out.println("  _   _ _   _  ___  ");
        System.out.println(" | | | | \\ | |/ _ \\ ");
        System.out.println(" | | | |  \\| | | | |");
        System.out.println(" | |_| | |\\  | |_| |");
        System.out.println("  \\___/|_| \\_|\\___/ ");
    }

    public void showRound(Player player, Player bot, CardHeap cardHeap, Deck deck, int round) {
        // clearScreen();
        showRoundNumber(round);
        showRemainingDeckCards(deck);
        showCardHeapSize(cardHeap);
        showLastPlayedCard(cardHeap);
        showColoredHand(player);
        showColoredHand(bot);
    }

    public void showColoredHand(Player player) {
        ArrayList<String> coloredHand = new ArrayList<>();

        for (Card card : player.getHand()) {
            String colorCode;
            colorCode = switch (card.getColor()) {
                case "z" -> ANSI_BLUE;
                case "a" -> ANSI_YELLOW;
                case "r" -> ANSI_RED;
                case "v" -> ANSI_GREEN;
                case "n" -> ANSI_WHITE;
                default -> ANSI_WHITE;
            };
            
            coloredHand.add(colorCode + card.toString() + ANSI_RESET);
        }

        System.out.println("Mano de " + player + ": " + coloredHand);
    }

    public void showLastPlayedCard(CardHeap cardHeap) {
        System.out.println("Ultima carta jugada: " + coloredCard(cardHeap.getLastCardPlayed()));
    }

    public void showRemainingDeckCards(Deck deck) {
        System.out.println("Cartas en el mazo: " + deck.getRemainingCards());
    }
    
    public void showCardHeapSize(CardHeap cardHeap) {
        System.out.println("Cartas en monte: " + cardHeap.getPlayedCardsSize());
    }

    public void showRoundNumber(int round) {
        System.out.println(ANSI_YELLOW + "\n=============== " + "Turno " + round + " ===============" + ANSI_RESET);
    }

    public String coloredCard(Card card) {
        String colorCode;
        colorCode = switch (card.getColor()) {
            case "z" -> ANSI_BLUE;
            case "a" -> ANSI_YELLOW;
            case "r" -> ANSI_RED;
            case "v" -> ANSI_GREEN;
            case "n" -> ANSI_WHITE;
            default -> ANSI_WHITE;
        };
        return colorCode + card.toString() + ANSI_RESET;
    }

    public void showHumanPlay(String playerName, Card card) {
        System.out.println(ANSI_GREEN + playerName + " jugó: " + coloredCard(card) + ANSI_RESET);
    }
    
    public void showBotPlay(String botName, Object card) {
        if (card instanceof Card card1) {
            System.out.println(ANSI_CYAN + botName + " jugó: " + coloredCard(card1) + ANSI_RESET);
        } else {
            System.out.println(ANSI_CYAN + botName + " jugó: " + card + ANSI_RESET);
        }
    }

    public void showDrawCard(String playerName) {
        System.out.println(ANSI_CYAN + playerName + " no pudo jugar, robó una carta." + ANSI_RESET);
    }

    public void showInputPrompt() {
        System.out.print(ANSI_YELLOW + "\nElige el índice de la carta a jugar: " + ANSI_RESET);
    }
    
    public void showInvalidPlay() {
        System.out.println(ANSI_RED + "No puedes jugar esa carta." + ANSI_RESET);
    }
    
    public void showIndexOutOfRange() {
        System.out.println(ANSI_RED + "Indice fuera de rango." + ANSI_RESET);
    }
   
    public void showInvalidInput() {
        System.out.println(ANSI_RED + "Entrada inválida." + ANSI_RESET);
    }

    public void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}
