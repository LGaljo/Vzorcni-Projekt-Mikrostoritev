package si.lg.vzorcni.entitete;

import javax.persistence.*;

@Entity(name = "uporabnik")
@NamedQueries(value = {
        @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM uporabnik u"),
        @NamedQuery(name = "Uporabnik.getById", query = "SELECT u FROM uporabnik u WHERE u.id = :id")
})
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Uporabnik() {
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
