package UI;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import Game_Manager.GameManager;
import Game_Manager.Turn.TurnManager;
import Map.Country;
import Map.Map;
import PlayerManager.Player;
import PlayerManager.PlayerManager.PlayerColor;

import java.awt.*;


public class UICreator {

    GameManager gameManager;
    String AppPath = "";

    public static JLabel lblTopPanel;

    public UICreator(GameManager _GameManager) {
        gameManager = _GameManager;

        AppPath = Data.GetAppPath();
    }



//#region MainMenu

public JFrame InitializeMainMenu() throws UnsupportedAudioFileException, 
IOException, LineUnavailableException
    {
        JFrame frame = new JFrame("Risk Game");

        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // frame.setUndecorated(true);
        // frame.setVisible(true);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        try
        {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(AppPath + "//Resource//StartMenu.snd").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch(IOException ex)
        {

        }

        int ScreenWidth = frame.getBounds().width;
        int ScreenHeight = frame.getBounds().height;

        ImageIcon startbtnIcon = new ImageIcon(AppPath + "\\UI\\Images\\the final\\start-normal.png");
        JButton startbtn = new JButton(new ImageIcon(startbtnIcon.getImage().getScaledInstance(250, 100 ,java.awt.Image.SCALE_SMOOTH)));
        startbtn.setBounds((ScreenWidth - 300) / 2, ScreenHeight - 100, 250, 100);
        startbtn.setRolloverEnabled(true);

        ImageIcon startbtnIconHover = new ImageIcon(AppPath + "\\UI\\Images\\the final\\start-hover.png");
        startbtn.setRolloverIcon(new ImageIcon(startbtnIconHover.getImage().getScaledInstance(250, 100 ,java.awt.Image.SCALE_SMOOTH)));

        startbtn.setBorderPainted(false);
        startbtn.setContentAreaFilled(false);

        startbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ListPlayerCount(frame);
                frame.setVisible(false);
            }
        });

        startbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        frame.getContentPane().add(startbtn);
        frame.add(StartMenuBGImage(ScreenWidth, ScreenHeight));
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;

    }
    
    public void ListPlayerCount(JFrame _frame)
    {
        int width = _frame.getWidth();
        int height = _frame.getHeight();

        JFrame frame = new JFrame("Risk Game");

        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);

        String AppPath = Data.GetAppPath();
        ImageIcon PlayerIconImage = new ImageIcon();
 
        JPanel InfoPanel = new JPanel();
        InfoPanel.setLayout(null);
        InfoPanel.setBounds((width - 400) / 2 , (height - 400) / 2 , 400, 400);
        

        PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\PlayerCount2.png");
        JButton button2 = new JButton();
        button2.setBounds(150, 80, 120, 60);
        button2.setIcon(new ImageIcon(PlayerIconImage.getImage().getScaledInstance(120, 60 ,java.awt.Image.SCALE_SMOOTH)));
        button2.setContentAreaFilled(false);
        button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                        PlayerListInfo(2, frame);
                    }
                });


        PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\PlayerCount3.png");
        JButton button3 = new JButton("3 Player");
        button3.setBounds(150, 160, 120, 60);
        button3.setIcon(new ImageIcon(PlayerIconImage.getImage().getScaledInstance(120, 60 ,java.awt.Image.SCALE_SMOOTH)));
        button3.setContentAreaFilled(false);
        button3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                        PlayerListInfo(3, frame);
                    }
                });



        PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\PlayerCount4.png");
        JButton button4 = new JButton("4 Player");
        button4.setBounds(150, 240, 120, 60);
        button4.setContentAreaFilled(false);
        button4.setIcon(new ImageIcon(PlayerIconImage.getImage().getScaledInstance(120, 60 ,java.awt.Image.SCALE_SMOOTH)));
        button4.setForeground(Color.black);
        button4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                        PlayerListInfo(4, frame);
                    }
                });

        InfoPanel.setLayout(null);
        InfoPanel.setBackground(new Color(0,0,0,0));

        InfoPanel.add(button2 , null);
        InfoPanel.add(button3 , null);
        InfoPanel.add(button4 , null);


        PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\Panel.png");
        JLabel panelBG = new JLabel(new ImageIcon(PlayerIconImage.getImage().getScaledInstance(InfoPanel.getWidth(), InfoPanel.getHeight() ,java.awt.Image.SCALE_SMOOTH)));
        panelBG.setLayout(null);
        panelBG.setBounds(0, 0, InfoPanel.getWidth(), InfoPanel.getHeight());
        InfoPanel.add(panelBG , null);

        frame.add(InfoPanel , null);
        frame.add(BackgroundImage(frame.getWidth(), frame.getHeight()));

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    boolean Hitler = false, Churchil = false , Musilini = false , Stalin = false;
    ArrayList<Integer> PlayerCharId = new ArrayList<>();
    ArrayList<JButton> Characters = new ArrayList<>();
    ArrayList<JTextField> PlayerName = new ArrayList<>();

    void PlayerListInfo(int PlayerNumber , JFrame _frame)
    {
        int width = _frame.getWidth();
        int height = _frame.getHeight();

        _frame.setVisible(false);
        JFrame frame = new JFrame("Risk Game");

        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        
        JPanel InfoPanel = new JPanel();
        InfoPanel.setLayout(null);
        InfoPanel.setBounds(40,40 , width, height);

        JLabel lblplayerCount = new JLabel("Player Count : " + String.valueOf(PlayerNumber));
        lblplayerCount.setBounds(30, 0, 100, 20);
        lblplayerCount.setForeground(Color.WHITE);

        for(int i = 0;i < PlayerNumber;i++)
        {
            JLabel lblPlayerName = new JLabel("Player Name : ");
            lblPlayerName.setBounds(30, 50 + i * 120, 100, 20);
            lblPlayerName.setForeground(Color.WHITE);

            JTextField textname = new JTextField();
            textname.setBounds(140, 50 + i * 120 , 100, 20);
            PlayerName.add(textname);

            PlayerCharId.add(-1);
            CreateSelectableCharacter(InfoPanel, i , -1);

            InfoPanel.add(lblPlayerName);
            InfoPanel.add(textname);
        }

        JButton buttonOk = new JButton("Ok");
        buttonOk.setBounds(30, 50 + PlayerNumber * 120, 60, 30);
        //buttonOk.setBorderPainted(false);
        buttonOk.setContentAreaFilled(true);
        buttonOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonOk.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    boolean ValidData = true;
                    for(int i = 0; i < PlayerCharId.size();i++)
                    {
                        if(PlayerCharId.get(i) == -1 || PlayerName.get(i).getText().trim() == "")
                        {
                            ValidData = false;
                        }
                    }

                    if(ValidData)
                    {
                        List<Player> players = new ArrayList<Player>();
                        PlayerColor color = PlayerColor.Black;
                        for(int i = 0; i < PlayerNumber;i++)
                        {
                            // 0 = Hitler , 1 = Musilini , 2 = Stalin , 3 = Churchil
                            switch (PlayerCharId.get(i)) {
                                case 0:
                                    color = PlayerColor.Black;
                                    break;
                            
                                case 1:
                                    color = PlayerColor.Green;
                                    break;

                                case 2:
                                    color = PlayerColor.Red;
                                    break;

                                case 3:
                                    color = PlayerColor.Blue;
                                    break;
                                default:
                                    break;
                            }
                            Player player = new Player(i, PlayerName.get(i).getText(), color);
                            players.add(player);
                        }
        
                        gameManager.InitializeGame(PlayerNumber , players); 
                        frame.setVisible(false);
                    }
                }  
            });

        InfoPanel.setBackground(new Color(0,0,0,0));

        InfoPanel.add(lblplayerCount , null);
        InfoPanel.add(buttonOk , null);

        frame.add(InfoPanel , null);
        frame.add(BackgroundImage(frame.getWidth(), frame.getHeight()));

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    void CreateSelectableCharacter(JPanel CharPanel , int PlayerId , int SelectedChar)
    {
        String AppPath = Data.GetAppPath();
        ImageIcon PlayerIconImage = new ImageIcon();
        
        int StartX = 260 , verSpaceBetweenChar = 120 , horSpaceBetweenChar = 120;

        if(Characters.size() > 0)
        {
            for(int i = 0 ; i < Characters.size();i++)
            {
                if(SelectedChar != -1)
                {
                    if(Integer.valueOf(Characters.get(i).getActionCommand()) == PlayerId)
                    {
                        CharPanel.remove(Characters.get(i));
                    }
                    if(Integer.valueOf(Characters.get(i).getName()) == SelectedChar)
                    {
                        CharPanel.remove(Characters.get(i));
                    }
                }
            }
        }

        // 0 = Hitler , 1 = Musilini , 2 = Stalin , 3 = Churchil
        if(!Hitler && PlayerCharId.get(PlayerId) == -1 || PlayerCharId.get(PlayerId) == 0)
        {
            PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\hitler.jpg");
            JButton HitlerBtn = new JButton();
            HitlerBtn.setActionCommand(String.valueOf(PlayerId));
            HitlerBtn.setName("0");

            HitlerBtn.setIcon(new ImageIcon(PlayerIconImage.getImage().getScaledInstance(100, 100 ,java.awt.Image.SCALE_SMOOTH)));
            HitlerBtn.setBounds(StartX, 20 + PlayerId * verSpaceBetweenChar, 100, 100);
            
            CharPanel.add(HitlerBtn);
            HitlerBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                            int _PlayerId = Integer.valueOf(e.getActionCommand());
                            PlayerCharId.set(_PlayerId, 0);
                            Hitler = true;
                            CreateSelectableCharacter(CharPanel, _PlayerId , 0);
                        }
                    });

            HitlerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Characters.add(HitlerBtn);
        }

        if(!Musilini && PlayerCharId.get(PlayerId) == -1  || PlayerCharId.get(PlayerId) == 1)
        {
            PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\musilini.jpg");
            JButton MusiliniBtn = new JButton();
            MusiliniBtn.setActionCommand(String.valueOf(PlayerId));
            MusiliniBtn.setName("1");

            MusiliniBtn.setIcon(new ImageIcon(PlayerIconImage.getImage().getScaledInstance(100, 100 ,java.awt.Image.SCALE_SMOOTH)));
            MusiliniBtn.setBounds(StartX + horSpaceBetweenChar,20 + PlayerId * verSpaceBetweenChar, 100, 100);

            CharPanel.add(MusiliniBtn);

            MusiliniBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                            int _PlayerId = Integer.valueOf(e.getActionCommand());
                            PlayerCharId.set(_PlayerId, 1);
                            Musilini = true;
                            CreateSelectableCharacter(CharPanel, _PlayerId , 1);
                        }
                    });

            MusiliniBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Characters.add(MusiliniBtn);
        }

        if(!Stalin && PlayerCharId.get(PlayerId) == -1  || PlayerCharId.get(PlayerId) == 2)
        {
            PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\stalin.jpg");
            JButton StalinBtn = new JButton();
            StalinBtn.setActionCommand(String.valueOf(PlayerId));
            StalinBtn.setName("2");

            StalinBtn.setIcon(new ImageIcon(PlayerIconImage.getImage().getScaledInstance(100, 100 ,java.awt.Image.SCALE_SMOOTH)));
            StalinBtn.setBounds(StartX + 2*horSpaceBetweenChar,20 + PlayerId * verSpaceBetweenChar, 100, 100);

            CharPanel.add(StalinBtn);

            StalinBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int _PlayerId = Integer.valueOf(e.getActionCommand());
                            PlayerCharId.set(_PlayerId, 2);
                            Stalin = true;
                            CreateSelectableCharacter(CharPanel, _PlayerId , 2);
                            CreateSelectableCharacter(CharPanel, Integer.valueOf(e.getActionCommand()) , 2);
                        }
                    });

            StalinBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Characters.add(StalinBtn);
        }

        if(!Churchil && PlayerCharId.get(PlayerId) == -1  || PlayerCharId.get(PlayerId) == 3)
        {
            PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\churchil.jpg");
            JButton ChurchilBtn = new JButton();
            ChurchilBtn.setActionCommand(String.valueOf(PlayerId));
            ChurchilBtn.setName("3");

            ChurchilBtn.setIcon(new ImageIcon(PlayerIconImage.getImage().getScaledInstance(100, 100 ,java.awt.Image.SCALE_SMOOTH)));
            ChurchilBtn.setBounds(StartX + 3*horSpaceBetweenChar,20 + PlayerId * verSpaceBetweenChar, 100, 100);

            CharPanel.add(ChurchilBtn);

            ChurchilBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                            int _PlayerId = Integer.valueOf(e.getActionCommand());
                            PlayerCharId.set(_PlayerId, 3);
                            Churchil = true;
                            CreateSelectableCharacter(CharPanel, _PlayerId , 3);
                            CreateSelectableCharacter(CharPanel, Integer.valueOf(e.getActionCommand()) , 3);
                        }
                    });

            ChurchilBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Characters.add(ChurchilBtn);
        }

        CharPanel.repaint();
        CharPanel.setVisible(false);
        CharPanel.setVisible(true);
    }

