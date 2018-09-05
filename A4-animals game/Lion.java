package Animals;

/**
 * The class Lion in animal hierarchy.
 * @author hyzhang
 *
 */
public class Lion extends Feline {
	
	/**
	 * The constructor. Invoking that in the superclass.
	 * @param p The initial position of this animal.
	 */
	public Lion(Point p) { super(p); }
	
	/* (non-Javadoc)
	 * @see Animals.Animal#getName()
	 */
	String getName() { return "Lion"; }
	
}
