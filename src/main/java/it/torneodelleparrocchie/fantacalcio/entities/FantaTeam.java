package it.torneodelleparrocchie.fantacalcio.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fantasquadra")
public class FantaTeam implements Serializable {

    @Id
    @Column(name = "nome")
    private String name;

    @OneToOne
    private User president;

    @Column(name = "fanta_soldi")
    private Long fantaMoney;

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
        if (name.equalsIgnoreCase("svincolato")) {
            throw new IllegalStateException("The team \"" + name + "\" cannot have a president");
        }
        this.president = president;
    }

    public Long getFantaMoney() {
        return fantaMoney;
    }

    public void setFantaMoney(Long fantaMoney) {
        if (name.equalsIgnoreCase("svincolato")) {
            throw new IllegalStateException("The team \"" + name + "\" cannot have money");
        }
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
