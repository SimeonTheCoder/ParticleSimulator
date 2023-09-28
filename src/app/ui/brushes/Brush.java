package app.ui.brushes;

import app.core.simulation.particles.Particle;
import app.math.Vec2;

public interface Brush {
    void build(Vec2 att, Vec2 rep, boolean grav, boolean partition, boolean movable, double mass, double fric, int group, int count, double initVelX, double initVelY);

    Particle action(Vec2 pos);

    int getGroup();

    int getCount();
}
