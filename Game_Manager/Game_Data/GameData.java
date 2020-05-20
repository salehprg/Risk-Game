package Game_Manager.Game_Data;

import Game_Manager.Turn.TurnManager;
import Map.Country;
import PlayerManager.Player;
import UI.Data;
import UI.UIManager;

public class GameData {


    public static void UpdateGameInfo()
    {
        UIManager.UpdateGameInfo();
    }

    public static void UpdateMapInfo()
    {
        if(TurnManager.getFirstCountrySelected() != null)
        {
            UIManager.UpdateMapInfo(TurnManager.getFirstCountrySelected());
        }
        if(TurnManager.getSecondCountrySelected() != null)
        {
            UIManager.UpdateMapInfo(TurnManager.getSecondCountrySelected());
        }
    }
}