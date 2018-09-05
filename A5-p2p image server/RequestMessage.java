import java.io.Serializable;


/**
 * Request message sent by a downloader to a uploader
 * @author yklam2
 *
 */
public class RequestMessage implements Serializable {
	private static final long serialVersionUID = 8901603444840078504L;
	/**
	 * Timestamp of the current image
	 */
	public long timestamp;
	/**
	 * Blocks that are requested
	 */
	public boolean [][] blockNeeded;
}
