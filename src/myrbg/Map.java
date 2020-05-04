
package myrbg;

public class Map {
    
    public class Continent 
    {   
        private String CountryName ;
        private int CountryID ;
        private int CountriesID ;//کشور های هر قاه ID 
        private int Bountysoldier ; // تعداد سرباز اضافه برا تصاحب هر قاره

        public void setCountryName(String CountryName) {
            this.CountryName = CountryName;
        }

        public void setCountriesID(int CountriesID) {
            this.CountriesID = CountriesID;
        }

        public void setBountysoldier(int Bountysoldier) {
            this.Bountysoldier = Bountysoldier;
        }

        public void setCountryID(int CountryID) {
            this.CountryID = CountryID;
        }
        
        public String getCountryName() {
            return CountryName;
        }

        public int getCountriesID() {
            return CountriesID;
        }

        public int getBountysoldier() {
            return Bountysoldier;
        }

        public int getCountryID() {
            return CountryID;
        }
        
        
    }
    
    
    
    
    public class Country
    {
        private int ownerID ;
        private int SodierCount ;
        private int CountryID ;
        private int NeighborsID [];

        public void setOwnerID(int ownerID) {
            this.ownerID = ownerID;
        }

        public void setSodierCount(int SodierCount) {
            this.SodierCount = SodierCount;
        }

        public void setCountryID(int CountryID) {
            this.CountryID = CountryID;
        }

        public void setNeighborsID(int[] NeighborsID) {
            this.NeighborsID = NeighborsID;
        }

        
        
        
        public int getOwnerID() {
            return ownerID;
        }

        public int getSodierCount() {
            return SodierCount;
        }

        public int getCountryID() {
            return CountryID;
        }

        public int[] getNeighborsID() {
            return NeighborsID;
        }
        
        
        
        
    }
    
    
}
