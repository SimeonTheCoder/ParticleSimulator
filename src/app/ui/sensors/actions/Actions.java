package app.ui.sensors.actions;

import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;
import app.math.Vec2;
import app.rendering.Renderer;
import app.ui.*;
import app.ui.brushes.BrushCreation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actions implements ActionListener {
    private ActionCode actionCode;
    private AppWindow window;

    public Actions(ActionCode actionCode, AppWindow window) {
        this.actionCode = actionCode;
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (actionCode) {
            case SIMULATION_WINDOW -> {
                Renderer renderer = new Renderer();

                Simulation simulation = new Simulation();

                simulation.globalForces.add(new Vec2(0, 9.8 / 100));

                Window window = new Window("Particle Simulation", 1000,
                        1000, renderer);

                window.ITERATIONS = 1;

                window.simulation = simulation;

                this.window.setVisible(false);

                Ui ui = new Ui();

                ui.window = window;
            }

            case POPUP_SUBMIT -> {
                window.setVisible(false);

                ((PopUp) window).getUi().call(((PopUp) window).getActionCode(), ((PopUp) window).getField().getText());
            }

            case SIMULATION_SAVE -> {
                PopUp popUp = new PopUp("Save Name", (Ui) window);

                popUp.setActionCode(ActionCode.SIMULATION_SAVE);
                popUp.frame.setSize(500, 100);
            }

            case SIMULATION_LOAD -> {
                PopUp popUp = new PopUp("File name", (Ui) window);

                popUp.setActionCode(ActionCode.SIMULATION_LOAD);
                popUp.frame.setSize(500, 100);
            }

            case BRUSH_CREATE_POPUP -> {
                BrushCreation brushCreation = new BrushCreation(window);
            }

            case BRUSH_CREATE_SUBMIT -> {
                BrushCreation brushUi = (BrushCreation) window;

                ((Ui) brushUi.window).window.setBrush(brushUi.makeBrush());

                ((Ui) brushUi.window).window.ERASER = false;

                brushUi.setVisible(false);
            }

            case PRESET_SOLID -> {
                ((BrushCreation) window).config(100, 3, 50, 1, 10, true, false, false, 1);
            }

            case PRESET_LIQUID -> {
                ((BrushCreation) window).config(100, 0.2, 50, 1, 3, true, false, false,  2);
            }

            case PRESET_GAS -> {
                ((BrushCreation) window).config(100, 0.1, 50, 10, 0.1, true,  false, false, 3);
            }

            case PRESET_WALL -> {
                ((BrushCreation) window).config(0, 0.1, 30, 10, 10000, false, false, false,  4);
            }

            case ERASER_ACTION -> {
                ((Ui) window).window.ERASER = true;
            }

            case SIMULATION_PAUSE -> {
                ((Ui) window).window.PAUSED = !((Ui) window).window.PAUSED;
            }

            case VIEW_PRESSURE -> {
                ((Ui) window).window.PRESSURE = !((Ui) window).window.PRESSURE;
            }

            case VIEW_VECTORS -> {
                ((Ui) window).window.VECTORS = !((Ui) window).window.VECTORS;
            }

            case CONFG_THREADS -> {
                ((Ui) window).window.THREADED = !((Ui) window).window.THREADED;
            }

            case ABOUT_INFO -> {
                JDialog dialog = new JDialog();

                dialog.setTitle("About SEPience");

                dialog.getContentPane().add(new JLabel("About v0.1: Created by Simeon Petkov, Copyright 2022"));

                dialog.setSize(400, 100);

                dialog.setLocation(600, 300);

                dialog.setVisible(true);
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }

            case REMOVE_WALL_ACTION -> {
                for (Particle particle : ((Ui) window).window.simulation.particles) {
                    if(particle.partition) particle.active = false;
                }
            }

            case PLATFORM_DOWNLOAD -> {
                ((Launcher) window).downloadSim();
            }

            case PLATFORM_OPEN -> {
                Launcher launcher = new Launcher();
            }
        }
    }
}
