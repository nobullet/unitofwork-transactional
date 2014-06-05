package dao;

import java.lang.reflect.ParameterizedType;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 * Very simple template class for DAO.
 *
 * @param <T> DAO type parameter.
 */
public abstract class Dao<T> {

    @Inject
    private Provider<EntityManager> entitiyManagerProvider;
    private volatile Class<T> genericClassReferenceCache;

    public T get(long id) {
        return getEntityManager().find(getDaoClass(), id);
    }

    public T merge(T entity) {
        if (entity == null) {
            throw new NullPointerException("Can't merge null.");
        }
        return getEntityManager().merge(entity);
    }

    public void persist(T entity) {
        if (entity == null) {
            throw new NullPointerException("Can't persist null.");
        }
        getEntityManager().persist(entity);
    }
    
    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    public EntityManager getEntityManager() {
        return entitiyManagerProvider.get();
    }

    @SuppressWarnings("unchecked")
    public final Class<T> getDaoClass() {
        Class<T> klass = genericClassReferenceCache;
        if (klass == null) {
            ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
            klass = (Class<T>) (parameterizedType.getActualTypeArguments()[0]);
            genericClassReferenceCache = klass;
        }
        return klass;
    }
}
