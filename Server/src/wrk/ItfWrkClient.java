package wrk;

/**
 * @author eggera
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public interface ItfWrkClient {

    public void register(String value);

    public void checkLogin(String value);
    
    public void doRobotAction(String value);
    
    public void upgradeUser(String value);
}
