package dao;

import java.util.List;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import models.User;

/**
 * Simple dao for user.
 */
@Singleton
public class UserDao extends Dao<User> {

    /**
     * Returns user by name or null.
     * @param name Name.
     * @return User by name or null.
     */
    public User getByName(String name) {
        try {
            return getEntityManager()
                    .createNamedQuery("User.byName", getDaoClass())
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    /**
     * Return all users.
     * @return 
     */
    public List<User> getAllUsers() {
        return getEntityManager().createNamedQuery("User.all", getDaoClass()).getResultList();
    }
}
