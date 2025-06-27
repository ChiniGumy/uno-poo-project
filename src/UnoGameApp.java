import game.*;
public class UnoGameApp {

    public static void main(String[] args) throws Exception {
        GameEngine gameEngine = new GameEngine();
        gameEngine.getPlayer().getHand();
        gameEngine.getBot().getHand();
    }
}
