package wrk;

import beans.MyDBException;
import beans.Users;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class WrkDB {

    private String pu;
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    public ItfWrkDb refWrk;

    public WrkDB() {
        this.pu = pu;
        try {
            emf = Persistence.createEntityManagerFactory(pu);
            em = emf.createEntityManager();
            et = em.getTransaction();
        } catch (Exception ex) {


        }
    }

    /**
     * @throws Throwable Throwable
     */
    public void finalize()
            throws Throwable {

    }

    public void insertInfo(Info info) {

    }

    public void addUser(Users u) {
        try {
            et.begin();
            em.persist(u);
            et.commit();
        } catch (Exception ex) {
            et.rollback();
        }
    }

    public void connect(String PU) {

    }

    public void deleteUser(Users u) {

    }

    public void disconnect() {

    }

    public void modifyUser(Users user) {

    }


    public List<Users> readUsers(Class cl) {
        List<Users> listeUser;
        Query query = em.createQuery("SELECT e FROM "+ cl.getSimpleName()+ " e");
        listeUser = query.getResultList();
        return listeUser;


    }
}//end WrkDB