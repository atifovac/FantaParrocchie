package it.torneodelleparrocchie.fantacalcio.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "fantasquadra")
@NamedQueries({
        @NamedQuery(name = "FantaTeam.findByPresident",
                query = "select ft from FantaTeam ft where ft.president=:president"),
        @NamedQuery(name = "FantaTeam.findByName",
                query = "select ft from FantaTeam ft where ft.name=:name")
})
public class FantaTeam implements Serializable {

    @Id
    @Column(name = "nome")
    @Pattern(regexp = "[a-zA-Z0-9_ .\\-]+",
            message = "Nome squadra non valido, utilizzati caratteri non accettati")
    @Length(max = 50, message = "Nome squadra non valido, utilizzati troppi caratteri")
    private String name;

    @OneToOne
    @NotNull
    private User president;

    @Column(name = "fanta_soldi")
    @Min(value = 0,
            message = "Non puoi scendere sotto i 0 crediti")
    private Integer fantaMoney;

    @OneToMany
    @Column(name = "rosa")
    @Size(max = 13, message = "Non puoi avere più di 13 giocatori [1P+5D+3C+3A+1E")
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

    public int getFantaMoney() {
        return fantaMoney;
    }

    public void setFantaMoney(int fantaMoney) {
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
