/** 
 * This class represent a soy milk item that can be checked out by the shop
 * Soy milk is a special kind of milk that always have a fat level of 0%
 * @author Kevin Lam
 */
public class SoyMilk extends Milk {
	/**
	 * Create a soy milk item of a certain <code>volume</code>
	 * @param volume
	 */
	public SoyMilk(double volume) {
		super(0, volume);
	}
	/* (non-Javadoc)
	 * @see Milk#getName()
	 */
	public String getName() {
		return "soy milk";
	}

	/* (non-Javadoc)
	 * @see Milk#getPrice()
	 */
	public double getPrice() {
		return getVolume() * 0.08;
	}
}
