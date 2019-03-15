package si.lg.vzorcni.storitve;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.lg.vzorcni.entitete.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class TagZrno {
    @PersistenceContext(unitName = "vzorcni-projekt")
    private EntityManager entityManager;

    public List<Tag> getTags(QueryParameters queryParameters) {
        if (queryParameters != null) {
            return JPAUtils.queryEntities(entityManager, Tag.class, queryParameters);
        } else {
            return entityManager.createNamedQuery("Tag.getAll", Tag.class).getResultList();
        }
    }
}
