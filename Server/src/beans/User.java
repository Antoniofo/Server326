package beans;

/**
 * @author eggera
 * @version 1.0
 * @created 11-nov.-2022 14:00:55
 */
public class User {

    private boolean isAdmin;

    private String username;

    private String password;

    private int idUsers;

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public User() {

    }

    public void finalize() throws Throwable {

    }
}//end User
