
/**
 * Represents a hippo
 * @author yklam2
 *
 */
public class Hippo extends Animal {

	/**
	 * Create a hippo in a forest
	 * @param forest The forest this hippo is placed
	 */
	public Hippo(Forest forest) {
		super(forest);
	}
	/* (non-Javadoc)
	 * @see Animal#getSymbol()
	 */
	public String getSymbol() {
		return "h";
	}
}
