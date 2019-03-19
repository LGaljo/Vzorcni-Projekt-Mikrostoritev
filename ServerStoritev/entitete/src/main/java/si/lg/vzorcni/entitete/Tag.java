package si.lg.vzorcni.entitete;

import javax.persistence.*;

@Entity(name = "tag")
@NamedQueries(value = {
        @NamedQuery(name = "Tag.getAll", query = "SELECT t FROM tag t"),
        @NamedQuery(name = "Tag.getByID", query = "SELECT t FROM tag t WHERE t.id = :id"),
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
