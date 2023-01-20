package app.ui.sensors.actions;

import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;
import app.math.Vec2;
import app.rendering.Renderer;
import app.ui.*;
import app.ui.brushes.BrushCreation;
import utils.lang.LANGTranslate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actions implements ActionListener {
    private ActionCode actionCode;
    private AppWindow window;

    private int param;

    public Actions(ActionCode actionCode, AppWindow window) {
        this.actionCode = actionCode;
        this.window = window;

        param = 0;
    }

    public Actions(ActionCode actionCode, AppWindow window, int param) {
        this.actionCode = actionCode;
        this.window = window;
        this.param = param;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (actionCode) {
            case SIMULATION_WINDOW: {
                Renderer renderer = new Renderer();

                Simulation simulation = new Simulation();

                simulation.globalForces.add(new Vec2(0, 9.8 / 100.0));

                Window window = new Window(LANGTranslate.translate("Simulation"), 1000,
                        1000, renderer);

                window.ITERATIONS = 1;

                window.simulation = simulation;

                this.window.setVisible(false);

                Ui ui = new Ui();

                ui.window = window;

                break;
            }

            case POPUP_SUBMIT: {
                window.setVisible(false);

                ((PopUp) window).getUi().call(((PopUp) window).getActionCode(), ((PopUp) window).getField().getText());

                break;
            }

            case SIMULATION_SAVE: {
                PopUp popUp = new PopUp(LANGTranslate.translate("File name"), (Ui) window);

                popUp.setActionCode(ActionCode.SIMULATION_SAVE);
                popUp.frame.setSize(500, 100);

                break;
            }

            case SIMULATION_LOAD: {
                PopUp popUp = new PopUp(LANGTranslate.translate("File name"), (Ui) window);

                popUp.setActionCode(ActionCode.SIMULATION_LOAD);
                popUp.frame.setSize(500, 100);

                break;
            }

            case BRUSH_CREATE_POPUP: {
                BrushCreation brushCreation = new BrushCreation(window);

                break;
            }

            case BRUSH_CREATE_SUBMIT: {
                BrushCreation brushUi = (BrushCreation) window;

                ((Ui) brushUi.window).window.setBrush(brushUi.makeBrush());

                ((Ui) brushUi.window).window.ERASER = false;

                brushUi.setVisible(false);

                break;
            }

            case PRESET_SOLID: {
                ((BrushCreation) window).config(100, 3, 50, 1, 10, true, false, false, 1, 1);

                break;
            }

            case PRESET_LIQUID: {
                ((BrushCreation) window).config(100, 0.2, 50, 1, 3, true, false, false, 2, 1);

                break;
            }

            case PRESET_GAS: {
                ((BrushCreation) window).config(100, 0.1, 50, 10, 0.1, true, false, false, 3, 1);

                break;
            }

            case PRESET_WALL: {
                ((BrushCreation) window).config(0, 0.1, 30, 10, 10000, false, false, false, 4, 1);

                break;
            }

            case PRESET_USED: {
                ((BrushCreation) window).parseFromFile(param);

                break;
            }

            case ERASER_ACTION: {
                ((Ui) window).window.ERASER = true;

                break;
            }

            case SIMULATION_PAUSE: {
                ((Ui) window).window.PAUSED = !((Ui) window).window.PAUSED;

                break;
            }

            case VIEW_PRESSURE: {
                ((Ui) window).window.PRESSURE = !((Ui) window).window.PRESSURE;

                break;
            }

            case VIEW_SPECTRUM: {
                ((Ui) window).window.SPECTRUM = !((Ui) window).window.SPECTRUM;

                break;
            }

            case VIEW_VECTORS: {
                ((Ui) window).window.VECTORS = !((Ui) window).window.VECTORS;

                break;
            }

            case CONFG_THREADS: {
                ((Ui) window).window.THREADED = !((Ui) window).window.THREADED;

                break;
            }

            case ABOUT_INFO: {
                JDialog dialog = new JDialog();

                dialog.setTitle(LANGTranslate.translate("About"));

                dialog.getContentPane().add(new JLabel(LANGTranslate.translate("About v0.3: Created by Simeon Petkov, Copyright 2023")));

                dialog.setSize(400, 100);

                dialog.setLocation(600, 300);

                dialog.setVisible(true);
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                break;
            }

            case REMOVE_WALL_ACTION: {
                for (Particle particle : ((Ui) window).window.simulation.particles) {
                    if (particle.partition) particle.active = false;

                    break;
                }
            }

            case PLATFORM_DOWNLOAD: {
                ((Launcher) window).downloadSim();
            }

            case PLATFORM_OPEN: {
                Launcher launcher = new Launcher();

                break;
            }

            case CHANGE_BACKGROUND: {
                ((Ui) window).window.handler.handle(17, ((Ui) window).window);

                break;
            }
        }
    }
}
