package app.rendering;

import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;

import java.awt.*;
import java.util.Random;

public class Renderer {
    public int[][] values;

    public Renderer() {
        values = new int[1000][1000];
        random = new Random();
    }

    public static boolean DEBUG = true;
    private Random random;

    public void render(Graphics2D g, Simulation simulation) {
        g.setColor(new Color(32, 32, 32));
        g.fillRect(0, 0, 1920, 1080);

        for (Particle particle : simulation.particles) {
            if (!particle.active) continue;

            if (particle.group == 1) {
                g.setColor(new Color(255, 0, 0));
            } else if (particle.group == 2) {
                g.setColor(new Color(0, 255, 0));
            } else if (particle.group == 3) {
                g.setColor(new Color(0, 0, 255));

//                continue;
            } else if (particle.group == 4) {
                g.setColor(new Color(255, 255, 255));
            } else if (particle.group == 5) {
                g.setColor(Color.DARK_GRAY);
            }

            if (particle.group != 4) {
                int closestDistance = Math.max(5, (int) (30 - particle.closestDistance));

                g.fillOval((int) particle.pos.x - closestDistance,
                        (int) particle.pos.y - closestDistance,
                        closestDistance, closestDistance);

                if(particle.group != 3) {
                    for (int i = 0; i < Math.min(5, particle.vel.length()); i++) {
                        g.fillOval((int) ((int) particle.pos.x - closestDistance - particle.vel.x * i),
                                (int) ((int) particle.pos.y - closestDistance - particle.vel.y * i),
                                closestDistance, closestDistance);
                    }
                }
            } else {
                g.fillRect((int) particle.pos.x, (int) particle.pos.y, 10, 10);
            }

//            g.drawLine((int) particle.pos.x, (int) particle.pos.y,
//                    (int) (particle.pos.x + particle.vel.x * 3), (int) (particle.pos.y + particle.vel.y * 3));
        }

//        for (Particle particle : simulation.particles) {
//            int xPos = (int) particle.pos.x;
//            int yPos = (int) particle.pos.y;
//
//            if(xPos > 0 && xPos < 1000) {
//                if(yPos > 0 && yPos < 1000) {
//                    values[yPos][xPos] += particle.vel.length() * 5;
//                }
//            }
//        }
//
//        for(int i = 0; i < 1000; i += 5) {
//            for(int j = 0; j < 1000; j += 5) {
//                int br = Math.max(0, Math.min(255, values[i][j]));
//
//                g.setColor(new Color(br, br, br));
//
//                g.fillRect(j, i, 5, 5);
//            }
//        }
    }
}
