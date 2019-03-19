package si.lg.vzorcni.storitve;

import si.lg.vzorcni.entitete.Odgovor;
import si.lg.vzorcni.entitete.Vprasanje;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class OdgovorZrno {
    @PersistenceContext(name = "vzorcni-projekt")
    private EntityManager entityManager;

    private Double pridobiPovprecje(List<Odgovor> list) {
        Double avg = 0.0;
        for (Odgovor os : qs) {
            avg += os.getAnswer();
        }

        return avg/qs.size();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Double dodajOdgovor(Vprasanje vpr, Integer odgovor) {
        Odgovor o = new Odgovor();
        o.setQuestion(vpr);
        o.setAnswer(odgovor);

        List<Odgovor> qs = entityManager.createNamedQuery("Odgovor.getByQuestion", Odgovor.class).setParameter("q_id", vpr.getId()).getResultList();

        return pridobiPovprecje(qs);
    }

    public Double pridobiVrednost(Vprasanje vpr) {
        List<Odgovor> qs = entityManager.createNamedQuery("Odgovor.getByQuestion", Odgovor.class).setParameter("q_id", vpr.getId()).getResultList();

        return pridobiPovprecje(qs);
    }
}
