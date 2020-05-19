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
        try {
            AppPath = new File(".").getCanonicalPath();
        } 
        catch (IOException e) {
        }
    }

    public void OpenInputSoldierWarDialog()
    {

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

    public void OpenMoveDialog()
    {

    }

    public void ChangeState()
    {

    }


}