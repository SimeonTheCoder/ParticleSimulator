package app.ui;

import app.core.Serialize;
import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;
import app.core.simulation.threads.SimulationThread;
import app.math.Vec2;
import app.rendering.Renderer;
import app.ui.sensors.KeyHandler;
import app.ui.sensors.Keyboard;
import app.ui.sensors.Mouse;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Window extends JPanel implements AppWindow {
    private JFrame frame;
    private Renderer renderer;
    private KeyHandler handler;

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

        handler = new KeyHandler();

        frame.addKeyListener(new Keyboard(handler, this));
        frame.addMouseListener(new Mouse(handler, this));

        PAUSED = false;
        THREADED = false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        long millis1 = System.currentTimeMillis();

        selector.x = MouseInfo.getPointerInfo().getLocation().x - 10;
        selector.y = MouseInfo.getPointerInfo().getLocation().y - 30;

        selector.x = ((int) (selector.x)) / 20 * 20;
        selector.y = ((int) (selector.y)) / 20 * 20;

        if(Mouse.MOUSE_DOWN) {
            handler.handle(6, this);
        }

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

        g.setColor(Color.PINK);
        g.drawString(String.valueOf(simulation.particles.size()), 20, 80);
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    public void save(String name) {
        Serialize serialize = new Serialize();

        serialize.save(name, simulation);
    }

    public void load(String name) {
        Serialize serialize = new Serialize();

        serialize.load(name, simulation);
    }
}
