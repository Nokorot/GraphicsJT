package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	static Socket echoSocket = null;
	static PrintWriter out = null;
	static BufferedReader in = null;

	public void cli_main(int port, String servername) throws IOException {
		try {
			echoSocket = new Socket(servername, port);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + servername);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + servername);
			System.exit(1);
		}

		String inputLine;
		
		System.out.println("Client ready!");
		while (true) {

			inputLine = (in.readLine().toString());
			if (inputLine == null) {
				System.out.println("Client closing!");
				break;
			}

			// get the input and tokenize it
			String[] tokens = inputLine.split(" ");

		}

		out.close();
		in.close();
		echoSocket.close();
		System.out.println("Client closing");
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.cli_main(3049, "localhost");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
