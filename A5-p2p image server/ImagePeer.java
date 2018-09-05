import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 * Alternative implementation, no peer list in peer
 * @author yklam2
 *
 */
public class ImagePeer {
	private int peerId;
	private int port;
	protected int getPort() {
		return port;
	}
	private UploadServer uploadServer;
	
	private ImagePanel imagePane;
	protected ImagePanel getImagePanel() {
		return imagePane;
	}

	public static void main(String [] args) {
		try {
			String inputValue = JOptionPane.showInputDialog("Connect to server:", "localhost");
			if(inputValue != null) {
				ImagePeer app = new ImagePeer(inputValue);
				app.showGUI();
			}			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error starting peer.");
		}
	}
	private ImagePeer(String host) throws IOException {
		/* first setup itself */
		this();
		
		/* try to connect to server */
		Socket socket = new Socket(host, ImageServer.SERVER_PORT);
		ServerHandler handler = new ServerHandler(socket);
		handler.start();
		
		
	}
	private void startDownloader(PeerData data) {
		new Downloader(this, data).start();
	}
	protected ImagePeer() throws IOException {
		
		/* First attach to a port */
		port = ImageServer.SERVER_PORT+1;
		while(uploadServer == null && port < 65536) {
			try {
				uploadServer = new UploadServer(this, port);
			} catch (IOException e) {
				port++;
			}
		}
		if(uploadServer == null) { // Cannot start server
			System.err.println("Unable to start a peer: no port available");
			throw new IOException();
		}
		uploadServer.start();
		System.out.println("Peer waiting for connection at port " + port);
		
		imagePane = new ImagePanel();
	}
	protected void setPeerId(int peerId) {
		this.peerId = peerId;
	}
	protected int getPeerId() {
		return peerId;
	}
	
	/**
	 * Load a complete image to canvas
	 * @param image Image to load
	 */
	public void loadImage(BufferedImage image) {
		imagePane.setImage(image);
	}
	
	private JFrame frame;
	private void showGUI() {
		
		frame = new JFrame("Image Peer #"+peerId);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(imagePane, BorderLayout.CENTER);
		        
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	private void updateTitle() {
		frame.setTitle("Image Peer #"+peerId);
	}
	/**
	 * Get a request message to send out 
	 * @return the message to send
	 */
	public RequestMessage getRequest() {
		return imagePane.getRequest();
	}
	/**
	 * Process a request message and prepare a response
	 * @param request Request received
	 * @return Response message to send back
	 */
	public ResponseMessage processRequest(RequestMessage request) {
		ResponseMessage response = imagePane.processRequest(request);
		if(response != null)
			response.peerId = peerId;
		return response;
	}
	
	/**
	 * Process a response message
	 * @param response Response received
	 */
	public void processResponse(ResponseMessage response) {
		imagePane.processResponse(response);
	}
	
	private class ServerHandler extends Thread {
		private ObjectInputStream in;
		private ObjectOutputStream out;
		private Socket socket;

		public ServerHandler(Socket socket) throws IOException {
			this.socket = socket;
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}
		private void exchangeInfo() throws IOException {
			out.writeInt(port);
			out.flush();

			PeerData data;
			try {
				data = (PeerData)in.readObject();
			} catch (ClassNotFoundException e) {
				throw new IOException();
			}
			
			peerId = data.peerId;
			updateTitle();
			startDownloader(new PeerData(ImageServer.SERVER_PORT, socket.getInetAddress().getHostAddress(), data.port));
		}
		public void run() {
			try {
				exchangeInfo();
				while(true) {
					PeerData d = (PeerData)in.readObject();
					if(d.peerId != peerId)
						startDownloader(d);
				}
			} catch (IOException | ClassNotFoundException e) {
				// Connection closed
			} finally {
				try {
					in.close();
					out.close();
					socket.close();
				} catch (IOException e) {}
				System.out.println("Server disconnected");
			}
		}
	}

}
