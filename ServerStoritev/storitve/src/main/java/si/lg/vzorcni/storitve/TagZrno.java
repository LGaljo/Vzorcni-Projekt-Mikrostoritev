package si.lg.vzorcni.storitve;

import si.lg.vzorcni.entitete.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class TagZrno {
    @PersistenceContext(unitName = "vzorcni-projekt")
    private EntityManager entityManager;

    public Tag pridobiTag(Integer tagID) {
        return entityManager.createNamedQuery("Tag.getByID", Tag.class).setParameter("id", tagID).getSingleResult();
    }
}
