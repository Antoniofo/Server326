package wrk;

import beans.Users;
import ctrl.ItfCtrlWrk;
import app.exceptions.MyDBException;

import java.util.List;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class Wrk implements ItfWrkRobot, ItfWrkClient, ItfWrkPhidget {

    public WrkDB wrkDb;
    public WrkUDP wrkUDP;
    public WrkServer wrkServer;
    public WrkRobot wrkRobot;
    public ItfCtrlWrk refCtrl;
    public WrkPhidget wrkPhidget;

    public Wrk() {
        wrkServer = new WrkServer(this);
        wrkRobot = new WrkRobot();
        wrkPhidget = new WrkPhidget();
        wrkDb = new WrkDB();
        wrkServer.start();
    }

    /**
     * @throws Throwable Throwable
     */
    public void finalize()
            throws Throwable {

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
    public boolean checkLogin(String value, String s) {
        boolean isOk = false;
        Users u = wrkDb.readUser(value, s);
        if (u != null) {
            refCtrl.log("Client: " + value + "Connected");
            refCtrl.connectUser(u);
            isOk = true;
        } else {
            refCtrl.log("Client: " + value + "don't exist");
        }
        return isOk;
    }

    @Override
    public void doRobotAction(String value) {
    }

    @Override
    public void upgradeUser(String value) {
        try {
            Users u = wrkDb.readUser(value);
            u.setIsAdmin((short) 1);
            wrkDb.modifyUser(u);
        } catch (MyDBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void log(String log) {
        refCtrl.log(log);
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

    @Override
    public void receiveTemperature(double temperature) {

    }

    @Override
    public void sendImage(byte[] frame) {
        wrkUDP.sendVideo(frame);
    }

    @Override
    public void sendAudio(byte[] lastAudio) {
        wrkUDP.sendSound(lastAudio);
    }
}//end Wrk