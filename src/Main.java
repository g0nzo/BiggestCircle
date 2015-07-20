import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	private static final int WIDTH = 512;
	private static final int HEIGHT = 512;

	private static final int POINT_SIZE = 10;
	private static final int GOOD_POINTS_NUMBER = 100;
	private static final int BAD_POINTS_NUMBER = 2;

	private static final int GENOME_SIZE = 10;
	private static final int R_SIZE = 4;

	private static final int GENOME_NUMBER = 3;
	private static final int CHROMOSOME_SIZE = GENOME_SIZE * 2+8;

	private static final int POPULATION_NUMBER = 10000;
	private static final int TAKE_FROM_POPULATION = (int) (POPULATION_NUMBER * 0.4);
	private static final double CROSSOVER_RATE = 0.7;
	private static final double MUTATION_RATE = 0.01;

	public static void main(String args[]) {

		View view = new View(WIDTH, HEIGHT, POINT_SIZE);
		Algorithm algorithm = new Algorithm(WIDTH, HEIGHT, GOOD_POINTS_NUMBER,
				BAD_POINTS_NUMBER, POPULATION_NUMBER, GENOME_SIZE,
				GENOME_NUMBER, CHROMOSOME_SIZE, TAKE_FROM_POPULATION, CROSSOVER_RATE, MUTATION_RATE);

		Controller controller = new Controller(view, algorithm);
		controller.loop();
	}

}
