
//Angry Robots
//ITCS and Physics - Integrated Projectile Project
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class AngryRobots {

	public static double Ay = -9.8;
	public static final double Ax = 0;
	public static double y = 0.0;
	public static double x = 0.0;
	public static double v0x = 0.0;
	public static double v0y = 0.0;
	public static double t = 0.0;
	public static double dt = 0.1;
	public static FileWriter file;

	public static void main(String[] args) {
		double v0 = Double.parseDouble(JOptionPane.showInputDialog("Please enter an initial velocity in m/s"));
		double angleDegrees = Double
				.parseDouble(JOptionPane.showInputDialog("Please enter an initial angle in degrees"));

		v0x = calculateV0X(v0, angleDegrees);
		v0y = calculateV0Y(v0, angleDegrees);
		try {
			file = new FileWriter("AngryRobotsData\\RobotsData" + (int) v0 + "v" + (int) angleDegrees + "d" + ".txt");
		} catch (IOException e) {
			System.out.println("file not created ):");
		}
		calculateProjectile(v0x, v0y);

		try {
			file.close();
		} catch (IOException e) {
			System.out.println("failed to close");
		}
	}

	public static double calculateV0X(double v0, double angleDegrees) {
		return Math.cos(Math.toRadians(angleDegrees)) * v0;
	}

	public static double calculateV0Y(double v0, double angleDegrees) {
		return Math.sin(Math.toRadians(angleDegrees)) * v0;
	}

	public static double calculateX(double v0x, double t) {
		return v0x * t;
	}

	public static double calculateY(double v0y, double t) {
		return (v0y * t) + (0.5 * Ay * Math.pow(t, 2));
	}

	public static void calculateProjectile(double v0x, double v0y) {
		while (y >= 0) {
			t += dt;
			x = calculateX(v0x, t);
			y = calculateY(v0y, t);
			System.out.println(t + ", " + x + ", " + y);
			if (y >= 0) {
				plotProjectile(x, y);
			}
		}
	}

	public static void plotProjectile(double x, double y) {
		try {
			file.write(x + "\n");
			file.write(y + "\n");
			file.write("\n");
		} catch (IOException e) {
			System.out.println("i failed to plot ):");
		}
	}
}