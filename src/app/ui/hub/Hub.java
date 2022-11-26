package app.ui.hub;

import app.ui.AppWindow;
import app.ui.sensors.ActionCode;
import app.ui.sensors.Actions;

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

        panel.add(simulationButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
}
