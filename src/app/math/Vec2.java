package app.math;

public class Vec2 {
    public double x, y;
    public static boolean FAST_ROOT = false;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length() {
        if(!FAST_ROOT) {
            return Math.sqrt(x * x + y * y);

//            return 1 / invSqrt(x * x + y * y);
        }else{
            return Double.longBitsToDouble( ( ( Double.doubleToLongBits( x*x + y*y )-(1l<<52) )>>1 ) + ( 1l<<61 ) );
        }
    }

    public void add (Vec2 vec) {
        this.x += vec.x;
        this.y += vec.y;
    }

    public void mul (double num) {
        this.x *= num;
        this.y *= num;
    }

    public void normalize() {
        double l = Math.max(0.1, length());

        this.x /= l;
        this.y /= l;
    }

    public static double invSqrt(double x) {
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i);
        x *= (1.5d - xhalf * x * x);
        return x;
    }
}
