package Animals;

/**
 * The class Tiger in animal hierarchy.
 * @author hyzhang
 *
 */
public class Tiger extends Feline {

	/**
	 * The constructor. Invoking that in the superclass.
	 * @param p The initial position of this animal.
	 */
	public Tiger(Point p) { super(p); }
	
	/* (non-Javadoc)
	 * @see Animals.Animal#getName()
	 */
	String getName() { return "Tiger"; }
}
