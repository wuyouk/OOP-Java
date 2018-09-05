/**
 * A plastic cup class, a container
 * @author yklam2
 */

public class PlasticCup implements Container {

	/* (non-Javadoc)
	 * @see Container#getCapacity()
	 */
	public int getCapacity() {
		return 300;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Plastic cup";
	}
}
