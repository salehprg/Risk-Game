
package myrbg;

//در این کلاس دو کلاس قاره و کشور پیاده سازی شده است


public class Map {
    
//کلاس اول مربوط به قاره است
    public class Continent 
    {   
        private String CountryName ;//اسم هر قاره
        private int ContinentID ;//ID هر قاره
        private int CountriesID[] ;//کشور های هر قاه ID 
        private int Bountysoldier ; // تعداد سرباز اضافه در صورت تصاحب کامل هر قاره
        
        
        ////////////////////////////////////////////////////////////////////////
        
        public Continent(String CountryName, int CountinentID, int Countries[], int Bountysoldire)
        {
            setCountryName(CountryName);
            setContinentID(ContinentID);
            setCountriesID(CountriesID);
            setBountysoldier(Bountysoldier);
        }

        ////////////////////////////////////////////////////////////////////////
        
        public void setCountryName(String CountryName) {
            this.CountryName = CountryName;
        }

        public void setCountriesID(int[] CountriesID) {
            this.CountriesID = CountriesID;
        }

        public void setBountysoldier(int Bountysoldier) {
            this.Bountysoldier = Bountysoldier;
        }

        public void setContinentID(int ContinentID) {
            this.ContinentID = ContinentID;
        }
        
        ////////////////////////////////////////////////////////////////////////
        
        public String getCountryName() {
            return CountryName;
        }

        public int[] getCountriesID() {
            return CountriesID;
        }

        public int getBountysoldier() {
            return Bountysoldier;
        }

        public int getContinentID() {
            return ContinentID;
        }
        
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //کلاس دوم مربوط به کشور است
    public class Country
    {
        private int ownerID ;//بازیکن صاحب کشورID
        private int SoldierCount ;//تعداد سرباز موجود در این کشور
        private int CountryID ;//کشورID
        private int NeighborsID [];//کشور های مجاورID
        
        ////////////////////////////////////////////////////////////////////////

        public Country(int CountryID, int OwnerID, int SoldierCount, int NeighborsID[])
    {
        setCountryID(CountryID);
        setOwnerID(OwnerID);
        setSoldierCount(SoldierCount);
        setNeighborsID(NeighborsID);
    }
        
        ////////////////////////////////////////////////////////////////////////
        
        public void setOwnerID(int ownerID) {
            this.ownerID = ownerID;
        }

        public void setSoldierCount(int SoldierCount) {
            this.SoldierCount = SoldierCount;
        }

        public void setCountryID(int CountryID) {
            this.CountryID = CountryID;
        }

        public void setNeighborsID(int[] NeighborsID) {
            this.NeighborsID = NeighborsID;
        }

        ////////////////////////////////////////////////////////////////////////
        
        public int getOwnerID() {
            return ownerID;
        }

        public int getSoldierCount() {
            return SoldierCount;
        }

        public int getCountryID() {
            return CountryID;
        }

        public int[] getNeighborsID() {
            return NeighborsID;
        }
        
               
    }
    
    
}
