
/**
 * This class represent a discount basket item that can be checked out by the shop
 * Discount basket is a special item that consists of a number of shop item with a special price
 * @author Kevin Lam
 */
public class DiscountBasket extends ShopItem {
	private ShopItem[] items;
	/**
	 * Create a discount basket with a list of items
	 * @param items The list of items
	 */
	public DiscountBasket(ShopItem [] items) {
		this.items = items;
	}
	/* (non-Javadoc)
	 * @see ShopItem#getDesc()
	 */
	public String getDesc() {
		return items.length+" item"+(items.length>1?"s":"")+" discount basket";
	}
	/* (non-Javadoc)
	 * @see ShopItem#getPrice()
	 */
	public double getPrice() {
		double total = 0;
		for(int i=0; i<items.length; ++i) {
			total += items[i].getPrice();
		}
		return total * 0.9;
	}
}
