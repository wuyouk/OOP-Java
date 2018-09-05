import java.text.DecimalFormat;

/**
 * This class represent a fruit item that can be checked out by the shop
 * @author Kevin Lam
 */
public class Fruit extends ShopItem {
	private double weight;
	/**
	 * Create a fruit item of a certain <code>weight</code>
	 * @param weight Weight of the fruit item
	 */
	public Fruit(double weight) {
		this.weight = weight;
	}
	/* (non-Javadoc)
	 * @see ShopItem#getDesc()
	 */
	public String getDesc() {
		// For demonstration only, number format is not graded
		DecimalFormat myFormatter = new DecimalFormat("#.###");
		return myFormatter.format(weight)+"g fruit";
	}
	/* (non-Javadoc)
	 * @see ShopItem#getPrice()
	 */
	public double getPrice() {
		return weight * 1.6;
	}
}
