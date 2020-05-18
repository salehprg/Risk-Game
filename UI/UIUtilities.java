package UI;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;


import java.awt.event.*;
import javafx.scene.input.MouseEvent;

public class UIUtilities {

    public int result = -1;
    
    UIManager uiManager;

    public UIUtilities(UIManager _uiManager)
    {
        uiManager = _uiManager;
    }

    public void OpenInputSoldierWarDialog()
    {

    }

    public int OpenWarDialog()
    {
        JFrame dialog = new JFrame();
        dialog.setBounds(50, 50, 200, 150);

        JButton button = new JButton("SendData");

        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        result += 2;
                        uiManager.warResult(result);
                        dialog.setVisible(false);  
                    }  
                }); 

        dialog.add(button);

        dialog.setUndecorated(true);
        dialog.setVisible(true);

        return result;
    }

    public void OpenInputSoldierDeployDialog()
    {

    }

    public void OpenMoveDialog()
    {

    }

    public void ChangeState()
    {

    }


}