package lk.ac.iit.csa.cex.client;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutionException;

/**
 * Student is a client that needs to pay the tuition fee to the University
 * for 5COSC022C.2 Client-Server Architectures (IIT Sri Lanka) 
 * import necessary packages. 
 * if your code does not compile your grade will be downgraded to zero. 
 * You can overwrite/change the code lines if you need to.
 * You can use the hints from your IDE
*/
public class Student {

    private static Socket socket;

    public static void main(String args[]) {
		try {

			int universityPort = 23044;
			//get the IP address of the your local machine by using the name of the pc.
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("Client: connecting to the sever on port " + universityPort);

			//Create a socket connection
        /*
	    Please be careful about the order of parameters that you want to use 
	    in the socket, otherwise, you will get constructor error
	    */
			socket = new Socket(address, universityPort);
			System.out.println("Just connected to the sever" + socket.getRemoteSocketAddress());

			//Get output stream using socket
			OutputStream os = socket.getOutputStream();

			//Create an output stream writer object
			//You may need OutputStream as a parameter
			OutputStreamWriter osw = new OutputStreamWriter(os);

			//Create a buffered writer object
			//You may need OutputStreamWriter Object as a parameter
			BufferedWriter bw  = new BufferedWriter(osw);

			//You need to money transfer the the tution fee.

			//The write the message to the buffer with UoW Number , module code and tution fee amount
			//You submit the tution fee with three data points.
			//it should have the module code for Client-Server Architectures, student uow id, and tution fee
			//Tution fee amount is the numerical digits of the UoW number without decimals
			//The write the message to the buffer
			Integer tutionFee = 1867571;
			String studentUoWId = "w1867571";
			String moduleCode = "5COSC022C";
			String tutionFeeMessage = "The Student with "+studentUoWId+" has paid fee of "+tutionFee+" for "+moduleCode + "\n";

			bw.write (tutionFeeMessage);
			bw.flush ();

			//print a message to the log to show that the tution fee value was sent to the server
			System.out.println("Client: tution fee sent to the server");

			//Receiving reply message back from the server.
			//The reply would be a receipt number
			//Get input stream using socket
			//Define input stream reader
			//Define buffer reader

			InputStream is = socket.getInputStream();
			InputStreamReader isr  = new InputStreamReader(is);
			BufferedReader br  = new BufferedReader(isr);
			String message = br.readLine();
			//print the received message from the server to the log
			System.out.println(message);

		}
		//Catch the appropriate exceptions and write a suitable message to the log
		//there could be more than one.
		catch (UnknownHostException ux){
			System.out.println(ux);
		} catch (IOException e) {
			System.out.println(e);
		} catch (StringIndexOutOfBoundsException ex) {
			System.out.println(ex);
		} finally {
			try {
				//write code to close the socket
				socket.close();
			} catch(Exception e) {
				System.out.println(e);
			}
		}
    }
}
