import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class TagZrno {
    @PersistenceContext(unitName = "test")
    private EntityManager entityManager;

    public List<Tag> getTags(QueryParameters queryParameters) {
        if (queryParameters != null) {
            return JPAUtils.queryEntities(entityManager, Tag.class, queryParameters);
        } else {
            return entityManager.createNamedQuery("Tag.getAll", Tag.class).getResultList();
        }
    }
}
