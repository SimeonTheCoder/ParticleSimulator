package app.core.simulation.threads;

import app.core.simulation.particles.Particle;
import app.math.Vec2;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationThread extends Thread {
    public static List<Particle> particles;
    public List<Vec2> globalForces;

    public int threadIndex;
    public double THREAD_COUNT = 8.;

    public static boolean DIS_CHECK = false;

    public volatile boolean paused;

    public SimulationThread(int threadIndex, List<Particle> particles, List<Vec2> globalForces) {
        SimulationThread.particles = particles;
        this.globalForces = globalForces;

        this.threadIndex = threadIndex;

        this.paused = false;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                if (!paused) {
                    long millisStart = System.currentTimeMillis();

                    for (int pIndex = (int) (threadIndex / THREAD_COUNT * particles.size()); pIndex < (int) ((threadIndex + 1) / THREAD_COUNT * particles.size()); pIndex++) {
                        if (pIndex < 0 || pIndex >= particles.size()) continue;

                        Particle particle = particles.get(pIndex);

                        particle.closestDistance = 100;

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

                        if (particle.pos.y > 1000) {
                            particle.pos.y = 1000;
                            particle.vel.y *= -1 * particle.fric;
                        }

                        for (Particle currParticle : particles) {
                            if (currParticle == particle || currParticle.group == 4) continue;

                            Vec2 dvec = new Vec2(particle.pos.x - currParticle.pos.x,
                                    particle.pos.y - currParticle.pos.y);

                            double dis = dvec.length();

//                            if (!((Double) dis).isNaN()) {
                            particle.closestDistance = Math.max(5, Math.min(particle.closestDistance, dis));
//                            }

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

                    long millisEnd = System.currentTimeMillis();

                    long millis = millisEnd - millisStart;

                    if (millis < 1000 / 60) {
                        TimeUnit.MILLISECONDS.sleep((long) (1000 / 60 - millis));
                    }
                }
            }
        } catch (Exception exception) {
            this.stop();
        }
    }
}
