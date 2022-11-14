package wrk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;


/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class WrkClient extends Thread {

	private Socket client;
	private BufferedReader in;
	private BufferedWriter out;
	private boolean runing;
	public ItfWrkClient refWrk;

	public WrkClient(){

	}

	/**
	 * 
	 * @exception Throwable Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	@Override
	public void run(){

	}

	public void sendMessage(){

	}
}//end WrkClient