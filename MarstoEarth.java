// Import the Math class
import java.lang.Math;
public class MarstoEarth {
    // Define some constants
    static final double G = 6.674e-11; // Gravitational constant
    static final double M = 5.972e24; // Mass of Earth
    static final double m = 7.342e22; // Mass of Moon
    static final double R = 6.371e6; // Radius of Earth
    static final double r = 1.737e6; // Radius of Moon
    static final double d = 3.844e8; // Distance between Earth and Moon
    static final double v = 1.022e4; // Escape velocity of Earth
    static final double v_m = 2.38e3; // Escape velocity of Moon
    static final double a = 1.524e11; // Semimajor axis of Mars orbit
    static final double e = 0.0934; // Eccentricity of Mars orbit
    static final double v_mars = 2.4e4; // Orbital velocity of Mars
    static final double m_mars = 6.39e23; // Mass of Mars
    static final double r_mars = 3.39e6; // Radius of Mars

    // Define a method to calculate the gravitational force
    public static double gravity(double m1, double m2, double d) {
        return G * m1 * m2 / Math.pow(d, 2);
    }

    // Define a method to calculate the orbital velocity
    public static double orbit(double m, double r) {
        return Math.sqrt(G * m / r);
    }

    // Define a method to calculate the delta-v
    public static double delta_v(double v1, double v2) {
        return Math.abs(v1 - v2);
    }

    // Define a method to launch the rocket
    public static void launch() {
        // Print the initial conditions
        System.out.println("Launching the rocket from Mars to Earth...");
        System.out.println("Initial mass of rocket: " + m_rocket + " kg");
        System.out.println("Initial velocity of rocket: " + v_rocket + " m/s");
        System.out.println("Initial distance from Mars: " + r_mars + " m");
        System.out.println("Initial distance from Earth: " + a + " m");
        System.out.println("Initial gravitational force from Mars: " + gravity(m_mars, m_rocket, r_mars) + " N");
        System.out.println("Initial gravitational force from Earth: " + gravity(M, m_rocket, a) + " N");
        System.out.println();

        // Calculate the delta-v needed to escape Mars' gravity
        double dv1 = delta_v(v_rocket, v_mars);
        System.out.println("Delta-v needed to escape Mars' gravity: " + dv1 + " m/s");

        // Calculate the mass of fuel needed to escape Mars' gravity
        double m_fuel1 = m_rocket * (Math.exp(dv1 / Isp) - 1);
        System.out.println("Mass of fuel needed to escape Mars' gravity: " + m_fuel1 + " kg");

        // Update the mass and velocity of the rocket after escaping Mars' gravity
        m_rocket -= m_fuel1;
        v_rocket += dv1;
        System.out.println("Mass of rocket after escaping Mars' gravity: " + m_rocket + " kg");
        System.out.println("Velocity of rocket after escaping Mars' gravity: " + v_rocket + " m/s");
        System.out.println();

        // Calculate the delta-v needed to transfer to the Earth's orbit
        double dv2 = delta_v(v_rocket, orbit(M, a));
        System.out.println("Delta-v needed to transfer to the Earth's orbit: " + dv2 + " m/s");

        // Calculate the mass of fuel needed to transfer to the Earth's orbit
        double m_fuel2 = m_rocket * (Math.exp(dv2 / Isp) - 1);
        System.out.println("Mass of fuel needed to transfer to the Earth's orbit: " + m_fuel2 + " kg");

        // Update the mass and velocity of the rocket after transferring to the Earth's orbit
        m_rocket -= m_fuel2;
        v_rocket -= dv2;
        System.out.println("Mass of rocket after transferring to the Earth's orbit: " + m_rocket + " kg");
        System.out.println("Velocity of rocket after transferring to the Earth's orbit: " + v_rocket + " m/s");
        System.out.println();

        // Calculate the delta-v needed to enter the Earth's atmosphere
        double dv3 = delta_v(v_rocket, v);
        System.out.println("Delta-v needed to enter the Earth's atmosphere: " + dv3 + " m/s");

        // Calculate the mass of fuel needed to enter the Earth's atmosphere
        double m_fuel3 = m_rocket * (Math.exp(dv3 / Isp) - 1);
        System.out.println("Mass of fuel needed to enter the Earth's atmosphere: " + m_fuel3 + " kg");

        // Update the mass and velocity of the rocket after entering the Earth's atmosphere
        m_rocket -= m_fuel3;
        v_rocket -= dv3;
        System.out.println("Mass of rocket after entering the Earth's atmosphere: " + m_rocket + " kg");
        System.out.println("Velocity of rocket after entering the Earth's atmosphere: " + v_rocket + " m/s");
        System.out.println();

        // Print the final conditions
        System.out.println("The rocket has successfully reached Earth!");
        System.out.println("Final mass of rocket: " + m_rocket + " kg");
        System.out.println("Final velocity of rocket: " + v_rocket + " m/s");
        System.out.println("Final distance from Mars: " + (a - r_mars) + " m");
        System.out.println("Final distance from Earth: " + R + " m");
        System.out.println("Final gravitational force from Mars: " + gravity(m_mars, m_rocket, a - r_mars) + " N");
        System.out.println("Final gravitational force from Earth: " + gravity(M, m_rocket, R) + " N");
        System.out.println();
    }

    // Define some parameters for the rocket
    static double m_rocket = 30000; // Mass of rocket in kg
    static double v_rocket = 0; // Velocity of rocket in m/s
    static double Isp = 300; // Specific impulse of rocket engine in s

    // Call the launch method
    public static void main() {
        launch();
    }
}
