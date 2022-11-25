package app.ui;

import app.ui.sensors.ActionCode;
import app.ui.sensors.Actions;

import javax.swing.*;

public class Ui implements AppWindow{
    public JFrame frame;
    public Window window;

    public Ui() {
        frame = new JFrame();

        frame.setTitle("SEPience");
        frame.setSize(75, 1000);

        frame.setLocation(1000, 0);

        JPanel panel = new JPanel();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new Actions(ActionCode.SIMULATION_SAVE, this));

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new Actions(ActionCode.SIMULATION_LOAD, this));

        panel.add(saveButton);
        panel.add(loadButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    public void call(ActionCode actionCode, String value) {
        System.out.println();

        switch (actionCode) {
            case SIMULATION_SAVE -> {
                window.save(value);

                break;
            }

            case SIMULATION_LOAD -> {
                window.load(value);

                break;
            }
        }
    }
}
