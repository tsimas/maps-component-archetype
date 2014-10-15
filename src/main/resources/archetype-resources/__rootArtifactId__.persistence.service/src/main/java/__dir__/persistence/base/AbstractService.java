package ${artifactId}.base;


import org.apache.felix.scr.annotations.Component;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Implemets all CommonService methods. All services related to entities are implemented here
 *
 * @param <E> Entity
 * @param <K> Key
 */
@Component(immediate = true)
public abstract class AbstractService<E, K>  extends PersistenceService implements Service<E, K> {

    public Logger log = getLogger(getEntityClass());


    @Override
    public E findById(K id) {
        EntityManager em = createEntityManager();
        try {
            return em.find(getEntityClass(), id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public E getById(K id) throws EntityNotFoundException {
        E entity = null;
        EntityManager em = createEntityManager();
        try {
            entity = em.find(getEntityClass(), id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        if (entity == null) {
            throw new EntityNotFoundException("Entity " + getEntityClass().getName() + " with id " + id + " not found");
        }
        return entity;
    }

    @Override
    public Collection<E> findAll() {
        EntityManager em = createEntityManager();
        try {
            return em.createNamedQuery(getEntityClass().getSimpleName() + ".findAll", getEntityClass()).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public abstract Class<E> getEntityClass();
}
