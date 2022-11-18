package ctrl;

import app.exceptions.MyDBException;
import beans.Users;
import ihm.ItfIhmCtrl;
import wrk.Wrk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:20
 */
public class Ctrl implements ItfCtrlIhm, ItfCtrlWrk {

    public ItfIhmCtrl refIhm;
    public Wrk refWrk;

    public void setRefIhm(ItfIhmCtrl refIhm) {
        this.refIhm = refIhm;
    }

    public void setRefWrk(Wrk refWrk) {
        this.refWrk = refWrk;
    }

    public Ctrl() {

    }

    /**
     * @throws Throwable Throwable
     */
    public void finalize()
            throws Throwable {

    }

    @Override
    public void addUser(Users user) {
        try {
            refWrk.addUser(user);
        } catch (MyDBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyUser(Users user) {
        try {
            refWrk.modifyUser(user);
        } catch (MyDBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Users user) {
        try {
            refWrk.deleteUser(user);
        } catch (MyDBException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Users> readUsers() {
        try {
            return refWrk.readUsers();
        } catch (MyDBException e) {
            System.out.println("Err");
        }
        return null;
    }

    @Override
    public void log(String log) {
    }

    @Override
    public void connectUser(Users u) {
        refIhm.connectUser(u);
    }

    @Override
    public void logOut() {
        refIhm.logOut();
    }

    public void start() {
        refIhm.startIhm();
    }
}//end Ctrl
