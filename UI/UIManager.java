package UI;

import javax.swing.JFrame;

import Game_Manager.GameManager;

public class UIManager {
    
    JFrame frame;
    UIUtilities uiUtilities;
    UICreator uiCreator;

    int result = -1;
    boolean Opened = false;

    public UIManager(GameManager _GameManager)
    {
        uiCreator = new UICreator(_GameManager);
        uiUtilities = new UIUtilities(this);
    }

    public void Initialize()
    {
        frame = uiCreator.Initialize();
    }

    public int OpenWarDialog()
    {
    
        if(!Opened)
        {
            uiUtilities.OpenWarDialog();
            Opened = true;
        }

        return result;
    }    

    public int GetwarResult()
    {
        return result;
    }
    public void warResult(int data)
    {
        result = data;
    }
}