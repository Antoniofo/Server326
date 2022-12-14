package ctrl;

import beans.Users;
import ihm.Ihm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:21
 */
public interface ItfCtrlIhm {

    public void addUser(Users user);

    public void modifyUser(Users user);

    public void deleteUser(Users user);

    public List<Users> readUsers();

    void killThread();
}
