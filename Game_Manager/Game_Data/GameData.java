package Game_Manager.Game_Data;

import Game_Manager.Turn.TurnManager;
import Map.Country;
import PlayerManager.Player;
import UI.Data;

public class GameData {

    TurnManager turnManager;
    Player CurrentPlayer;

    public GameData(TurnManager _turnManager)
    {
        turnManager = _turnManager;
    }

    void GetData()
    {
        CurrentPlayer = turnManager.getCurrentPlayer();
    }

    public void UpdateGameInfo()
    {
        GetData();
    }

    public void UpdateMapInfo(Country _country)
    {
        Data.buttons.get(_country.GetCountryID()).setText(String.valueOf(_country.GetSoldierCount()));
    }
}