
/**
 * Represents a wolf
 * @author yklam2
 *
 */
public class Wolf extends Canine {
	/**
	 * Create a wolf in a forest
	 * @param forest The forest this wolf is placed
	 */
	public Wolf(Forest forest) {
		super(forest);
	}
	/* (non-Javadoc)
	 * @see Canine#getSymbol()
	 */
	public String getSymbol() {
		return "w";
	}
}
