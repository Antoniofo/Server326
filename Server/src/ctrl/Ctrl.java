package ctrl;

import beans.Users;
import ihm.ItfIhmCtrl;
import wrk.Wrk;

import java.util.ArrayList;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:20
 */
public class Ctrl implements ItfCtrlIhm, ItfCtrlWrk {

    public ItfIhmCtrl refIhm;
    public Wrk refWrk;

    public Ctrl() {

    }

    /**
     *
     * @exception Throwable Throwable
     */
    public void finalize()
            throws Throwable {

    }

    @Override
    public void addUser(Users user) {

    }

    @Override
    public void modifyUser(Users user) {
    }

    @Override
    public void deleteUser(Users user) {
    }



    @Override
    public ArrayList<Users> readUsers() {
    }

    @Override
    public void log(String log) {
    }

}//end Ctrl
