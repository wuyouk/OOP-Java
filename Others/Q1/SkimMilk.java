/**
 * This class represent a skim milk item that can be checked out by the shop
 * Skim milk is a special kind of milk that always have a fat level of 2%
 * @author Kevin Lam
 */
public class SkimMilk extends Milk {
	/**
	 * Create a skim milk item of a certain <code>volume</code>
	 * @param volume Volume of the skim milk item
	 */
	public SkimMilk(double volume) {
		super(2, volume);
	}
	/* (non-Javadoc)
	 * @see Milk#getName()
	 */
	public String getName() {
		return "skim milk";
	}
}
