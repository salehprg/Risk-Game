import java.util.ArrayList;
import java.util.List;
import Game_Manager.*;
import Game_Manager.Game_Data.GameData;
import PlayerManager.Player;
import PlayerManager.PlayerManager.PlayerColor;
import UI.*;

public class MyRBG {

    static int Res = -1;

    public static void Result(int data)
    {
        Res = data;
    }

    public static void main(String[] args) {

        UIManager uiManager = new UIManager();
        uiManager.InitializeMenu();

    }
}
