package Game_Manager.Turn;

import PlayerManager.*;
import Map.*;

public class SoldierManager {

    public void GiveSoldierToPlayer(Player _Player)
    {
        _Player.setUnimployedSoldiersCount(SoldiersByContinent(_Player) + SoldiersByCountries(_Player));
        
    }

    public void MoveSoldier(int _SoldierToMove , TurnManager turnManager)
    {
        int SoldierOnFirstCountry = turnManager.getFirstCountrySelected().GetSoldierCount();
        int SoldierOnSecondCountry = turnManager.getSecondCountrySelected().GetSoldierCount();

        turnManager.getFirstCountrySelected().SetSoldierCount(SoldierOnFirstCountry - _SoldierToMove);
        turnManager.getSecondCountrySelected().SetSoldierCount(SoldierOnSecondCountry + _SoldierToMove);
    }

    public boolean DeploySoldier(int SoldierCount , Country ToCountry , TurnManager turnManager)
    {
        int UnEmployeedSoldier = turnManager.getCurrentPlayer().getUnimployedSoldiersCount();

        if(UnEmployeedSoldier >= SoldierCount)
        {
            if(turnManager.CheckDeploy(ToCountry))
            {
                ToCountry.AddSoldier(SoldierCount);
                turnManager.getCurrentPlayer().setUnimployedSoldiersCount(UnEmployeedSoldier - SoldierCount);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public void Initialize()
    {
        
    }

    int SoldiersByCountries(Player _Player)
    {
        return _Player.getCountriesCount() / 3;
    }

    int SoldiersByContinent(Player _Player)
    {
        Continent _continents[] = Map.continents;
        boolean HaveContinent = true;
        int result = 0;
        
        
        for(int i = 0; i < _continents.length; i++)
        {
            HaveContinent = true;

            Continent CurrentContinent = _continents[i];
            for(int j = 0; j < CurrentContinent.CountriesID.length; j++)
            {
                if(Map.getCountry(CurrentContinent.CountriesID[j]).GetOwnerId() != _Player.getPlayerID())
                {
                    HaveContinent = false;
                    break;
                }
            }

            if(HaveContinent)
            {
                result += CurrentContinent.Bountysoldier;
            }
        }

        return result;
    }
}