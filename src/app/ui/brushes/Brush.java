package app.ui.brushes;

import app.core.simulation.particles.Particle;
import app.math.Vec2;

public interface Brush {
    void build(Vec2 att, Vec2 rep, boolean grav, boolean partition, boolean movable, double mass, double fric, int group, int count);

    Particle action(Vec2 pos, Vec2 vel);

    int getGroup();

    int getCount();
}
