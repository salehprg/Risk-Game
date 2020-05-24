package UI;

import java.util.List;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Game_Manager.GameManager;
import Game_Manager.Turn.TurnManager;
import Map.Country;
import PlayerManager.Player;
import PlayerManager.PlayerManager;

public class UIManager {
    
    public static JFrame frame;
    UIUtilities uiUtilities;
    UICreator uiCreator;
    GameManager gameManager;

    int result = -1;
    boolean Opened = false;

    public UIManager()
    {
        GameManager _gameManager = new GameManager(this);
        gameManager = _gameManager;
        uiCreator = new UICreator(_gameManager);
        uiUtilities = new UIUtilities(this);
    }

    public void InitializeMenu()
    {
        frame = uiCreator.InitializeMainMenu();
    }

    public void InitializeGame()
    {
        frame = uiCreator.InitializeMainGame();
    }

    public static void UpdateMapInfo(Country _country)
    {
        JButton CountryButton = Data.buttons.get(_country.GetCountryID());

        ImageIcon CountryIcon = new ImageIcon();
        String AppPath = Data.GetAppPath();

        switch(PlayerManager.getPlayer(_country.GetOwnerId()).getPlayerColor())
        {
            case Blue:
                CountryIcon = new ImageIcon(AppPath + "\\UI\\Images\\Players\\Blue.png");
                break;

            case Red:
                CountryIcon = new ImageIcon(AppPath + "\\UI\\Images\\Players\\Red.png");
                break;

            case Green:
                CountryIcon = new ImageIcon(AppPath + "\\UI\\Images\\Players\\Green.png");
                break;

            case Yellow:
                CountryIcon = new ImageIcon(AppPath + "\\UI\\Images\\Players\\Yellow.png");
                break;
        }

        CountryButton.removeAll();
        CountryButton.repaint();

        CountryButton.setIcon(new ImageIcon(CountryIcon.getImage().getScaledInstance(CountryButton.getWidth() , CountryButton.getHeight() ,java.awt.Image.SCALE_SMOOTH)));

        JLabel SoldierCount = new JLabel(String.valueOf(_country.GetSoldierCount()));
        CountryButton.setLayout(null);

        SoldierCount.setBounds((CountryButton.getWidth() - 12) / 2 , (CountryButton.getHeight() - 10) / 2 , 20, 10);
        SoldierCount.setLayout(null);

        CountryButton.add(SoldierCount , null);

        if(_country.GetSelected())
        {
            JLabel selected = new JLabel("^");
            selected.setLayout(null);
            selected.setBounds((CountryButton.getWidth()) / 2 , (CountryButton.getHeight() - 10) / 2 , 20, 10);
            CountryButton.add(selected , null);
        }

        Data.buttons.set(_country.GetCountryID(), CountryButton);
    }
    
    public static void UpdateGameInfo()
    {
        List<Player> players = TurnManager.getPlayerList();

        if(Data.PlayerSoldierLabels.size() != 0)
        {
            
            for(int i = 0; i < players.size(); i++)
            {
                Player SelectedPlayer = players.get(i);
                JLabel playerName = Data.PlayerNameLabels.get(i);
                JLabel playerUnEmSoldier = Data.PlayerSoldierLabels.get(i);
                JLabel playerCountrycount = Data.PlayerCountryCount.get(i);

                playerUnEmSoldier.setText(String.valueOf(SelectedPlayer.getUnimployedSoldiersCount()));
                playerCountrycount.setText(String.valueOf(SelectedPlayer.getCountriesCount()));

                if(SelectedPlayer.GetIsActive())
                {
                    playerName.setText(SelectedPlayer.getPlayerName() + "^" + GameManager.CurrentState);
                }
                else
                {
                    playerName.setText(SelectedPlayer.getPlayerName());
                }
    
            }
        }
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void OpenWar_Dialog(InputModel input)
    {
        uiUtilities.OpenWarDialog(input);
    }

    public void OpenWarResult_Dialog(InputModel input)
    {
        uiUtilities.OpenWarResultDialog(input);
    }

    public void OpenSoldierMoveInput_Dialog(InputModel input)
    {
        uiUtilities.OpenMoveDialog(input);
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