import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
	
	public static void main(String[] args) {
		try {
			get2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		try {
//			if (args.length != 3)
//				throw new Exception();
//			URL url = new URL(args[1]);
//			int port = Integer.parseInt(args[2]);
//			try {
//				if (args[0].equals("GET")) {
//					System.out.println("trying get");
//					get(url, port);
//				}
//				else if (args[0].equals("HEAD")) {
//					head(url, port);
//				}
//				else if (args[0].equals("PUT")) {
//					put(url, port);
//				}
//				else if (args[0].equals("POST")) {
//					post(url, port);
//				}
//			} catch (IOException ex) {
//				System.out.println("error"+ "\n");
//				System.out.println(ex.getMessage());
//			}
//		}
//		catch (Exception exc) {
//			System.out.println("Noob!");
//			System.out.println(args.length);
//		}
//	}

	private static void post(URL url, int port) {
		// TODO Auto-generated method stub
		
	}

	private static void put(URL url, int port) {
		// TODO Auto-generated method stub
		
	}

	private static void head(URL url, int port) {
		// TODO Auto-generated method stub
		
	}

	private static void get(URL url, int port) throws IOException {
//		URL oracle = new URL("http://www.oracle.com/");
//		BufferedReader in = new BufferedReader(
//		        new InputStreamReader(oracle.openStream()));
//		String inputLine;
//		System.out.println(in.readLine());
//        while ((inputLine = in.readLine()) != null) {
//            System.out.println(inputLine);
//        }
//        in.close();
        
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in)); 
    	Socket clientSocket = new Socket("localhost", 6789);
    	 DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    	String sentence = inFromUser.readLine(); 
    	outToServer.writeBytes(sentence + '\n');
    	String modifiedSentence = inFromServer.readLine();
    	 System.out.println("FROM SERVER: " + modifiedSentence); 
    	 clientSocket.close();
	}
	private static void get2() throws IOException {
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in)); 
    	Socket clientSocket = new Socket("www.google.com", 80);
    	 DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    	String sentence = inFromUser.readLine(); 
    	outToServer.writeBytes(sentence + "\r\n"+ "Host: www.google.com:80" +"\r\n\r\n");
    	String modifiedSentence = inFromServer.readLine();
    	int code = getCode(modifiedSentence);
    	System.out.println(code);
    	handle(code);
    	while ((modifiedSentence = inFromServer.readLine()) != null) {
    		System.out.println("FROM SERVER: " + modifiedSentence); 
    		if (modifiedSentence.contains("HREF=")) {
    			System.out.println("contains HREF");
    			String newLocation = modifiedSentence.substring(9, modifiedSentence.length()-11);
    			System.out.println(newLocation);
    			DataOutputStream outToServer2 = new DataOutputStream(clientSocket.getOutputStream());
    			BufferedReader inFromServer2 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    			outToServer2.writeBytes("GET " + newLocation+ " HTTP/1.1" + "\r\n"+ "Host: www.google.com:80" +"\r\n\r\n");
    			String serverResponse;
    	    	while ((serverResponse = inFromServer2.readLine()) != null) {
    	    		System.out.println("FROM SERVER: "+ serverResponse);
    	    	}
    		}
    	}
//    	 clientSocket.close();
	}
	
	private static void handle(int code) {
		// TODO Auto-generated method stub
		
	}
	private static int getCode(String firstResponse) {
		return Integer.parseInt(firstResponse.substring(9, 12));
	}
	
}