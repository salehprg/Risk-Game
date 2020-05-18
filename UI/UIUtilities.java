package UI;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

public class UIUtilities {

    InputModel result;
    
    UIManager uiManager;

    public UIUtilities(UIManager _uiManager)
    {
        uiManager = _uiManager;
    }

    public void OpenInputSoldierWarDialog()
    {

    }

    // public int OpenWarDialog()
    // {
    //     JFrame dialog = new JFrame();
    //     dialog.setBounds(50, 50, 200, 150);

    //     JButton button = new JButton("SendData");

    //     button.addActionListener(new ActionListener(){  
    //         public void actionPerformed(ActionEvent e){  
    //                     result += 2;
    //                     uiManager.SetDialogResult(result);
    //                     dialog.setVisible(false);  
    //                 }  
    //             }); 

    //     dialog.add(button);

    //     dialog.setUndecorated(true);
    //     dialog.setVisible(true);

    //     return result;
    // }

    public void OpenInputSoldierDeployDialog()
    {
        JFrame dialog = new JFrame();
        dialog.setBounds(50, 50, 200, 150);

        JButton button = new JButton("SendData");
        button.setBounds(10, 10, 30, 30);

        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        result = new InputModel();
                        result.DeploySoldier = 2;
                        
                        uiManager.SetDialogResult(result);
                        dialog.setVisible(false);  
                    }  
                }); 

        dialog.add(button);

        dialog.setUndecorated(true);
        dialog.setVisible(true);
    }

    public void OpenMoveDialog()
    {

    }

    public void ChangeState()
    {

    }


}