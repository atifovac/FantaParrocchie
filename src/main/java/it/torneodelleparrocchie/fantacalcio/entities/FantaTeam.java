package it.torneodelleparrocchie.fantacalcio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "fantasquadra")
@NamedQueries(
        @NamedQuery(name = "FantaTeam findByPresident",
                query = "select ft from FantaTeam ft where ft.president=:user"
        )
)
public class FantaTeam implements Serializable {

    @Id
    @Column(name = "nome")
    private String name;

    @OneToOne
    private User president;

    @Column(name = "fanta_soldi")
    private Long fantaMoney;

    @OneToMany
    @Column(name = "rosa")
    private List<Player> roster;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getPresident() {
        return president;
    }

    public void setPresident(User president) {
        this.president = president;
    }

    public Long getFantaMoney() {
        return fantaMoney;
    }

    public void setFantaMoney(Long fantaMoney) {
        this.fantaMoney = fantaMoney;
    }

    public List<Player> getRoster() {
        return roster;
    }

    public void setRoster(List<Player> roster) {
        this.roster = roster;
    }

    @Override
    public String toString() {
        return "FantaTeam{" +
                "name='" + name + '\'' +
                ", president='" + president + '\'' +
                ", fantaMoney=" + fantaMoney +
                ", roster=" + roster +
                '}';
    }
}
