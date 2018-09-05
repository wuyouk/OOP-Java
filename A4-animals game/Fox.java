package Animals;

/**
 * The class Fox in animal hierarchy.
 * @author hyzhang
 *
 */
public class Fox extends Canine {
	
	/**
	 * The constructor. Invoking that in the superclass.
	 * @param p The initial position of this animal.
	 */
	public Fox(Point p) { super(p); }
	
	/* (non-Javadoc)
	 * @see Animals.Animal#getName()
	 */
	String getName() { return "Fox"; }
}
