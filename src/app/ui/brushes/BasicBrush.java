package app.ui.brushes;

import app.core.simulation.particles.Particle;
import app.math.Vec2;

public class BasicBrush implements Brush {
    private Particle instance;
    private int count;

    @Override
    public void build(Vec2 att, Vec2 rep, boolean grav, boolean partition, boolean movable, double mass, double fric, int group, int count, double initVelX, double initVelY) {
        Particle particle = new Particle();

        particle.att = att;
        particle.rep = rep;
        particle.grav = grav;
        particle.mass = mass;
        particle.fric = fric;
        particle.group = group;
        particle.partition = partition;
        particle.movable = movable;

        particle.pos = new Vec2(0, 0);
        particle.vel = new Vec2(initVelX, initVelY);

        particle.active = true;
        particle.speedCap = 5;

        this.count = count;

        this.instance = particle;
    }

    @Override
    public Particle action(Vec2 pos) {
        return instance.clone().setPos(pos);
    }

    @Override
    public int getGroup() {
        return instance.group;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
