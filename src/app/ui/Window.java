package app.ui;

import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;
import app.math.Vec2;
import app.rendering.Renderer;
import app.ui.sensors.KeyHandler;
import app.ui.sensors.Keyboard;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel {
    private JFrame frame;
    private Renderer renderer;

    public Simulation simulation;
    public int ITERATIONS;

    public Vec2 selector;

    public Window(String title, int xSize, int ySize, Renderer renderer) {
        frame = new JFrame(title);

        frame.setSize(xSize, ySize);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(this);

        frame.setVisible(true);

        this.renderer = renderer;

        selector = new Vec2(0, 0);

        frame.addKeyListener(new Keyboard(new KeyHandler(), this));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for(int i = 0; i < ITERATIONS; i ++) {
            simulation.step();
        }

        renderer.render((Graphics2D) g, simulation);

        g.setColor(new Color(255, 255, 0));
        g.fillRect((int) selector.x, (int) selector.y, 10, 10);

        repaint();
    }
}
