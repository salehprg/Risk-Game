package PlayerManager;

import java.util.List;

//در این کلاس اطلاعات بازیکن پیاده شده است
public class PlayerManager {

    List<Player> players;
    
    
    public PlayerManager(List<Player> _Players)
    {
        setPlayers(_Players);
    }
    
    /**
        * @param players the players to set
        */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
   
    /**
     * @return the players
     */
    public Player getPlayer(int id) {
        return players.get(id);
    }

    public int getPlayerLastIndex()
    {
        return players.size() - 1;
    }
    
    public void GiveSoldierToPlayer()
    {
        
    }
}
