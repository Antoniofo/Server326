package ctrl;

import ihm.Ihm;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:21
 */
public interface ItfCtrlIhm {

    public void addUser(User user);

    public void modifyUser(User user);

    public void deleteUser(User user);

    public ArrayList<User> readUsers();

}
