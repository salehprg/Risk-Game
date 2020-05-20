package UI;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;


public class UIUtilities {

    InputModel result;
    
    UIManager uiManager;

    int frameWidth , frameHeight;

    String AppPath;

    public UIUtilities(UIManager _uiManager)
    {
        uiManager = _uiManager;
        AppPath = Data.GetAppPath();
    }

    public void OpenWarDialog(InputModel input)
    {
        frameWidth = uiManager.getFrame().getWidth();
        frameHeight = uiManager.getFrame().getHeight();
        
        JFrame dialog = new JFrame();
        dialog.setUndecorated(true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);   //Center Screen

        ImageIcon BoardGameIcon = new ImageIcon(AppPath + "\\UI\\Images\\BoardGame\\Letter.png");

        JLabel backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon(BoardGameIcon.getImage().getScaledInstance(dialog.getWidth(), dialog.getHeight() ,java.awt.Image.SCALE_SMOOTH)));
        backgroundImage.setBounds(0, 0, dialog.getWidth(), dialog.getHeight());

        JLabel FromCountry = new JLabel("From " + String.valueOf(input.FirstCountry.GetCountryID()) + 
                                        " To " + String.valueOf(input.SecondCountry.GetCountryID()));
        FromCountry.setBounds(30, 30, 150, 20);


        int AttackerSoldier = (input.AttackerSoldier > 3 ? 3 : input.AttackerSoldier);
        JLabel Attackerlbl = new JLabel("Max Soldier Attacker : " + String.valueOf(AttackerSoldier));
        Attackerlbl.setBounds(30, 50, 200, 20);

        JTextField txtboxAttck = new JTextField();
        txtboxAttck.setBounds(30, 80 , 100, 20);



        int DefenderSoldier = (input.DefenderSoldier > 2 ? 2 : input.DefenderSoldier);
        JLabel Defenderlbl = new JLabel("Max Soldier Defender : " + String.valueOf(DefenderSoldier));
        Defenderlbl.setBounds(30, 120, 200, 20);

        JTextField txtboxDfnd = new JTextField();
        txtboxDfnd.setBounds(30, 150 , 100, 20);


        JButton buttonOk = new JButton("Ok");
        buttonOk.setBounds(30, 110, 60, 30);
        //buttonOk.setBorderPainted(false);
        buttonOk.setContentAreaFilled(false);
        buttonOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonOk.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        result = new InputModel();
                        result.AttackerSoldier = Integer.valueOf(txtboxAttck.getText());
                        result.DefenderSoldier = Integer.valueOf(txtboxDfnd.getText());
                        
