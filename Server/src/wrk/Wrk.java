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
     *
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

    @Override
    public void doRobotAction(String[] value) {
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
                byte[] audio = getAudio();
                wrkRobot.sendAudio(audio);
        }

    }

    private byte[] getAudio() {
        byte[] datas = null;
        try{
            File f = new File("res/out.wav");

            if (f.exists()) {
                AudioInputStream is = AudioSystem.getAudioInputStream(f);
                DataInputStream dis = new DataInputStream(is);
                try {
                    AudioFormat format = is.getFormat();
                    byte[] auddatas = new byte[(int) (is.getFrameLength() * format.getFrameSize())];
                    dis.readFully(auddatas);
                    datas = new byte[auddatas.length-44]; //44-> longueur de l'header wav RIFF
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

    @Override
    public void log(String log) {
        refCtrl.log(log);
    }

    @Override
    public void logOut() {
        refCtrl.logOut();
    }

    @Override
    public void connectRobot() {
        wrkRobot.connect("10.18.1.252", 7837, 306657269);
    }

    @Override
    public void insertInformation(String s) {
        Informations info = new Informations();
        info.setHumidity(Double.valueOf(s));
        info.setTemperature((long) currentTemperature);
        info.setDate(new Date());
        info.setFkUser(refCtrl.getUser());
        try {
            wrkDb.addInfo(info);
        } catch (MyDBException e) {
            System.out.println("ERR "+e.getMessage());
        }
    }


    public void addUser(Users user) throws MyDBException {
        wrkDb.addUser(user);
    }

    public void modifyUser(Users user) throws MyDBException {
        wrkDb.modifyUser(user);
    }

    public void deleteUser(Users user) throws MyDBException {
        wrkDb.deleteUser(user);

    }

    public void setRefCtrl(ItfCtrlWrk refCtrl) {
        this.refCtrl = refCtrl;
    }

    public List<Users> readUsers() throws MyDBException {

        return wrkDb.readUsers(Users.class);
    }

    /**
     *
     * @param temperature
     */
    @Override
    public void receiveTemperature(double temperature) {
        currentTemperature = temperature;
        wrkServer.sendMessage("Temperature," + temperature);
    }

    /**
     *
     * @param frame
     */
    @Override
    public void sendImage(byte[] frame) {
        wrkUDP.sendVideo(frame);
    }

    /**
     *
     * @param lastAudio
     */
    @Override
    public void sendAudio(byte[] lastAudio) {
        wrkUDP.sendSound(lastAudio);
    }
}//end Wrk