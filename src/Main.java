import app.core.simulation.Simulation;
import app.math.Vec2;
import app.rendering.Renderer;
import app.ui.Window;

public class Main {
    public static void main(String[] args) {
        Renderer renderer = new Renderer();

        Simulation simulation = new Simulation();

//        for(int i = 0; i < 30; i ++) {
//            for(int j = 0; j < 20; j ++) {
//                simulation.particles.add(
//                        Particle.build()
//                                .setPos(new Vec2(500 + i * 10 - 15, j * 4 + 300))
//                                .setGrav(true)
//                                .setFric(.5)
//                                .setVel(new Vec2(0, 0))
//                                .setRep(new Vec2(50, .5))
//                                .setAtt(new Vec2(100, .5))
//                                .setGroup(1)
//                );
//            }
//        }

//        for(int i = 0; i < 30; i ++) {
//            for(int j = 0; j < 20; j ++) {
//                simulation.particles.add(
//                        Particle.build()
//                                .setPos(new Vec2(500 + i * 10 - 500, j * 4 + 300))
//                                .setGrav(true)
//                                .setFric(.5)
//                                .setVel(new Vec2(-5, 0))
//                                .setRep(new Vec2(30, .5))
//                                .setAtt(new Vec2(70, .5))
//                );
//            }
//        }

//        for(int i = 0; i < 30; i ++) {
//            for(int j = 0; j < 20; j ++) {
//                simulation.particles.add(
//                        Particle.build()
//                                .setPos(new Vec2(500 + i * 10 - 500, j * 4 + 300))
//                                .setGrav(true)
//                                .setFric(.5)
//                                .setVel(new Vec2(0, 0))
//                                .setRep(new Vec2(50, 10))
//                                .setAtt(new Vec2(100, .1))
//                                .setMass(10)
//                                .setGroup(2)
//                );
//            }
//        }

//        for(int i = 0; i < 500; i ++) {
//            simulation.particles.add(
//                    Particle.build()
//                            .setPos(new Vec2(1, i))
//                            .setGrav(false)
//                            .setFric(.5)
//                            .setVel(new Vec2(0, 0))
//                            .setRep(new Vec2(20, 10))
//                            .setAtt(new Vec2(0, .1))
//                            .setMass(10000000)
//                            .setGroup(3)
//            );
//        }

//        for(int i = 0; i < 1000; i ++) {
//            simulation.particles.add(
//                    Particle.build()
//                            .setPos(new Vec2(i, 1))
//                            .setGrav(false)
//                            .setFric(.5)
//                            .setVel(new Vec2(0, 0))
//                            .setRep(new Vec2(20, 10))
//                            .setAtt(new Vec2(0, .1))
//                            .setMass(10000000)
//                            .setGroup(4)
//            );
//        }

//        for(int i = 0; i < 30; i ++) {
//            for(int j = 0; j < 20; j ++) {
//                simulation.particles.add(
//                        Particle.build()
//                                .setPos(new Vec2(500 + i * 10 - 500, j * 4 + 300))
//                                .setGrav(true)
//                                .setFric(.5)
//                                .setVel(new Vec2(0, 0))
//                                .setRep(new Vec2(50, 1))
//                                .setAtt(new Vec2(100, .1))
//                                .setMass(10)
//                                .setGroup(2)
//                );
//            }
//        }

        simulation.globalForces.add(new Vec2(0, 9.8 / 100));
//        simulation.globalForces.add(new Vec2(9.8 / 10, 0));

        Window window = new Window("Particle Simulation", 1000,
                1000, renderer);

        window.ITERATIONS = 1;

        window.simulation = simulation;
    }
}
