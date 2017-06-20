package it.torneodelleparrocchie.fantacalcio.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fantasquadra")
public class FantaTeam implements Serializable {

    public FantaTeam() {
        this.name = "svincolato";
    }

    @Id
    @Column(name = "nome")
    private String name;

    @Column(name = "presidente")
    private String president;

    @Column(name = "fanta_soldi")
    private Long fantaMoney;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public Long getFantaMoney() {
        return fantaMoney;
    }

    public void setFantaMoney(Long fantaMoney) {
        this.fantaMoney = fantaMoney;
    }

    @Override
    public String toString() {
        return "FantaTeam{" +
                "name='" + name + '\'' +
                ", president='" + president + '\'' +
                ", fantaMoney=" + fantaMoney +
                '}';
    }
}
