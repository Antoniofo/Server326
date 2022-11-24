package wrk;

import java.io.IOException;
import java.net.*;
import java.sql.SQLOutput;
import java.util.ArrayList;


/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class WrkServer extends Thread {
    public void setRunning(boolean running) {
        this.running = running;
        if (running == false) {
            try {
                server.close();
            } catch (IOException e) {
            }
        }
    }

    public WrkClient client;
    private ItfWrkClient ref;
    private volatile boolean running;
    private int port = 7777;
    private volatile ServerSocket server;

    public WrkServer(ItfWrkClient ref) {
        this.ref = ref;
        try {
            server = new ServerSocket(port);
            server.setSoTimeout(0);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param msg
     */
    public void sendMessage(String msg) {
        client.sendMessage(msg);
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                synchronized (server) {
                    Socket socketClient = server.accept();
                    client = new WrkClient(socketClient, ref);
                    client.start();
                    sleep(10);
                }
            } catch (IOException e) {
            } catch (InterruptedException e) {
            }
        }
    }


}//end WrkServer