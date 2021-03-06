package test;

import java.net.*;
import java.io.*;

public class ServeConnection extends Thread {
	private Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;

	public ServeConnection(Socket s) throws IOException {

		// init connection with client
		socket = s;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O.");
			System.exit(1);
		}
		start();
	}

	public void run() {

		System.out.println("client accepted from: " + socket.getInetAddress() + ":" + socket.getPort());

		// get commands from client, until is he communicating or until no error
		// occurs
		String inputLine, outputLine;

		try {
			while ((inputLine = in.readLine()) != null) {

				System.out.println("request: " + inputLine);
				outputLine = inputLine;
				out.println("I've recived " + outputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("server ending");
		out.close();
		try {
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
