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
    public ItfWrkRobot refWrk;

    /**
     * Constructor of WrkRobot.
     * @param refWrk reference to Wrk
     */
    public WrkRobot(ItfWrkRobot refWrk) {
        super("Thread Etat Robot");
        this.refWrk = refWrk;
        robot = new Robot();
    }

    /**
     * Set the running status of the thread.
     * @param running Thread status
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * listening to the image sent by the robot and sending them to Wrk.
     */
    @Override
    public void run() {

        running = true;
        while (running) {
            if (robot != null) {
                if (robot.isConnected()) {
                    refWrk.sendImage(robot.getLastImage());

                } else {
                    _sleep(1000);
                }
            }


        }
    }

    /**
     * Make the thread stops for an amount of time.
     * @param millis The amount of time in millisecond
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
     * Make the robot head goes UP.
     */
    public void headUp() {
        robot.setHeadDirection(RobotState.HeadDirection.UP);
    }

    /**
     * Make the robot head stops.
     */
    public void headNeutral() {
        robot.setHeadDirection(RobotState.HeadDirection.NONE);
    }

    /**
     * Make the robot head goes down.
     */
    public void headDown() {
        robot.setHeadDirection(RobotState.HeadDirection.DOWN);
    }

    /**
     * Connect to the robot
     * @param ip ip of the robot
     * @param id id of the robot
     * @param pw pw of the robot
     */
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
            System.out.println("Unreachable " + e.getMessage());
        }
    }

    /**
     * Make the robot trying to dock itself.
     */
    public void dock() {
        robot.dock();
    }

    /**
     * Make the robot's led on and off.
     */
    public void led() {
        robot.setLedEnabled(!robot.getRobotState().isLedEnabled());
    }

    /**
     * Make the robot move backward at MAKE SPEED.
     */
    public void moveBackward() {
        robot.setRightSpeed((short) Short.MIN_VALUE);
        robot.setLeftSpeed((short) Short.MIN_VALUE);
    }

    /**
     * Make the robot move forward at MAX SPEED.
     */
    public void moveForward() {
        robot.setRightSpeed((short) Short.MAX_VALUE);
        robot.setLeftSpeed((short) Short.MAX_VALUE);
    }

    /**
     * Make the robot stops.
     */
    public void neutral() {
        robot.setRightSpeed((short) 0);
        robot.setLeftSpeed((short) 0);
    }

    /**
     * Make the robot stand up.
     */
    public void standUp() {
        robot.standUp();
    }

    /**
     * Make the robot turn left.
     */
    public void turnLeft() {
        robot.setRightSpeed(Short.MAX_VALUE);
        robot.setLeftSpeed(Short.MIN_VALUE);
    }

    /**
     * Make the robot turn right.
     */
    public void turnRight() {
        robot.setLeftSpeed(Short.MAX_VALUE);
        robot.setRightSpeed(Short.MIN_VALUE);
    }

    /**
     * Make the robot undock.
     */
    public void undock() {
        robot.undock();
    }

    /**
     * Make the robot plays the audio.
     * @param lastAudio The audio the robot will play
     */
    public void sendAudio(byte[] lastAudio) {
        robot.sendAudio(lastAudio);
    }

    /**
     * Disconnect the robot.
     */
    public void disconnect() {
        if (robot.isConnected()) {
            robot.disconnect();
        }
    }
}//end WrkRobot
