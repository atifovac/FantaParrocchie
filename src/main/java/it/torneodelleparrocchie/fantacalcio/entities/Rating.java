package it.torneodelleparrocchie.fantacalcio.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "votazione",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"giornata", "giocatore"})
        })
@NamedQueries({
        @NamedQuery(name = "Rating.findByGiornataAndGiocatore",
                query = "select ra from Rating ra where ra.giornata=:giornata and ra.giocatore=:giocatore"),
        @NamedQuery(name = "Rating.findByGiornata",
                query = "select ra from Rating ra where ra.giornata=:giornata")
})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "giornata")
    private Day giornata;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "giocatore")
    private Player giocatore;

    @Column
    private Float voto;

    public Long getId() {
        return id;
    }

    public Day getGiornata() {
        return giornata;
    }

    public void setGiornata(Day giornata) {
        this.giornata = giornata;
    }

    public Player getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Player giocatore) {
        this.giocatore = giocatore;
    }

    public Float getVoto() {
        return voto;
    }

    public void setVoto(Float voto) {
        this.voto = voto;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", giornata=" + giornata +
                ", giocatore=" + giocatore +
                ", voto=" + voto +
                '}';
    }
}
