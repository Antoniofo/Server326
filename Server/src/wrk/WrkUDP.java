package wrk;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import ch.emf.info.robot.links.Robot;
import sun.security.x509.IPAddressName;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:16
 */
public class WrkUDP {
    private DatagramSocket datagramSocket;
    private String ip = "127.0.0.1";

    public WrkUDP() {
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws Throwable Throwable
     */
    public void finalize()
            throws Throwable {

    }

    public void sendVideo(byte[] frame) {
        try {
            DatagramPacket dp = new DatagramPacket(frame, frame.length, InetAddress.getByName(ip), 42069);
            datagramSocket.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSound(byte[] audio) {
        try {
            DatagramPacket dp = new DatagramPacket(audio, audio.length, InetAddress.getByName(ip), 42070);
            datagramSocket.send(dp);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}//end WrkUDP