package Game_Manager;

import java.util.List;

import Game_Manager.Turn.*;
import Map.Country;
import Map.Map;
import PlayerManager.Player;

public class GameManager {

    public SoldierManager soldierManager;
    public WarManger warManger;
    public Map mapManger;
    public TurnManager turnManager;
    public Move move;
    
    public GameManager(List<Player> players)
    {
        soldierManager = new SoldierManager();
        warManger = new WarManger();
        mapManger = new Map();
        turnManager = new TurnManager(players , mapManger);
        move = new Move();
    }

    public void InitializeGame()
    {
        soldierManager.Initialize();
    }

    public boolean DeploySoldier(int SoldierCount , Country ToCountry)
    {
        return turnManager.DeploySoldier(ToCountry, SoldierCount);
    }
}