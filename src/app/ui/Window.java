package app.ui;

import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;
import app.core.simulation.threads.SimulationThread;
import app.math.Vec2;
import app.rendering.Renderer;
import app.ui.sensors.KeyHandler;
import app.ui.sensors.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Window extends JPanel {
    private JFrame frame;
    private Renderer renderer;

    public Simulation simulation;
    public int ITERATIONS;

    public Vec2 selector;
    public boolean PAUSED;
    public boolean THREADED;

    public Window(String title, int xSize, int ySize, Renderer renderer) {
        frame = new JFrame(title);

        frame.setSize(xSize, ySize);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(this);

        frame.setVisible(true);

        this.renderer = renderer;

        selector = new Vec2(0, 0);

        frame.addKeyListener(new Keyboard(new KeyHandler(), this));

        PAUSED = false;
        THREADED = false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        long millis1 = System.currentTimeMillis();

        if(!PAUSED) {
            for (int i = 0; i < ITERATIONS; i++) {
                if(!THREADED) {
                    simulation.step();
                }else{
                    simulation.threadedStep();
                }
            }
        }

        renderer.render((Graphics2D) g, simulation);

        g.setColor(new Color(255, 255, 0));
        g.fillRect((int) selector.x, (int) selector.y, 10, 10);

        repaint();

        if(PAUSED) {
            g.setColor(Color.BLUE);
            g.drawString("Paused", 20, 20);
        }

        if(THREADED) {
            g.setColor(Color.BLUE);
            g.drawString("Threaded", 20, 60);
        }

        if(SimulationThread.DIS_CHECK) {
            g.setColor(Color.PINK);
            g.drawString("CHECK", 20, 80);
        }

        long millis2 = System.currentTimeMillis();

        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(1 / ((millis2 - millis1) + .0) * 1000), 20, 40);
    }
}
