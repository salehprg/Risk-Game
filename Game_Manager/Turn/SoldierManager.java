package Game_Manager.Turn;

import PlayerManager.*;

import java.util.*;

import Game_Manager.Game_Data.GameData;
import Map.*;
import Map.Map;

public class SoldierManager {

    public static void GiveSoldierToPlayer(Player _Player)
    {
        _Player.setUnimployedSoldiersCount(SoldiersByContinent(_Player) + SoldiersByCountries(_Player));
        
    }

    public static boolean MoveSoldier(int _SoldierToMove)
    {
        int SoldierOnFirstCountry = TurnManager.getFirstCountrySelected().GetSoldierCount();
        int SoldierOnSecondCountry = TurnManager.getSecondCountrySelected().GetSoldierCount();

        if(_SoldierToMove <= SoldierOnFirstCountry - 1)
        {
            TurnManager.getFirstCountrySelected().SetSoldierCount(SoldierOnFirstCountry - _SoldierToMove);
            TurnManager.getSecondCountrySelected().SetSoldierCount(SoldierOnSecondCountry + _SoldierToMove);

            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean DeploySoldier(int SoldierCount)
    {
        int UnEmployeedSoldier = TurnManager.getCurrentPlayer().getUnimployedSoldiersCount();
        Country ToCountry = TurnManager.getFirstCountrySelected();

        if(UnEmployeedSoldier >= SoldierCount && SoldierCount != 0)
        {
            if(TurnManager.CheckDeploy(ToCountry))
            {
                ToCountry.AddSoldier(SoldierCount);
                ToCountry.SetOwnerId(TurnManager.CurrentPlayer.getPlayerID());
                TurnManager.getCurrentPlayer().setUnimployedSoldiersCount(UnEmployeedSoldier - SoldierCount);

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
    
    public static void Initialize(int n)
    {
        int [] CountryID = new int [41] ;//ID player owner dar khane haye arraye gharar migirnd
        int limit = 41 / n ;//had aghal tedad country baraye har player
        int testCountry ;
        Random myRandom = new Random();
        int  remaining = 0 ;
        int [] remainingSoldierPlayer = new int [n + 1] ;//meghdar sarbaz baghi mande baad az taghsim countris
        
        for(int i = 0 ; i < 41 ; i++)
        {
            CountryID[i] = 0 ;
        }//meghdar 0 baraye tamam country ha
        
           
        for(int i = 0 ; i < n ;i++)
        {
            int CountryCount = 0 ; // tedad country haye har player
            
            while( limit >= CountryCount )
            {
                testCountry = myRandom.nextInt(41) ;
                if(CountryID[testCountry] != 0)//saheb nadasht
                {
                    CountryID[testCountry] = i + 1 ;//player i+1 owner jadid mishavad
                    CountryCount++ ;
                }
            }    
        }

        for(int i =0 , j = 0; i < 41 ; i++)//peyda kardan owner country haye baghi monde
        {
            if(CountryID[i] == 0)
            {
                CountryID[i] = remaining + 1 ;
                remaining ++ ;
            }
        }

        if ( n == 2)//halat mokhtalef tedad sarbaz baraye n (tedad player) mokhtalef
        {
            for(int i = 0 ; i < n ; i++)
            {
                if( i < remaining)
                {
                    remainingSoldierPlayer [i + 1] = 30 - (limit + 1);
                }else
                {
                    remainingSoldierPlayer [i + 1] = 30 - (limit) ;
                }

            }
        }else if ( n == 3)
        {
            for(int i = 0 ; i < n ; i++)
            {
                if( i < remaining)
                {
                    remainingSoldierPlayer [i + 1] = 25 - (limit+ 1);
                }else
                {
                    remainingSoldierPlayer [i + 1] = 25 - (limit) ;
                }

            }
        } else if ( n == 4)
        {
            for(int i = 0 ; i < n ; i++)
            {
                if( i < remaining)
                {
                    remainingSoldierPlayer [i + 1] = 20 - (limit+ 1);
                }else
                {
                    remainingSoldierPlayer [i + 1] = 20 - (limit) ;
                }
            }
        }   
    }

    static int SoldiersByCountries(Player _Player)
    {
        return _Player.getCountriesCount() / 3;
    }

    static int SoldiersByContinent(Player _Player)
    {
        List<Continent> _continents = Map.continents;
        boolean HaveContinent = true;
        int result = 0;
        
        
        for(int i = 0; i < _continents.size(); i++)
        {
            HaveContinent = true;

            Continent CurrentContinent = _continents.get(i);
            for(int j = 0; j < CurrentContinent.CountriesID.size(); j++)
            {
                if(Map.getCountry(CurrentContinent.CountriesID.get(j)).GetOwnerId() != _Player.getPlayerID())
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