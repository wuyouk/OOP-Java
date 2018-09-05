import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Server, a special peer that will only send out images.
 * Also responsible to maintain the peer list
 * @author yklam2
 *
 */
public class ImageServer extends ImagePeer implements Runnable {
	
	/**
	 * The port this server listen to
	 */
	public static final int SERVER_PORT = 8000;
	/**
	 * Peer number of server
	 */
	public static final int SERVER_PEAR_ID = 1;
	
	private int peerCounter;
	private ServerSocket serverSocket;
	
	private Map<PeerData, PeerHandler> peerList;
	
	public static void main(String [] args) {
		try {
			ImageServer app = new ImageServer(SERVER_PORT);
			app.showGUI();
		} catch (IOException e) {
			System.err.println("Error starting server.");
		}
	}
	
	/**
	 * Create a server at specific port
	 * @param port Port number the server should listen to
	 * @throws IOException When server cannot be started
	 */
	public ImageServer(int port) throws IOException {
		// peer #1 is reserved for the server 
		peerCounter = SERVER_PEAR_ID;
		setPeerId(peerCounter++);
		
		serverSocket = new ServerSocket(port);
		peerList = Collections.synchronizedMap(new HashMap<PeerData, PeerHandler>());
	}
	
	/**
	 * Show the UI of the server
	 */
	public void showGUI() {
		ImageChooser imageChooser = new ImageChooser();
		BufferedImage image = imageChooser.getImage();
		if(image != null) {
			JFrame frame = new JFrame("Image Server");
			loadImage(image);
			frame.add(getImagePanel(), BorderLayout.CENTER);
			JButton imageLoader = new JButton("Load another image");
			imageLoader.addActionListener(imageChooser);
			frame.add(imageLoader, BorderLayout.PAGE_END);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
			frame.setResizable(false);
			// start server thread
			new Thread(this).start();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			while(true) {
				Socket socket = serverSocket.accept();
				new PeerHandler(socket).exchangeInfo();
			}

		} catch (IOException e) {
			System.err.print("Failed to start server");
			System.exit(0);
		}
	}	
	
	private class ImageChooser implements ActionListener {
		private JFileChooser imgChooser;
		public ImageChooser() { 
			imgChooser = new JFileChooser();
		}
		public void actionPerformed(ActionEvent event) {
			BufferedImage image = getImage();
			if(image != null) 
				loadImage(image);
		}
		public BufferedImage getImage() {
			int result = imgChooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File file = imgChooser.getSelectedFile();
	            try {
		            BufferedImage image = ImageIO.read(file);
		            if(image == null) {
		            	throw new IOException();
		            }
		            return image;
	            } catch (IOException e) {
	            	JOptionPane.showMessageDialog(null, "Error loading image file", "alert", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        return null;
		}
		
	}
	
	private class PeerHandler {
		private Socket socket;
		private PeerData data;
		private ObjectOutputStream out;
		private ObjectInputStream in;
		public PeerHandler(Socket socket) throws IOException {
			this.socket = socket;
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		}
		public void exchangeInfo() {
			
			try {
				int peerId = peerCounter++;
				String peerHost = socket.getInetAddress().getHostAddress();
				int peerPort = in.readInt();
				data = new PeerData(peerId, peerHost, peerPort);
				System.out.println("Incoming "+data+" from: "+peerHost);
			
				PeerData  initData = new PeerData(data.peerId, null, getPort());

				out.writeObject(initData);
				out.flush();
				
				// send peer list
				for(PeerHandler handler: peerList.values()) {
					if(!send(handler.data)) {
						peerList.remove(handler.data);
					}
				}
				new Boardcaster(data).start();
				peerList.put(data, this);
				
			} catch (IOException e) {
				System.err.println("Error communicating with peer");
			}

		}
		public boolean send(PeerData data) {
			try {
				out.reset();
				out.writeObject(data);
			} catch(IOException e) {
				return false;
			}
			return true;
		}
	}

	private class Boardcaster extends Thread {
		private PeerData data;
		public Boardcaster(PeerData data) {
			this.data = data;
		}
		public void run() {
			for(PeerHandler handler: peerList.values()) {
				if(!handler.send(data)) {
					peerList.remove(handler.data);
				}
			}
		}
	}
}
