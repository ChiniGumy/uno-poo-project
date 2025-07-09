package game;
import game.card.Card;
import game.card.SpecialCard;
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

        System.out.println("Mano de " + player + ": " + coloredHand + " [" + coloredHand.size() + "]");
    }

    public void showLastPlayedCard(CardHeap cardHeap) {
        System.out.println("Ultima carta jugada: " + showCurrentColor() + cardHeap.getLastCardPlayed() + ANSI_RESET);
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

    public String showCurrentColor() {
        return switch (GameEngine.currentColor) {
            case "z" -> ANSI_BLUE ;
            case "a" -> ANSI_YELLOW;
            case "r" -> ANSI_RED;
            case "v" -> ANSI_GREEN;
            default -> ANSI_WHITE;
        };
    }

    public void showCardEffect(SpecialCard card, Player opponent) {
        switch (card.getEffect()) {
            case "+2" -> System.out.println("\n" + showCurrentColor() + "+2 " + ANSI_RESET + ANSI_CYAN + "para " + opponent + ANSI_RESET);
            case "+4" -> System.out.println("\n" + showCurrentColor() + "+4 " + ANSI_RESET + ANSI_CYAN + "para " + opponent + ANSI_RESET);
            case "^" -> System.out.println(ANSI_RED + "\nBloqueando siguiente turno " + ANSI_RESET);
            case "&" -> System.out.println(ANSI_RED + "\nBloqueando siguiente turno" + ANSI_RESET);
            default -> { }
        }
    }

    public void showNewColor(String color) {
        switch (color) {
            case "z" -> System.out.println("\nSe cambio el color a " + ANSI_BLUE + "Azul" + ANSI_RESET );
            case "a" -> System.out.println("\nSe cambio el color a " + ANSI_YELLOW + "Amarillo" + ANSI_RESET );
            case "r" -> System.out.println("\nSe cambio el color a " + ANSI_RED + "Rojo" + ANSI_RESET );
            case "v" -> System.out.println("\nSe cambio el color a " + ANSI_GREEN + "Verde" + ANSI_RESET );
            default -> {
            }
        }
    }

    public void showAvailableColors() {
        System.out.println("\nCambio de color!");
        System.out.println(ANSI_BLUE + "Azul [Z]" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Amarillo [A]" + ANSI_RESET);
        System.out.println(ANSI_RED + "Rojo [R]" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Verde [V]" + ANSI_RESET);
    }

    public void showHumanPlay(String playerName, Card card) {
        System.out.println(ANSI_CYAN + "\n" + playerName + " jugo: " + showCurrentColor() + card.toString() + ANSI_RESET);
    }
    
    public void showBotPlay(String botName, Card card) {
        System.out.println(ANSI_CYAN + "\n" + botName + " jugo: " + showCurrentColor() + card.toString() + ANSI_RESET);
    }

    public void showDrawCard(String playerName) {
        System.out.println(ANSI_CYAN + "\n" + playerName + " no pudo jugar, robo una carta." + ANSI_RESET);
    }

    public void showRemakeDeck() {
        System.out.println(ANSI_CYAN + "\nEl mazo se acabo, agregando al mazo de nuevo..." + ANSI_RESET);
    }

    public void showInvalidColor() {
        System.out.println(ANSI_RED + "\nColor inválido." + ANSI_RESET);
    }

    public void showColorInput() {
        System.out.print(ANSI_YELLOW + "\nElige a que color cambiar: " + ANSI_RESET);
    }

    public void showInputPrompt() {
        System.out.print(ANSI_YELLOW + "\nElige el indice de la carta a jugar: " + ANSI_RESET);
    }
    
    public void showInvalidPlay() {
        System.out.println(ANSI_RED + "\nNo puedes jugar esa carta." + ANSI_RESET);
    }
    
    public void showIndexOutOfRange() {
        System.out.println(ANSI_RED + "\nIndice fuera de rango." + ANSI_RESET);
    }
   
    public void showInvalidInput() {
        System.out.println(ANSI_RED + "\nEntrada inválida." + ANSI_RESET);
    }

    public void turnSkipped(Player player) {
        System.out.println("\n" + ANSI_CYAN + player.toString() + " No puede jugar porque le bloquearon el turno" + ANSI_RESET);
    }

    public void sayUno(Player player) {
        System.out.println(ANSI_CYAN + "\n" + player.toString() + " dice UNO!" + ANSI_RESET);
    }

    public void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}
