/**
 * An apple class, a ingredient
 * @author yklam2
 */
public class Apple extends Ingredient {

	/**
	 * Create an apple, volume between 40 to 50.
	 */
	public Apple() {
		super(40, 50);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { 
		return "Apple";
	}
}
/**
 * A factory class that create apple object
 * @author yklam2
 */
class AppleFactory extends IngredientFactory {
	public Ingredient getIngredient() {
		return new Apple();
	}
	public String getIngredientName() {
		return "Apple";
	}
	
}