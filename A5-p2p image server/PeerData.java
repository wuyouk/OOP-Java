import java.io.Serializable;

/**
 * Peer data, also act as a message for communication
 * @author yklam2
 *
 */
public class PeerData implements Serializable {

	private static final long serialVersionUID = -4281130988831612527L;
	/**
	 * Peer ID
	 */
	public int peerId;
	/**
	 * Peer Host
	 */
	public String host;
	/**
	 * Peer Port
	 */
	public int port;
	
	/**
	 * Prepare a peer data object
	 * @param peerId Peer ID
	 * @param host Peer Host
	 * @param port Peer Port
	 */
	public PeerData(int peerId, String host, int port) {
		this.peerId = peerId;
		this.host = host;
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Peer #"+peerId;
	}
}
