// Import the Math class
import java.lang.Math;

public class EarthtoMoon {
    // Define some constants
    static final double G = 6.674e-11; // Gravitational constant
    static final double M = 5.972e24; // Mass of Earth
    static final double m = 7.342e22; // Mass of Moon
    static final double R = 6.371e6; // Radius of Earth
    static final double r = 1.737e6; // Radius of Moon
    static final double d = 3.844e8; // Distance between Earth and Moon
    static final double v = 1.022e4; // Escape velocity of Earth

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
        System.out.println("Launching the rocket from Earth to Moon...");
        System.out.println("Initial mass of rocket: " + m_rocket + " kg");
        System.out.println("Initial velocity of rocket: " + v_rocket + " m/s");
        System.out.println("Initial distance from Earth: " + R + " m");
        System.out.println("Initial distance from Moon: " + (d - R) + " m");
        System.out.println("Initial gravitational force from Earth: " + gravity(M, m_rocket, R) + " N");
        System.out.println("Initial gravitational force from Moon: " + gravity(m, m_rocket, d - R) + " N");
        System.out.println();

        // Calculate the delta-v needed to escape Earth's gravity
        double dv1 = delta_v(v_rocket, v);
        System.out.println("Delta-v needed to escape Earth's gravity: " + dv1 + " m/s");

        // Calculate the mass of fuel needed to escape Earth's gravity
        double m_fuel1 = m_rocket * (Math.exp(dv1 / Isp) - 1);
        System.out.println("Mass of fuel needed to escape Earth's gravity: " + m_fuel1 + " kg");

        // Update the mass and velocity of the rocket after escaping Earth's gravity
        m_rocket -= m_fuel1;
        v_rocket += dv1;
        System.out.println("Mass of rocket after escaping Earth's gravity: " + m_rocket + " kg");
        System.out.println("Velocity of rocket after escaping Earth's gravity: " + v_rocket + " m/s");
        System.out.println();

        // Calculate the delta-v needed to enter Moon's orbit
        double dv2 = delta_v(v_rocket, orbit(m, r));
        System.out.println("Delta-v needed to enter Moon's orbit: " + dv2 + " m/s");

        // Calculate the mass of fuel needed to enter Moon's orbit
        double m_fuel2 = m_rocket * (Math.exp(dv2 / Isp) - 1);
        System.out.println("Mass of fuel needed to enter Moon's orbit: " + m_fuel2 + " kg");

        // Update the mass and velocity of the rocket after entering Moon's orbit
        m_rocket -= m_fuel2;
        v_rocket -= dv2;
        System.out.println("Mass of rocket after entering Moon's orbit: " + m_rocket + " kg");
        System.out.println("Velocity of rocket after entering Moon's orbit: " + v_rocket + " m/s");
        System.out.println();

        // Print the final conditions
        System.out.println("The rocket has successfully reached the Moon!");
        System.out.println("Final mass of rocket: " + m_rocket + " kg");
        System.out.println("Final velocity of rocket: " + v_rocket + " m/s");
        System.out.println("Final distance from Earth: " + (d - r) + " m");
        System.out.println("Final distance from Moon: " + r + " m");
        System.out.println("Final gravitational force from Earth: " + gravity(M, m_rocket, d - r) + " N");
        System.out.println("Final gravitational force from Moon: " + gravity(m, m_rocket, r) + " N");
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

/** Source: Conversation with Bing, 12/18/2023
        (1) CodeConvert AI - Convert code with a click of a button. https://www.codeconvert.ai/python-to-java-converter.
        (2) Online Python to Java Converter Tool - JavaInUse. https://www.javainuse.com/py2java.
        (3) Is There an Online Tool to Convert Python code into Java code?. https://bing.com/search?q=convert+Python+code+to+Java.
        (4) Is There an Online Tool to Convert Python code into Java code?. https://www.tutorialspoint.com/is-there-an-online-tool-to-convert-python-code-into-java-code. **/