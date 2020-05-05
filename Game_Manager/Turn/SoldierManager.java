package Game_Manager.Turn;

import PlayerManager.*;
import Map.*;

public class SoldierManager {
    int SoldiersByCountries(Player _Player)
    {
        return _Player.CountriesCount / 3;
    }

    int SoldiersByContinent(Player _Player)
    {
        Continent _continents[] = Map.continents;

        for(int i =0; i < _continents.length; i++)
        {
            Continent CurrentContinent = _continents[i];
            for(int j = 0; j < _continents[i].CountriesID.length; j++)
            {
                
            }
        }
    }
}