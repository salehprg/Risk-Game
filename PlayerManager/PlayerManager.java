package PlayerManager;
//در این کلاس اطلاعات بازیکن پیاده شده است
public class PlayerManager {

    Player players[];
    
    
    public PlayerManager(Player _Players[])
    {
        setPlayers(_Players);
    }
    
    /**
        * @param players the players to set
        */
    public void setPlayers(Player[] players) {
        this.players = players;
    }
   
    /**
     * @return the players
     */
    public Player getPlayer(int id) {
        Player result = null;

        for(int i = 0 ; i < players.length; i++)
        {
            if(players[i].getPlayerID() == id)
            {
                result = players[i];
            }
        }

        return result;
    }
    
    
}
