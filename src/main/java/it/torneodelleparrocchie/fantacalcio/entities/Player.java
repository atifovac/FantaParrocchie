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
        @NamedQuery(name = "Player.getByNameAndSurnameAndRoleAndRealTeam",
                query = "select p from Player p " +
                        "where p.name=:name and p.surname=:surname and p.role=:role and p.realTeam=:realTeam")
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role != null && !role.isEmpty())
            this.role = role;
    }

    public String getRealTeam() {
        return realTeam;
    }

    public void setRealTeam(String realTeam) {
        if (realTeam != null && !realTeam.isEmpty())
            this.realTeam = realTeam;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", realTeam='" + realTeam + '\'' +
                '}';
    }
}
