package si.lg.vzorcni.storitve;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.lg.vzorcni.entitete.Uporabnik;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class UporabnikZrno {
    @PersistenceContext(name = "vzorcni-projekt")
    private EntityManager entityManager;

    public List<Uporabnik> pridobiUporabnike(QueryParameters queryParameters) {
        if (queryParameters != null) {
            return JPAUtils.queryEntities(entityManager, Uporabnik.class, queryParameters);
        } else {
            return entityManager.createNamedQuery("Uporabnik.getAll", Uporabnik.class).getResultList();
        }
    }

    public Long pridobiUporabnikiCount(QueryParameters queryParameters) {
        return JPAUtils.queryEntitiesCount(entityManager, Uporabnik.class, queryParameters);
    }

    public Uporabnik pridobiUporabnika(Integer id) {
        return entityManager.createNamedQuery("Uporabnik.getById", Uporabnik.class).setParameter("id", id).getSingleResult();
    }
}
