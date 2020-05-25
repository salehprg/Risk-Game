package Game_Manager.Game_Data;

import Game_Manager.Turn.TurnManager;
import Map.Map;
import PlayerManager.PlayerManager;
import UI.UIManager;

public class GameData {
    

    public static void UpdateGameInfo()
    {
        UIManager.UpdateGameInfo();
    }

    public static void UpdateMapInfo()
    {
        int PlayerId = -1;
        boolean PlayerWin = true;

        for(int i = 0;i < PlayerManager.getPlayers().size();i++)
        {
            PlayerManager.getPlayer(i).SetCountriesCount(0);
        }

        for(int i = 0; i < Map.countries.size(); i++)
        {
            int SelectedCountryOwnerId = Map.countries.get(i).GetOwnerId();

            if(SelectedCountryOwnerId != -1)
            {
                
                PlayerManager.getPlayer(SelectedCountryOwnerId).AddCountriesCount(1);

                if(PlayerId == -1)
                {
                    PlayerId = Map.countries.get(i).GetOwnerId();
                }
                else
                {
                    if(PlayerId != Map.countries.get(i).GetOwnerId())
                    {
                        PlayerWin = false;
                    }
                }
                UIManager.UpdateMapInfo(Map.countries.get(i));
            }
        }

        if(PlayerWin)
        {
            System.out.println(TurnManager.getCurrentPlayer().getPlayerName() + " Win !");
        }
    }

    public static void UpdateInfo()
    {
        
    }
    
}