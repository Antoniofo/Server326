package wrk;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class WrkRobot extends Thread implements ItfRobotWrk {

    private boolean runing;
    public ItfWrkRobot refWrk;

    public WrkRobot() {

    }

    /**
     *
     * @exception Throwable Throwable
     */
    public void finalize()
            throws Throwable {

    }

    public void headUp() {

    }

    public void headNeutral() {

    }

    public void headDown() {

    }
    
    public void onBatteryReceived(byte battery){
        
    }

    public void onAudioReceived(byte[] audio) {

    }

    public void onImageReceived(byte[] image) {

    }

    public void connect() {

    }

    public void dock() {

    }

    public void led() {

    }

    public void moveBackward() {

    }

    public void moveForward() {

    }

    public void neutral() {

    }

    public void standUp() {

    }

    public void turnLeft() {

    }

    public void turnRight() {

    }

    public void undock() {

    }
}//end WrkRobot
