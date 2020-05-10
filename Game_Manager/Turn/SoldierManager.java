package Game_Manager.Turn;

import PlayerManager.*;
import Map.*;

public class SoldierManager {

    public void GiveSoldierToPlayer(Player _Player)
    {
        _Player.setUnimployedSoldiersCount(SoldiersByContinent(_Player) + SoldiersByCountries(_Player));
        
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