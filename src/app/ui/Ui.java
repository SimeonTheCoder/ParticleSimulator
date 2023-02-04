package app.ui;

import app.ui.color.Palette;
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
        frame.setBackground(Palette.secondaryColor);

        frame.setLocation(1000, 0);

        JPanel panel = new JPanel();

        panel.setBackground(Palette.secondaryColor);
//        panel.setBackground(new Color(32, 0, 0));

//        frame.setResizable(false);

        JButton saveButton = new JButton("\uD83D\uDCBE");
        saveButton.addActionListener(new Actions(ActionCode.SIMULATION_SAVE, this));
        saveButton.setFont(saveButton.getFont().deriveFont(30, 30));
        saveButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton loadButton = new JButton("📁");
        loadButton.addActionListener(new Actions(ActionCode.SIMULATION_LOAD, this));
        loadButton.setFont(loadButton.getFont().deriveFont(30, 30));
        loadButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton brushMakeButton = new JButton("\uD83D\uDD8C");
        brushMakeButton.addActionListener(new Actions(ActionCode.BRUSH_CREATE_POPUP, this));
        brushMakeButton.setFont(brushMakeButton.getFont().deriveFont(30, 30));
        brushMakeButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton eraserButton = new JButton("⌫");
        eraserButton.addActionListener(new Actions(ActionCode.ERASER_ACTION, this));
        eraserButton.setFont(eraserButton.getFont().deriveFont(30, 30));
        eraserButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton removeButton = new JButton("\uD83D\uDCA5");
        removeButton.addActionListener(new Actions(ActionCode.REMOVE_WALL_ACTION, this));
        removeButton.setFont(removeButton.getFont().deriveFont(30, 30));
        removeButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton pauseButton = new JButton("⏸");
        pauseButton.addActionListener(new Actions(ActionCode.SIMULATION_PAUSE, this));
        pauseButton.setFont(pauseButton.getFont().deriveFont(30, 30));
        pauseButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton pressureButton = new JButton("\uD83D\uDC53");
        pressureButton.addActionListener(new Actions(ActionCode.VIEW_PRESSURE, this));
        pressureButton.setFont(pressureButton.getFont().deriveFont(30, 30));
        pressureButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton spectrumButton = new JButton("\uD83C\uDFA8");
        spectrumButton.addActionListener(new Actions(ActionCode.VIEW_SPECTRUM, this));
        spectrumButton.setFont(spectrumButton.getFont().deriveFont(30, 30));
        spectrumButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton motionVectorButton = new JButton(" V ");
        motionVectorButton.addActionListener(new Actions(ActionCode.VIEW_VECTORS, this));
        motionVectorButton.setFont(pressureButton.getFont().deriveFont(30, 30));
        motionVectorButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton backgroundButton = new JButton("\uD83C\uDFDE");
        backgroundButton.addActionListener(new Actions(ActionCode.CHANGE_BACKGROUND, this));
        backgroundButton.setFont(pressureButton.getFont().deriveFont(30, 30));
        backgroundButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton threadsButton = new JButton("☠");
        threadsButton.addActionListener(new Actions(ActionCode.CONFG_THREADS, this));
        threadsButton.setFont(threadsButton.getFont().deriveFont(30, 30));
        threadsButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton spritesButton = new JButton("👁");
        spritesButton.addActionListener(new Actions(ActionCode.ENABLE_SPRITES, this));
        spritesButton.setFont(threadsButton.getFont().deriveFont(30, 30));
        spritesButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        JButton liquidButton = new JButton("\uD83C\uDF0A");
        liquidButton.addActionListener(new Actions(ActionCode.ENABLE_EXP_LIQUID, this));
        liquidButton.setFont(threadsButton.getFont().deriveFont(30, 30));
        liquidButton.setBorder(new LineBorder(Palette.secondaryColor, 15, true));

        saveButton.setBackground(Palette.secondaryColor);
        loadButton.setBackground(Palette.secondaryColor);

        saveButton.setForeground(Palette.primaryColor);
        loadButton.setForeground(Palette.primaryColor);

        spritesButton.setForeground(Palette.primaryColor);
        spritesButton.setBackground(Palette.secondaryColor);

        liquidButton.setForeground(Palette.primaryColor);
        liquidButton.setBackground(Palette.secondaryColor);

        panel.add(saveButton);
        panel.add(loadButton);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

        Dimension d = separator.getPreferredSize();
        d.width = 140;

        separator.setPreferredSize(d);
//        panel.add(separator);

//        panel.add(new JSeparator());

        brushMakeButton.setBackground(Palette.secondaryColor);
        eraserButton.setBackground(Palette.secondaryColor);

        brushMakeButton.setForeground(Palette.primaryColor);
        eraserButton.setForeground(Palette.primaryColor);

        panel.add(brushMakeButton);
        panel.add(eraserButton);

        panel.add(removeButton);

        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);

        Dimension d2 = separator2.getPreferredSize();
        d2.width = 140;

        separator2.setPreferredSize(d2);
//        panel.add(separator2);

        pauseButton.setBackground(Palette.secondaryColor);
        threadsButton.setBackground(Palette.secondaryColor);

        pauseButton.setForeground(Palette.primaryColor);
        threadsButton.setForeground(Palette.primaryColor);

        pressureButton.setForeground(Palette.primaryColor);
        pressureButton.setBackground(Palette.secondaryColor);

        spectrumButton.setForeground(Palette.primaryColor);
        spectrumButton.setBackground(Palette.secondaryColor);

        removeButton.setForeground(Palette.primaryColor);
        removeButton.setBackground(Palette.secondaryColor);

        motionVectorButton.setForeground(Palette.primaryColor);
        motionVectorButton.setBackground(Palette.secondaryColor);

        backgroundButton.setForeground(Palette.primaryColor);
        backgroundButton.setBackground(Palette.secondaryColor);

        panel.add(pauseButton);

        panel.add(pressureButton);
        panel.add(spectrumButton);

        panel.add(motionVectorButton);

        panel.add(backgroundButton);

        panel.add(threadsButton);

        panel.add(spritesButton);

        panel.add(liquidButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    public void call(ActionCode actionCode, String value) {
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
