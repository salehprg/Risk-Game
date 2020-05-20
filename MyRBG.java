import java.util.ArrayList;
import java.util.List;
import Game_Manager.*;
import Game_Manager.Game_Data.GameData;
import PlayerManager.Player;
import PlayerManager.PlayerManager.PlayerColor;
import UI.*;

public class MyRBG {

    static int Res = -1;

    public static void Result(int data)
    {
        Res = data;
    }

    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        
        Player player1 = new Player(0, "PlayerName", PlayerColor.Blue, 0);
        Player player2 = new Player(1, "Player2",  PlayerColor.Red, 0);

        player1.setUnimployedSoldiersCount(4);
        player2.setUnimployedSoldiersCount(6);
        players.add(player1);
        players.add(player2);


        GameManager gameManager = new GameManager(players);
        gameManager.InitializeGame();

        UIManager uiManager = new UIManager(gameManager);
        uiManager.Initialize();

        GameData.UpdateGameInfo();
    }
}
