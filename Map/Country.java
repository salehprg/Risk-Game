package Map;

public class Country
    {
        int ownerID ;//بازیکن صاحب کشورID
        int SoldierCount ;//تعداد سرباز موجود در این کشور
        int CountryID ;//کشورID
        String CountryName ;//کشورID
        int NeighborsID [];//کشور های مجاورID
        boolean Checked; //برای چک کردن راه ارتباطی
        boolean Selected; //چک کردن در حالت انتخاب یا نه
        
        public boolean GetChecked()
        {
            return Checked;
        }
        public void SetChecked(Boolean _Checked)
        {
            Checked = _Checked;
        }

        public boolean GetSelected()
        {
            return Selected;
        }
        public void SetSelected(Boolean _Selected)
        {
            Selected = _Selected;
        }

        public int GetOwnerId()
        {
            return ownerID;
        }
        public void SetOwnerId(int _OwnerId)
        {
            ownerID = _OwnerId;
        }


        public int GetSoldierCount()
        {
            return SoldierCount;
        }
        public void SetSoldierCount(int _SoldierCount)
        {
            SoldierCount = _SoldierCount;
        }


        public void AddSoldier(int _SoldierCount)
        {
            SoldierCount += _SoldierCount;
        }
        public void RemoveSoldier(int _SoldierCount)
        {
            SoldierCount -= _SoldierCount;
        }


        public int GetCountryID()
        {
            return CountryID;
        }
        public void SetCountryID(int _CountryID)
        {
            CountryID = _CountryID;
        }


        public int[] GetNeighborsID()
        {
            return NeighborsID;
        }
        public void SetNeighborsID(int _NeighborsID[])
        {
            NeighborsID = _NeighborsID;
        }

        public void SetCountryName(String _CountryName)
        {
            CountryName = _CountryName;
        }

        public String GetCountryName()
        {
            return CountryName;
        }
               
    }