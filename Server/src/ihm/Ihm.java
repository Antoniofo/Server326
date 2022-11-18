package ihm;

import beans.Users;
import ctrl.Ctrl;
import ctrl.ItfCtrlIhm;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void connectUser(Users u) {
        ihmMain.updateConnectedUser(u);
    }

    @Override
    public void logOut() {
        ihmMain.updateConnectedUser(null);
    }

    public void showMainScreen() {
        ihmMain.start();
        ihmPwd.quit();

    }

    public void showPasswordAccess() {
        ihmPwd.start();
        ihmMain.quit();
    }

    public List<Users> getUsers() {
        return refCtrl.readUsers();
    }

    public void showUserManagement(boolean b) {
        ihmUser.start(b);
    }
    public void updateUsers(){
        ihmMain.updateUsers();
    }


    public void deleteUser(Users selectedItem) {
        refCtrl.deleteUser(selectedItem);
    }

    public List<Users> readUsers() {
        return refCtrl.readUsers();
    }
    public void addUsers(Users user){
        refCtrl.addUser(user);
    }
    public void modifyUser(Users user){
        refCtrl.modifyUser(user);
    }

    public Users getSelectedUser() {
        return ihmMain.getSelectedUser();
    }

    public void log(String text){
        ihmMain.log(text);
    }
}//end Ihm