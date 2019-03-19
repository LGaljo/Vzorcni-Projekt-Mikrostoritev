package si.lg.vzorcni.entitete;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "response")
@NamedQueries(value = {
        @NamedQuery(name = "si.lg.vzorcni.entitete.Odgovor.getAll", query = "SELECT r FROM response r ORDER BY r.date DESC")
})
public class Odgovor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Vprasanje question;
    private Date date;
    private Integer answer;

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

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}
