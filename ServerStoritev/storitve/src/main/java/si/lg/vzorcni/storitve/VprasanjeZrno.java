package si.lg.vzorcni.storitve;

import si.lg.vzorcni.entitete.Tag;
import si.lg.vzorcni.entitete.Vprasanje;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class VprasanjeZrno {
    @PersistenceContext(name = "vzorcni-projekt")
    private EntityManager entityManager;

    public Vprasanje pridobiVprasanje(Integer id) {
        return entityManager.createNamedQuery("Vprasanje.getVprasanjeById", Vprasanje.class).setParameter("id", id).getSingleResult();
    }

    public List<Vprasanje> pridobiVprasanjeTAG(Tag tag) {
        return entityManager.createNamedQuery("Vprasanje.getVprasanjeByTag", Vprasanje.class).setParameter("tag", tag).getResultList();
    }
}
