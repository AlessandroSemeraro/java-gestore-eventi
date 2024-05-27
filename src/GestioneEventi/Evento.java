package GestioneEventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Evento {

    //Attributi
    private String titolo;
    public LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    //Costruttore
    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalArgumentException {

        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Inserisci una data valida!");
        }

        if (postiTotali <= 0) {
            throw new IllegalArgumentException("Il numero di posti deve essere maggiore di zero!");
        }

        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }

    //Getter e setter
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    //Metodi
    public void prenota(int numeroPrenotazioni) throws IllegalArgumentException {

        try {
            if (data.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La data dell'evento non può essere passata!");
            }
            //controllo di non aver inserito numero valido
            if (numeroPrenotazioni == 0) {
                throw new IllegalArgumentException("Devi prenotare almeno un posto!");
            }
            if (numeroPrenotazioni <= 0) {
                throw new IllegalArgumentException("Inserisci un numero di disdette valido!");
            }

            if (numeroPrenotazioni > postiTotali - postiPrenotati) {
                throw new IllegalArgumentException("Non ci sono abbastanza posti disponibili per questo evento!");
            }

            postiPrenotati += numeroPrenotazioni;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Devi inserire un numero valido per le prenotazioni.");
        }
    }

    public int[] disdici(int numeroDisdette) throws IllegalArgumentException {
        try {
            if (data.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La data dell'evento non può essere passata!");
            }
            if (numeroDisdette <= 0) {
                throw new IllegalArgumentException("Devi disdire almeno una prenotazione!");
            }

            if (numeroDisdette > postiPrenotati) {
                throw new IllegalArgumentException("Non ci sono abbastanza prenotazioni da disdire!");
            }
            //rimuovi posti
            postiPrenotati -= numeroDisdette;

            return new int[]{postiPrenotati, postiTotali - postiPrenotati};
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Devi inserire un numero valido per le disdette!");
        }
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = data.format(formatter);

        return String.format("Evento: %s - Data: %s", titolo, formattedDate);
    }
}



