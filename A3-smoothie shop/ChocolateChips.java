/**
 * A chocolate chips class, a ingredient
 * @author yklam2
 */
public class ChocolateChips extends Ingredient {

	/**
	 * Create a chocolate chip object, volume between 30 to 40
	 */
	public ChocolateChips() {
		super(30, 40);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { 
		return "Chocolate chips";
	}
}
/**
 * A factory class that create chocolate chips object
 * @author yklam2
 */
class ChocolateChipsFactory extends IngredientFactory {
	public Ingredient getIngredient() {
		return new ChocolateChips();
	}
	public String getIngredientName() {
		return "Chocolate chips";
	}
	
}