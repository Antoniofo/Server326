package wrk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;


/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class WrkServer extends Thread {

    public WrkClient client;
    private boolean running;
    private int port = 42071;
    private ServerSocket server;

    public WrkServer() {
    }

    public void startServer() {
        try {
            server = new ServerSocket(port);
            server.setSoTimeout(1000);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @throws Throwable Throwable
     */
    public void finalize() throws Throwable {

    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                synchronized (server) {
                    Socket socketClient = server.accept();
                    client = new WrkClient(socketClient);
                    client.start();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}//end WrkServer