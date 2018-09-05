import java.io.Serializable;


/**
 * Response message sent by uploader to downloader
 * @author yklam2
 *
 */
public class ResponseMessage implements Serializable {
	private static final long serialVersionUID = 3129784548189888658L;
	
	/**
	 * Peer ID of the responsing peer
	 */
	public int peerId;
	/**
	 * Column of block being sent
	 */
	public int col;
	/**
	 * Row of block being sent
	 */
	public int row;
	/**
	 * Image block data
	 */
	public ImageBlock block;
}
