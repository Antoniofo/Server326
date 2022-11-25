package wrk;

/**
 * @author eggera
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public interface ItfWrkRobot {

    void sendImage(byte[] lastImage);

    void sendRobotStatus(boolean connected);
}
