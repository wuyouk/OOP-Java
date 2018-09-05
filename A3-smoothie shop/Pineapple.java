/**
 * A pineapple class, a ingredient and a container
 * @author yklam2
 */
public class Pineapple extends Ingredient implements Container {

	/**
	 * Create a pineapple object, volume between 75 to 90
	 */
	public Pineapple() {
		super(75, 90);
	}

	/* (non-Javadoc)
	 * @see Container#getCapacity()
	 */
	public int getCapacity() {
		return getVolume() * 2;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Pineapple";
	}

}
/**
 * A factory class that create pineapple object
 * @author yklam2
 */
class PineappleFactory extends IngredientFactory {
	public Ingredient getIngredient() {
		return new Pineapple();
	}
	public String getIngredientName() {
		return "Pineapple";
	}
	
}