                        uiManager.SetDialogResult(result);
                        dialog.setVisible(false);  
                    }  
                }); 

        
        JButton buttonClose = new JButton("X");
        buttonClose.setBounds(100, 110, 60, 30);
        //buttonClose.setBorderPainted(false);
        buttonClose.setContentAreaFilled(false);
        buttonClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        buttonClose.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        result = new InputModel();
                        result.IsExited = true;
                        
                        uiManager.SetDialogResult(result);
                        dialog.setVisible(false);  
                    }  
                }); 

        
        dialog.setBackground(new Color(0,0,0,0));

        
        dialog.add(txtboxAttck , null);
        dialog.add(txtboxDfnd , null);

        dialog.add(FromCountry , null);

        dialog.add(Attackerlbl , null);
        dialog.add(Defenderlbl , null);
        
        dialog.add(buttonOk , null);
        dialog.add(buttonClose , null);
        dialog.add(backgroundImage , null);

        dialog.setLayout(null);
        
        dialog.setVisible(true);
    }

    public void OpenInputSoldierDeployDialog(InputModel input)
    {
        frameWidth = uiManager.getFrame().getWidth();
        frameHeight = uiManager.getFrame().getHeight();
        
        JFrame dialog = new JFrame();
        dialog.setUndecorated(true);
        dialog.setSize(200, 150);
        dialog.setLocationRelativeTo(null);   //Center Screen

        ImageIcon BoardGameIcon = new ImageIcon(AppPath + "\\UI\\Images\\BoardGame\\Letter.png");

        JLabel backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon(BoardGameIcon.getImage().getScaledInstance(dialog.getWidth(), dialog.getHeight() ,java.awt.Image.SCALE_SMOOTH)));
        backgroundImage.setBounds(0, 0, dialog.getWidth(), dialog.getHeight());

        JLabel maxDeploy = new JLabel("Max Soldier : " + String.valueOf(input.DeploySoldier));
        maxDeploy.setBounds(30, 20, 100, 20);

        JTextField txtbox = new JTextField();
        txtbox.setBounds(30, 50 , 100, 20);

        JButton buttonOk = new JButton("Ok");
        buttonOk.setBounds(30, 80, 60, 30);
        //buttonOk.setBorderPainted(false);
        buttonOk.setContentAreaFilled(false);
        buttonOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonOk.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        result = new InputModel();
                        result.DeploySoldier = Integer.valueOf(txtbox.getText());
                        
                        uiManager.SetDialogResult(result);
                        dialog.setVisible(false);  
                    }  
                }); 

        
        JButton buttonClose = new JButton("X");
        buttonClose.setBounds(100, 80, 60, 30);
        //buttonClose.setBorderPainted(false);
        buttonClose.setContentAreaFilled(false);
        buttonClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        buttonClose.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        dialog.setVisible(false);  
                    }  
                }); 

        
        dialog.setBackground(new Color(0,0,0,0));

        
        dialog.add(txtbox , null);
        dialog.add(maxDeploy , null);
        dialog.add(buttonOk , null);
        dialog.add(buttonClose , null);
        dialog.add(backgroundImage , null);

        dialog.setLayout(null);
        
        dialog.setVisible(true);
    }

    public void OpenMoveDialog(InputModel input)
    {
        frameWidth = uiManager.getFrame().getWidth();
        frameHeight = uiManager.getFrame().getHeight();
        
        JFrame dialog = new JFrame();
        dialog.setUndecorated(true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(null);   //Center Screen

        ImageIcon BoardGameIcon = new ImageIcon(AppPath + "\\UI\\Images\\BoardGame\\Letter.png");

        JLabel backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon(BoardGameIcon.getImage().getScaledInstance(dialog.getWidth(), dialog.getHeight() ,java.awt.Image.SCALE_SMOOTH)));
        backgroundImage.setBounds(0, 0, dialog.getWidth(), dialog.getHeight());

        JLabel FromCountry = new JLabel("From " + String.valueOf(input.FirstCountry.GetCountryID()) + 
                                        " To " + String.valueOf(input.SecondCountry.GetCountryID()));
        FromCountry.setBounds(30, 30, 150, 20);

        JLabel maxDeploy = new JLabel("Max Soldier To Move : " + String.valueOf(input.MoveSoldier));
        maxDeploy.setBounds(30, 50, 200, 20);

        JTextField txtbox = new JTextField();
        txtbox.setBounds(30, 80 , 100, 20);

        JButton buttonOk = new JButton("Ok");
        buttonOk.setBounds(30, 110, 60, 30);
        //buttonOk.setBorderPainted(false);
        buttonOk.setContentAreaFilled(false);
        buttonOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonOk.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        result = new InputModel();
                        result.MoveSoldier = Integer.valueOf(txtbox.getText());
                        
                        uiManager.SetDialogResult(result);
                        dialog.setVisible(false);  
                    }  
                }); 

        
        JButton buttonClose = new JButton("X");
        buttonClose.setBounds(100, 110, 60, 30);
        //buttonClose.setBorderPainted(false);
        buttonClose.setContentAreaFilled(false);
        buttonClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        buttonClose.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        result = new InputModel();
                        result.IsExited = true;
                        
                        uiManager.SetDialogResult(result);
                        dialog.setVisible(false);  
                    }  
                }); 

        
        dialog.setBackground(new Color(0,0,0,0));

        
        dialog.add(txtbox , null);
        dialog.add(FromCountry , null);
        dialog.add(maxDeploy , null);
        dialog.add(buttonOk , null);
        dialog.add(buttonClose , null);
        dialog.add(backgroundImage , null);

        dialog.setLayout(null);
        
        dialog.setVisible(true);
    }

    public void ChangeState()
    {

    }


}