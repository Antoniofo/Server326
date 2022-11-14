package ctrl;

import ihm.ItfIhmCtrl;
import wrk.Wrk;

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
    public void addUser(Object user) {
    }

    @Override
    public void modifyUser(Object user) {
    }

    @Override
    public void deleteUser(Object user) {
    }

    @Override
    public Object readUsers() {
    }

    @Override
    public void log(String log) {
    }

}//end Ctrl
