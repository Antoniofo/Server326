package ctrl;


import beans.Users;

/**
 * @author eggera
 * @version 1.0
 * @created 11-nov.-2022 11:44:21
 */
public interface ItfCtrlWrk {
    public void log(String log);

    void connectUser(Users u);

    void logOut();
}