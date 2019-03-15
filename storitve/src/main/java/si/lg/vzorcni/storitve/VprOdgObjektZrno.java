package si.lg.vzorcni.storitve;

import si.lg.vzorcni.entitete.VprOdgObjekt;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class VprOdgObjektZrno {
    @PersistenceContext(name = "vzorcni-projekt")
    private EntityManager entityManager;

    public VprOdgObjekt getQuestionByID(Integer id) {
        return entityManager.find(VprOdgObjekt.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void addAnswer(Integer VprOdgId, String odgovor) {

    }
}