//#endregion

//#region Main game UI
    JFrame InitializeMainGame()
    {
        JFrame frame = new JFrame("Risk Game");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);

        //frame.setSize(800, 600);
        frame.setLayout(null);

        int ScreenWidth = frame.getBounds().width;
        int ScreenHeight = frame.getBounds().height;

        int  ML = 40 , MT = 100 , MR = 40 , MB = 20;

        JButton BoardGameBtn = CreateBoardImage(ScreenWidth - (MR + ML), ScreenHeight - (MT + MB));

        JPanel panel = CreateCountryUI(ScreenWidth, ScreenHeight , ML , MT , MR , MB);


        panel.add(BoardGameBtn , null);

        ImageIcon TopPanelIcon = new ImageIcon(AppPath + "\\UI\\Images\\the final\\top_panel.png");
        JLabel TopPanel = new JLabel(new ImageIcon(TopPanelIcon.getImage().getScaledInstance(panel.getWidth(), 80 ,java.awt.Image.SCALE_SMOOTH)));
        TopPanel.setLayout(null);
        TopPanel.setBounds(panel.getX(), panel.getY() - 80, panel.getWidth(), 80);


            JLabel labelMp = new JLabel();
            labelMp.setBounds(50 , 50  , 100 , 10);

            TopPanel.addMouseMotionListener(new MouseInputAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    labelMp.setText(e.getX() + " | " + e.getY());
                }
            });
            labelMp.setForeground(Color.white);

        lblTopPanel = TopPanel;
        //frame.add(labelMp);
        frame.add(FinishMyTurn());
        frame.add(TopPanel);

        
        CreatePlayerUI(TopPanel);
        CreatePlayerCharacter(frame);

        frame.getContentPane().add(panel);

        frame.add(BackgroundImage(ScreenWidth, ScreenHeight));

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;

    }
    
    void CreatePlayerCharacter(JFrame _frame)
    {
        List<Player> players = TurnManager.getPlayerList();

        String AppPath = Data.GetAppPath();
        ImageIcon PlayerIconImage = new ImageIcon();

        for(int i = 0; i < players.size(); i++)
        {
            Player SelectedPlayer = players.get(i);
            JLabel PlayerImage = new JLabel();
            // Black = Hitler , Green = Musilini , 2 = Stalin , 3 = Churchil
            switch (SelectedPlayer.getPlayerColor()) {
                case Black:
                    PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\hitler.jpg");
                    break;
            
                case Green:
                    PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\musilini.jpg");
                    break;

                case Red:
                    PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\stalin.jpg");
                    break;

                case Blue:
                    PlayerIconImage = new ImageIcon(AppPath + "\\UI\\Images\\the final\\churchil.jpg");
                    break;
                default:
                    break;
            }

            PlayerImage.setIcon(new ImageIcon(PlayerIconImage.getImage().getScaledInstance(70, 50 ,java.awt.Image.SCALE_SMOOTH)));
            PlayerImage.setBounds(Data.PlayerNameLabels.get(i).getX() + 50 , Data.PlayerNameLabels.get(i).getY() - 20, 70, 50);
            _frame.add(PlayerImage , 0);
        }

    }

    void CreatePlayerUI(JLabel parent)
    {
        int refrenceX = Data.RefrenceTopPanelX;
        int refrenceY = Data.RefrenceTopPanelY;

        List<Player> players = TurnManager.getPlayerList();

        String AppPath = Data.GetAppPath();
        ImageIcon PlayerIconImage = new ImageIcon();

        for(int i = 0; i < players.size(); i++)
        {
            Player SelectedPlayer = players.get(i);

            JLabel playerName = new JLabel(SelectedPlayer.getPlayerName());
            playerName.setBounds(parent.getWidth() * (96 + i * 150) / refrenceX, parent.getHeight() * 18 / refrenceY, 150, 40);
            playerName.setForeground(Color.white);

            JLabel UnEmpSoldierCount = new JLabel(String.valueOf(SelectedPlayer.getUnimployedSoldiersCount()));
            UnEmpSoldierCount.setBounds(parent.getWidth() * (96 + i * 150) / refrenceX, parent.getHeight() * 53 / refrenceY, 50, 20);
            UnEmpSoldierCount.setForeground(Color.white);

            JLabel playerCountryCount = new JLabel("0");
            playerCountryCount.setBounds(parent.getWidth() * (167 + i * 150) / refrenceX, parent.getHeight() * 53 / refrenceY, 50, 20);
            playerCountryCount.setForeground(Color.white);

            Data.PlayerNameLabels.add(playerName);
            Data.PlayerSoldierLabels.add(UnEmpSoldierCount);
            Data.PlayerCountryCount.add(playerCountryCount);

            parent.add(playerCountryCount , null);
            parent.add(playerName , null);
            parent.add(UnEmpSoldierCount , null);
        }
    }

    JLabel StartMenuBGImage(int Width , int Height)
    {
        ImageIcon BackGroundIcon = new ImageIcon(AppPath + "\\UI\\Images\\the final\\StartMenu.gif");
        JLabel BackGround = new JLabel();
        BackGround.setIcon(new ImageIcon(BackGroundIcon.getImage().getScaledInstance(Width, Height ,java.awt.Image.SCALE_DEFAULT)));
        BackGroundIcon.setImageObserver(BackGround);
        BackGround.setBounds(0, 0, Width , Height);
        BackGround.setLayout(null);

        return BackGround;
    }

    JLabel BackgroundImage(int Width , int Height)
    {
        ImageIcon BackGroundIcon = new ImageIcon(AppPath + "\\UI\\Images\\the final\\backgrund.png");
        JLabel BackGround = new JLabel(new ImageIcon(BackGroundIcon.getImage().getScaledInstance(Width, Height ,java.awt.Image.SCALE_SMOOTH)));

        BackGround.setBounds(0, 0, Width , Height);
        BackGround.setLayout(null);

        return BackGround;
    }

    JButton CreateBoardImage(int Width , int Height)
    {
        ImageIcon BoardGameIcon = new ImageIcon(AppPath + "\\UI\\Images\\BoardGame\\BoardGame.jpg");

        JButton BoardGame = new JButton(new ImageIcon(BoardGameIcon.getImage().getScaledInstance(Width, Height ,java.awt.Image.SCALE_SMOOTH)));
        BoardGame.setBounds(0 , 0 , Width, Height);
        BoardGame.setContentAreaFilled(false);
        BoardGame.setBorderPainted(false);

        return BoardGame;
    }

    JPanel CreateCountryUI(int _Width , int _Height, int ML , int MT , int MR , int MB)
    {
        int RefrenceX  = Data.RefrenceX;
        int RefrenceY  = Data.RefrenceY;

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

            CountryButton.setBounds(width * CountryBound[i][0] / RefrenceX - 15 , height * CountryBound[i][1] / RefrenceY , 50 , 28);

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

    JButton FinishMyTurn()
    {
        ImageIcon BoardGameIcon = new ImageIcon(AppPath + "\\UI\\Images\\the final\\NextTurn.png");
        
        JButton finishTurnBtn = new JButton(new ImageIcon(BoardGameIcon.getImage().getScaledInstance(50, 50 ,java.awt.Image.SCALE_SMOOTH)));
        finishTurnBtn.setBounds(lblTopPanel.getWidth() * (700) / Data.RefrenceTopPanelX, lblTopPanel.getHeight() * 50 / Data.RefrenceTopPanelY, 50, 50);
        finishTurnBtn.setContentAreaFilled(false);
        finishTurnBtn.setBorderPainted(false);
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