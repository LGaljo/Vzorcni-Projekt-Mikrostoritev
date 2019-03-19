package si.lg.vzorcni.entitete;

import javax.persistence.*;

@Entity(name = "tag")
@NamedQueries(value = {
        @NamedQuery(name = "si.lg.vzorcni.entitete.Tag.getAll", query = "SELECT t FROM tag t")
})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Tag() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
