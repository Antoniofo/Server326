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


    public WrkClient client;
    private ItfWrkClient ref;
    private volatile boolean running;
    private int port = 7777;
    private volatile ServerSocket server;

    /**
     * Constructor of WrkServer
     *
     * @param ref reference of the Wrk
     */
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
     * Set running status of the Thread. Close the socket if the status given is false.
     *
     * @param running Thread status
     */
    public void setRunning(boolean running) {
        this.running = running;
        if (running == false) {
            try {
                server.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Send message to client using sub-WrkClient.
     *
     * @param msg The message sent
     */
    public void sendMessage(String msg) {
        if (client != null) {
            client.sendMessage(msg);
        }

    }

    /**
     * Establishment of the connection with the client and starts to listen the messages.
     * Also synchronizing the connections with the clients.
     */
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


}