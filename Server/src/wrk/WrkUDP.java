package wrk;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;

import javafx.scene.image.Image;
import sun.security.x509.IPAddressName;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:16
 */
public class WrkUDP {
    private DatagramSocket datagramSocketImg;

    private DatagramSocket datagramSocketAud;
    private String ip = "127.0.0.1";

    public WrkUDP() {
        try {
            datagramSocketImg = new DatagramSocket();
            datagramSocketAud = new DatagramSocket();
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
        System.out.println(frame.length);
        try {
            DatagramPacket dp = new DatagramPacket(frame, frame.length, InetAddress.getByName(ip), 42069);
            datagramSocketImg.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSound(byte[] audio) {
        System.out.println(audio.length);
        try {
            DatagramPacket dp = new DatagramPacket(audio, audio.length, InetAddress.getByName(ip), 42070);
            datagramSocketAud.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}//end WrkUDP