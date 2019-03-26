package si.lg.vzorcni.storitve;

import si.lg.vzorcni.entitete.Odgovor;
import si.lg.vzorcni.entitete.OdgovorDTO;
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
        for (Odgovor os : list) {
            avg += os.getValue();
        }

        return avg/list.size();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Double dodajOdgovor(Vprasanje vpr, Integer odgovor) {
        Odgovor o = new Odgovor();
        o.setQuestion(vpr);
        o.setValue(odgovor);

        try {
            entityManager.persist(o);
            entityManager.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Odgovor> qs = entityManager.createNamedQuery("Odgovor.getByQuestion", Odgovor.class).setParameter("q_id", vpr.getId()).getResultList();

        if (qs.isEmpty()) {
            return 0.0;
        }
        return pridobiPovprecje(qs);
    }

    public OdgovorDTO pridobiVrednost(Vprasanje vpr) {
        List<Odgovor> qs = entityManager.createNamedQuery("Odgovor.getByQuestion", Odgovor.class).setParameter("q_id", vpr.getId()).getResultList();
        OdgovorDTO dto = new OdgovorDTO(0.0, vpr);

        if (!qs.isEmpty()) {
            dto.setAvg(pridobiPovprecje(qs));
        }

        return dto;
    }
}
