package frolov.excelFromPro100.gui;

import frolov.excelFromPro100.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class GUI implements ActionListener {

    private final Controller controller;
    private String currentCommand;

    JFileChooser fileChooser;

    JFrame mainFrame;
    JFrame errorFrame;
    JFrame innerFrame;
    JLabel errorLabel;

    public enum FrameActions {
        DATA,
        TEMPLATE,
        MAP,
        EXEC,
        ERROR_CLOSE
    }

    public GUI() {

        this.controller = new Controller();

        new MainFrame(this);
        new ErrorFrame(this);
        new InnerFrame(this);
    }

    private void showError(String error) {
        if(!error.equals("")) {
            errorLabel.setText(error);
            errorFrame.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(FrameActions.DATA.name())) {
            currentCommand = FrameActions.DATA.name();
            innerFrame.setTitle("Choose data file.txt");
            innerFrame.setVisible(true);
        } else if(e.getActionCommand().equals(FrameActions.TEMPLATE.name())) {
            currentCommand = FrameActions.TEMPLATE.name();
            innerFrame.setTitle("Choose template file.xls");
            innerFrame.setVisible(true);
        } else if(e.getActionCommand().equals(FrameActions.MAP.name())) {
            currentCommand = FrameActions.MAP.name();
            innerFrame.setTitle("Choose map file");
            innerFrame.setVisible(true);
        } else if(e.getActionCommand().equals(FrameActions.EXEC.name())) {
            showError(controller.exec(System.getProperty("user.dir")));
        } else if(e.getActionCommand().equals(FrameActions.ERROR_CLOSE.name())) {
            mainFrame.setVisible(true);
            errorFrame.setVisible(false);
            System.out.println("EXEC");
        } else if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
            innerFrame.setVisible(false);
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if(currentCommand.equals(FrameActions.DATA.name())) {
                showError(controller.setDataPath(path));
            } else if (currentCommand.equals(FrameActions.TEMPLATE.name())) {
                showError(controller.setTemplatePath(path));
            } else if (currentCommand.equals(FrameActions.MAP.name())) {
                showError(controller.setMap(path));
            }
        } else {
            innerFrame.setVisible(false);
        }
    }
}
