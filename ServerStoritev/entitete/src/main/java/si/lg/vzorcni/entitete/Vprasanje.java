package si.lg.vzorcni.entitete;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "question")
@NamedQueries(value = {
        @NamedQuery(
                name = "Vprasanje.getVprasanjeById",
                query = "SELECT v FROM question v WHERE v.id = :vprasanje_id"),
        @NamedQuery(
                name = "Vprasanje.getVprasanjeByTag",
                query = "SELECT v FROM question v WHERE v.tag = :tag"),
        @NamedQuery(
                name = "Vprasanje.getAll",
                query = "SELECT v FROM question v")
})
public class Vprasanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public Vprasanje() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
