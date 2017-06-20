package it.torneodelleparrocchie.fantacalcio.entities;
/**
 * Created by dsalvatore on 19/06/17.
 */

import javax.persistence.*;

@Entity
@Table(name = "giocatore")
@NamedQueries({
        @NamedQuery(name = "Player.getAllByRealTeam",
                query = "select p from Player p where p.realTeam=:realTeam"),
        @NamedQuery(name = "Player.getAllByFantaTeamName",
                query = "select p from Player p where p.fantaTeam.name=:fantaTeam")
})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "cognome")
    private String surname;


    @Column(name = "ruolo")
    private String role;

    @Column(name = "squadra_reale")
    private String realTeam;

    @ManyToOne
    @JoinColumn(name = "fanta_squadra", referencedColumnName = "nome")
    private FantaTeam fantaTeam;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRealTeam() {
        return realTeam;
    }

    public void setRealTeam(String realTeam) {
        this.realTeam = realTeam;
    }

    public FantaTeam getFantaTeam() {
        return fantaTeam;
    }

    public void setFantaTeam(FantaTeam fantaTeam) {
        this.fantaTeam = ((fantaTeam == null || "".equals(fantaTeam.getName())) ? new FantaTeam() : fantaTeam);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", realTeam='" + realTeam + '\'' +
                ", fantaTeam=" + fantaTeam +
                '}';
    }
}
