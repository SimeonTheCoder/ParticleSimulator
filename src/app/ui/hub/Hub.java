package app.ui.hub;

import app.ui.AppWindow;
import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Hub implements AppWindow {
    public JFrame frame;

    public Hub() {
        frame = new JFrame();

        frame.setTitle("SEPience");
        frame.setSize(200, 200);

        frame.setResizable(false);

        frame.setLocation(600, 300);

        JPanel panel = new JPanel();

        panel.setBackground(new Color(0, 20, 40));

        ImageIcon imageIcon = new ImageIcon("ParticleSimulation/assets/brand/sepience_logo_red.png");

        JLabel icon = new JLabel(imageIcon);
        panel.add(icon);

        JButton simulationButton = new JButton("Simulation");
        simulationButton.addActionListener(new Actions(ActionCode.SIMULATION_WINDOW, this));

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(new Actions(ActionCode.ABOUT_INFO, this));

        JButton platformButton = new JButton("Manage Simulations");
        platformButton.addActionListener(new Actions(ActionCode.PLATFORM_OPEN, this));

        simulationButton.setBackground(new Color(250, 188, 26));
        simulationButton.setBorder(new LineBorder(new Color(0, 20, 40), 1, true));

        platformButton.setBackground(new Color(250, 188, 26));
        platformButton.setBorder(new LineBorder(new Color(0, 20, 40), 1, true));

        aboutButton.setBackground(new Color(250, 188, 26));
        aboutButton.setBorder(new LineBorder(new Color(0, 20, 40), 1, true));

        simulationButton.setForeground(new Color(0, 20, 40));
        platformButton.setForeground(new Color(0, 20, 40));
        aboutButton.setForeground(new Color(0, 20, 40));

        panel.add(simulationButton);

        panel.add(aboutButton);

        panel.add(platformButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
}
