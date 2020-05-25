
package Map;
////////////////////////////////////////////////////
//در این کلاس دو کلاس قاره و کشور پیاده سازی شده است//
////////////////////////////////////////////////////

import java.util.*;

import UI.Data;

public class Map {
    
    public static List<Continent> continents = new ArrayList<Continent>();
    public static List<Country> countries = new ArrayList<Country>();
    
    public static void Initialize()
    {
        InitializeContinent();
        InitializeCountries();
    }
    
    static void InitializeContinent()
    {
        Continent NAmerica = new Continent();
        NAmerica.Bountysoldier = 5;
        NAmerica.ContinentID = 0;
        NAmerica.ContinentName = "NAmerica";
        
        Continent SAmerica = new Continent();
        SAmerica.Bountysoldier = 2;
        SAmerica.ContinentID = 1;
        SAmerica.ContinentName = "SAmerica";

        Continent Africa = new Continent();
        Africa.Bountysoldier = 3;
        Africa.ContinentID = 2;
        Africa.ContinentName = "Africa";
        
        Continent Europe = new Continent();
        Europe.Bountysoldier = 5;
        Europe.ContinentID = 3;
        Europe.ContinentName = "Europe";

        Continent Asia = new Continent();
        Asia.Bountysoldier = 7;
        Asia.ContinentID = 4;
        Asia.ContinentName = "Asia";

        Continent Australia = new Continent();
        Australia.Bountysoldier = 2;
        Australia.ContinentID = 5;
        Australia.ContinentName = "Australia";

        continents.add(NAmerica);
        continents.add(SAmerica);
        continents.add(Africa);
        continents.add(Europe);
        continents.add(Asia);
        continents.add(Australia);
    }

    static void InitializeCountries()
    {
        String CountryNames[] = Data.CountryName;

        
        for(int i = 0; i < CountryNames.length;i++)
        {
            String CurrentContinent = CountryNames[i].split("\\.")[0];
            String CountryName = CountryNames[i].split("\\.")[1];

            Country NewCountry = new Country();
            NewCountry.CountryID = i;
            NewCountry.SetCountryName(CountryName);
            NewCountry.SetOwnerId(-1);
            NewCountry.SetNeighborsID(Data.NeighbourId[(i * 2) + 1]);

            countries.add(NewCountry);

            switch(CurrentContinent)
            {
                case "NAmerica" :
                    continents.get(0).CountriesID.add(i);
                    break;

                case "SAmerica" :
                    continents.get(1).CountriesID.add(i);
                    break;

                case "Africa" :
                    continents.get(2).CountriesID.add(i);
                    break;

                case "Europe" :
                    continents.get(3).CountriesID.add(i);
                    break;

                case "Asia" :
                    continents.get(4).CountriesID.add(i);
                    break;

                case "Australia" :
                    continents.get(5).CountriesID.add(i);
                    break;
            }
        }
    }

    public static Country getCountry(int countryId)
    {
        for(int i = 0; i< countries.size(); i++)
        {
            if(countries.get(i).CountryID == countryId)
            {
                return countries.get(i);
            }
        }

        return null;
    }
}
