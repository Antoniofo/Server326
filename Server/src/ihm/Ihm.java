package ihm;

import ctrl.ItfCtrlIhm;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:38:45
 */
public class Ihm implements ItfIhmCtrl {

    private IhmMainScreen ihmMain;
    private IhmPasswordAccess ihmPwd;
    private IhmUserManagement ihmUser;
    private ItfCtrlIhm refCtrl;

    public Ihm() {
        ihmMain = new IhmMainScreen(this);
        ihmPwd = new IhmPasswordAccess(this);
        ihmUser = new IhmUserManagement(this);
    }

    public void setRefCtrl(ItfCtrlIhm refCtrl) {
        this.refCtrl = refCtrl;
    }

    public void finalize() throws Throwable {

    }

    @Override
    public void quit() {

    }

    @Override
    public void startIhm() {
        ihmPwd.start();
    }

    public void showMainScreen() {
        ihmMain.start();
        ihmPwd.quit();

    }

    public void showPasswordAccess() {
        ihmPwd.start();
        ihmMain.quit();
    }
}//end Ihm