package ${artifactId}.base;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

/**
 * This interface defines methods that can be implemented all entity type
 *
 * @param <E> Entity type
 * @param <K> Key type
 */
public interface Service<E, K> {

    public E findById(K id);

    public E getById(K id) throws EntityNotFoundException;

    public Collection<E> findAll();



}
