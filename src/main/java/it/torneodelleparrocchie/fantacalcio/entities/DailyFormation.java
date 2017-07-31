package it.torneodelleparrocchie.fantacalcio.entities;

import it.torneodelleparrocchie.fantacalcio.enums.FormationModuleEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "formazione_giornaliera",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"giornata", "fanta_squadra"})
        })
@NamedQueries({
        @NamedQuery(name = "DailyFormation.findByFantaSquadraAndGiornata",
                query = "select df from DailyFormation df " +
                        "where df.fantaSquadra=:fantaSquadra and df.giornata=:giornata"),
        @NamedQuery(name = "DailyFormation.findAllByFantaSquadra",
                query = "select df from DailyFormation df " +
                        "where df.fantaSquadra=:fantaSquadra"),
        @NamedQuery(name = "DailyFormation.findAllByGiornata",
                query = "select df from DailyFormation df " +
                        "where df.giornata=:giornata"),
        @NamedQuery(name = "DailyFormation.findLastByGiornataAndFantaSquadra",
                query = "select df from DailyFormation df " +
                        "where df.giornata.numeroGiornata=(" +
                        "   select max(ldf.giornata.numeroGiornata) from DailyFormation ldf " +
                        "   where ldf.giornata=:giornata) and df.fantaSquadra=:fantaSquadra")
})
public class DailyFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "fanta_squadra")
    private FantaTeam fantaSquadra;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "giornata")
    private Day giornata;

    @OneToMany
    @JoinColumn(name = "formazione")
    private List<Rating> formazione;

    @Column
    private Float punti;

    @Column(name = "modulo_formazione")
    @Enumerated(EnumType.STRING)
    private FormationModuleEnum formationModule;

    public DailyFormation() {
        formazione = new ArrayList<>();
        punti = 0.0F;
    }

    public Long getId() {
        return id;
    }

    public FantaTeam getFantaSquadra() {
        return fantaSquadra;
    }

    public void setFantaSquadra(FantaTeam fantaSquadra) {
        this.fantaSquadra = fantaSquadra;
    }

    public Day getGiornata() {
        return giornata;
    }

    public void setGiornata(Day giornata) {
        this.giornata = giornata;
    }

    public List<Rating> getFormazione() {
        return formazione;
    }

    public void setFormazione(List<Rating> formazione) {
        this.formazione = formazione;
    }

    public Float getPunti() {
        return punti;
    }

    public void setPunti(Float punti) {
        this.punti = punti;
    }

    public FormationModuleEnum getFormationModule() {
        return formationModule;
    }

    public void setFormationModule(FormationModuleEnum formationModule) {
        this.formationModule = formationModule;
    }

    @Override
    public String toString() {
        return "DailyFormation{" +
                "id=" + id +
                ", fantaSquadra=" + fantaSquadra +
                ", giornata=" + giornata +
                ", formazione=" + formazione +
                ", punti=" + punti +
                ", formationModule=" + formationModule +
                '}';
    }
}
