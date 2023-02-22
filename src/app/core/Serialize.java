package app.core;

import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;
import app.math.Vec2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Serialize {
    public void save(String name, Simulation simulation) {
        StringBuilder builder = new StringBuilder();

        List<Vec2> positions = new ArrayList<>();

        for (Particle particle : simulation.particles) {
            boolean has = false;

            for (Vec2 position : positions) {
                if (particle.pos.x == position.x && particle.pos.y == position.y) {
                    has = true;
                    break;
                }
            }

            if (has) particle.active = false;

            positions.add(particle.pos);
        }

        for (Particle particle : simulation.particles) {
            if (particle.active) {
                builder.append(String.format("%.3f %.3f %.3f %.3f %.3f %.3f %.3f %.3f %s %.3f %d %s %s %.3f ",
                        particle.pos.x, particle.pos.y,
                        particle.vel.x, particle.vel.y,
                        particle.att.x, particle.att.y,
                        particle.rep.x, particle.rep.y,
                        particle.grav, particle.mass,
                        particle.group,
                        particle.partition,
                        particle.movable,
                        particle.speedCap));
            }
        }

        FileWriter writer = null;

        try {
            writer = new FileWriter("ParticleSimulation\\data\\simulations\\" + name + ".pts");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(String name, Simulation simulation) {
        simulation.particles.clear();

        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("ParticleSimulation\\data\\simulations\\" + name + ".pts"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line = scanner.nextLine();

        line = line.replace(',', '.');

        String[] content = line.split(" ");

        for (int i = 0; i < content.length; i += 14) {
            try {
                Particle particle = Particle.build()
                        .setPos(new Vec2(Double.parseDouble(content[0 + i]), Double.parseDouble(content[1 + i])))
                        .setVel(new Vec2(Double.parseDouble(content[2 + i]), Double.parseDouble(content[3 + i])))
                        .setAtt(new Vec2(Double.parseDouble(content[4 + i]), Double.parseDouble(content[5 + i])))
                        .setRep(new Vec2(Double.parseDouble(content[6 + i]), Double.parseDouble(content[7 + i])))
                        .setGrav(Boolean.parseBoolean(content[8 + i]))
                        .setMass(Double.parseDouble(content[9 + i]))
                        .setGroup(Integer.parseInt(content[10 + i]))
                        .setPartition(Boolean.parseBoolean(content[11 + i]))
                        .setMovable(Boolean.parseBoolean(content[12 + i]))
                        .setCap(Double.parseDouble(content[13 + i]));

                simulation.particles.add(particle);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
