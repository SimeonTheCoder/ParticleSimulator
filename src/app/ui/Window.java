package app.ui;

import app.core.Serialize;
import app.core.simulation.Simulation;
import app.core.simulation.threads.SimulationThread;
import app.math.Vec2;
import app.rendering.Renderer;
import app.ui.brushes.Brush;
import app.ui.sensors.KeyHandler;
import app.ui.sensors.Keyboard;
import app.ui.sensors.Mouse;
import utils.cfg.CFGPropertyReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JPanel implements AppWindow {
    private JFrame frame;
    private Renderer renderer;
    private KeyHandler handler;

    public Simulation simulation;
    public int ITERATIONS;

    public Vec2 selector;
    public boolean PAUSED;
    public boolean THREADED;
    public boolean ERASER;

    public boolean PRESSURE;
    public boolean VECTORS;

    public int currentBackground = 0;
    public int backgroundCount = 4;

    public BufferedImage backgroundImage;

    public Brush brush;

    public Window(String title, int xSize, int ySize, Renderer renderer) {
        frame = new JFrame(title);

        frame.setSize(xSize, ySize);
//        frame.setUndecorated(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(this);

        frame.setVisible(true);

        this.renderer = renderer;

        selector = new Vec2(0, 0);

        handler = new KeyHandler();

        frame.addKeyListener(new Keyboard(handler, this));
        frame.addMouseListener(new Mouse(handler, this));

        frame.setResizable(false);

        PAUSED = false;
        THREADED = false;
        ERASER = true;
        PRESSURE = false;

        backgroundCount = new File("ParticleSimulation/config/backgrounds").listFiles().length;

        backgroundImage = null;

        String[] syntax = {
                "background_image",
                "gravity"
        };

        String filename = String.format("%s%d.cfg", "ParticleSimulation/config/backgrounds/background_", 1);

        File file = new File(filename);

        if (file.exists()) {
            String backgroundAddress = CFGPropertyReader.readString(file, syntax, 0);

            try {
                backgroundImage = ImageIO.read(new File(backgroundAddress));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            double gravity = CFGPropertyReader.readDouble(file, syntax, 1);

//            simulation.globalForces.clear();
//            simulation.globalForces.add(new Vec2(0, gravity));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
        }else{
            g.setColor(new Color(0, 20, 40));
            g.setColor(new Color(0, 20, 40));
            g.fillRect(0, 0, 1920, 1080);
        }

        g.setColor(new Color(0, 20, 40));

        g.fillRect(0, 1000, 1920, 1080 - 1000);

        long millis1 = System.currentTimeMillis();

        selector.x = MouseInfo.getPointerInfo().getLocation().x - frame.getLocation().x;
        selector.y = MouseInfo.getPointerInfo().getLocation().y - 10 - frame.getLocation().y;

        selector.x = ((int) (selector.x)) / 20 * 20;
        selector.y = ((int) (selector.y)) / 20 * 20;

        if (Mouse.MOUSE_DOWN) {
            if (!ERASER) {
                handler.handle(16, this);
            } else {
                handler.handle(9, this);
            }
        }

        if (!PAUSED) {
            for (int i = 0; i < ITERATIONS; i++) {
                if (!THREADED) {
                    simulation.step();
                } else {
                    simulation.threadedStep();
                }
            }
        }

        renderer.render((Graphics2D) g, simulation, PRESSURE, VECTORS);

        g.setColor(new Color(250, 180, 26));
        g.fillRect((int) selector.x, (int) selector.y, 10, 10);

        repaint();

        if (PAUSED) {
            g.setColor(Color.BLUE);
            g.drawString("Paused", 20, 20);
        }

        if (THREADED) {
            g.setColor(Color.BLUE);
            g.drawString("Threaded", 20, 60);
        }

        if (SimulationThread.DIS_CHECK) {
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

    public void setBrush(Brush brush) {
        this.brush = brush;
    }
}
