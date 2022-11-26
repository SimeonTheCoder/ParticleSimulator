package app.core.simulation.threads;

import app.core.simulation.Simulation;
import app.core.simulation.particles.Particle;
import app.math.Vec2;

import java.util.List;

public class SimulationThread extends Thread{
    public static List<Particle> particles;
    public List<Vec2> globalForces;

    public int threadIndex;
    public double THREAD_COUNT = 4.;

    public static boolean DIS_CHECK = false;
    public static boolean paused = false;

    private int clock = 0;

    public SimulationThread(int threadIndex, List<Particle> particles, List<Vec2> globalForces) {
        SimulationThread.particles = particles;
        this.globalForces = globalForces;

        this.threadIndex = threadIndex;
    }

    @Override
    public synchronized void run() {
        while(true) {
            clock ++;

            if (clock % 100 == 0) {
                System.out.println(SimulationThread.paused);
            }

            if(!SimulationThread.paused) {
                Simulation.finished[threadIndex] = false;

                for (int pIndex = (int) (threadIndex / THREAD_COUNT * particles.size()); pIndex < (int) ((threadIndex + 1) / THREAD_COUNT * particles.size()); pIndex++) {
                    if (pIndex < 0 || pIndex >= particles.size()) continue;

                    Particle particle = particles.get(pIndex);

                    particle.closestDistance = 30;

                    if (!particle.active) continue;

                    if (particle.pos.x < 0) {
                        particle.pos.x = 0;
                        particle.vel.x *= -1 * particle.fric;
                    }

                    if (particle.pos.x > 1000) {
                        particle.pos.x = 1000;
                        particle.vel.x *= -1 * particle.fric;
                    }

                    if (particle.pos.y < 0) {
                        particle.pos.y = 0;
                        particle.vel.y *= -1 * particle.fric;
                    }

                    if (particle.pos.y > 700) {
                        particle.pos.y = 700;
                        particle.vel.y *= -1 * particle.fric;
                    }

                    for (Particle currParticle : particles) {
                        if (currParticle == particle || currParticle.group == 4) continue;

                        if (DIS_CHECK && (Math.abs(currParticle.pos.x - particle.pos.x) > 70 || Math.abs(currParticle.pos.y - particle.pos.y) > 70)) {
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

                    if (particle.grav) {
                        for (Vec2 globalForce : globalForces) {
                            particle.vel.add(globalForce);
                        }
                    }

                    particle.pos.add(particle.vel);
                }

                Simulation.finished[threadIndex] = true;
            }
        }
    }
}
