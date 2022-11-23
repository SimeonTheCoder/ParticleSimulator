package app.ui.sensors;

import app.core.simulation.particles.Particle;
import app.core.simulation.threads.SimulationThread;
import app.math.Vec2;
import app.ui.Window;

public class KeyHandler {
    private static final int MOVEMENT_SPEED = 20;
    private static final int SPAWN_RATE = 5;

    public void handle(int key, Window window) {
        switch (key) {
            case 1: {
                window.selector.add(new Vec2(-MOVEMENT_SPEED, 0));

                break;
            }

            case 2 : {
                window.selector.add(new Vec2(MOVEMENT_SPEED, 0));

                break;
            }

            case 3 : {
                window.selector.add(new Vec2(0, -MOVEMENT_SPEED));

                break;
            }

            case 4 : {
                window.selector.add(new Vec2(0, MOVEMENT_SPEED));

                break;
            }

            case 5 : {
                for(int i = 0; i < SPAWN_RATE; i++) {
                    window.simulation.particles.add(
                            Particle.build()
                                    .setVel(new Vec2(0, 0))
                                    .setPos(new Vec2(window.selector.x, window.selector.y))
                                    .setGroup(1)
                                    .setAtt(new Vec2(100, 1))
                                    .setRep(new Vec2(50, 1))
                                    .setGrav(true)
                                    .setMass(10)
                                    .setFric(.5)
                                    .setCap(1)
                    );
                }

                break;
            }

            case 6 : {
                for(int i = 0; i < SPAWN_RATE; i++) {
                    window.simulation.particles.add(
                            Particle.build()
                                    .setVel(new Vec2(0, 0))
                                    .setPos(new Vec2(window.selector.x, window.selector.y))
                                    .setGroup(2)
                                    .setAtt(new Vec2(100, .2))
                                    .setRep(new Vec2(50, 1))
                                    .setGrav(true)
                                    .setMass(3)
                                    .setFric(.5)
                    );
                }

                break;
            }

            case 7 : {
                for(int i = 0; i < SPAWN_RATE; i++) {
                    window.simulation.particles.add(
                            Particle.build()
                                    .setVel(new Vec2(0, 0))
                                    .setPos(new Vec2(window.selector.x, window.selector.y))
                                    .setGroup(3)
                                    .setAtt(new Vec2(100, .1))
                                    .setRep(new Vec2(50, 10))
                                    .setGrav(true)
                                    .setMass(.1)
                                    .setFric(.5)
                    );
                }

                break;
            }

            case 8 : {
                for(int i = -10; i < 10; i+= 5) {
                    for(int j = -10; j < 10; j+= 5) {
                        window.simulation.particles.add(
                                Particle.build()
                                        .setVel(new Vec2(0, 0))
                                        .setPos(new Vec2(window.selector.x + i, window.selector.y + j))
                                        .setGroup(4)
                                        .setAtt(new Vec2(0, .1))
                                        .setRep(new Vec2(30, 10))
                                        .setGrav(false)
                                        .setMass(1000000)
                                        .setFric(.5)
                        );
                    }
                }

                break;
            }

            case 9 : {
                Particle minParticle = null;

                double minDistance = Double.MAX_VALUE;

                for (Particle particle : window.simulation.particles) {
                    Vec2 dvec = new Vec2(particle.pos.x - window.selector.x, particle.pos.y - window.selector.y);

                    double dis = dvec.length();

                    if(minDistance > dis && particle.active) {
                        minDistance = dis;
                        minParticle = particle;
                    }
                }

                minParticle.active = false;

                break;
            }

            case 10 : {
                for(int i = 0; i < 500; i+= 20) {
                    window.simulation.particles.add(
                            Particle.build()
                                    .setVel(new Vec2(0, 0))
                                    .setPos(new Vec2(0, i))
                                    .setGroup(3)
                                    .setAtt(new Vec2(100, .1))
                                    .setRep(new Vec2(50, 10))
                                    .setGrav(true)
                                    .setMass(1)
                                    .setFric(.5)
                    );
                }

                break;
            }

            case 11 : {
                for(int i = 0; i < SPAWN_RATE; i++) {
                    window.simulation.particles.add(
                            Particle.build()
                                    .setVel(new Vec2(0, 0))
                                    .setPos(new Vec2(window.selector.x, window.selector.y))
                                    .setGroup(5)
                                    .setAtt(new Vec2(100, .1))
                                    .setRep(new Vec2(50, 10))
                                    .setGrav(true)
                                    .setMass(7)
                                    .setFric(.5)
                    );
                }

                break;
            }

            case 12: {
                window.PAUSED = !window.PAUSED;

                break;
            }

            case 13 : {
                window.THREADED = !window.THREADED;

                break;
            }

            case 14 : {
                Vec2.FAST_ROOT = !Vec2.FAST_ROOT;

                break;
            }

            case 15: {
                SimulationThread.DIS_CHECK = !SimulationThread.DIS_CHECK;

                break;
            }
        }
    }
}
