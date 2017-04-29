package test;

import java.io.IOException;

public class MyClientServerSnippet {
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("Client: java snippet.MyClientServerSnippet<hostname> <port>");
			System.err.println("Server: java snippet.MyClientServerSnippet<port>");
			System.exit(1);
		} else if (args.length > 1) {
			System.out.println("Starting client...\n");
			Client client = new Client();
			client.cli_main(3049, "127.0.0.1");
		} else {
			System.out.println("Starting server...\n");
			Server server = new Server();
			server.svr_main(3049);
		}
	}
}
