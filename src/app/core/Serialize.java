package app.core;

import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;
import app.math.Vec2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Serialize {
    public void save(String name, Simulation simulation) {
        System.out.println();

        StringBuilder builder = new StringBuilder();

        for (Particle particle : simulation.particles) {
            if(particle.active) {
                builder.append(String.format("%.3f %.3f %.3f %.3f %.3f %.3f %.3f %.3f %s %.3f %d",
                        particle.pos.x, particle.pos.y,
                        particle.vel.x, particle.vel.y,
                        particle.att.x, particle.att.y,
                        particle.rep.x, particle.rep.y,
                        particle.grav, particle.mass,
                        particle.group));

                builder.append(System.lineSeparator());
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

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            line = line.replace(',', '.');

            String[] content = line.split(" ");

            for (String s : content) {
                System.out.println(s);
            }

            Particle particle = Particle.build()
                    .setPos(new Vec2(Double.parseDouble(content[0]), Double.parseDouble(content[1])))
                    .setVel(new Vec2(Double.parseDouble(content[2]), Double.parseDouble(content[3])))
                    .setAtt(new Vec2(Double.parseDouble(content[4]), Double.parseDouble(content[5])))
                    .setRep(new Vec2(Double.parseDouble(content[6]), Double.parseDouble(content[7])))
                    .setGrav(Boolean.parseBoolean(content[8]))
                    .setMass(Double.parseDouble(content[9]))
                    .setGroup(Integer.parseInt(content[10]));

            simulation.particles.add(particle);
        }
    }
}
