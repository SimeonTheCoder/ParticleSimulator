package app.rendering;

import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    }

    public static boolean DEBUG = true;

    public void render(Graphics2D g, Simulation simulation, boolean PRESSURE, boolean VECTORS, boolean SPECTRUM, boolean SPRITES) {
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
        } else if (!SPECTRUM) {
            for (Particle particle : simulation.particles) {
                if (!particle.active) continue;

                int closestDistance = 30 - Math.max(15, (int) (particle.closestDistance));

                if (sprites[particle.group] != null && SPRITES) {
                    if (particle.group == 4) {
                        g.drawImage(sprites[particle.group], (int) (particle.pos.x), (int) (particle.pos.y), 10, 10, null);
                    } else {
                        g.drawImage(sprites[particle.group], (int) (particle.pos.x - closestDistance * 2), (int) (particle.pos.y - closestDistance * 2),
                                closestDistance * 4, closestDistance * 4, null);
                    }
                } else {
                    if (particle.group == 1) g.setColor(Color.RED);
                    if (particle.group == 2) g.setColor(Color.BLUE);
                    if (particle.group == 3) g.setColor(Color.GREEN);

//                    int r = g.getColor().getRed();
//                    int gr = g.getColor().getGreen();
//                    int b = g.getColor().getBlue();
//
//                    r = (int) Math.max(0, Math.min(255, r - particle.pos.y / 5));
//                    gr = (int) Math.max(0, Math.min(255, gr - particle.pos.y / 5));
//                    b = (int) Math.max(0, Math.min(255, r - particle.pos.y / 5));

                    g.fillOval((int) particle.pos.x,
                            (int) particle.pos.y,
                            10, 10);

                    for (int k = 0; k < 1; k++) {
                        g.fillOval((int) ((int) particle.pos.x + particle.vel.x * k * 2),
                                (int) ((int) particle.pos.y + particle.vel.y * k * 2),
                                10 + k * 2, 10 + k * 2);
                    }

//                    g.setColor(Color.BLACK);
//
//                    g.drawOval((int) particle.pos.x,
//                            (int) particle.pos.y,
//                            10, 10);
                }

                if (VECTORS) {
                    g.setColor(Color.RED);
//                    g.setStroke(new BasicStroke(1));

                    g.drawLine((int) particle.pos.x, (int) particle.pos.y,
                            (int) (particle.pos.x + particle.vel.x * 3), (int) (particle.pos.y + particle.vel.y * 3));
                }
            }
        } else {
            for (Particle particle : simulation.particles) {
                if (!particle.active) continue;

                int closestDistance = 30 - Math.max(15, (int) (particle.closestDistance));

                double br = (particle.vel.length() / 20.0);
                br = Math.max(0, Math.min(1, br));

                Color c = Color.getHSBColor((float) br, 1.0f, 1.0f);

                g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue()));

                g.fillOval((int) particle.pos.x - closestDistance,
                        (int) particle.pos.y - closestDistance,
                        closestDistance * 2, closestDistance * 2);

                if (VECTORS) {
                    g.drawLine((int) particle.pos.x, (int) particle.pos.y,
                            (int) (particle.pos.x + particle.vel.x * 3), (int) (particle.pos.y + particle.vel.y * 3));
                }
            }
        }
    }
}
