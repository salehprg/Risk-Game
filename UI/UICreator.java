package UI;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import Game_Manager.GameManager;
import Map.Country;
import Map.Map;

import java.awt.Color;
import java.awt.Dimension;

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

    public void Initialize() 
    {
        JFrame frame= new JFrame("Risk Game");

        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // frame.setUndecorated(true);
        // frame.setVisible(true);

        frame.setSize(1000, 600);   

        int ScreenWidth = frame.getBounds().width;
        int ScreenHeight = frame.getBounds().height;

        JButton BoardGameBtn = CreateBoardImage(ScreenWidth, ScreenHeight);
        //frame.add(BoardGameBtn);

        JPanel panel = CreateCountryUI(ScreenWidth, ScreenHeight);

        panel.add(BoardGameBtn , null);
        
        //frame.add(BoardGameBtn);
        JLabel labelMp = new JLabel();
        labelMp.setText("Test");
        labelMp.setBounds(0 , 0 , 100 , 10);

        BoardGameBtn.addMouseMotionListener(new MouseInputAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                labelMp.setText(e.getX() + " | " + e.getY());
            }
        });

        panel.add(labelMp);
        frame.add(panel);

        frame.setLayout(null);    
        frame.setVisible(true);    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        //frame.setComponentZOrder(label, 1);
    }         

    JButton CreateBoardImage(int Width , int Height)
    {
        int ImageWidth = Width;
        int ImageHeight = Height;

        ImageIcon BoardGameIcon = new ImageIcon(AppPath + "\\UI\\Images\\BoardGame\\BoardGame.png");

        JButton BoardGame = new JButton(new ImageIcon(BoardGameIcon.getImage().getScaledInstance(ImageWidth, ImageHeight ,java.awt.Image.SCALE_SMOOTH)));
        BoardGame.setBounds(0 , 0 , ImageWidth, ImageHeight);

        BoardGame.setContentAreaFilled(false);
        BoardGame.setBorderPainted(false);
        
        return BoardGame;
    }

    JPanel CreateCountryUI(int _Width , int _Height)
    {
        int RefrenceX  = 600;
        int RefrenceY  = 400;

        int[][] CountryBound = Data.CountryBound;
        String[] CountryName = Data.CountryName;


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground( Color.ORANGE );
        panel.setBounds(0, 0, _Width, _Height);
        
        for(int i = 0; i < CountryBound.length; i++)
        {
            JButton CountryButton = new JButton();
            
            CountryButton.setActionCommand(String.valueOf(i));

            CountryButton.setName(CountryName[i]);
            CountryButton.setBounds(_Width * CountryBound[i][0] / RefrenceX ,_Height * CountryBound[i][1] / RefrenceY , 20 , 20);

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