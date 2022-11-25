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

    private String IP;
    private int id;
    private int pw;
    private Robot robot;
    private volatile boolean running;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public ItfWrkRobot refWrk;

    public WrkRobot(ItfWrkRobot refWrk) {
        super("Thread Etat Robot");
        this.refWrk = refWrk;
        robot = new Robot();
    }

    @Override
    public void run() {

        running = true;
        while (running) {
            if (robot != null) {
                refWrk.sendRobotStatus(robot.isConnected());
                if (robot.isConnected()) {
                    refWrk.sendImage(robot.getLastImage());

                } else {
                    _sleep(1000);
                }
            }


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

    public void connect(String ip, int id, int pw) {
        try {
            IP = ip;
            this.id = id;
            this.pw = pw;
            if (ip != null) {
                robot.connect(ip, id, pw);
                if (!robot.isConnected()) {
                    System.out.println("err");
                }
            }
        } catch (UnreachableRobotException e) {
            System.out.println("Unreadchable " + e.getMessage());
        }
    }

    public void dock() {
        robot.dock();
    }

    public void led() {
        robot.setLedEnabled(!robot.getRobotState().isLedEnabled());
    }

    public void moveBackward() {
        robot.setRightSpeed((short) Short.MIN_VALUE);
        robot.setLeftSpeed((short) Short.MIN_VALUE);
    }

    public void moveForward() {
        robot.setRightSpeed((short) Short.MAX_VALUE);
        robot.setLeftSpeed((short) Short.MAX_VALUE);
    }

    public void neutral() {
        robot.setRightSpeed((short) 0);
        robot.setLeftSpeed((short) 0);
    }

    public void standUp() {
        robot.standUp();
    }

    public void turnLeft() {
        robot.setRightSpeed(Short.MAX_VALUE);
        robot.setLeftSpeed(Short.MIN_VALUE);
    }

    public void turnRight() {
        robot.setLeftSpeed(Short.MAX_VALUE);
        robot.setRightSpeed(Short.MIN_VALUE);
    }

    public void undock() {
        robot.undock();
    }


    public void sendAudio(byte[] lastAudio) {
        robot.sendAudio(lastAudio);
    }

    public void disconnect() {
        if (robot.isConnected()) {
            robot.disconnect();
        }
    }
}//end WrkRobot
