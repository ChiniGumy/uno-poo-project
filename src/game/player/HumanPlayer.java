package game.player;

import game.UI;
import game.card.Card;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private final UI ui;
    Scanner scanner;

    public HumanPlayer() {
        this.scanner = new Scanner(System.in);
        this.ui = new UI();
    }

    @Override
    public String toString() {
        return "Jugador";
    }

    public void waitForUser() {
        System.out.println(UI.ANSI_YELLOW + "\nPresiona Enter para continuar..." + UI.ANSI_RESET);
        try {
            scanner.nextLine();
        } catch (Exception e) { }
    }

    @Override
    public Card playTurn(Card topCard) {
        while (true) {
            ui.showInputPrompt();
            String input = scanner.nextLine();
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < hand.size()) {
                    Card cardToPlay = hand.get(index);
                    if (cardToPlay.isPlayable(topCard)) {
                        removeCard(index);
                        return cardToPlay;
                    } else {
                        ui.showInvalidPlay();
                    }
                } else {
                    ui.showIndexOutOfRange();
                }
            } catch (NumberFormatException e) {
                ui.showInvalidInput();
            }
        }
    }
}
