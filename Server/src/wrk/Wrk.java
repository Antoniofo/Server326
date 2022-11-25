package wrk;

import beans.Informations;
import beans.Users;
import ctrl.ItfCtrlWrk;
import app.exceptions.MyDBException;
import javafx.scene.image.Image;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class Wrk implements ItfWrkRobot, ItfWrkClient, ItfWrkPhidget {

    private double currentTemperature;
    public WrkDB wrkDb;
    public WrkUDP wrkUDP;
    public WrkServer wrkServer;
    public WrkRobot wrkRobot;
    public ItfCtrlWrk refCtrl;
    public WrkPhidget wrkPhidget;

    /**
     * Constructor of Wrk
     */
    public Wrk() {
        wrkServer = new WrkServer(this);
        wrkServer.start();
        wrkRobot = new WrkRobot(this);
        wrkPhidget = new WrkPhidget(this);
        wrkDb = new WrkDB();
        wrkUDP = new WrkUDP();
        wrkRobot.start();
    }

    /**
     * Create a Users and call sub-WrkDB to add it to the Database.
     * @param username The username of the user
     * @param pwd The password of the user
     * @param s1 The privilege of the user
     * @return
     */
    @Override
    public boolean register(String username, String pwd, String s1) {
        Users u = new Users();
        short a = (short) ((s1.equals("false")) ? 0 : 1);
        u.setIsAdmin(a);
        u.setUsername(username);
        u.setPassword(pwd);
        try {
            wrkDb.addUser(u);
            return true;
        } catch (MyDBException e) {
            return false;
        }

    }

    /**
     * Call the sub-WrkDB to verify the user exist, also log the connection and send the user to the CTRL.
     * @param value Username of the user
     * @param s password of the user
     * @return 0 If the user does not exist in the Database, 1 if the user is a simple user and 2 if the user is an admin
     */
    @Override
    public int checkLogin(String value, String s) {
        int isOk = 0;
        Users u = wrkDb.readUser(value, s);
        if (u != null) {
            if (u.getIsAdmin() == 0) {
                refCtrl.log("Client: " + value + " Connected");
                isOk = 1;
            } else {
                refCtrl.log("Admin: " + value + " Connected");
                isOk = 2;
            }
            refCtrl.connectUser(u);
        } else {
            refCtrl.log("Client: " + value + " don't exist");
        }
        return isOk;
    }

    /**
     * Receive and action the robot can do and call sub-WrkRobot.
     * @param value the action the robot can do
     */
    @Override
    public void doRobotAction(String[] value) {
        Users u = refCtrl.getUser();
        switch (value[1]) {
            case "UNDOCK":
                wrkRobot.undock();
                break;
            case "DOCK":
                wrkRobot.dock();
                break;
            case "LED":
                wrkRobot.led();
                break;
            case "STAND":
                wrkRobot.standUp();
                break;
            case "RTRIG":
                wrkRobot.moveForward();
                break;
            case "LTRIG":
                wrkRobot.moveBackward();
                break;
            case "LJRIGHT":
                wrkRobot.turnRight();
                break;
            case "LJLEFT":
                wrkRobot.turnLeft();
                break;
            case "STOP":
                wrkRobot.neutral();
                break;
            case "STOPHEAD":
                wrkRobot.headNeutral();
                break;
            case "UPHEAD":
                wrkRobot.headUp();
                break;
            case "DOWNHEAD":
                wrkRobot.headDown();
                break;
            case "DPUP":

                if (u != null) {
                    if (u.getIsAdmin() == 1) {
                        byte[] audio = getAudio("res/1.wav");
                        wrkRobot.sendAudio(audio);
                    }
                }
                break;
            case "DPRIGHT":

                if (u != null) {
                    if (u.getIsAdmin() == 1) {
                        byte[] audio = getAudio("res/2.wav");
                        wrkRobot.sendAudio(audio);
                    }
                }
                break;
            case "DPDOWN":

                if (u != null) {
                    if (u.getIsAdmin() == 1) {
                        byte[] audio = getAudio("res/3.wav");
                        wrkRobot.sendAudio(audio);
                    }
                }
                break;
            case "DPLEFT":
                
                if (u != null) {
                    if (u.getIsAdmin() == 1) {
                        byte[] audio = getAudio("res/4.wav");
                        wrkRobot.sendAudio(audio);
                    }
                }
                break;
        }
    }

    /**
     * Transform a file into byte arrays.
     * @param path the path where the file is
     * @return the byte array of the file
     */
    private byte[] getAudio(String path) {
        byte[] datas = null;
        try {
            File f = new File(path);

            if (f.exists()) {
                AudioInputStream is = AudioSystem.getAudioInputStream(f);
                DataInputStream dis = new DataInputStream(is);
                try {
                    AudioFormat format = is.getFormat();
                    byte[] auddatas = new byte[(int) (is.getFrameLength() * format.getFrameSize())];
                    dis.readFully(auddatas);
                    datas = new byte[auddatas.length - 44]; //44-> longueur de l'header wav RIFF
                    System.arraycopy(auddatas, 44, datas, 0, datas.length);
                } finally {
                    dis.close();
                }
            } else {
                System.err.println("Le fichier " + f.getAbsolutePath() + " n'existe pas.");
            }
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return datas;
    }

    /**
     * Change the privilege of the user by calling the sub-WrkDB and sending a message to the client
     * by calling sub-WrkServer
     * @param value
     */
    @Override
    public void upgradeUser(String value) {
        try {
            Users u = wrkDb.readUser(value);
            if (u.getIsAdmin() == 0) {
                u.setIsAdmin((short) 1);
                wrkDb.modifyUser(u);
                refCtrl.log("User: " + value + " has been promoted to Admin");
                refCtrl.updateLists(u);
                wrkServer.sendMessage("ADMINMODE");
            }
        } catch (MyDBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Send a log message to the Ctrl.
     * @param log the log message
     */
    @Override
    public void log(String log) {
        refCtrl.log(log);
    }

    /**
     * Send the logOut to the Ctrl.
     */
    @Override
    public void logOut() {
        refCtrl.logOut();
    }

    /**
     * Connect to the robot using the sub-WrkRobot.
     */
    @Override
    public void connectRobot() {
        wrkRobot.connect("172.20.10.7", 7837, 306657269);
    }

    /**
     * Create an information and adds it into the Database using sub-WrkDB.
     * @param s Humidity value
     */
    @Override
    public void insertInformation(String s) {
        Informations info = new Informations();
        info.setHumidity(Double.valueOf(s));
        info.setTemperature((long) currentTemperature);
        info.setDate(new Date());
        info.setFkUser(refCtrl.getUser());

        if (((info.getTemperature() > 30 || info.getTemperature() < 0) || info.getHumidity() > 60) && refCtrl.getUser().getIsAdmin() == 0) {
            wrkRobot.disconnect();
            wrkServer.sendMessage("ALERT");
        }

        try {
            wrkDb.addInfo(info);
        } catch (MyDBException e) {
            System.out.println("ERR " + e.getMessage());
        }
    }

    /**
     * Disconnect the robot using sub-WrkRobot.
     */
    @Override
    public void disconnectRobot() {
        wrkRobot.disconnect();
    }

    /**
     * Change the IP of the UDP DatagramSocket using sub-WrkUDP.
     * @param inetAddress The new IP
     */
    @Override
    public void changeIP(InetAddress inetAddress) {
        wrkUDP.setIp(inetAddress);
    }

    /**
     * adding a user using sub-WrkDB.
     * @param user the user to add
     * @throws MyDBException
     */
    public void addUser(Users user) throws MyDBException {
        wrkDb.addUser(user);
    }

    /**
     * Modify a user using sub-WrkDB.
     * @param user the user to modify
     * @throws MyDBException
     */
    public void modifyUser(Users user) throws MyDBException {
        wrkDb.modifyUser(user);
    }

    /**
     * Delete a user using sub-WrkDB.
     * @param user the user to delete
     * @throws MyDBException
     */
    public void deleteUser(Users user) throws MyDBException {
        wrkDb.deleteUser(user);

    }

    /**
     * Sets the reference to the Ctrl.
     * @param refCtrl the reference to the Ctrl
     */
    public void setRefCtrl(ItfCtrlWrk refCtrl) {
        this.refCtrl = refCtrl;
    }

    /**
     * Reads all user from the Database using sub-WrkDB.
     * @return A list of User
     * @throws MyDBException
     */
    public List<Users> readUsers() throws MyDBException {

        return wrkDb.readUsers(Users.class);
    }

    /**
     * Receiving the temperature from the phidget and sending it to the client using sub-WrkServer.
     * @param temperature The temperature received
     */
    @Override
    public void receiveTemperature(double temperature) {
        currentTemperature = temperature;
        wrkServer.sendMessage("Temperature," + temperature);
    }

    /**
     * Sending the image using sub-WrkUDP.
     * @param frame the image to send
     */
    @Override
    public void sendImage(byte[] frame) {
        wrkUDP.sendVideo(frame);
    }

    /**
     * Kills all the thread form sub-WrkServer and sub-WrkRobot, also calling GC.
     */
    public void killThread() {
        try {
            wrkServer.setRunning(false);
            wrkRobot.setRunning(false);
            wrkRobot.join();
            wrkServer.join();
            System.gc();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}