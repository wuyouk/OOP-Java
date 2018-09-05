/**
 * A coconut class, a ingredient and a container
 * @author yklam2
 */
public class Coconut extends Ingredient implements Container {

	/**
	 * Create a coconut, volume between 60-80
	 */
	public Coconut() {
		super(60, 80);
	}
	/* (non-Javadoc)
	 * @see Container#getCapacity()
	 */
	public int getCapacity() {
		return getVolume() * 5;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { 
		return "Coconut";
	}	
}
/**
 * A factory class that create coconut object
 * @author yklam2
 */
class CoconutFactory extends IngredientFactory {
	public Ingredient getIngredient() {
		return new Coconut();
	}
	public String getIngredientName() {
		return "Coconut";
	}
	
}