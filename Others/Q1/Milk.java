import java.text.DecimalFormat;


/**
 * This class represent a milk item that can be checked out by the shop
 * @author Kevin Lam
 */
public class Milk extends ShopItem {
	private double fat;
	private double volume;
	/**
	 * Create a milk item of a certain <code>fat</code> level and <code>volume</code>
	 * @param fat Fat level of the milk item
	 * @param volume Volume of the milk item
	 */
	public Milk(double fat, double volume) {
		this.fat = fat;
		this.volume = volume;
	}
	/**
	 * Get the volume of this milk item
	 * @return Volume of this milk item
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 * Get the name of this milk item
	 * @return Name of this milk item
	 */
	public String getName() {
		// For demonstration only, number format is not graded
		DecimalFormat myFormatter = new DecimalFormat("#.###");
		return myFormatter.format(fat)+"% milk";
	}
	/* (non-Javadoc)
	 * @see ShopItem#getDesc()
	 */
	public String getDesc() {
		// For demonstration only, number format is not graded
		DecimalFormat myFormatter = new DecimalFormat("#.###");
		return myFormatter.format(volume)+"mL "+getName();
	}
	/* (non-Javadoc)
	 * @see ShopItem#getPrice()
	 */
	public double getPrice() {
		return (50-fat)/100 * volume * 0.1;
	}
}
