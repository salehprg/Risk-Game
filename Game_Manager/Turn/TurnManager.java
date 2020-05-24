package Game_Manager.Turn;

import java.util.List;

import Game_Manager.GameManager;
import Game_Manager.GameManager.State;
import Game_Manager.Game_Data.GameData;
import Map.Country;
import Map.Map;
import PlayerManager.*;
import UI.Data;

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
        return playerManager.getPlayers();
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
            CurrentPlayer = playerManager.getPlayer(0);
        }
        else
        {
            CurrentPlayer = playerManager.getPlayer(CurrentId + 1);
        }

        CurrentPlayer.setUnimployedSoldiersCount(4);
        CurrentPlayer.setIsActive(true);  // New Player

        GameManager.ChangeState(State.DeploySoldier);
        
        GameData.UpdateGameInfo();
        ClearSelectedCountry();
    }


//#region Deploy

    public static boolean CheckDeploy(Country _Country)
    {
        int CurrentPlayerId = CurrentPlayer.getPlayerID();

        return (CurrentPlayerId == _Country.GetOwnerId() || _Country.GetOwnerId() == -1 );
    }

//#endregion

//#region Move

    public static boolean CheckConnection(Country _FromCountry , Country _ToCountry)
    {
        _FromCountry.SetChecked(true);
        int[] Neighbors = _FromCountry.GetNeighborsID();
        boolean res = false;

        for(int i = 0;i < Neighbors.length ;i++)
        {
            if(!res)
            {
                Country CurrentCountry = Map.getCountry(Neighbors[i]);

                if(Neighbors[i] == _ToCountry.GetCountryID() && CurrentCountry.GetOwnerId() == TurnManager.CurrentPlayer.getPlayerID())
                {
                    System.out.println("True");
                    res = true;
                }
                else if(!CurrentCountry.GetChecked() && CurrentCountry.GetOwnerId() == TurnManager.CurrentPlayer.getPlayerID())
                {
                    System.out.println("Check");
                    if(CheckConnection(CurrentCountry, _ToCountry))
                    {
                        res = true;
                    }
                }
                else
                {
                    System.out.println("False");
                    res = false;
                }
            }
        }

        return res;
    }

    public static boolean CheckMove()
    {
        if(FirstCountrySelected.GetOwnerId() != CurrentPlayer.getPlayerID() || SecondCountrySelected.GetOwnerId() != CurrentPlayer.getPlayerID())
        {
            return false;
        }
        else
        {
            return CheckConnection(FirstCountrySelected, SecondCountrySelected);
        }
    }

    public static void CleanCheck()
    {
        for(int i = 0; i < Map.countries.size();i++)
        {
            Map.countries.get(i).SetChecked(false);
        }
    }
//#endregion

//#region War

    public static boolean CheckWar()
    {
        if(FirstCountrySelected.GetOwnerId() != CurrentPlayer.getPlayerID())
        {
            return false;
        }
        else
        {
            int[] AttackerNeighbour = FirstCountrySelected.GetNeighborsID();

            for(int i = 0; i < AttackerNeighbour.length; i++)
            {
                if(AttackerNeighbour[i] == SecondCountrySelected.GetCountryID())
                {
                    return true;
                }
            }
            return false;
        }
    }

    public static void AddDefenderCountryToAttackerCountry()
    {
        SecondCountrySelected.SetOwnerId(CurrentPlayer.getPlayerID());
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
                return;
            }
            if(FirstCountrySelected != null && _Country.GetCountryID() == FirstCountrySelected.GetCountryID())
            {
                FirstCountrySelected = null;
                return;
            }

            if(FirstCountrySelected == null)
            {
                FirstCountrySelected = _Country;
            }
            else
            {
                SecondCountrySelected = _Country;
            }
        }
        else
        {
            //Error on Invalid Player
        }
    }

    public static void ClearSelectedCountry()
    {
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