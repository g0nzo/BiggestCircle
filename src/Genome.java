import java.util.ArrayList;

public class Genome {
	private ArrayList<Integer> bits;
	private int bitsNumber;
	private double fitness;
	
	public Genome(int bitsNumber) {
		this.bitsNumber = bitsNumber;
		bits = new ArrayList<Integer>();
		randomBits();
		
		fitness = 0.0;
	}
	
	public Genome(Genome genome) {
		this.bitsNumber = genome.getBitsNumber();
		bits = new ArrayList<Integer>(genome.getBits());

		fitness = 0.0;
	}

	public void randomBits() {
		bits = new ArrayList<Integer>();
		
		for (int i = 0; i < bitsNumber; i++) {
			if(Math.random()<0.5)
				bits.add(0);
			else
				bits.add(1);
		}
	}
	
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public void setBits(ArrayList<Integer> bits) {
		this.bits = bits;
	}
	
	public ArrayList<Integer> getBits() {
		return bits;
	}
	
	public int getBitsNumber() {
		return bitsNumber;
	}
}
