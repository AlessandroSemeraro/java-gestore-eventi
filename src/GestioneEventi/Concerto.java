package GestioneEventi;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {
    //Attributi
    private LocalTime ora;
    private BigDecimal prezzo;

    //Costruttore
    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, BigDecimal prezzo) throws IllegalArgumentException {
        super(titolo, data, postiTotali);

        if (ora == null) {
            throw new IllegalArgumentException("L'ora del concerto non può essere nulla!");
        }

        if (prezzo == null || prezzo.signum() <= 0) {
            throw new IllegalArgumentException("Il prezzo del concerto deve essere un valore positivo!");
        }

        this.ora = ora;
        this.prezzo = prezzo;
    }

    //Getter e Setter
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public String getDataFormattata() {
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return data.format(formatterData);
    }

    public String getOraFormattata() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return ora.format(dateTimeFormatter);
    }

    public String getPrezzoFormattato() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00€");
        return decimalFormat.format(prezzo);
    }

    //toString
    @Override
    public String toString() {
        return getDataFormattata() + " - " + getOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }
}


