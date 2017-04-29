package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public void svr_main(int port) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + port);
			System.exit(1);
		}

		System.out.println("Server ready");

		try {
			while (true) {
				Socket socket = serverSocket.accept();
				try {
					new ServeConnection(socket);
				} catch (IOException e) {
					System.err.println("IO Exception");
				}
			}
		} finally {
			serverSocket.close();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.svr_main(3049);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
