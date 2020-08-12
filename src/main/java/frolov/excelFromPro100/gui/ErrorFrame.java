package frolov.excelFromPro100.gui;

import javax.swing.*;
import java.io.File;

public class ErrorFrame {

    ErrorFrame(GUI gui)
    {
        gui.errorLabel = new JLabel();
        JButton errorButton = new JButton();
        errorButton.setText("OK");
        errorButton.setActionCommand(GUI.FrameActions.ERROR_CLOSE.name());
        errorButton.addActionListener(gui);

        gui.errorFrame = new JFrame();
        gui.errorFrame.setVisible(false);
        gui.errorFrame.setTitle("Error");
        gui.errorFrame.setSize(500, 500);

        JPanel errorPanel = new JPanel();

        errorPanel.add(gui.errorLabel);
        errorPanel.add(errorButton);
        gui.errorFrame.add(errorPanel);
    }
}
