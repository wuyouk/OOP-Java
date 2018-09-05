/**
 * An orange class, a ingredient
 * @author yklam2
 */
public class Orange extends Ingredient {

	/**
	 * Create an orane object, volume between 50 to 60.
	 */
	public Orange() {
		super(50, 60);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { 
		return "Orange";
	}
}
/**
 * A factory class that create orange object
 * @author yklam2
 */
class OrangeFactory extends IngredientFactory {
	public Ingredient getIngredient() {
		return new Orange();
	}
	public String getIngredientName() {
		return "Orange";
	}
	
}