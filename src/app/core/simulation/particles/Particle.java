package app.core.simulation.particles;

import app.math.Vec2;

public class Particle {
    public Vec2 pos, vel;
    public Vec2 att, rep;

    public Vec2 aff;

    public boolean grav;
    public boolean active;
    public boolean partition;
    public boolean movable;

    public double mass;
    public double speedCap;

    public double fric;
    public int group;

    public double closestDistance;

    public Particle() {
        this.active = true;

        closestDistance = 100;
    }

    public Particle(Vec2 pos, Vec2 vel, double mass) {
        this.pos = pos;
        this.vel = vel;
        this.mass = mass;
        this.speedCap = 50;

        this.active = true;

        closestDistance = 100;
    }

    public static Particle build(Vec2 pos, Vec2 vel, double mass) {
        return new Particle(pos, vel, mass).setCap(5);
    }

    public static Particle build() {
        return new Particle().setCap(5).setMass(1);
    }

    public Particle(Vec2 pos, Vec2 vel, Vec2 att, Vec2 rep, Vec2 aff, boolean grav, double mass, double fric) {
        this.pos = pos;
        this.vel = vel;
        this.att = att;
        this.rep = rep;
        this.aff = aff;
        this.grav = grav;
        this.mass = mass;
        this.fric = fric;

        closestDistance = 100;
    }

    public void cap() {
        double mag = vel.length();

        mag = Math.min(mag, speedCap);

        vel.normalize();
        vel.mul(mag);
    }

    public Vec2 getPos() {
        return pos;
    }

    public Particle setPos(Vec2 pos) {
        this.pos = pos;

        return this;
    }

    public Vec2 getVel() {
        return vel;
    }

    public Particle setVel(Vec2 vel) {
        this.vel = vel;

        return this;
    }

    public Vec2 getAtt() {
        return att;
    }

    public Particle setAtt(Vec2 att) {
        this.att = att;

        return this;
    }

    public Vec2 getRep() {
        return rep;
    }

    public Particle setRep(Vec2 rep) {
        this.rep = rep;

        return this;
    }

    public Vec2 getAff() {
        return aff;
    }

    public Particle setAff(Vec2 aff) {
        this.aff = aff;

        return this;
    }

    public boolean isGrav() {
        return grav;
    }

    public Particle setGrav(boolean grav) {
        this.grav = grav;

        return this;
    }

    public double getMass() {
        return mass;
    }

    public Particle setMass(double mass) {
        this.mass = mass;

        return this;
    }

    public double getFric() {
        return fric;
    }

    public Particle setFric(double fric) {
        this.fric = fric;

        return this;
    }

    public int getGroup() {
        return group;
    }

    public Particle setGroup(int group) {
        this.group = group;

        return this;
    }

    public double getCap() {
        return speedCap;
    }

    public Particle setCap(double speedCap) {
        this.speedCap = speedCap;

        return this;
    }

    public Particle clone() {
        Particle particle = new Particle();

        particle.setPos(new Vec2(pos.x, pos.y));
        particle.setVel(new Vec2(vel.x, vel.y));
        particle.active = this.active;
        particle.setAtt(new Vec2(att.x, att.y));
        particle.setRep(new Vec2(rep.x, rep.y));
        particle.setFric(this.fric);
        particle.setMass(mass);
        particle.setGrav(grav);
        particle.setGroup(group);
        particle.setCap(speedCap);
        particle.setPartition(partition);
        particle.setMovable(movable);

        return particle;
    }

    public Particle setPartition(boolean partition) {
        this.partition = partition;

        return this;
    }

    public Particle setMovable(boolean movable) {
        this.movable = movable;

        return this;
    }
}
