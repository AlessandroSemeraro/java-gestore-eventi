package GestioneEventi;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Aggiungi un nuovo evento!");

        System.out.println("Inserisci il titolo dell'Evento: ");
        String titolo = scan.nextLine();

        int postiTotali = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Inserisci il numero di posti totali:");
            String postiTotaliInput = scan.nextLine();

            if (postiTotaliInput.isEmpty()) {
                System.out.println("Devi inserire un numero valido di posti totali!");
            } else {
                try {
                    postiTotali = Integer.parseInt(postiTotaliInput);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Devi inserire un numero valido di posti totali!");
                }
            }
        }

        Date data = null;
        validInput = false;

        while (!validInput) {
            System.out.println("Inserisci la data dell'Evento (formato: yyyy-MM-dd):");
            String dataString = scan.nextLine();

            if (dataString.isEmpty()) {
                System.out.println("Devi inserire una data valida!");
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);

                try {
                    Date parsedDate = dateFormat.parse(dataString);
                    if (parsedDate.before(new Date())) {
                        System.out.println("La data dell'evento non è corretta!");
                    } else {
                        data = parsedDate;
                        validInput = true;
                    }
                } catch (java.text.ParseException e) {
                    System.out.println("Il formato data non valido!");
                }
            }
        }

        Evento evento = null;
        try {
            evento = new Evento(titolo, LocalDate.now(),postiTotali);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        if (evento != null) {
            System.out.println("Vuoi prenotare un posto? (Sì/No)");
            String rispostaPrenotazione = scan.nextLine();

            if (rispostaPrenotazione.equalsIgnoreCase("Si")) {
                System.out.println("Quanti posti vuoi prenotare?");
                int postiPrenotati;

                try {
                    postiPrenotati = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Devi inserire un numero valido di prenotazioni.");
                    return;
                }

                try {
                    evento.prenota(postiPrenotati);
                    System.out.println("Hai prenotato: " + postiPrenotati + " posto/i.Sono disponibili ancora: " + (postiTotali - postiPrenotati) + " posto/i." );
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Nessuna prenotazione effettuata.");
            }

            System.out.println("Vuoi disdire un posto? (Sì/No)");
            String rispostaDisdetta = scan.nextLine();

            if (rispostaDisdetta.equalsIgnoreCase("Si")){
                System.out.println("Quanti posti vuoi disdire?");
                int postiDisdetti;

                try {
                    postiDisdetti = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Devi inserire un numero valido di prenotazioni.");
                    return;
                }

                try {
                    int[] result = evento.disdici(postiDisdetti);
                    int postiPrenotatiDopoDisdette = result[0];
                    int postiDisponibiliDopoDisdette = result[1];
                    System.out.println("Hai disdetto " + postiDisdetti + " posto/i. Sono disponibili ancora: " + postiDisponibiliDopoDisdette + " posto/i.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Nessuna disdetta è stata effettuata.");
            }
        }
        Concerto concerto1 = new Concerto("Prova concerto", LocalDate.parse("2025-12-20"), 500, LocalTime.of(21, 30), new BigDecimal("100.00"));
        System.out.println("Concerto 1: " + concerto1.toString());

        scan.close();
    }
}