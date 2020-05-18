package UI;

import javax.swing.JFrame;

import Game_Manager.GameManager;
import Map.Country;

public class UIManager {
    
    JFrame frame;
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

    // public void OpenWarDialog()
    // {
    //     uiUtilities.OpenWarDialog();
    // }    

    public void OpenSoldierInput_Dialog()
    {
        uiUtilities.OpenInputSoldierDeployDialog();
    }

    public void SetDialogResult(InputModel data)
    {
        gameManager.CountryUIClick(null , true , data);
    }
}