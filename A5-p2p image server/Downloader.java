import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * The downloader that is responsible to get image blocks from other peers
 * @author yklam2
 *
 */
public class Downloader extends Thread {
	private ImagePeer peer;
	private PeerData source;
	/**
	 * Prepare a downloader that connects to another specific peer
	 * @param peer Peer that is using this downloader
	 * @param source Peer information of the peer to connect to
	 */
	public Downloader(ImagePeer peer, PeerData source) {
		this.peer = peer;
		this.source = source;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			Socket socket = new Socket(source.host, source.port);
			System.out.println("Connected to "+source.host+":"+source.port);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			try {
				
				while(true) {
					RequestMessage request = peer.getRequest();
					out.reset();
					out.writeObject(request);
					
					ResponseMessage response = (ResponseMessage)in.readObject();
					if(response != null) {
						peer.processResponse(response);
						
					} else {
						// nothing received, sleep before next request
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
						
					}

				}
			} catch (IOException | ClassNotFoundException e) {

				// Connection closed, this handler is done
				System.out.println("Connection to " + source.host+":"+source.port+" closed");
			}
		} catch (IOException e) {
			// Connection closed, this handler is done
			System.out.println("Connection to " + source.host+":"+source.port+" failed");
		}
		
	}
}
