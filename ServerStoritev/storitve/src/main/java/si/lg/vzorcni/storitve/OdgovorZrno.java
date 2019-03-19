package si.lg.vzorcni.storitve;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.lg.vzorcni.entitete.Vprasanje;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class OdgovorZrno {
    @PersistenceContext(name = "vzorcni-projekt")
    private EntityManager entityManager;

    @Transactional(Transactional.TxType.REQUIRED)
    public void addAnswer(Integer VprOdgId, String odgovor) {

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public List<Vprasanje> pridobiVprasanjePoTagu(QueryParameters queryParameters) {
        return JPAUtils.queryEntities(entityManager, Vprasanje.class, queryParameters);
    }
}
