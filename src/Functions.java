import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Functions {

	public static Ellipse2D.Double getEllipseFromCenter(double x, double y, double radius)
	{
	    double newX = x - radius;
	    double newY = y - radius;

	    Ellipse2D.Double ellipse = new Ellipse2D.Double(newX, newY, radius*2, radius*2);

	    return ellipse;
	}
	
	public static int binToDecimal(List<Integer> list) {
		int result = 0;
		int mult = 1;                   

		for (int i = 0; i < list.size(); i++) {
			result += list.get(i) * mult;
			mult *= 2;
		}

		return result;
	}
	
	public static boolean isCircleInScreen(Ellipse2D.Double circle, int width, int height) {
		return (circle.getMinX()>= 0 && circle.getMaxX() <= width && circle.getMinY() >=0 && circle.getMaxY() <= height);
	}
	
	public static boolean circleContainsPoint(ArrayList<Point2D> list, Ellipse2D.Double circle) {
		boolean contains = false;
		
		for(int i =0; !contains && i<list.size(); i++)
			if(circle.contains(list.get(i)))
				contains = true;
		
		return contains;
	}
	
	public static int numberOfContainedPoints(ArrayList<Point2D> list, Ellipse2D.Double circle) {
		int contained = 0;
		
		for(int i = 0; i<list.size(); i++)
			if(circle.contains(list.get(i))) {
				contained++;
			}
		
		return contained;
	}
	
	public static Ellipse2D.Double genomeToCircle(Genome genome, int geneSize, int chromosmeSize) {
		
		int x = binToDecimal(genome.getBits().subList(0, geneSize));
		int y = binToDecimal(genome.getBits().subList(geneSize, geneSize*2));
		int r = binToDecimal(genome.getBits().subList(geneSize*2, chromosmeSize)); 

		return getEllipseFromCenter(x, y, r);
	}
	
}
