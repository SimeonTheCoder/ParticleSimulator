package app.ui.brushes;

import app.core.simulation.particles.Particle;
import app.math.Vec2;

public class BasicBrush implements Brush {
    private Particle instance;
    private int count;

    @Override
    public void build(Vec2 att, Vec2 rep, boolean grav, boolean partition, boolean movable, double mass, double fric, int group, int count, double speedCap) {
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
        particle.vel = new Vec2(0, 0);

        particle.active = true;
        particle.speedCap = speedCap;

        this.count = count;

        this.instance = particle;
    }

    @Override
    public Particle action(Vec2 pos, Vec2 vel) {
        return instance.clone().setPos(pos).setVel(vel);
    }

    @Override
    public int getGroup() {
        return instance.group;
    }

    public Particle getInstance() {
        return instance;
    }

    public void setInstance(Particle instance) {
        this.instance = instance;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
