package Game_Manager.Turn;

import java.util.List;

import javax.swing.JLabel;

import Game_Manager.GameManager;
import Game_Manager.GameManager.State;
import Game_Manager.Game_Data.GameData;
import Map.Country;
import Map.Map;
import PlayerManager.*;
import UI.Data;
import UI.UIManager;

public class TurnManager {
    static Player CurrentPlayer;
    static PlayerManager playerManager;
    static GameManager gameManager;
    
    
    static Country FirstCountrySelected , SecondCountrySelected;

    public TurnManager(List<Player> player , GameManager _gameManager)
    {
        playerManager = new PlayerManager(player);    
        gameManager = _gameManager;
    }

    public static List<Player> getPlayerList()
    {
        return PlayerManager.getPlayers();
    }
    
    public static Player getCurrentPlayer()
    {
        return CurrentPlayer;
    }

    public void NextTurn()
    {
        int CurrentId = (CurrentPlayer != null ? CurrentPlayer.getPlayerID() : -1);  //Null just for the first time game Run

        if(CurrentPlayer != null)
        {
            CurrentPlayer.setIsActive(false);  //Old player
        }

        if(CurrentId + 1 > playerManager.getPlayerLastIndex())
        {
            CurrentPlayer = PlayerManager.getPlayer(0);
        }
        else
        {
            CurrentPlayer = PlayerManager.getPlayer(CurrentId + 1);
        }

        if(!CurrentPlayer.getIsLost())
        {
            CurrentPlayer.setUnimployedSoldiersCount(4);
            CurrentPlayer.setIsActive(true);  // New Player

            GameManager.ChangeState(State.DeploySoldier);
            
            GameData.UpdateGameInfo();
            ClearSelectedCountry();
            GameData.UpdateMapInfo();
        }
        else
        {
            NextTurn();
        }
    }


//#region Deploy

    public static boolean CheckDeploy(Country _Country)
    {
        int CurrentPlayerId = CurrentPlayer.getPlayerID();

        return (CurrentPlayerId == _Country.GetOwnerId() || _Country.GetOwnerId() == -1 );
    }

//#endregion

//#region Move

    
//#endregion

//#region War

    

    public static void AddDefenderCountryToAttackerCountry()
    {
        PlayerManager.getPlayer(SecondCountrySelected.GetOwnerId()).AddCountriesCount(-1);
        SecondCountrySelected.SetOwnerId(CurrentPlayer.getPlayerID());
        CurrentPlayer.AddCountriesCount(1);
    }

//#endregion



//#region Country Action

    public static void SetCountrySelected(Country _Country)
    {
        if((GameManager.CurrentState != State.War && _Country.GetOwnerId() == CurrentPlayer.getPlayerID())
            || (GameManager.CurrentState == State.DeploySoldier && _Country.GetOwnerId() == -1)
            || (GameManager.CurrentState == State.War && FirstCountrySelected == null && _Country.GetOwnerId() == CurrentPlayer.getPlayerID())
            || (GameManager.CurrentState == State.War && SecondCountrySelected == null && _Country.GetOwnerId() != CurrentPlayer.getPlayerID()))
        {
            if(SecondCountrySelected != null && _Country.GetCountryID() == SecondCountrySelected.GetCountryID())
            {
                SecondCountrySelected = null;
                SecondCountrySelected.SetSelected(false);
                return;
            }
            if(FirstCountrySelected != null && _Country.GetCountryID() == FirstCountrySelected.GetCountryID())
            {
                FirstCountrySelected = null;
                FirstCountrySelected.SetSelected(false);
                return;
            }

            if(FirstCountrySelected == null)
            {
                FirstCountrySelected = _Country;
                FirstCountrySelected.SetSelected(true);
            }
            else
            {
                SecondCountrySelected = _Country;
                SecondCountrySelected.SetSelected(true);
            }
        }
        else
        {
            //Error on Invalid Player
        }
    }

    public static void ClearSelectedCountry()
    {
        if(FirstCountrySelected != null)
        {
            FirstCountrySelected.SetSelected(false);
        }
        if(SecondCountrySelected != null)
        {
            SecondCountrySelected.SetSelected(false);
        }

        FirstCountrySelected = null;
        SecondCountrySelected = null;
        
    }
    
    public static Country getFirstCountrySelected() {
        return FirstCountrySelected;
    }
    
    public static Country getSecondCountrySelected() {
        return SecondCountrySelected;
    }

//#endregion
}