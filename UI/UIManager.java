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

    public static JLabel lblState;

    public UIManager()
    {
        GameManager _gameManager = new GameManager(this);
        gameManager = _gameManager;
        uiCreator = new UICreator(_gameManager);
        uiUtilities = new UIUtilities(this);
    }

    public void InitializeMenu()
    {
        try
        {
            frame = uiCreator.InitializeMainMenu();
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound."); 
            ex.printStackTrace(); 
        }
    }

    public

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
                CountryIcon = new ImageIcon(AppPath + "\\UI\\Images\\Players\\armies\\blue_soldier.png");
                break;

            case Red:
                CountryIcon = new ImageIcon(AppPath + "\\UI\\Images\\Players\\armies\\red_soldier.png");
                break;

            case Black:
                CountryIcon = new ImageIcon(AppPath + "\\UI\\Images\\Players\\armies\\black_soldier.png");
                break;

            case Green:
                CountryIcon = new ImageIcon(AppPath + "\\UI\\Images\\Players\\armies\\green_soldier.png");
                break;
        }

        CountryButton.removeAll();
        CountryButton.repaint();

        // CountryButton.setIcon(new ImageIcon(CountryIcon.getImage().getScaledInstance(25 , CountryButton.getHeight() + 5 ,java.awt.Image.SCALE_SMOOTH)));

        JLabel playerImg = new JLabel(new ImageIcon(CountryIcon.getImage().getScaledInstance(30 , CountryButton.getHeight() + 10 ,java.awt.Image.SCALE_SMOOTH)));
        playerImg.setLayout(null);
        CountryButton.add(playerImg);

        playerImg.setBounds(0, 0, 30, CountryButton.getHeight() + 10);

        JLabel SoldierCount = new JLabel(String.valueOf(_country.GetSoldierCount()));
        CountryButton.setLayout(null);

        SoldierCount.setBounds((CountryButton.getWidth() - 10) / 2 , (CountryButton.getHeight() - 10) / 2 , 20, 10);
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

                if(SelectedPlayer.getPlayerID() == TurnManager.getCurrentPlayer().getPlayerID())
                {
                    ImageIcon PlayerState = new ImageIcon();
                    String AppPath = Data.GetAppPath();

                    switch(GameManager.CurrentState)
                    {
                        case Move:
                            PlayerState = new ImageIcon(AppPath + "\\UI\\Images\\the final\\Move.png");
                            break;

                        case War:
                            PlayerState = new ImageIcon(AppPath + "\\UI\\Images\\the final\\War.png");
                            break;

                        case DeploySoldier:
                            PlayerState = new ImageIcon(AppPath + "\\UI\\Images\\the final\\Deploy.png");
                            break;
                    }


                    // PlayerState.setIcon(new ImageIcon(CountryIcon.getImage().getScaledInstance(25 , PlayerState.getHeight() + 5 ,java.awt.Image.SCALE_SMOOTH)));

                    if(lblState == null)
                    {
                        JLabel playerImg = new JLabel();
                        playerImg = new JLabel(new ImageIcon(PlayerState.getImage().getScaledInstance(20 , 30 ,java.awt.Image.SCALE_SMOOTH)));
                        playerImg.setLayout(null);

                        UICreator.lblTopPanel.add(playerImg , null);
                        lblState = playerImg;
                    }

                    lblState.setBounds(0,0, 20, 30);
                    lblState.setText(SelectedPlayer.getPlayerName() + "^");
                    lblState.setIcon(new ImageIcon(PlayerState.getImage().getScaledInstance(20 , 30 ,java.awt.Image.SCALE_SMOOTH)));
                    //lblState.setLocation(frame.getWidth() * (Data.PlayerNameLabels.get(i).getX() + Data.PlayerNameLabels.get(i).getWidth() + 5) / Data.RefrenceX, frame.getHeight() * (Data.PlayerNameLabels.get(i).getY() + 35) / Data.RefrenceY);
                    lblState.setLocation(UICreator.lblTopPanel.getWidth() * (playerName.getX()) / Data.RefrenceTopPanelX, UICreator.lblTopPanel.getHeight() * (playerName.getY()) / Data.RefrenceTopPanelY);


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

    public void HandleStartGame()
    {

    }

    public void SetDialogResult(InputModel data)
    {
        gameManager.CountryUIClick(null , true , data);
    }
}