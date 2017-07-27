package it.torneodelleparrocchie.fantacalcio.entities;
/**
 * Created by dsalvatore on 19/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.enums.RealTeamEnum;
import it.torneodelleparrocchie.fantacalcio.enums.RosterRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "giocatore",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"nome", "cognome"})
        })
@NamedQueries({
        @NamedQuery(name = "Player.getAllByRealTeam",
                query = "select p from Player p where p.realTeam=:realTeam"),
        @NamedQuery(name = "Player.getByNameAndSurnameAndRosterRoleAndRealTeam",
                query = "select p from Player p " +
                        "where p.name=:name and p.surname=:surname and p.rosterRole=:role and p.realTeam=:realTeam"),
        @NamedQuery(name = "Player.getByNameAndSurnameAndFormationRoleAndRealTeam",
                query = "select p from Player p " +
                        "where p.name=:name and p.surname=:surname and p.formationRole=:role and p.realTeam=:realTeam"),
        @NamedQuery(name = "Player.getByNameAndSurname",
                query = "select p from Player p where p.name=:name and p.surname=:surname"),
        @NamedQuery(name = "Player.getByRealTeamAndRosterRole",
                query = "select p from Player p where p.realTeam=:realTeam and p.rosterRole=:rosterRole")
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
    @Enumerated(EnumType.STRING)
    private RosterRoleEnum rosterRole;

    @Column(name = "squadra_reale")
    @Enumerated(EnumType.STRING)
    private RealTeamEnum realTeam;

    @Column(name = "quotazione")
    private Integer value;

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

    public RosterRoleEnum getRosterRole() {
        return rosterRole;
    }

    public void setRosterRole(RosterRoleEnum rosterRole) {
        this.rosterRole = rosterRole;
    }

    public String getFormationRole() {
        return formationRole;
    }

    public void setFormationRole(String formationRole) {
        this.formationRole = formationRole;
    }

    @Enumerated(EnumType.STRING)
    public RealTeamEnum getRealTeam() {
        return realTeam;
    }

    public void setRealTeam(RealTeamEnum realTeam) {
        this.realTeam = realTeam;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
