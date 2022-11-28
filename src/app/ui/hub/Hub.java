package app.ui.hub;

import app.ui.AppWindow;
import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;

import javax.swing.*;
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

        panel.setBackground(new Color(32, 32, 32));

        ImageIcon imageIcon = new ImageIcon("ParticleSimulation/assets/brand/sepience_logo_red.png");

        JLabel icon = new JLabel(imageIcon);
        panel.add(icon);

        JButton simulationButton = new JButton("Simulation");
        simulationButton.addActionListener(new Actions(ActionCode.SIMULATION_WINDOW, this));

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(new Actions(ActionCode.ABOUT_INFO, this));

        simulationButton.setBackground(new Color(32, 32, 32));
        aboutButton.setBackground(new Color(32, 32, 32));

        simulationButton.setForeground(new Color(255, 255, 255));
        aboutButton.setForeground(new Color(255, 255, 255));

        panel.add(simulationButton);
        panel.add(aboutButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
}
