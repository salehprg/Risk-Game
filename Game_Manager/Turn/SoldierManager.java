package Game_Manager.Turn;

import PlayerManager.*;
import Map.*;

public class SoldierManager {

    void GiveSoldierToPlayer(Player _Player)
    {
        _Player.UnimployedSoldiersCount += SoldiersByContinent(_Player) + SoldiersByCountries(_Player);
    }



    int SoldiersByCountries(Player _Player)
    {
        return _Player.CountriesCount / 3;
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
                if(Map.getCountry(CurrentContinent.CountriesID[j]).ownerID != _Player.ID)
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