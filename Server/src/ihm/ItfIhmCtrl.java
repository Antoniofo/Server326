package ihm;


import beans.Users;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:38:45
 */
public interface ItfIhmCtrl {

	public void quit();

	public void startIhm();

    void connectUser(Users u);
}