package app.core.simulation;

import app.core.simulation.particles.Particle;
import app.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public List<Particle> particles;
    public List<Vec2> globalForces;

    public Simulation(List<Particle> particles) {
        this.particles = particles;
    }

    public Simulation() {
        particles = new ArrayList<>();
        globalForces = new ArrayList<>();
    }

    public void step() {
        for (Particle particle : particles) {
            if(!particle.active) continue;

            if(particle.pos.x < 0) {
                particle.pos.x = 0;
                particle.vel.x *= -1 * particle.fric;
            }

            if(particle.pos.x > 1000) {
//                particle.active = false;
                particle.pos.x = 1000;
                particle.vel.x *= -1 * particle.fric;
            }

            if(particle.pos.y < 0) {
                particle.pos.y = 0;
                particle.vel.y *= -1 * particle.fric;
            }

            if(particle.pos.y > 500) {
                particle.pos.y = 500;
                particle.vel.y *= -1 * particle.fric;
            }

            if(particle.grav) {
                for (Vec2 globalForce : globalForces) {
                    particle.vel.add(globalForce);
                }
            }

            particle.cap(10);

            particle.pos.add(particle.vel);

            for (Particle currParticle : particles) {
                if (currParticle == particle || currParticle.group == 4) continue;

                Vec2 dvec = new Vec2(particle.pos.x - currParticle.pos.x,
                        particle.pos.y - currParticle.pos.y);

                double dis = dvec.length();

                dvec.normalize();

                dis = Math.max(dis, 1);

                if (particle.rep.x > dis) {
                    dvec.mul(particle.rep.y * 1 / dis);
                    dvec.mul(-1 / currParticle.mass);

                    currParticle.vel.add(dvec);
                } else if (particle.att.x > dis) {
                    dvec.mul(particle.att.y * 1 / dis / currParticle.mass);

                    currParticle.vel.add(dvec);
                }
            }
        }
    }
}
