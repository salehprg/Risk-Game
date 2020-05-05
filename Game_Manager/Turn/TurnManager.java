package Game_Manager.Turn;

import PlayerManager.*;

public class TurnManager {
    Player CurrentPlayer;
    PlayerManager playerManager;

    public TurnManager(Player player[])
    {
        playerManager = new PlayerManager(player);
    }
    void NextPlayer()
    {
        
    }
}