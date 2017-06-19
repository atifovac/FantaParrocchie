package it.torneodelleparrocchie.fantacalcio.entities;
/**
 * Created by dsalvatore on 19/06/17.
 */

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "giocatore")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "cognome")
    private String surname;

    @Column(name = "squadra_reale")
    private String realTeam;

    @Column(name = "squadra_fanta")
    private String fantaTeam;

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

    public String getFantaTeam() {
        return fantaTeam;
    }

    public void setFantaTeam(String fantaTeam) {
        this.fantaTeam = (("".equals(fantaTeam) || fantaTeam == null) ? "svincolato" : fantaTeam);
    }
}
