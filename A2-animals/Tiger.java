
/**
 * Represents a tiger
 * @author yklam2
 *
 */
public class Tiger extends Feline {
	/**
	 * Create a tiger in a forest
	 * @param forest The forest this tiger is placed
	 */
	public Tiger(Forest forest) {
		super(forest);
	}
	/* (non-Javadoc)
	 * @see Feline#getSymbol()
	 */
	public String getSymbol() {
		return "t";
	}
}
