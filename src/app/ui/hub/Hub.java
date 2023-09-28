package app.ui.hub;

import app.ui.AppWindow;
import app.ui.color.Palette;
import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;
import utils.lang.LANGTranslate;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Hub implements AppWindow {
    public JFrame frame;

    public Hub() {
        frame = new JFrame();

        frame.setTitle("SEPience");
        frame.setSize(400, 300);

        frame.setResizable(false);
        frame.setUndecorated(true);

        frame.setLocation(550, 300);

        JPanel panel = new JPanel();

        panel.setBackground(Palette.secondaryColor);

        ImageIcon imageIcon = new ImageIcon("ParticleSimulation/assets/brand/sepience_logo_red.png");

        JLabel icon = new JLabel(imageIcon);

        panel.add(icon);

        JButton simulationButton = new JButton(LANGTranslate.translate("Simulation"));
        simulationButton.addActionListener(new Actions(ActionCode.SIMULATION_WINDOW, this));
        simulationButton.setFont(simulationButton.getFont().deriveFont(30, 30));
        simulationButton.setBorder(new LineBorder(Palette.thirdColor, 5, true));
        simulationButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {simulationButton.setBackground(Palette.primaryColor);simulationButton.setForeground(Palette.secondaryColor);}
            public void mouseExited(MouseEvent evt) {simulationButton.setBackground(Palette.thirdColor);simulationButton.setForeground(Palette.primaryColor);}
        });

        JButton aboutButton = new JButton(LANGTranslate.translate("About"));
        aboutButton.addActionListener(new Actions(ActionCode.ABOUT_INFO, this));
        aboutButton.setFont(aboutButton.getFont().deriveFont(30, 30));
        aboutButton.setBorder(new LineBorder(Palette.thirdColor, 5, true));
        aboutButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {aboutButton.setBackground(Palette.primaryColor);aboutButton.setForeground(Palette.secondaryColor);}
            public void mouseExited(MouseEvent evt) {aboutButton.setBackground(Palette.thirdColor);aboutButton.setForeground(Palette.primaryColor);}
        });

        JButton platformButton = new JButton(LANGTranslate.translate("Manage"));
        platformButton.addActionListener(new Actions(ActionCode.PLATFORM_OPEN, this));
        platformButton.setFont(platformButton.getFont().deriveFont(30, 30));
        platformButton.setBorder(new LineBorder(Palette.thirdColor, 5, true));

        simulationButton.setBackground(Palette.thirdColor);
//        simulationButton.setBorder(new LineBorder(Palette.secondaryColor, 1, true));

        platformButton.setBackground(Palette.thirdColor);
//        platformButton.setBorder(new LineBorder(Palette.secondaryColor, 1, true));

        aboutButton.setBackground(Palette.thirdColor);
//        aboutButton.setBorder(new LineBorder(Palette.secondaryColor, 1, true));

        simulationButton.setForeground(Palette.primaryColor);
        platformButton.setForeground(Palette.primaryColor);
        aboutButton.setForeground(Palette.primaryColor);

        panel.add(simulationButton);

        panel.add(aboutButton);

//        panel.add(platformButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
}
