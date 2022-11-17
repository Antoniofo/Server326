package wrk;

import beans.Users;
import ctrl.ItfCtrlWrk;
import app.exceptions.MyDBException;

import java.util.ArrayList;
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
        wrkDb = new WrkDB();
    }

    /**
     * @throws Throwable Throwable
     */
    public void finalize()
            throws Throwable {

    }


    @Override
    public void register(String value) {

    }

    @Override
    public void checkLogin(String value) {
    }

    @Override
    public void doRobotAction(String value) {
    }

    @Override
    public void upgradeUser(String value) {
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