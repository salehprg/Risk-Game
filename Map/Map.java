
package Map;
////////////////////////////////////////////////////
//در این کلاس دو کلاس قاره و کشور پیاده سازی شده است//
////////////////////////////////////////////////////

public class Map {
    
    public Continent continents[];
    public Country countries[];
    
    public Country getCountry(int countryId)
    {
        for(int i = 0; i< countries.length; i++)
        {
            if(countries[i].CountryID == countryId)
            {
                return countries[i];
            }
        }

        return null;
    }
}
