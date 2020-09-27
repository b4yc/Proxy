
/**
CPSC 441 Assignment 1
Name: Baylee Cheung
UCID: 30064955
Tutorial: T03
**/

import java.net.*;
import java.io.*;
import java.util.*;

public class Proxy {
	/** Port for the proxy */
	private static int port;
	/** Socket for client connections */
	private static ServerSocket socket;

	/** Create the Proxy object and the socket */
	public static void init(int p) {
		port = p;
		try {
			socket = new ServerSocket(p);
		} catch (IOException e) {
			System.out.println("Error creating socket: " + e);
			System.exit(-1);
		}
		System.out.println("initiated");
	}

	public static void handle(Socket client) {
		System.out.println("handling");
		Socket server = null;
		HttpRequest request = null;
		HttpResponse response = null;

		/* Process request. If there are any exceptions, then simply
		* return and end this request. This unfortunately means the
		* client will hang for a while, until it timeouts. */

		/* Read request */
		try {
			// set input stream to client
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			request = new HttpRequest(fromClient);
		} catch (IOException e) {
			System.out.println("Error reading request from client: " + e);
			return;
		}
		/* Send request to server */
		DataOutputStream toServer;
		try {
			/* Open socket and write request to socket */
			System.out.println("making a server socket");
			server = new Socket(request.getHost(), port);
			//server = socket.accept();
			toServer = new DataOutputStream(server.getOutputStream());
			/* Fill in */
			// send request to server
			System.out.println("Client requests: \n" + request.toString());
			//toServer.write(request.toString().getBytes());	// if string not working try bytes?????
			toServer.writeUTF(request.toString());		//UTF error????


		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + request.getHost());
			System.out.println(e);
			return;
		} catch (IOException e) {
			System.out.println("Error writing request to server: " + e);
			return;
		}
		/* Read response and forward it to client */
		try {
			DataInputStream fromServer = new DataInputStream(server.getInputStream());
			response = new HttpResponse(fromServer);
			DataOutputStream toClient = new DataOutputStream(client.getOutputStream());
			/* Fill in */
			/* Write response to client. First headers, then body */
			//String replaced = original.relace("this", "that");
			// write messages using dataoutputstream class methods (no bytes just strings)
			// write strings, read strings. 
			// change the content here
			toClient.writeUTF(response.toString());
			String body = new String(response.body, "Base64");	// need to import
			toClient.writeUTF(body);

			client.close();
			server.close();
			/* Insert object into the cache */
			/* Fill in (optional exercise only) */
		} catch (IOException e) {
			System.out.println("Error writing response to client: " + e);
		}
	}

	/** Read command line arguments and start proxy */
	public static void main(String args[]) {
		int myPort = 0;

		try {
			myPort = Integer.parseInt(args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Need port number as argument");
			System.exit(-1);
		} catch (NumberFormatException e) {
			System.out.println("Please give port number as integer.");
			System.exit(-1);
		}

		init(myPort);

		/** Main loop. Listen for incoming connections and spawn a new
		* thread for handling them */
		Socket client = null;

		while (true) {
			try {
				System.out.println("trying to make connection");
				//client = new Socket("localhost", port);
				client = socket.accept();
				System.out.println("here");
				handle(client);
				
			} catch (IOException e) {
				System.out.println("Error reading request from client: " + e);
				/* Definitely cannot continue processing this request,
				* so skip to next iteration of while loop. */
				continue;
			}
		}
	}
}
