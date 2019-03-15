package si.lg.vzorcni.entitete;

import javax.persistence.*;
import java.util.List;

@Entity(name = "qna")
public class VprOdgObjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question;

    @ElementCollection
    private List<String> answers;

    public VprOdgObjekt() {
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

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
