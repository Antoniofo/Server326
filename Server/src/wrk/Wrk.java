package wrk;

import beans.Users;
import ctrl.ItfCtrlWrk;

import java.util.ArrayList;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class Wrk implements ItfWrkRobot, ItfWrkClient, ItfWrkPhidget {

	public WrkDB wrkDb;	
	public WrkUDP wrkUDP;
	public WrkServer wrkServer;
	public WrkRobot wrkRobot;
	public ItfCtrlWrk refCtrl;
	public WrkPhidget wrkPhidget;

	public Wrk(){

	}

	/**
	 * 
	 * @exception Throwable Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

    @Override
    public void sendImage(Object frame) {
    }

    @Override
    public void register(String value) {

    }

    @Override
    public void checkLogin(String value) {
    }

    @Override
    public void doRobotAction(String value) {
    }

    @Override
    public void upgradeUser(String value) {
    }
    
    public void addUser(){
                
    }
    
    public void modifyUser(){
        
    }

    public void deleteUser(){
        
    }
    
    public ArrayList<Users> readUsers(){

        
    }
    
    @Override
    public void receiveTemperature(double temperature) {
    }

    @Override
    public void sendImage(MBFImage frame) {

    }
}//end Wrk