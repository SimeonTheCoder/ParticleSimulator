package app.ui.brushes;

import app.core.simulation.particles.Particle;
import app.math.Vec2;

public class BasicBrush implements Brush{
    private Particle instance;

    @Override
    public void build(Vec2 att, Vec2 rep, boolean grav, double mass, double fric, int group) {
        Particle particle = new Particle();

        particle.att = att;
        particle.rep = rep;
        particle.grav = grav;
        particle.mass = mass;
        particle.fric = fric;
        particle.group = group;

        particle.pos = new Vec2(0, 0);
        particle.vel = new Vec2(0, 0);

        particle.active = true;
        particle.speedCap = 5;

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
}
