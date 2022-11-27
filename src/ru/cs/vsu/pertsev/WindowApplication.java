package ru.cs.vsu.pertsev;

import javax.swing.*;
import java.awt.*;

public class WindowApplication extends JFrame {
    private JPanel mainPanel;

    public WindowApplication() throws HeadlessException {
        this.setTitle("BebraCompany: Task 10");
        this.setPreferredSize(new Dimension(768, 480));
        this.setContentPane(mainPanel);




        this.pack();
        this.setVisible(true);
    }
}
