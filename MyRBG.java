import java.util.ArrayList;
import java.util.List;
import Game_Manager.*;
import PlayerManager.Player;
import UI.Data;
import UI.UICreator;

public class MyRBG {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        
        Player player1 = new Player(0, "PlayerName", 0, 0);
        Player player2 = new Player(1, "Player2", 1, 0);

        players.add(player1);
        players.add(player2);


        GameManager gameManager = new GameManager(players);
        gameManager.InitializeGame();

        UICreator uiCreator = new UICreator(gameManager);
        uiCreator.Initialize();

        System.out.println(Data.NeighbourId[0][1]);
        System.out.println(Data.NeighbourId[0][2]);
        System.out.println(Data.NeighbourId[0][3]);

    }
}
