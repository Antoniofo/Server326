package wrk;

/**
 * @author eggera
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public interface ItfWrkClient {

    public boolean register(String value, String s, String s1);

    public int checkLogin(String value, String s);
    
    public void doRobotAction(String value);
    
    public void upgradeUser(String value);

    void log(String s);

    void logOut();
}
