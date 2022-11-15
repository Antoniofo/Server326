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
public class WrkRobot extends Thread implements ItfWrkRobot {

    private Robot robot;
    private boolean running;
    private int pw;
    private int id;
    private MyRobot myRobot;
    private boolean lastConnected;
    public ItfWrkRobot refWrk;

    public WrkRobot() {
        super("Thread Etat Robot");
        robot = new Robot();


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

    public void connect(String ip, int id, int pw) {
        myRobot = new MyRobot(ip, id, pw);
        Thread t = new Thread(){
            @Override
            public void run() {
                try{
                    String ip = myRobot.getIp();
                    if (ip != null) {
                        robot.connect(ip, myRobot.getId(), myRobot.getPw());
                        if (!robot.isConnected()) {
                            System.out.println("err");
                        }
                    }
                } catch (UnreachableRobotException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    public void dock() {
        robot.dock();
    }

    public void led() {
        robot.setLedEnabled(!robot.getRobotState().isLedEnabled());
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
        robot.standUp();
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
    robot.getLastImage();
    }

    @Override
    public void sendAudio(byte[] lastAudio) {
        robot.sendAudio(lastAudio);
    }
}//end WrkRobot
