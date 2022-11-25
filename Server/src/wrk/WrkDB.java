package wrk;

import beans.Informations;
import beans.Users;
import app.exceptions.MyDBException;
import app.helpers.SystemLib;

import javax.persistence.*;
import java.util.List;


/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class WrkDB {

    private String pu = "PU";
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    /**
     * Constructor of WrkDB.
     */
    public WrkDB() {
        try {
            emf = Persistence.createEntityManagerFactory(pu);
            em = emf.createEntityManager();
            et = em.getTransaction();
        } catch (Exception ex) {


        }
    }

    /**
     * Add a user to the Database.
     * @param u The User to add.
     * @throws MyDBException
     */
    public void addUser(Users u) throws MyDBException {
        try {
            et.begin();
            em.persist(u);
            et.commit();
        } catch (Exception ex) {
            et.rollback();
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());
        }
    }

    /**
     * Add information to Database.
     * @param info The information to add
     * @throws MyDBException
     */
    public void addInfo(Informations info) throws MyDBException {
        try {
            et.begin();
            em.persist(info);
            et.commit();

        } catch (Exception e) {
            et.rollback();
            throw new MyDBException(SystemLib.getFullMethodName(), e.getMessage());
        }

    }

    /**
     * Deletes a user from the Database.
     * @param u The user to delete
     * @throws MyDBException
     */
    public void deleteUser(Users u) throws MyDBException {
        try {
            et.begin();
            em.remove(u);
            et.commit();
        } catch (Exception ex) {
            et.rollback();
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());
        }
    }

    /**
     * Get the user from the Database with his username.
     * @param username The username of the user.
     * @return The user from the Database.
     */
    public Users readUser(String username) {
        Users user;
        Query query = em.createQuery("Select u from " + Users.class.getSimpleName() + " u where u.username like '" + username + "'");
        user = (Users) query.getSingleResult();
        return user;
    }

    /**
     * Disconnect from the Database.
     */
    public void disconnect() {
        em.close();
        emf.close();
    }

    /**
     * Modifiy a user in the Database.
     * @param user The user to modify
     * @throws MyDBException
     */
    public void modifyUser(Users user) throws MyDBException {
        try {
            et.begin();
            em.merge(user);
            et.commit();

        } catch (Exception ex) {
            et.rollback();
            throw new MyDBException(SystemLib.getFullMethodName(), ex.getMessage());

        }
    }

    /**
     * Get all users in the Database.
     * @param cl The class to get all users
     * @return All users in the Database.
     * @throws MyDBException
     */
    public List<Users> readUsers(Class cl) throws MyDBException {
        List<Users> listeUser;
        Query query = em.createQuery("Select u from " + Users.class.getSimpleName() + " u");
        listeUser = query.getResultList();
        return listeUser;


    }

    /**
     * Get a user from the Database using username and password
     * @param username The username of the user
     * @param pwd The password of the user
     * @return The user from the Database
     */
    public Users readUser(String username, String pwd) {
        Users user;
        Query query = em.createQuery("Select u from " + Users.class.getSimpleName() + " u where u.username like '" + username + "' and u.password like '" + pwd + "'");
        try {
            user = (Users) query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }

        return user;
    }
}