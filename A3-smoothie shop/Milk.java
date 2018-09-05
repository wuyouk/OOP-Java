/**
 * A milk class, a ingredient
 * @author yklam2
 */
public class Milk extends Ingredient {

	/**
	 * Create a milk object, volume always 100.
	 */
	public Milk() {
		super(100);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { 
		return "Milk";
	}
}
/**
 * A factory class that create milk object
 * @author yklam2
 */
class MilkFactory extends IngredientFactory {
	public Ingredient getIngredient() {
		return new Milk();
	}
	public String getIngredientName() {
		return "Milk";
	}
	
}