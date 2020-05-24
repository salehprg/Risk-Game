package UI;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import Game_Manager.GameManager;
import Game_Manager.Turn.TurnManager;
import Map.Country;
import Map.Map;
import PlayerManager.Player;

import java.awt.*;


public class UICreator {

    GameManager gameManager;
    String AppPath = "";

    public UICreator(GameManager _GameManager) {
        gameManager = _GameManager;

        AppPath = Data.GetAppPath();
    }



//#region MainMenu
public JFrame InitializeMainMenu() 
    {
        JFrame frame = new JFrame("Risk Game");

        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // frame.setUndecorated(true);
        // frame.setVisible(true);

        frame.setSize(1000, 600);   
        
        
        int ScreenWidth = frame.getBounds().width;
        int ScreenHeight = frame.getBounds().height;

        JButton startbtn = new JButton("Start");
        startbtn.setBounds((ScreenWidth - 100) / 2, (ScreenHeight - 50) / 2, 100, 50);
        
        startbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                gameManager.InitializeGame(2);
                frame.setVisible(false);
            }
        });

        frame.getContentPane().add(startbtn);   
        frame.setLayout(null);
        frame.setVisible(true);    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        return frame;

    }         
//#endregion

//#region Main game UI
    JFrame InitializeMainGame() 
    {
        JFrame frame = new JFrame("Risk Game");

        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // frame.setUndecorated(true);
        // frame.setVisible(true);

        frame.setSize(1000, 600);   
        
        
        int ScreenWidth = frame.getBounds().width;
        int ScreenHeight = frame.getBounds().height;

        int  ML = 40 , MT = 100 , MR = 40 , MB = 20;

        JButton BoardGameBtn = CreateBoardImage(ScreenWidth - (MR + ML), ScreenHeight - (MT + MB));

        JPanel panel = CreateCountryUI(ScreenWidth, ScreenHeight , ML , MT , MR , MB);

        panel.add(BoardGameBtn , null);

            JLabel labelMp = new JLabel();
            labelMp.setBounds(50 , 50  , 100 , 10);

            BoardGameBtn.addMouseMotionListener(new MouseInputAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    labelMp.setText(e.getX() + " | " + e.getY());
                }
            });
    

        panel.add(labelMp);

        CreatePlayerUI(frame);
        frame.add(FinishNyTurn());
        
        frame.getContentPane().add(panel);   
        frame.setLayout(null);
        frame.setVisible(true);    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        return frame;

    }         

    void CreatePlayerUI(JFrame _frame)
    {
        List<Player> players = TurnManager.getPlayerList();

        for(int i = 0; i < players.size(); i++)
        {
            Player SelectedPlayer = players.get(i);

            JLabel playerName = new JLabel(SelectedPlayer.getPlayerName());
            playerName.setBounds(20 + i * 160, 5, 150, 40);

            JLabel UnEmpSoldierCount = new JLabel(String.valueOf(SelectedPlayer.getUnimployedSoldiersCount()));
            UnEmpSoldierCount.setBounds(20 + i * 160, 30, 50, 20);

            JLabel playerCountryCount = new JLabel("0");
            playerCountryCount.setBounds(50 + i * 160, 30, 50, 20);

            Data.PlayerNameLabels.add(playerName);
            Data.PlayerSoldierLabels.add(UnEmpSoldierCount);
            Data.PlayerCountryCount.add(playerCountryCount);

            _frame.add(playerCountryCount , null);
            _frame.add(playerName , null);
            _frame.add(UnEmpSoldierCount , null);
        }
    }

    JButton CreateBoardImage(int Width , int Height)
    {
        int ImageWidth = Width;
        int ImageHeight = Height;

        ImageIcon BoardGameIcon = new ImageIcon(AppPath + "\\UI\\Images\\BoardGame\\BoardGame.jpg");

        JButton BoardGame = new JButton(new ImageIcon(BoardGameIcon.getImage().getScaledInstance(ImageWidth, ImageHeight ,java.awt.Image.SCALE_SMOOTH)));
        BoardGame.setBounds(0 , 0 , ImageWidth, ImageHeight);

        BoardGame.setContentAreaFilled(false);
        BoardGame.setBorderPainted(false);
        
        return BoardGame;
    }

    JPanel CreateCountryUI(int _Width , int _Height, int ML , int MT , int MR , int MB)
    {
        int RefrenceX  = 800;
        int RefrenceY  = 600;

        int width = _Width - (MR + ML);
        int height = _Height - (MT + MB);

        int[][] CountryBound = Data.CountryBound;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground( Color.ORANGE );
        panel.setBounds(ML, MT, width, height);
        
        for(int i = 0; i < CountryBound.length; i++)
        {
            JButton CountryButton = new JButton();
            CountryButton.setActionCommand(String.valueOf(i));

            CountryButton.setBounds(width * CountryBound[i][0] / RefrenceX - 15 , height * CountryBound[i][1] / RefrenceY , 50 , 20);

            CountryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            CountryButton.setContentAreaFilled(false);
            CountryButton.setBorderPainted(false);

            CountryButton.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                            CountryClick(Integer.valueOf(e.getActionCommand()));  
                        }  
                    }); 

            Data.buttons.add(CountryButton);
            panel.add(CountryButton , null);
        }

        return panel;
    }

    void CountryClick(int CountryId)
    {
        Country ClickedCountry = Map.getCountry(CountryId);      

        gameManager.CountryUIClick(ClickedCountry , false , null);
    }

    JButton FinishNyTurn()
    {
        JButton finishTurnBtn = new JButton("Next Turn");
        finishTurnBtn.setBounds(100, 60, 120, 30);
        //finishTurnBtn.setBorderPainted(false);
        finishTurnBtn.setContentAreaFilled(false);
        finishTurnBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        finishTurnBtn.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        gameManager.FinishTurn();
                    }  
                }); 

        return finishTurnBtn;
    }
//#endregion


}