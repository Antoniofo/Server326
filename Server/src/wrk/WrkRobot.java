package wrk;

import ch.emf.info.robot.links.Robot;
import ch.emf.info.robot.links.bean.RobotState;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class WrkRobot extends Thread implements ItfWrkRobot {

    private Robot robot;
    private boolean running;
    private boolean lastConnected;
    public ItfWrkRobot refWrk;

    public WrkRobot() {
        super("Thread Etat Robot");
        robot = new Robot;


    }

    @Override
    public void run() {
        running = true;
        while (running) {
            _sleep(10);

            refWrk.sendImage(robot.getLastImage());
            refWrk.sendAudio(robot.getLastAudio());

        }
    }

    private void _sleep(int millis) {
        try {
            sleep(millis);
        } catch (InterruptedException ex) {
            System.err.println("Erreur lors du sleep du thread " + super.getName()
                    + ". \n" + ex.getMessage());
        }
    }

    /**
     * @throws Throwable Throwable
     */
    public void finalize()
            throws Throwable {

    }

    public void headUp() {
        robot.setHeadDirection(RobotState.HeadDirection.UP);
    }

    public void headNeutral() {
        robot.setHeadDirection(RobotState.HeadDirection.NONE);
    }

    public void headDown() {
        robot.setHeadDirection(RobotState.HeadDirection.DOWN);
    }

    public void connect() {

    }

    public void dock() {
        robot.dock();
    }

    public void led() {

    }

    public void moveBackward() {
        robot.setRightSpeed((short) Short.MAX_VALUE);
        robot.setRightSpeed((short) Short.MAX_VALUE);
    }

    public void moveForward() {
        robot.setRightSpeed((short) Short.MAX_VALUE);
        robot.setRightSpeed((short) Short.MAX_VALUE);
    }

    public void neutral() {
        robot.setRightSpeed((short) 0);
        robot.setLeftSpeed((short) 0);
    }

    public void standUp() {

    }

    public void turnLeft() {
        robot.setRightSpeed((short) 600);
        robot.setLeftSpeed((short) 200);
    }

    public void turnRight() {
        robot.setRightSpeed((short) 200);
        robot.setLeftSpeed((short) 600);
    }

    public void undock() {
        robot.undock();
    }

    @Override
    public void sendImage(byte[] frame) {

    }

    @Override
    public void sendAudio(byte[] lastAudio) {

    }
}//end WrkRobot
