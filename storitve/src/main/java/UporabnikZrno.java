import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class UporabnikZrno {
    @PersistenceContext(name = "test")
    private EntityManager entityManager;

    public List<Uporabnik> pridobiUporabnike(QueryParameters queryParameters) {
        if (queryParameters != null) {
            return JPAUtils.queryEntities(entityManager, Uporabnik.class, queryParameters);
        } else {
            return entityManager.createNamedQuery("Uporabnik.getAll").getResultList();
        }
    }
}
