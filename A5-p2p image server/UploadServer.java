import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Upload server that is used to handle incoming peer connection
 * @author yklam2
 *
 */
public class UploadServer extends Thread {

	private ImagePeer peer;
	private ServerSocket serverSocket;
	/**
	 * Try to set up a server at the specific port
	 * @param peer Image peer that this upload server is working for
	 * @param port Port to listen to
	 * @throws IOException When the server cannot be started
	 */
	public UploadServer(ImagePeer peer, int port) throws IOException {
		this.peer = peer;
		serverSocket = new ServerSocket(port);
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while(true) {
			Socket socket;
			try {
				socket = serverSocket.accept();
				new Uploader(socket).start();
			} catch (IOException e) {
				System.err.println("Failed to initialize peer");
			}
		}
	}
	private class Uploader extends Thread {
		private Socket socket;
		public Uploader(Socket socket) throws IOException {
			this.socket = socket;
		}
		public void run() {
			try {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				out.flush();
				
				while(true) {
					RequestMessage request = (RequestMessage)in.readObject();
					ResponseMessage response = peer.processRequest(request);
					out.reset();
					out.writeObject(response);
				}
			} catch(IOException | ClassNotFoundException e) {
				// Connection closed, this handler is done
				System.out.println("Connection from " + socket.getInetAddress().getHostAddress()+" closed");
			}
		}
	}
}
