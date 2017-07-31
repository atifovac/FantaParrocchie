package it.torneodelleparrocchie.fantacalcio.entities;

import javax.persistence.*;

@Entity
@Table(name = "giornata")
@NamedQueries({
        @NamedQuery(name = "Day.findMaxByNumeroGiornata",
                query = "select d from Day d where d.numeroGiornata=(" +
                        "select max(md.numeroGiornata) from Day md)"),
        @NamedQuery(name = "Day.findMinNumeroGiornataCalcolabile",
                query = "select d from Day d where d.numeroGiornata=(" +
                        "  select min(md.numeroGiornata) from Day md where md.calcolabile=true)")
})
public class Day {

    @Id
    @Column(name = "numero_giornata")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numeroGiornata;

    @Column
    private Boolean calcolabile = false;

    @Column
    private Boolean inseribile = true;

    public int getNumeroGiornata() {
        return numeroGiornata;
    }

    public void setNumeroGiornata(int numeroGiornata) {
        this.numeroGiornata = numeroGiornata;
    }

    public Boolean isCalcolabile() {
        return calcolabile;
    }

    public void setCalcolabile(Boolean calcolabile) {
        this.calcolabile = calcolabile;
    }

    public Boolean isInseribile() {
        return inseribile;
    }

    public void setInseribile(Boolean inseribile) {
        this.inseribile = inseribile;
    }

    @Override
    public String toString() {
        return "Day{" +
                "numeroGiornata=" + numeroGiornata +
                ", calcolabile=" + calcolabile +
                ", inseribile=" + inseribile +
                '}';
    }
}
