package app.ui;

import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Ui implements AppWindow {
    public JFrame frame;
    public Window window;

    public Ui() {
        frame = new JFrame();

        frame.setTitle("SEPience");
        frame.setSize(100, 1000);

//        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 20, 40));

        frame.setLocation(1000, 0);

        JPanel panel = new JPanel();

        panel.setBackground(new Color(0, 20, 40));
//        panel.setBackground(new Color(32, 0, 0));

//        frame.setResizable(false);

        JButton saveButton = new JButton("\uD83D\uDCBE");
        saveButton.addActionListener(new Actions(ActionCode.SIMULATION_SAVE, this));
        saveButton.setFont(saveButton.getFont().deriveFont(30, 30));
        saveButton.setBorder(new LineBorder(new Color(0, 20, 40), 15, true));

        JButton loadButton = new JButton("📁");
        loadButton.addActionListener(new Actions(ActionCode.SIMULATION_LOAD, this));
        loadButton.setFont(loadButton.getFont().deriveFont(30, 30));
        loadButton.setBorder(new LineBorder(new Color(0, 20, 40), 15, true));

        JButton brushMakeButton = new JButton("\uD83D\uDD8C");
        brushMakeButton.addActionListener(new Actions(ActionCode.BRUSH_CREATE_POPUP, this));
        brushMakeButton.setFont(brushMakeButton.getFont().deriveFont(30, 30));
        brushMakeButton.setBorder(new LineBorder(new Color(0, 20, 40), 15, true));

        JButton eraserButton = new JButton("⌫");
        eraserButton.addActionListener(new Actions(ActionCode.ERASER_ACTION, this));
        eraserButton.setFont(eraserButton.getFont().deriveFont(30, 30));
        eraserButton.setBorder(new LineBorder(new Color(0, 20, 40), 15, true));

        JButton removeButton = new JButton("\uD83D\uDCA5");
        removeButton.addActionListener(new Actions(ActionCode.REMOVE_WALL_ACTION, this));
        removeButton.setFont(removeButton.getFont().deriveFont(30, 30));
        removeButton.setBorder(new LineBorder(new Color(0, 20, 40), 15, true));

        JButton pauseButton = new JButton("⏸");
        pauseButton.addActionListener(new Actions(ActionCode.SIMULATION_PAUSE, this));
        pauseButton.setFont(pauseButton.getFont().deriveFont(30, 30));
        pauseButton.setBorder(new LineBorder(new Color(0, 20, 40), 15, true));

        JButton pressureButton = new JButton("\uD83D\uDC53");
        pressureButton.addActionListener(new Actions(ActionCode.VIEW_PRESSURE, this));
        pressureButton.setFont(pressureButton.getFont().deriveFont(30, 30));
        pressureButton.setBorder(new LineBorder(new Color(0, 20, 40), 15, true));

        JButton motionVectorButton = new JButton("➡️");
        motionVectorButton.addActionListener(new Actions(ActionCode.VIEW_VECTORS, this));
        motionVectorButton.setFont(pressureButton.getFont().deriveFont(30, 30));
        motionVectorButton.setBorder(new LineBorder(new Color(0, 20, 40), 15, true));

        JButton threadsButton = new JButton("☠");
        threadsButton.addActionListener(new Actions(ActionCode.CONFG_THREADS, this));
        threadsButton.setFont(threadsButton.getFont().deriveFont(30, 30));
        threadsButton.setBorder(new LineBorder(new Color(0, 20, 40), 15, true));

        saveButton.setBackground(new Color(0, 20, 40));
        loadButton.setBackground(new Color(0, 20, 40));

        saveButton.setForeground(new Color(250, 180, 26));
        loadButton.setForeground(new Color(250, 180, 26));

        panel.add(saveButton);
        panel.add(loadButton);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

        Dimension d = separator.getPreferredSize();
        d.width = 140;

        separator.setPreferredSize(d);
//        panel.add(separator);

//        panel.add(new JSeparator());

        brushMakeButton.setBackground(new Color(0, 20, 40));
        eraserButton.setBackground(new Color(0, 20, 40));

        brushMakeButton.setForeground(new Color(250, 180, 26));
        eraserButton.setForeground(new Color(250, 180, 26));

        panel.add(brushMakeButton);
        panel.add(eraserButton);

        panel.add(removeButton);

        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);

        Dimension d2 = separator2.getPreferredSize();
        d2.width = 140;

        separator2.setPreferredSize(d2);
//        panel.add(separator2);

        pauseButton.setBackground(new Color(0, 20, 40));
        threadsButton.setBackground(new Color(0, 20, 40));

        pauseButton.setForeground(new Color(250, 180, 26));
        threadsButton.setForeground(new Color(250, 180, 26));

        pressureButton.setForeground(new Color(250, 180, 26));
        pressureButton.setBackground(new Color(0, 20, 40));

        removeButton.setForeground(new Color(250, 180, 26));
        removeButton.setBackground(new Color(0, 20, 40));

        motionVectorButton.setForeground(new Color(250, 180, 26));
        motionVectorButton.setBackground(new Color(0, 20, 40));

        panel.add(pauseButton);

        panel.add(pressureButton);
        panel.add(motionVectorButton);

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
            case SIMULATION_SAVE: {
                window.save(value);

                break;
            }

            case SIMULATION_LOAD: {
                window.load(value);

                break;
            }
        }
    }
}
