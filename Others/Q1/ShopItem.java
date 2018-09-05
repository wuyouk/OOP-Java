import java.text.DecimalFormat;

/**
 * The super-class of all items that can be checked out by the shop
 * @author Kevin Lam
 */
public class ShopItem {
	/**
	 * Get the price of this item
	 * @return price of this item
	 */
	public double getPrice() {
		return 0;
	}
	/**
	 * Get description of the item
	 * @return description of the item
	 */
	public String getDesc() {
		return "shop item";
	}
	/**
	 * Get a string representation of this item
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// For demonstration only, number format is not graded
		DecimalFormat myFormatter = new DecimalFormat("#.###");
		return getDesc()+" - $"+myFormatter.format(getPrice());
	}
}
