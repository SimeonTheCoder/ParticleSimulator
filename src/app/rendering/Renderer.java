package app.rendering;

import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Renderer {
    public int[][] values;

    private int time = 0;

    public BufferedImage[] sprites;

    public String[] spriteAddresses = {
            "-",
            "ParticleSimulation/assets/sprites/liquid.png",
            "ParticleSimulation/assets/sprites/gas.png",
            "ParticleSimulation/assets/sprites/solid.png"
    };

    public Renderer() {
        values = new int[1000][1000];

        sprites = new BufferedImage[5];

        try {
            int i = 1;

            for (String spriteAddress : spriteAddresses) {
                if (!spriteAddress.equals("-")) {
                    BufferedImage sprite = ImageIO.read(new File(spriteAddress));

                    sprites[i] = sprite;
                }

                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        random = new Random();
    }

    public static boolean DEBUG = true;
    private Random random;

    public void render(Graphics2D g, Simulation simulation, boolean PRESSURE, boolean VECTORS) {
        time++;

        int densitySum = 0;

        if (PRESSURE) {
            values = new int[1000][1000];

            for (Particle particle : simulation.particles) {
                int xPos = (int) particle.pos.x;
                int yPos = (int) particle.pos.y;

                if (xPos > 0 && xPos < 1000) {
                    if (yPos > 0 && yPos < 1000) {
                        double d = Math.max(0, 30 - particle.closestDistance);

                        for (int i = (int) (-d / 2); i < d; i++) {
                            for (int j = (int) (-d / 2); j < d; j++) {
                                if (xPos + i >= 0 && xPos + i < 1000 && yPos + j >= 0 && yPos + j < 1000) {
                                    values[yPos + j][xPos + i] += particle.vel.length() * 5;
                                }
                            }
                        }

                        values[yPos][xPos] += particle.vel.length() * 5;
                    }
                }
            }

            for (int i = 0; i < 1000; i += 5) {
                for (int j = 0; j < 1000; j += 5) {
                    int br = Math.max(0, Math.min(255, values[i][j]));

                    densitySum += values[i][j] / 25.0;

                    g.setColor(new Color(br, br, br));

                    g.fillRect(j, i, 5, 5);
                }
            }

            g.setColor(Color.BLUE);
            g.drawString(String.valueOf(densitySum / (1000.0 * 1000.0)), 20, 100);
        } else {
            for (Particle particle : simulation.particles) {
                if (!particle.active) continue;

                int closestDistance = 30 - Math.max(15, (int) (particle.closestDistance));

                g.setColor(Color.RED);

                if (sprites[particle.group] != null) {
                    if (particle.group == 4) {
                        g.drawImage(sprites[particle.group], (int) (particle.pos.x), (int) (particle.pos.y), 10, 10, null);
                    } else {
                        for (int i = 0; i < 5; i++) {
                            g.drawImage(sprites[particle.group], (int) (particle.pos.x - closestDistance * 2 - particle.vel.x * i), (int) (particle.pos.y - closestDistance * 2 - particle.vel.y * i),
                                    closestDistance * 4, closestDistance * 4, null);
                        }
                    }
                } else {
//                    double br = (particle.vel.length() / 20.0);
//                    br = Math.max(0, Math.min(1, br));
//
//                    Color c = Color.getHSBColor((float) br, 1.0f, 1.0f);
//
//                    g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 20));

                    g.fillOval((int) particle.pos.x - closestDistance,
                            (int) particle.pos.y - closestDistance,
                            closestDistance * 2, closestDistance * 2);
                }

                if (VECTORS) {
                    g.drawLine((int) particle.pos.x, (int) particle.pos.y,
                            (int) (particle.pos.x + particle.vel.x * 3), (int) (particle.pos.y + particle.vel.y * 3));
                }
            }
        }
    }
}
