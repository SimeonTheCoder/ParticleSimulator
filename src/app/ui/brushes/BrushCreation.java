package app.ui.brushes;

import app.math.Vec2;
import app.ui.AppWindow;
import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;

import javax.swing.*;
import java.awt.*;

public class BrushCreation implements AppWindow {
    public JFrame frame;
    public AppWindow window;

    private JTextField attRadius;
    private JTextField attForce;
    private JTextField repRadius;
    private JTextField repForce;
    private JTextField mass;
    private JTextField group;

    private JCheckBox gravity;

    public BrushCreation(AppWindow window) {
        this.window = window;

        frame = new JFrame();

        frame.setTitle("SEPience");
        frame.setSize(200, 500);

        frame.setLocation(600, 300);

        JPanel panel = new JPanel();

        JLabel attractionLabelRadius = new JLabel("Attraction radius:");
        attRadius = new JTextField(10);

        JLabel attractionLabelStrength = new JLabel("Attraction strength:");
        attForce = new JTextField(10);

        JLabel repulsionLabelRadius = new JLabel("Repulsion radius:");
        repRadius = new JTextField(10);

        JLabel repulsionLabelStrength = new JLabel("Repulsion strength:");
        repForce = new JTextField(10);

        JLabel gravityLabel = new JLabel("Gravity");
        gravity = new JCheckBox();

        JLabel massLabel = new JLabel("Mass:");
        mass = new JTextField(10);

        JLabel groupLabel = new JLabel("Group: ");
        group = new JTextField(10);

        panel.add(attractionLabelRadius);
        panel.add(attRadius);

        panel.add(attractionLabelStrength);
        panel.add(attForce);

        panel.add(repulsionLabelRadius);
        panel.add(repRadius);

        panel.add(repulsionLabelStrength);
        panel.add(repForce);

        panel.add(gravityLabel);
        panel.add(gravity);

        panel.add(massLabel);
        panel.add(mass);

        panel.add(groupLabel);
        panel.add(group);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

        Dimension d = separator.getPreferredSize();
        d.width = 140;

        separator.setPreferredSize(d);
        panel.add(separator);

        JButton solidPresetButton = new JButton("Solid");
        solidPresetButton.addActionListener(new Actions(ActionCode.PRESET_SOLID, this));

        JButton liquidPresetButton = new JButton("Liquid");
        liquidPresetButton.addActionListener(new Actions(ActionCode.PRESET_LIQUID, this));

        JButton gasPresetButton = new JButton("Gas");
        gasPresetButton.addActionListener(new Actions(ActionCode.PRESET_GAS, this));

        JButton wallPresetButton = new JButton("Wall");
        wallPresetButton.addActionListener(new Actions(ActionCode.PRESET_WALL, this));

        panel.add(solidPresetButton);
        panel.add(liquidPresetButton);
        panel.add(gasPresetButton);
        panel.add(wallPresetButton);

        JButton createBrush = new JButton(" - Create brush - ");
        createBrush.addActionListener(new Actions(ActionCode.BRUSH_CREATE_SUBMIT, this));

        panel.add(separator);

        panel.add(createBrush);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    public Brush makeBrush() {
        BasicBrush brush = new BasicBrush();

        brush.build(
                new Vec2(Double.parseDouble(attRadius.getText()), Double.parseDouble(attForce.getText())),
                new Vec2(Double.parseDouble(repRadius.getText()), Double.parseDouble(repForce.getText())),
                gravity.isSelected(),
                Double.parseDouble(mass.getText()),
                0.5,
                Integer.parseInt(group.getText())
        );

        return brush;
    }

    public void config(double attRadius, double attForce, double repRadius, double repForce, double mass, boolean grav, int group) {
        this.attRadius.setText(String.valueOf(attRadius));
        this.attForce.setText(String.valueOf(attForce));

        this.repRadius.setText(String.valueOf(repRadius));
        this.repForce.setText(String.valueOf(repForce));

        this.mass.setText(String.valueOf(mass));

        this.gravity.setSelected(grav);

        this.group.setText(String.valueOf(group));
    }
}
