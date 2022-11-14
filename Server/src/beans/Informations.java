package beans;

import java.util.Date;


/**
 * @author eggera
 * @version 1.0
 * @created 11-nov.-2022 14:00:55
 */
public class Informations {

    private int idInformations;
    
    private Date date;
    
    private double temperature;
    
    private double humidity;
    
    private User fk_user;
    
	public Informations(){

	}

	public void finalize() throws Throwable {

	}
}//end Informations