package app.ui.brushes;

import app.math.Vec2;
import app.ui.AppWindow;
import app.ui.color.Palette;
import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;
import utils.cfg.CFGPropertyReader;
import utils.lang.LANGTranslate;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BrushCreation implements AppWindow {
    private static final String BRUSH_CONFIG_FILE = "ParticleSimulation/config/brushes/preset_";

    private static final String[] syntax = {
            "attraction_force",
            "attraction_radius",
            "repulsion_force",
            "repulsion_radius",
            "mass",
            "gravity",
            "partition",
            "movable",
            "group",
            "count",
            "velX",
            "velY"
    };

    public JFrame frame;
    public AppWindow window;

    private JTextField attRadius;
    private JTextField attForce;
    private JTextField repRadius;
    private JTextField repForce;

    private JTextField initialVelX;
    private JTextField initialVelY;

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
        frame.setSize(200, 450);

        frame.setLocation(600, 300);

        JPanel panel = new JPanel();

        panel.setBackground(Palette.secondaryColor);

        JLabel attractionLabelRadius = new JLabel(LANGTranslate.translate("Attraction radius"));
        attRadius = new JTextField(10);
        attractionLabelRadius.setForeground(Palette.primaryColor);
        attRadius.setForeground(Palette.primaryColor);
        attRadius.setBackground(Palette.thirdColor);

        JLabel attractionLabelStrength = new JLabel(LANGTranslate.translate("Attraction strength"));
        attForce = new JTextField(10);
        attractionLabelStrength.setForeground(Palette.primaryColor);
        attForce.setForeground(Palette.primaryColor);
        attForce.setBackground(Palette.thirdColor);

        JLabel repulsionLabelRadius = new JLabel(LANGTranslate.translate("Repulsion radius"));
        repRadius = new JTextField(10);
        repulsionLabelRadius.setForeground(Palette.primaryColor);
        repRadius.setForeground(Palette.primaryColor);
        repRadius.setBackground(Palette.thirdColor);

        JLabel repulsionLabelStrength = new JLabel(LANGTranslate.translate("Repulsion strength"));
        repForce = new JTextField(10);
        repulsionLabelStrength.setForeground(Palette.primaryColor);
        repForce.setForeground(Palette.primaryColor);
        repForce.setBackground(Palette.thirdColor);

        JLabel gravityLabel = new JLabel(LANGTranslate.translate("Gravity"));
        gravity = new JCheckBox();
        gravityLabel.setForeground(Palette.primaryColor);
        gravityLabel.setBackground(Palette.secondaryColor);

        JLabel initialVelocityLabel = new JLabel(LANGTranslate.translate("Initial velocity"));
        initialVelX = new JTextField(4);
        initialVelY = new JTextField(4);
        initialVelocityLabel.setForeground(Palette.primaryColor);
        initialVelX.setForeground(Palette.primaryColor);
        initialVelX.setBackground(Palette.thirdColor);
        initialVelY.setForeground(Palette.primaryColor);
        initialVelY.setBackground(Palette.thirdColor);

        JLabel partitionLabel = new JLabel(LANGTranslate.translate("Partition"));
        partitionLabel.setForeground(Palette.primaryColor);
        partitionLabel.setBackground(Palette.thirdColor);
        partition = new JCheckBox();

        JLabel movableLabel = new JLabel(LANGTranslate.translate("Movable"));
        movableLabel.setForeground(Palette.primaryColor);
        movableLabel.setBackground(Palette.thirdColor);
        movable = new JCheckBox();

        JLabel massLabel = new JLabel(LANGTranslate.translate("Mass"));
        mass = new JTextField(10);
        massLabel.setForeground(Palette.primaryColor);
        mass.setForeground(Palette.primaryColor);
        mass.setBackground(Palette.thirdColor);

        JLabel groupLabel = new JLabel(LANGTranslate.translate("Group"));
        group = new JTextField(10);
        groupLabel.setForeground(Palette.primaryColor);
        group.setForeground(Palette.primaryColor);
        group.setBackground(Palette.thirdColor);

        JLabel countLabel = new JLabel(LANGTranslate.translate("Count"));
        count = new JTextField(10);
        countLabel.setForeground(Palette.primaryColor);
        count.setForeground(Palette.primaryColor);
        count.setBackground(Palette.thirdColor);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        JSeparator separator4 = new JSeparator(SwingConstants.HORIZONTAL);

        Dimension d = separator.getPreferredSize();
        d.width = 160;

        separator.setPreferredSize(d);
        separator2.setPreferredSize(d);
        separator3.setPreferredSize(d);
        separator4.setPreferredSize(d);

        panel.add(attractionLabelRadius);
        panel.add(attRadius);

        panel.add(attractionLabelStrength);
        panel.add(attForce);

        panel.add(repulsionLabelRadius);
        panel.add(repRadius);

        panel.add(repulsionLabelStrength);
        panel.add(repForce);

        panel.add(separator);

        panel.add(gravityLabel);
        panel.add(gravity);

        panel.add(separator2);

        panel.add(initialVelocityLabel);
        panel.add(initialVelX);
        panel.add(initialVelY);

        panel.add(separator3);

//        panel.add(partitionLabel);
//        panel.add(partition);

//        panel.add(movableLabel);
//        panel.add(movable);

        panel.add(massLabel);
        panel.add(mass);

        panel.add(groupLabel);
        panel.add(group);

        panel.add(countLabel);
        panel.add(count);

        panel.add(separator4);

        int fileCount = new File("ParticleSimulation/config/brushes").listFiles().length;

        for (int i = 1; i <= fileCount; i++) {
            String filename = String.format("%s%d.cfg", BRUSH_CONFIG_FILE, i);

            File file = new File(filename);

            if (file.exists()) {
                Scanner scanner = null;

                try {
                    scanner = new Scanner(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                String line = scanner.nextLine();

                String presetTitle = line.split(" = ")[1];

                JButton presetButton = new JButton(presetTitle);
                presetButton.addActionListener(new Actions(ActionCode.PRESET_USED, this, i));

                presetButton.setBackground(Palette.secondaryColor);
                presetButton.setForeground(Palette.primaryColor);

                panel.add(presetButton);
            }
        }

        JButton createBrush = new JButton(LANGTranslate.translate("Create brush"));
        createBrush.addActionListener(new Actions(ActionCode.BRUSH_CREATE_SUBMIT, this));

        panel.add(separator);

        panel.add(createBrush);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new Actions(ActionCode.CANCEL_WINDOW, this));

        panel.add(cancelButton);

        frame.add(panel);

        frame.setUndecorated(true);

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
                1,
                Integer.parseInt(group.getText()),
                Integer.parseInt(count.getText()),
                Double.parseDouble(initialVelX.getText()),
                Double.parseDouble(initialVelY.getText())
        );

        return brush;
    }

    public void config(double attRadius, double attForce, double repRadius, double repForce, double mass, boolean grav,
                       boolean part, boolean mov, int group, int count, double initVelX, double initVelY) {
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

        this.initialVelX.setText(String.valueOf(initVelX));
        this.initialVelY.setText(String.valueOf(initVelY));
    }

    public void parseFromFile(int index) {
        String filename = BRUSH_CONFIG_FILE + index + ".cfg";

        File file = new File(filename);

        if (file.exists()) {
            Scanner scanner = null;

            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            double attForceVal = CFGPropertyReader.readDouble(file, syntax, 0), attForceRad = CFGPropertyReader.readDouble(file, syntax, 1);
            double repForceVal = CFGPropertyReader.readDouble(file, syntax, 2), repForceRad = CFGPropertyReader.readDouble(file, syntax, 3);

            double mass = CFGPropertyReader.readDouble(file, syntax, 4);

            boolean grav = CFGPropertyReader.readBool(file, syntax, 5);
            boolean part = CFGPropertyReader.readBool(file, syntax, 6);
            boolean mov = CFGPropertyReader.readBool(file, syntax, 7);

            int group = CFGPropertyReader.readInt(file, syntax, 8);
            int count = CFGPropertyReader.readInt(file, syntax, 9);

            double velX;
            try {
                velX = CFGPropertyReader.readDouble(file, syntax, 10);
            } catch (Exception exception) {
                velX = 0;
            }

            double velY;
            try {
                velY = CFGPropertyReader.readDouble(file, syntax, 11);
            } catch (Exception exception) {
                velY = 0;
            }

            config(attForceRad, attForceVal, repForceRad, repForceVal, mass, grav, part, mov, group, count, velX, velY);
        }
    }
}
