package ${artifactId}.base;

import com.avon.util.cluster.UtilThread;
import org.apache.felix.scr.annotations.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

/**
 * Implements all entityManager methods.
 *
 */
@Component(immediate = true)
public abstract class PersistenceService {

    public static final String PERSISTENCE_UNIT_NAME = "${rootArtifactId}";
    private EntityManagerFactory entityManagerFactory;


    public EntityManagerFactory getEntityManagerFactory() {
        return UtilThread.getClusterEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    protected EntityManager createEntityManager() {
        if (getEntityManagerFactory() == null) {
            throw new PersistenceException("Could not find " + PERSISTENCE_UNIT_NAME + " entity manager factory");
        }
        EntityManager em = getEntityManagerFactory().createEntityManager();
        if (em == null) {
            throw new PersistenceException("Could not create entity manager for " + PERSISTENCE_UNIT_NAME);
        }
        return em;
    }
}
