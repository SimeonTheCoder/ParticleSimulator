package app.math;

public class Vec2 {
    public double x, y;
    public static boolean FAST_ROOT = false;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public void add(Vec2 vec) {
        this.x += vec.x;
        this.y += vec.y;
    }

    public void mul(double num) {
        this.x *= num;
        this.y *= num;
    }

    public void normalize() {
        double l = Math.max(0.1, length());

        this.x /= l;
        this.y /= l;
    }
}
