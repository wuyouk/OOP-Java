/**
 * A banana class, a ingredient
 * @author yklam2
 */
public class Banana extends Ingredient {

	/**
	 * Create a banana, volume between 35 to 40
	 */
	public Banana() {
		super(35, 40);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { 
		return "Banana";
	}
}
/**
 * A factory class that create banana object
 * @author yklam2
 */
class BananaFactory extends IngredientFactory {
	public Ingredient getIngredient() {
		return new Banana();
	}
	public String getIngredientName() {
		return "Banana";
	}
	
}