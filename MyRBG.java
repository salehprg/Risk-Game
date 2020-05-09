import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import Game_Manager.*;
import PlayerManager.Player;

public class MyRBG {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        
        Player player1 = new Player(0, "PlayerName", 0, 0);

        players.add(player1);

        GameManager gameManager = new GameManager(players);
        
        gameManager.InitializeGame();
    }
    
}
