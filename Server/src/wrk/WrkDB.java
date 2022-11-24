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
     *
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
     *
     * @param u
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
     *
     * @param info
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
     *
     * @param u
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
     *
     * @param username
     * @return
     */
    public Users readUser(String username) {
        Users user;
        Query query = em.createQuery("Select u from " + Users.class.getSimpleName() + " u where u.username like '" + username + "'");
        user = (Users) query.getSingleResult();
        return user;
    }

    public void disconnect() {
        em.close();
        emf.close();
    }

    /**
     *
     * @param user
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
     *
     * @param cl
     * @return
     * @throws MyDBException
     */
    public List<Users> readUsers(Class cl) throws MyDBException {
        List<Users> listeUser;
        Query query = em.createQuery("Select u from " + Users.class.getSimpleName() + " u");
        listeUser = query.getResultList();
        return listeUser;


    }

    /**
     *
     * @param username
     * @param pwd
     * @return
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
}//end WrkDB