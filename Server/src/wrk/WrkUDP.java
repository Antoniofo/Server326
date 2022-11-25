package wrk;


import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;


/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:16
 */
public class WrkUDP {
    private DatagramSocket datagramSocketImg;
    private volatile InetAddress ip;

    /**
     * Constructor of WrkUdp
     */
    public WrkUDP() {
        try {
            datagramSocketImg = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send Video to client. The images are cut to fit in the datagram package.
     * @param frame image sent.
     */
    public void sendVideo(byte[] frame) {
        float nombreDecoupe = frame.length / Short.MAX_VALUE;
        for (int i = 0; i < nombreDecoupe; i++) {
            byte[] packet = Arrays.copyOfRange(frame, (int) (i * nombreDecoupe), (int) (i * nombreDecoupe + Short.MAX_VALUE));
            try {
                DatagramPacket dp = new DatagramPacket(packet, packet.length, ip, 42069);
                datagramSocketImg.send(dp);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the IP where the datagram is sent.
     * @param ip destination IP
     */
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }
}//end WrkUDP