package lk.ac.iit.csa.cex.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The University is the server. It accepts requests from Students.
 * if your code does not compile your grade will be downgraded to zero. 
 * You can overwrite/change the code lines if you need to.
 * You can use the hints from your IDE
**/
public class University {

    private static Socket socket;

    public static void main(String[] args) {
        try {

            int universityPort = 23044;
            //create the server socket
			ServerSocket universitySocket = new ServerSocket(universityPort);

			//write a message to the log say the server is started
			System.out.println("Server: starting the sever on port " + universitySocket.getLocalPort());

			//Reading the message from the client by accepting the request
			socket = universitySocket.accept();
			System.out.println("Client accepted the request"+ socket.getRemoteSocketAddress());

			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			//read the input stream from the buffer using using buffered reader
			//You may need to pass InputStreamReader as a parameter
        BufferedReader br = new BufferedReader(isr);

			String receivedMessage = br.readLine();

			//write a message to the log indicating what was recieved from the client
			System.out.println("Recieved from the Student"+receivedMessage);


			String returnMessage;
			//write code to extract the UoW Number, module code and tution fee from the message.

			//Verify if the module code and the sent amount sent is correct.
			//logic of amount validation is given in the Student class
			//if the amount is  correct, prepare the message to say transaction was successfull along with current time
			//if the amount is not correct, prepare the message to say transaction failed along with amount received.

			if (receivedMessage.equals("The Student with w1867571 has paid fee of 1867571 for 5COSC022C")){
				returnMessage = "Transaction was successfull";
			} else  {
				returnMessage = "Trasction was failed";
			}

			//Now send the message back.
			//obtain the instance of output stream object from the socket
        OutputStream os =  socket.getOutputStream();

			//Create a new instance of output stream writer object and use OutputStream instance as its parameter
        OutputStreamWriter osw  = new OutputStreamWriter(os);

			//Creates a BufferedWriter object to hold the message and use use OutputStreamWriter instance as its parameter
        BufferedWriter bw  = new BufferedWriter(osw);

			bw.write (returnMessage);

			//write a message to the log indicating what was sent to the client
			System.out.println("Server: "+returnMessage);;

			bw.flush();
		}
		//Catch  exceptions, there could be many; and write an appropriate message to the log
		catch (UnknownHostException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		//Catches general exception
		catch (Exception e) {
			System.out.println(e);
		}
    finally {
			try {
				//close the connection
				socket.close();
			} catch(Exception e){}
		}
    }
}
