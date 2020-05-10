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


    public boolean DeploySoldier(Country _Country , int SoldierCount)
    {
        int CurrentPlayerId = CurrentPlayer.getPlayerID();

        if(CurrentPlayerId == _Country.GetOwnerId())
        {
            _Country.AddSoldier(SoldierCount);

            return true;
        }
        else
        {
            return false;
            //Error on Invalid Player
        }
    }


//#region Move

    public boolean CheckConnection(Country _FromCountry , Country _ToCountry)
    {
        _FromCountry.SetChecked(true);
        int[] Neighbors = _FromCountry.GetNeighborsID();
        
        for(int i = 0;i < Neighbors.length; i++)
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

    public boolean Move()
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

//#endregion

    public void NextTurn(GameManager _GameManager)
    {
        int CurrentId = (CurrentPlayer != null ? CurrentPlayer.getPlayerID() : -1);

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





    public Country getFirstCountrySelected() {
        return FirstCountrySelected;
    }

    public Country getSecondCountrySelected() {
        return SecondCountrySelected;
    }


}