import Game_Manager.*;
import PlayerManager.Player;

public class MyRBG {

    public static void main(String[] args) {
        Player players[] = new Player[4];

        GameManager gameManager = new GameManager(players);
        
        gameManager.InitializeGame();
    }
    
}
