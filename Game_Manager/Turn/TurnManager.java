package Game_Manager.Turn;

import java.util.List;

import Game_Manager.GameManager;
import Map.Country;
import Map.Map;
import PlayerManager.*;

public class TurnManager {
    Player CurrentPlayer;
    PlayerManager playerManager;
    Map mapManager;
    
    enum State
    {
        DeploySoldier,
        Move,
        War
    }

    State CurrentState;
    
    Country FirstCountrySelected , SecondCountrySelected;


    public TurnManager(List<Player> player , Map _Map)
    {
        playerManager = new PlayerManager(player);
        mapManager = _Map;
    }

    public Player getCurrentPlayer()
    {
        return CurrentPlayer;
    }

    public boolean DeploySoldier(Country _Country , int SoldierCount)
    {
        if(CurrentState != State.DeploySoldier)
        {
            return false;
        }

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

    public boolean CheckConnection(Country _FromCountry , Country _ToCountry)
    {
        _FromCountry.SetChecked(true);
        int[] Neighbors = _FromCountry.GetNeighborsID();
        
        for(int i = 0;i < Neighbors.length; i++)
        {
            Country CurrentCountry = mapManager.getCountry(Neighbors[i]);

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

    public boolean Move(Country FromCountry , Country ToCountry)
    {
        if(FromCountry.GetOwnerId() != CurrentPlayer.getPlayerID() || ToCountry.GetOwnerId() != CurrentPlayer.getPlayerID())
        {
            return false;
        }
        else
        {
            return CheckConnection(FromCountry, ToCountry);
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

    public void NextPlayer()
    {
        int CurrentId = CurrentPlayer.getPlayerID();

        if(CurrentId + 1 > playerManager.getPlayerLastIndex())
        {
            CurrentPlayer = playerManager.getPlayer(0);
        }
        else
        {
            CurrentPlayer = playerManager.getPlayer(CurrentId + 1);
        }

    }


}