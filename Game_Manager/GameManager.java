package Game_Manager;

import Game_Manager.Turn.*;
import PlayerManager.Player;

public class GameManager {

    public SoldierManager soldierManager;
    public WarManger warManger;
    public TurnManager turnManager;
    public Move move;
    
    public GameManager(Player players[])
    {
        soldierManager = new SoldierManager();
        warManger = new WarManger();
        turnManager = new TurnManager(players);
        move = new Move();
    }

    public void InitializeGame()
    {
        soldierManager.Initialize();
    }
}