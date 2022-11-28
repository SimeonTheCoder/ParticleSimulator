package app.ui;

import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;

import javax.swing.*;
import java.awt.*;

public class Ui implements AppWindow {
    public JFrame frame;
    public Window window;

    public Ui() {
        frame = new JFrame();

        frame.setTitle("SEPience");
        frame.setSize(75, 1000);

        frame.setLocation(1000, 0);

        JPanel panel = new JPanel();

        panel.setBackground(new Color(32, 32, 32));
//        panel.setBackground(new Color(32, 0, 0));

        frame.setResizable(false);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new Actions(ActionCode.SIMULATION_SAVE, this));

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new Actions(ActionCode.SIMULATION_LOAD, this));

        JButton brushMakeButton = new JButton("Brush");
        brushMakeButton.addActionListener(new Actions(ActionCode.BRUSH_CREATE_POPUP, this));

        JButton eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(new Actions(ActionCode.ERASER_ACTION, this));

        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new Actions(ActionCode.SIMULATION_PAUSE, this));

        JButton pressureButton = new JButton("Pressure");
        pressureButton.addActionListener(new Actions(ActionCode.VIEW_PRESSURE, this));

        JButton threadsButton = new JButton("Threads");
        threadsButton.addActionListener(new Actions(ActionCode.CONFG_THREADS, this));

        saveButton.setBackground(new Color(32, 32, 32));
        loadButton.setBackground(new Color(32, 32, 32));

        saveButton.setForeground(new Color(255, 255, 255));
        loadButton.setForeground(new Color(255, 255, 255));

        panel.add(saveButton);
        panel.add(loadButton);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

        Dimension d = separator.getPreferredSize();
        d.width = 140;

        separator.setPreferredSize(d);
        panel.add(separator);

//        panel.add(new JSeparator());

        brushMakeButton.setBackground(new Color(32, 32, 32));
        eraserButton.setBackground(new Color(32, 32, 32));

        brushMakeButton.setForeground(new Color(255, 255, 255));
        eraserButton.setForeground(new Color(255, 255, 255));

        panel.add(brushMakeButton);
        panel.add(eraserButton);

        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);

        Dimension d2 = separator2.getPreferredSize();
        d2.width = 140;

        separator2.setPreferredSize(d2);
        panel.add(separator2);

        pauseButton.setBackground(new Color(32, 32, 32));
        threadsButton.setBackground(new Color(32, 32, 32));

        pauseButton.setForeground(new Color(255, 255, 255));
        threadsButton.setForeground(new Color(255, 255, 255));

        panel.add(pauseButton);
        panel.add(pressureButton);
        panel.add(threadsButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    public void call(ActionCode actionCode, String value) {
        System.out.println();

        switch (actionCode) {
            case SIMULATION_SAVE -> {
                window.save(value);

                break;
            }

            case SIMULATION_LOAD -> {
                window.load(value);

                break;
            }
        }
    }
}
