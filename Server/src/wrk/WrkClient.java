package wrk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

    public WrkClient(Socket socket) {
        this.client = socket;
    }

    /**
     * @throws Throwable Throwable
     */
    public void finalize()
            throws Throwable {

    }

    @Override
    public void run() {
        runing = true;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            while (runing) {
                String msg = in.readLine();
                if(msg != null){
                    System.out.println(msg);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void sendMessage() {

    }
}//end WrkClient