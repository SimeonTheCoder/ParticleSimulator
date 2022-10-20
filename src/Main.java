import app.core.simulation.Simulation;
import app.math.Vec2;
import app.rendering.Renderer;
import app.ui.Window;

public class Main {
    public static void main(String[] args) {
        Renderer renderer = new Renderer();

        Simulation simulation = new Simulation();

        simulation.globalForces.add(new Vec2(0, 9.8 / 100));

        Window window = new Window("Particle Simulation", 1000,
                1000, renderer);

        window.ITERATIONS = 1;

        window.simulation = simulation;
    }
}
