package frolov.excelFromPro100.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    MainFrame(GUI gui) {

        gui.mainFrame = new JFrame();

        JButton dataButton = new JButton("Choose data file.txt");
        dataButton.setActionCommand(GUI.FrameActions.DATA.name());
        dataButton.addActionListener(gui);

        JButton templateButton = new JButton("Choose template file.xls");
        templateButton.setActionCommand(GUI.FrameActions.TEMPLATE.name());
        templateButton.addActionListener(gui);

        JButton mapButton = new JButton("Choose color map file");
        mapButton.setActionCommand(GUI.FrameActions.MAP.name());
        mapButton.addActionListener(gui);

        JButton execButton = new JButton("Save");
        execButton.setActionCommand(GUI.FrameActions.EXEC.name());
        execButton.addActionListener(gui);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(dataButton);
        panel.add(templateButton);
        panel.add(mapButton);
        panel.add(execButton);

        gui.mainFrame.add(panel);

        gui.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.mainFrame.setTitle("GUI");
        gui.mainFrame.pack();
        gui.mainFrame.setSize(500, 500);
        gui.mainFrame.setVisible(true);
    }
}
