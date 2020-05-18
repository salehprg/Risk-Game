package UI;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import Game_Manager.GameManager;
import Map.Country;
import Map.Map;

import java.awt.Color;


public class UICreator {

    GameManager gameManager;
    String AppPath = "";

    public UICreator(GameManager _GameManager) {
        gameManager = _GameManager;

        try {
            AppPath = new File(".").getCanonicalPath();
        } 
        catch (IOException e) {
        }
    }

    public JFrame Initialize() 
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

        frame.getContentPane().add(panel);   
        frame.setLayout(null);
        frame.setVisible(true);    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        return frame;

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
        String[] CountryName = Data.CountryName;


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground( Color.ORANGE );
        panel.setBounds(ML, MT, width, height);
        
        for(int i = 0; i < CountryBound.length; i++)
        {
            JButton CountryButton = new JButton(String.valueOf(i));
            
            CountryButton.setActionCommand(String.valueOf(i));

            CountryButton.setName(CountryName[i]);
            CountryButton.setBounds(width * CountryBound[i][0] / RefrenceX - 15 , height * CountryBound[i][1] / RefrenceY , 50 , 20);

            CountryButton.setContentAreaFilled(false);
            CountryButton.setBorderPainted(true);

            CountryButton.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                            System.out.println(e.getActionCommand());
                            CountryClick(Integer.valueOf(e.getActionCommand()));  
                        }  
                    }); 

            panel.add(CountryButton);
        }

        return panel;
    }


    void CountryClick(int CountryId)
    {
        Country ClickedCountry = Map.getCountry(CountryId);
        gameManager.CountryUIClick(ClickedCountry);
    }


}