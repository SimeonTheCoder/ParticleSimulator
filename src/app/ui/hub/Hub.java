package app.ui.hub;

import app.ui.AppWindow;
import app.ui.color.Palette;
import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;
import utils.lang.LANGTranslate;

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

        panel.setBackground(Palette.secondaryColor);

        ImageIcon imageIcon = new ImageIcon("ParticleSimulation/assets/brand/sepience_logo_red.png");

        JLabel icon = new JLabel(imageIcon);
        panel.add(icon);

        JButton simulationButton = new JButton(LANGTranslate.translate("Simulation"));
        simulationButton.addActionListener(new Actions(ActionCode.SIMULATION_WINDOW, this));

        JButton aboutButton = new JButton(LANGTranslate.translate("About"));
        aboutButton.addActionListener(new Actions(ActionCode.ABOUT_INFO, this));

        JButton platformButton = new JButton(LANGTranslate.translate("Manage"));
        platformButton.addActionListener(new Actions(ActionCode.PLATFORM_OPEN, this));

        simulationButton.setBackground(Palette.primaryColor);
        simulationButton.setBorder(new LineBorder(Palette.secondaryColor, 1, true));

        platformButton.setBackground(Palette.primaryColor);
        platformButton.setBorder(new LineBorder(Palette.secondaryColor, 1, true));

        aboutButton.setBackground(Palette.primaryColor);
        aboutButton.setBorder(new LineBorder(Palette.secondaryColor, 1, true));

        simulationButton.setForeground(Palette.secondaryColor);
        platformButton.setForeground(Palette.secondaryColor);
        aboutButton.setForeground(Palette.secondaryColor);

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
