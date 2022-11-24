package wrk;

import beans.MyRobot;
import ch.emf.info.robot.links.Robot;
import ch.emf.info.robot.links.bean.RobotState;
import ch.emf.info.robot.links.exception.UnreachableRobotException;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class WrkRobot extends Thread {

    private Robot robot;
    private boolean running;
    public ItfWrkRobot refWrk;

    /**
     *
     * @param refWrk
     */
    public WrkRobot(ItfWrkRobot refWrk) {
        super("Thread Etat Robot");
        this.refWrk = refWrk;
        robot = new Robot();
    }

    /**
     *
     */
    @Override
    public void run() {

        running = true;
        while (running) {
            if (robot != null && robot.isConnected()) {
                System.out.println(robot.getLastImage());
                refWrk.sendImage(robot.getLastImage());
                //   refWrk.sendAudio(robot.getLastAudio());
            } else {
                _sleep(1000);
            }
        }
    }

    /**
     *
     * @param millis
     */
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

    /**
     *
     */
    public void headUp() {
        robot.setHeadDirection(RobotState.HeadDirection.UP);
    }

    /**
     *
     */
    public void headNeutral() {
        robot.setHeadDirection(RobotState.HeadDirection.NONE);
    }

    /**
     *
     */
    public void headDown() {
        robot.setHeadDirection(RobotState.HeadDirection.DOWN);
    }

    /**
     *
     * @param ip
     * @param id
     * @param pw
     */
    public void connect(String ip, int id, int pw) {
        try {
            if (ip != null) {
                robot.connect(ip, id, pw);
                if (!robot.isConnected()) {
                    System.out.println("err");
                }
            }
        } catch (UnreachableRobotException e) {
            System.out.println("Unreadchable "+e.getMessage());
        }
    }

    /**
     *
     */
    public void dock() {
        robot.dock();
    }

    /**
     *
     */
    public void led() {
        robot.setLedEnabled(!robot.getRobotState().isLedEnabled());
    }

    /**
     *
     */
    public void moveBackward() {
        robot.setRightSpeed((short) Short.MIN_VALUE);
        robot.setLeftSpeed((short) Short.MIN_VALUE);
    }

    /**
     *
     */
    public void moveForward() {
        robot.setRightSpeed((short) Short.MAX_VALUE);
        robot.setLeftSpeed((short) Short.MAX_VALUE);
    }

    /**
     *
     */
    public void neutral() {
        robot.setRightSpeed((short) 0);
        robot.setLeftSpeed((short) 0);
    }

    /**
     *
     */
    public void standUp() {
        robot.standUp();
    }

    /**
     *
     */
    public void turnLeft() {
        robot.setRightSpeed(Short.MAX_VALUE);
        robot.setLeftSpeed(Short.MIN_VALUE);
    }

    /**
     *
     */
    public void turnRight() {
        robot.setLeftSpeed(Short.MAX_VALUE);
        robot.setRightSpeed(Short.MIN_VALUE);
    }

    /**
     *
     */
    public void undock() {
        robot.undock();
    }

    /**
     *
     * @param lastAudio
     */
    public void sendAudio(byte[] lastAudio) {
        robot.sendAudio(lastAudio);
    }
}//end WrkRobot
