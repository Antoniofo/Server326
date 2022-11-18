package wrk;

import java.io.*;
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

    public WrkClient(Socket socket, ItfWrkClient refWrk) {
        super("Thread-TCP");
        this.refWrk = refWrk;
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
                    String[] t = msg.split(",");
                    switch (t[0]){
                        case "checklogin":
                            boolean isOk = refWrk.checkLogin(t[1],t[2]);
                            if(isOk){
                                System.out.println(t[1]+t[2]);
                                sendMessage("LOGINOK");
                            }else{
                                sendMessage("LOGINNOK");
                            }
                            break;
                        case "ROBOTINIT":
                            break;
                        case "logout":
                            break;
                        case "register":
                            boolean oK = refWrk.register(t[1],t[2],t[3]);
                            if(oK){
                                sendMessage("REGISTEROK");
                            }else{
                                sendMessage("REGISTERNOK");
                            }
                            break;
                        case "Connected":
                            refWrk.log("Client Connected at "+client.getInetAddress().getHostAddress());
                            break;
                        case "upgrade":
                            refWrk.upgradeUser(t[1]);
                        case "humidity":
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Err: "+e.getMessage());
        }


    }

    public void sendMessage(String msg) {
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
        } catch (IOException ex) {

        }
    }
}//end WrkClient