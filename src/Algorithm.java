import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Random;

public class Algorithm {

	//size of screen
	private int height;
	private int width;

	private int goodPointsNumber;
	private int badPointsNumber;

	private ArrayList<Point2D> goodPointsList;
	private ArrayList<Point2D> badPointsList;
	private Ellipse2D.Double bestCircle;

	private int populationSize;
	private int genomeSize;
	private int genomeNumber;
	private int chromosomeSize;
	private int takeFromPopulation; 
	private double crossoverRate;
	private double mutationRate;

	private ArrayList<Genome> population;

	public Algorithm(int width, int height, int goodPointsNumber,
			int badPointsNumber, int populationSize, int genomeSize,
			int genomeNumber, int chromosomeSize, int takeFromPopulation,
			double crossoverRate, double mutationRate) {
		
		this.width = width;
		this.height = height;
		this.goodPointsNumber = goodPointsNumber;
		this.badPointsNumber = badPointsNumber;

		this.populationSize = populationSize;
		this.genomeSize = genomeSize;
		this.genomeNumber = genomeNumber;
		this.chromosomeSize = chromosomeSize;
		this.takeFromPopulation = takeFromPopulation;
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;

		this.goodPointsList = new ArrayList<Point2D>();
		this.badPointsList = new ArrayList<Point2D>();
		
		bestCircle = Functions.getEllipseFromCenter(0, 0, 1);
		reset();
	}

	public void epoch() {
		crossover();
		computeFitness();
		sortPopulationByFitness();
		bestCircle = Functions.genomeToCircle(population.get(0), genomeSize, chromosomeSize);
	}

	public void generatePopulation() {
		population = new ArrayList<Genome>();
		System.out.println(populationSize);
		for (int i = 0; i < populationSize; i++) {
			Genome genome = new Genome(chromosomeSize);
			population.add(genome);
		}
	}

	public void computeFitness() {
		for (int i = 0; i < populationSize; i++) {
			System.out.println(i);
			Genome genome = population.get(i);
			Ellipse2D.Double circle = Functions.genomeToCircle(genome,
					genomeSize, chromosomeSize);

			if (!Functions.isCircleInScreen(circle, width, height)
					|| Functions.circleContainsPoint(badPointsList, circle))
				genome.setFitness(0.0);
			else
				genome.setFitness(Functions.numberOfContainedPoints(goodPointsList, circle));
		}
	}

	public void sortPopulationByFitness() {
		Collections.sort(population, new Comparator<Genome>() {
			public int compare(Genome g1, Genome g2) {
				return Double.compare(g2.getFitness(), g1.getFitness());
			}

		});
	}

	public void crossover() {
		ArrayList<Genome> newPopulation = new ArrayList<Genome>();

		for (int i = 0; i < populationSize; i += 2) {
			Random rand = new Random();
			int id1 = rand.nextInt(takeFromPopulation);
			int id2 = rand.nextInt(takeFromPopulation);

			Genome parent1 = population.get(id1);
			Genome parent2 = population.get(id2);

			ArrayList<Integer> child1 = new ArrayList<Integer>();
			ArrayList<Integer> child2 = new ArrayList<Integer>();

			if (Math.random() < crossoverRate) {
				int crossoverPoint = rand.nextInt(chromosomeSize);
				child1.addAll(parent1.getBits().subList(0, crossoverPoint));
				child1.addAll(parent2.getBits().subList(crossoverPoint,
						chromosomeSize));

				child2.addAll(parent2.getBits().subList(0, crossoverPoint));
				child2.addAll(parent1.getBits().subList(crossoverPoint,
						chromosomeSize));

			} else {
				child1.addAll(parent1.getBits());
				child2.addAll(parent2.getBits());
			}
			
			for(int i2 = 0; i2<chromosomeSize; i2++)
			{
				if(Math.random()<mutationRate)
				{
					child1.set(i2, (child1.get(i2)== 0)? 1 : 0);
					child2.set(i2, (child2.get(i2)== 0)? 1 : 0);
				}
			}
			
			Genome genChild1 = new Genome(parent1);
			genChild1.setBits(child1);

			Genome genChild2 = new Genome(parent2);
			genChild2.setBits(child2);
			
			newPopulation.add(genChild1);
			newPopulation.add(genChild2);
		}
		population = new ArrayList<Genome>(newPopulation);
	}

	public void reset() {
		System.out.println(populationSize);
		this.goodPointsList = new ArrayList<Point2D>();
		this.badPointsList = new ArrayList<Point2D>();
		randomPoints();
		generatePopulation();
	}

	public void randomPoints() {
		Random random = new Random();
		for (int i = 0; i < goodPointsNumber; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			goodPointsList.add(new Point2D.Double(x, y));
		}

		for (int i = 0; i < badPointsNumber; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			badPointsList.add(new Point2D.Double(x, y));
		}
	}

	public ArrayList<Point2D> getGoodPointsList() {
		return goodPointsList;
	}

	public ArrayList<Point2D> getBadPointsList() {
		return badPointsList;
	}

	public Ellipse2D.Double getCircle() {
		return bestCircle;
	}
	
}
