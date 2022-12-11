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
    
    private JTextField count;

    private JCheckBox gravity;
    private JCheckBox partition;
    private JCheckBox movable;

    public BrushCreation(AppWindow window) {
        this.window = window;

        frame = new JFrame();

        frame.setTitle("SEPience");
        frame.setSize(200, 500);

        frame.setLocation(600, 300);

        JPanel panel = new JPanel();

        panel.setBackground(new Color(0, 20, 40));

        JLabel attractionLabelRadius = new JLabel("Attraction radius:");
        attRadius = new JTextField(10);
        attractionLabelRadius.setForeground(new Color(250, 128, 26));
        attRadius.setForeground(new Color(250, 128, 26));
        attRadius.setBackground(new Color(0, 20, 40));

        JLabel attractionLabelStrength = new JLabel("Attraction strength:");
        attForce = new JTextField(10);
        attractionLabelStrength.setForeground(new Color(250, 128, 26));
        attForce.setForeground(new Color(250, 128, 26));
        attForce.setBackground(new Color(0, 20, 40));

        JLabel repulsionLabelRadius = new JLabel("Repulsion radius:");
        repRadius = new JTextField(10);
        repulsionLabelRadius.setForeground(new Color(250, 128, 26));
        repRadius.setForeground(new Color(250, 128, 26));
        repRadius.setBackground(new Color(0, 20, 40));

        JLabel repulsionLabelStrength = new JLabel("Repulsion strength:");
        repForce = new JTextField(10);
        repulsionLabelStrength.setForeground(new Color(250, 128, 26));
        repForce.setForeground(new Color(250, 128, 26));
        repForce.setBackground(new Color(0, 20, 40));

        JLabel gravityLabel = new JLabel("Gravity");
        gravity = new JCheckBox();
        gravityLabel.setForeground(new Color(250, 128, 26));
        gravityLabel.setBackground(new Color(0, 20, 40));

        JLabel partitionLabel = new JLabel("Partition");
        partitionLabel.setForeground(new Color(250, 128, 26));
        partitionLabel.setBackground(new Color(0, 20, 40));
        partition = new JCheckBox();

        JLabel movableLabel = new JLabel("Movable");
        movableLabel.setForeground(new Color(250, 128, 26));
        movableLabel.setBackground(new Color(0, 20, 40));
        movable = new JCheckBox();

        JLabel massLabel = new JLabel("Mass:");
        mass = new JTextField(10);
        massLabel.setForeground(new Color(250, 128, 26));
        mass.setForeground(new Color(250, 128, 26));
        mass.setBackground(new Color(0, 20, 40));

        JLabel groupLabel = new JLabel("Group: ");
        group = new JTextField(10);
        groupLabel.setForeground(new Color(250, 128, 26));
        group.setForeground(new Color(250, 128, 26));
        group.setBackground(new Color(0, 20, 40));
        
        JLabel countLabel = new JLabel("Count: ");
        count = new JTextField(10);
        countLabel.setForeground(new Color(250, 128, 26));
        count.setForeground(new Color(250, 128, 26));
        count.setBackground(new Color(0, 20, 40));

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

        panel.add(partitionLabel);
        panel.add(partition);

        panel.add(movableLabel);
        panel.add(movable);

        panel.add(massLabel);
        panel.add(mass);

        panel.add(groupLabel);
        panel.add(group);

        panel.add(countLabel);
        panel.add(count);

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

        solidPresetButton.setBackground(new Color(0, 20, 40));
        liquidPresetButton.setBackground(new Color(0, 20, 40));

        solidPresetButton.setForeground(new Color(250, 128, 26));
        liquidPresetButton.setForeground(new Color(250, 128, 26));

        gasPresetButton.setBackground(new Color(0, 20, 40));
        wallPresetButton.setBackground(new Color(0, 20, 40));

        gasPresetButton.setForeground(new Color(250, 128, 26));
        wallPresetButton.setForeground(new Color(250, 128, 26));

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
        Brush brush = new BasicBrush();

        brush.build(
                new Vec2(Double.parseDouble(attRadius.getText()), Double.parseDouble(attForce.getText())),
                new Vec2(Double.parseDouble(repRadius.getText()), Double.parseDouble(repForce.getText())),
                gravity.isSelected(),
                partition.isSelected(),
                movable.isSelected(),
                Double.parseDouble(mass.getText()),
                0.5,
                Integer.parseInt(group.getText()),
                Integer.parseInt(count.getText())
        );

        return brush;
    }

    public void config(double attRadius, double attForce, double repRadius, double repForce, double mass, boolean grav, boolean part, boolean mov, int group, int count) {
        this.attRadius.setText(String.valueOf(attRadius));
        this.attForce.setText(String.valueOf(attForce));

        this.repRadius.setText(String.valueOf(repRadius));
        this.repForce.setText(String.valueOf(repForce));

        this.mass.setText(String.valueOf(mass));

        this.gravity.setSelected(grav);

        this.group.setText(String.valueOf(group));

        this.partition.setSelected(part);
        this.movable.setSelected(mov);

        this.count.setText(String.valueOf(count));
    }
}
