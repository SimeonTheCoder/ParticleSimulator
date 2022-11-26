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

        JButton threadsButton = new JButton("Threads");
        threadsButton.addActionListener(new Actions(ActionCode.CONFG_THREADS, this));

        panel.add(saveButton);
        panel.add(loadButton);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

        Dimension d = separator.getPreferredSize();
        d.width = 140;

        separator.setPreferredSize(d);
        panel.add(separator);

//        panel.add(new JSeparator());

        panel.add(brushMakeButton);
        panel.add(eraserButton);

        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);

        Dimension d2 = separator2.getPreferredSize();
        d2.width = 140;

        separator2.setPreferredSize(d2);
        panel.add(separator2);

        panel.add(pauseButton);
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
