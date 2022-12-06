package app.ui;

import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Launcher implements AppWindow{
    private JFrame frame;

    public Launcher() {
        frame = new JFrame();

        frame.setSize(500, 500);

        frame.setResizable(false);
//        frame.setUndecorated(true);

        JPanel panel = new JPanel();

        JButton fetchButton = new JButton("Download Simulation");
        fetchButton.addActionListener(new Actions(ActionCode.PLATFORM_DOWNLOAD, this));

        panel.add(fetchButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {

    }

    public void downloadSim() {
        try {
            String data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);

            FileWriter writer = new FileWriter("ParticleSimulation\\data\\simulations\\test.pts");

            writer.write(data);

            writer.close();
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
