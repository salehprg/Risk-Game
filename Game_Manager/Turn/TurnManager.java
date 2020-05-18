package Game_Manager.Turn;

import java.util.List;

import Game_Manager.GameManager;
import Game_Manager.GameManager.State;
import Map.Country;
import Map.Map;
import PlayerManager.*;

public class TurnManager {
    Player CurrentPlayer;
    PlayerManager playerManager;
    
    
    Country FirstCountrySelected , SecondCountrySelected;


    public TurnManager(List<Player> player , Map _Map)
    {
        playerManager = new PlayerManager(player);    }

    public Player getCurrentPlayer()
    {
        return CurrentPlayer;
    }

    public void NextTurn(GameManager _GameManager)
    {
        int CurrentId = (CurrentPlayer != null ? CurrentPlayer.getPlayerID() : -1);  //Null just for the first time game Run

        if(CurrentId + 1 > playerManager.getPlayerLastIndex())
        {
            CurrentPlayer = playerManager.getPlayer(0);
        }
        else
        {
            CurrentPlayer = playerManager.getPlayer(CurrentId + 1);
        }

        _GameManager.ChangeState(State.DeploySoldier);
    }


//#region Deploy

    public boolean CheckDeploy(Country _Country)
    {
        int CurrentPlayerId = CurrentPlayer.getPlayerID();

        return (CurrentPlayerId == _Country.GetOwnerId());
    }

//#endregion

//#region Move

    public boolean CheckConnection(Country _FromCountry , Country _ToCountry)
    {
        _FromCountry.SetChecked(true);
        int[] Neighbors = _FromCountry.GetNeighborsID();
        
        for(int i = 0;i < Neighbors.length ; i++)
        {
            Country CurrentCountry = Map.getCountry(Neighbors[i]);

            if(Neighbors[i] == _ToCountry.GetCountryID())
            {
                return true;
            }
            else if(!CurrentCountry.GetChecked())
            {
                return CheckConnection(CurrentCountry, _ToCountry);
            }
            else
            {
                return false;
            }
        }

        return false;
    }

    public boolean CheckMove()
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

//#endregion

//#region War

    public boolean CheckWar()
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

    public void AddDefenderCountryToAttackerCountry()
    {
        SecondCountrySelected.SetOwnerId(CurrentPlayer.getPlayerID());
    }

//#endregion



//#region Country Action

    public void SetCountrySelected(Country _Country)
    {
        if(_Country.GetOwnerId() == CurrentPlayer.getPlayerID())
        {
            if(_Country.GetCountryID() == SecondCountrySelected.GetCountryID())
            {
                SecondCountrySelected = null;
                return;
            }
            if(_Country.GetCountryID() == FirstCountrySelected.GetCountryID())
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

    public void ClearSelectedCountry()
    {
        FirstCountrySelected = null;
        SecondCountrySelected = null;
    }
    public Country getFirstCountrySelected() {
        return FirstCountrySelected;
    }
    public Country getSecondCountrySelected() {
        return SecondCountrySelected;
    }

//#endregion
}