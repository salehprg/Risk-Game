package PlayerManager;

import java.util.List;

//در این کلاس اطلاعات بازیکن پیاده شده است
public class PlayerManager {

    static List<Player> players;
    
    public enum PlayerColor
    {
        Red,
        Blue,
        Black,
        Green
    }
    
    public PlayerManager(List<Player> _Players)
    {
        setPlayers(_Players);
    }
    


    public static List<Player> getPlayers() {
        return players;
    }

    /**
        * @param _players the players to set
        */
    public static void setPlayers(List<Player> _players) {
        players = _players;
    }
   
    /**
     * @return the players
     */
    public static Player getPlayer(int id) {
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
