package frolov.excelFromPro100.gui;

import javax.swing.*;
import java.io.File;

public class InnerFrame {

    InnerFrame(GUI gui) {
        gui.innerFrame = new JFrame();
        gui.fileChooser = new JFileChooser();
        gui.fileChooser.addActionListener(gui);
        gui.fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        gui.innerFrame.add(gui.fileChooser);

        //innerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.innerFrame.setSize(500, 500);
        gui.innerFrame.setVisible(false);
        //innerFrame.pack();
        gui.innerFrame.setTitle("test");
    }
}
