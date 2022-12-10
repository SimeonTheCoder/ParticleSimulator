package app.core.simulation;

import app.core.simulation.particles.Particle;
import app.core.simulation.threads.SimulationThread;
import app.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public List<Particle> particles;
    public List<Vec2> globalForces;
    public List<SimulationThread> threads;
    public int THREAD_COUNT = 8;

    public Simulation(List<Particle> particles) {
        this.particles = particles;
    }

    public static boolean[] finished;

    public Simulation() {
        particles = new ArrayList<>();
        globalForces = new ArrayList<>();

        threads = new ArrayList<>();

        SimulationThread.paused = true;

        finished = new boolean[THREAD_COUNT];

        for(int i = 0; i < THREAD_COUNT; i ++) {
            finished[i] = false;

            threads.add(new SimulationThread(i, particles, globalForces));
            threads.get(i).THREAD_COUNT = THREAD_COUNT;
            threads.get(i).start();
        }
    }

    public void step() {
        SimulationThread.paused = true;

        for (Particle particle : particles) {
            particle.closestDistance = 30;

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

            if(particle.pos.y > 700) {
                particle.pos.y = 700;
                particle.vel.y *= -1 * particle.fric;
            }

            for (Particle currParticle : particles) {
                if (currParticle == particle || currParticle.group == 4) continue;

                if (SimulationThread.DIS_CHECK && (Math.abs(currParticle.pos.x - particle.pos.x) > 70 || Math.abs(currParticle.pos.y - particle.pos.y) > 70)) {
                    continue;
                }

                Vec2 dvec = new Vec2(particle.pos.x - currParticle.pos.x,
                        particle.pos.y - currParticle.pos.y);

                double dis = dvec.length();

                if(!((Double) dis).isNaN()) {
                    particle.closestDistance = Math.max(5, Math.min(particle.closestDistance, dis));
                }

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

            particle.cap();

            if(particle.grav) {
                for (Vec2 globalForce : globalForces) {
                    particle.vel.add(globalForce);
                }
            }

            particle.pos.add(particle.vel);
        }
    }

    public void threadedStep() {
        SimulationThread.paused = false;

        for (SimulationThread thread : threads) {
            if(!thread.isAlive()) {
                thread = new SimulationThread(thread.threadIndex, particles, globalForces);

                thread.start();
            }
        }
    }
}
