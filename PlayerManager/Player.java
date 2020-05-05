package PlayerManager;

public class Player {
    private int PlayerID ;
    private String PlayerName ;
    private int PlayerColor ;
    private int CountriesCount ;
    private int UnimployedSoldiersCount ;
    private boolean IsActive ;
    private int Status ;
    
    
   public Player(int PlayerID, String PlayerName, int PlayerColor, int CountriesCount )
   {
       setPlayerID(PlayerID);
       setPlayerName(PlayerName);
       setPlayerColor(PlayerColor);
       setCountriesCount(CountriesCount);
       setUnimployedSoldiersCount(0);
       setIsActive(false);
   }
   
   
    public void setPlayerID(int PlayerID) {
        this.PlayerID = PlayerID;
    }

    public void setPlayerName(String PlayerName) {
        this.PlayerName = PlayerName;
    }

    public void setPlayerColor(int PlayerColor) {
        this.PlayerColor = PlayerColor;
    }

    public void setCountriesCount(int CountriesCount) {
        this.CountriesCount = CountriesCount;
    }

    public void setUnimployedSoldiersCount(int UnimployedSoldiersCount) {
        this.UnimployedSoldiersCount = UnimployedSoldiersCount;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public int getPlayerColor() {
        return PlayerColor;
    }

    public int getCountriesCount() {
        return CountriesCount;
    }

    public int getUnimployedSoldiersCount() {
        return UnimployedSoldiersCount;
    }

    public boolean isIsActive() {
        return IsActive;
    }

    public int getStatus() {
        return Status;
    }
}