package si.lg.vzorcni.entitete;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "response")
@NamedQueries(value = {
        @NamedQuery(name = "Odgovor.getAll", query = "SELECT r FROM response r ORDER BY r.date DESC"),
        @NamedQuery(name = "Odgovor.getByQuestion", query = "SELECT r FROM response r WHERE r.question.id = :q_id ORDER BY r.date DESC")
})
public class Odgovor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Vprasanje question;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(columnDefinition = "int default 0")
    private Integer value;

    public Odgovor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vprasanje getQuestion() {
        return question;
    }

    public void setQuestion(Vprasanje question) {
        this.question = question;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
