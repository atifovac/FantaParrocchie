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
        @NamedQuery(name = "Player.getByNameAndSurnameAndRosterRoleAndRealTeam",
                query = "select p from Player p " +
                        "where p.name=:name and p.surname=:surname and p.rosterRole=:role and p.realTeam=:realTeam"),
        @NamedQuery(name = "Player.getByNameAndSurnameAndFormationRoleAndRealTeam",
                query = "select p from Player p " +
                        "where p.name=:name and p.surname=:surname and p.formationRole=:role and p.realTeam=:realTeam")
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

    @Column(name = "ruolo_formazione")
    private String formationRole;

    @Column(name = "ruolo_rosa")
    private String rosterRole;

    @Column(name = "squadra_reale")
    private String realTeam;

    @Column(name = "quotazione")
    private Long value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty())
            this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname != null && !surname.isEmpty())
            this.surname = surname;
    }

    public String getRosterRole() {
        return rosterRole;
    }

    public void setRosterRole(String rosterRole) {
        this.rosterRole = rosterRole;
    }

    public String getFormationRole() {
        return formationRole;
    }

    public void setFormationRole(String formationRole) {
        this.formationRole = formationRole;
    }

    public String getRealTeam() {
        return realTeam;
    }

    public void setRealTeam(String realTeam) {
        if (realTeam != null && !realTeam.isEmpty())
            this.realTeam = realTeam;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", formationRole='" + formationRole + '\'' +
                ", rosterRole='" + rosterRole + '\'' +
                ", realTeam='" + realTeam + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
