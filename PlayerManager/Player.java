package PlayerManager;

import PlayerManager.PlayerManager.PlayerColor;

public class Player {
    private int PlayerID ;
    private String PlayerName ;
    private PlayerColor PlayerColor ;
    private int CountriesCount ;
    private int UnimployedSoldiersCount ;
    private int FirstRunUnimployedSoldiersCount ;
    private boolean IsActive ;
    private boolean Lost ;
    
    
   public Player(int PlayerID, String PlayerName, PlayerColor PlayerColor)
   {
       setPlayerID(PlayerID);
       setPlayerName(PlayerName);
       setPlayerColor(PlayerColor);
       setUnimployedSoldiersCount(0);
       setIsActive(false);
   }
   
   
    public void setPlayerID(int PlayerID) {
        this.PlayerID = PlayerID;
    }

    public void setPlayerName(String PlayerName) {
        this.PlayerName = PlayerName;
    }

    public void setPlayerColor(PlayerColor PlayerColor) {
        this.PlayerColor = PlayerColor;
    }

    public void SetCountriesCount(int CountriesCount) {
        this.CountriesCount = CountriesCount;
    }

    public void AddCountriesCount(int CountriesCount) {
        this.CountriesCount += CountriesCount;
    }

    public void setUnimployedSoldiersCount(int UnimployedSoldiersCount) {
        this.UnimployedSoldiersCount = UnimployedSoldiersCount;
    }

    public void setFirstRunUnimployedSoldiersCount(int FirstRunUnimployedSoldiersCount) {
        this.FirstRunUnimployedSoldiersCount = FirstRunUnimployedSoldiersCount;
    }

    public int getFirstRunUnimployedSoldiersCount() {
        return FirstRunUnimployedSoldiersCount;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }

    public void setStatus(boolean IsLost) {
        this.Lost = IsLost;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public PlayerColor getPlayerColor() {
        return PlayerColor;
    }

    public int getCountriesCount() {
        return CountriesCount;
    }

    public int getUnimployedSoldiersCount() {
        return UnimployedSoldiersCount;
    }

    public boolean GetIsActive() {
        return IsActive;
    }

    public boolean getIsLost() {
        return Lost;
    }
}