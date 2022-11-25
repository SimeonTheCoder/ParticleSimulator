package app.ui.sensors;

import app.core.simulation.Simulation;
import app.math.Vec2;
import app.rendering.Renderer;
import app.ui.AppWindow;
import app.ui.PopUp;
import app.ui.Ui;
import app.ui.Window;

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
            case SIMULATION_WINDOW ->  {
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

                break;
            }

            case POPUP_SUBMIT -> {
                window.setVisible(false);

                ((PopUp) window).getUi().call(((PopUp) window).getActionCode(), ((PopUp) window).getField().getText());
            }

            case SIMULATION_SAVE -> {
                PopUp popUp = new PopUp("Save Name", (Ui) window);

                popUp.setActionCode(ActionCode.SIMULATION_SAVE);
            }

            case SIMULATION_LOAD -> {
                PopUp popUp = new PopUp("File name", (Ui) window);

                popUp.setActionCode(ActionCode.SIMULATION_LOAD);
            }
        }
    }
}
