package Animals;

/**
 * The class Wolf in animal hierarchy.
 * @author hyzhang
 *
 */
public class Wolf extends Canine {

	/**
	 * The constructor. Invoking that in the superclass.
	 * @param p The initial position of this animal.
	 */
	public Wolf(Point p) { super(p); }
	
	/* (non-Javadoc)
	 * @see Animals.Animal#getName()
	 */
	String getName() { return "Wolf"; }
	
}
