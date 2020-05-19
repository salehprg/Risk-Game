package UI;

import javax.swing.JFrame;

import Game_Manager.GameManager;
import Map.Country;

public class UIManager {
    
    static JFrame frame;
    UIUtilities uiUtilities;
    UICreator uiCreator;
    GameManager gameManager;

    int result = -1;
    boolean Opened = false;

    public UIManager(GameManager _GameManager)
    {
        gameManager = _GameManager;
        uiCreator = new UICreator(_GameManager);
        uiUtilities = new UIUtilities(this);
    }

    public void Initialize()
    {
        frame = uiCreator.Initialize();
    }

    public static void UpdateMapInfo(Country _country)
    {
        Data.buttons.get(_country.GetCountryID()).setText(String.valueOf(_country.GetSoldierCount()));
    }
    
    public void UpdateGameInfo()
    {

    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void OpenSoldierInput_Dialog(InputModel input)
    {
        uiUtilities.OpenInputSoldierDeployDialog(input);
    }

    public void SetDialogResult(InputModel data)
    {
        gameManager.CountryUIClick(null , true , data);
    }
}