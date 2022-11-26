package app.ui.hub;

import app.ui.AppWindow;
import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;

import javax.swing.*;

public class Hub implements AppWindow {
    public JFrame frame;

    public Hub() {
        frame = new JFrame();

        frame.setTitle("SEPience");
        frame.setSize(200, 200);

        frame.setLocation(600, 300);

        JPanel panel = new JPanel();

        ImageIcon imageIcon = new ImageIcon("ParticleSimulation/assets/brand/sepience_logo_red.png");

        JLabel icon = new JLabel(imageIcon);
        panel.add(icon);

        JButton simulationButton = new JButton("Simulation");
        simulationButton.addActionListener(new Actions(ActionCode.SIMULATION_WINDOW, this));

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(new Actions(ActionCode.ABOUT_INFO, this));

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
