/**
 * A peach class, a ingredient
 * @author yklam2
 *
 */
public class Peach extends Ingredient {

	/**
	 * Create a peach object, volume between 30 to 35
	 */
	public Peach() {
		super(30, 35);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Peach";
	}

}
/**
 * A factory class that create peach object
 * @author yklam2
 */
class PeachFactory extends IngredientFactory {
	public Ingredient getIngredient() {
		return new Peach();
	}
	public String getIngredientName() {
		return "Peach";
	}
	
}