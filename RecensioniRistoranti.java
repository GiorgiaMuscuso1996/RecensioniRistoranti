import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Ristorante {

    private String nome;
    private String indirizzo;
    private List<Recensione> recensioni;

    public Ristorante(String nome, String indirizzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.recensioni = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void aggiungiRecensione(Recensione recensione) {
        recensioni.add(recensione);
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public double getPunteggioMedio() {
        if (recensioni.isEmpty()) {
            return 0;
        }

        int sommaPunteggi = 0;
        for (Recensione recensione : recensioni) {
            sommaPunteggi += recensione.getPunteggio();
        }

        return (double) sommaPunteggi / recensioni.size();
    }
}

class Recensione {

    private int punteggio;
    private String testoDescrittivo;
    private Utente utente;

    public Recensione(int punteggio, String testoDescrittivo, Utente utente) {
        this.punteggio = punteggio;
        this.testoDescrittivo = testoDescrittivo;
        this.utente = utente;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public String getTestoDescrittivo() {
        return testoDescrittivo;
    }

}

class Utente {

    private String nomeUtente;
    private List<Recensione> recensioniScritte;
    private List<Ristorante> ristorantiPreferiti;

    public Utente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
        this.recensioniScritte = new ArrayList<>();
        this.ristorantiPreferiti = new ArrayList<>();
    }

    public void scriviRecensione(Ristorante ristorante, int punteggio, String testoDescrittivo) {
        Recensione recensione = new Recensione(punteggio, testoDescrittivo, this);
        ristorante.aggiungiRecensione(recensione);
        recensioniScritte.add(recensione);
    }

    public List<Recensione> getRecensioniScritte() {
        return recensioniScritte;
    }

    public void aggiungiRistorantePreferito(Ristorante ristorante) {
        ristorantiPreferiti.add(ristorante);
    }

    public List<Ristorante> getRistorantiPreferiti() {
        return ristorantiPreferiti;
    }
}

public class RecensioniRistoranti {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        Ristorante r1 = new Ristorante("Ristorante 1", "Indirizzo 1");
        Ristorante r2 = new Ristorante("Ristorante 2", "Indirizzo 2");

        Utente u1 = new Utente("Utente 1");
        Utente u2 = new Utente("Utente 2");

        u1.scriviRecensione(r1, 4, "Buono");
        u2.scriviRecensione(r2, 3, "Mediocre");

        u2.aggiungiRistorantePreferito(r1);

        // recensioni di R1
        List<Recensione> recensioniR1 = r1.getRecensioni();
        for (Recensione recensione : recensioniR1) {
            System.out.println("Recensioni di Ristorante 1: " + recensione.getPunteggio() + " "
                    + recensione.getTestoDescrittivo());
        }

        // recensioni di R2
        List<Recensione> recensioniR2 = r2.getRecensioni();
        for (Recensione recensione : recensioniR2) {
            System.out.println("Recensioni di Ristorante 2: " + recensione.getPunteggio() + " "
                    + recensione.getTestoDescrittivo());
        }

        // punteggio medio di R1
        double punteggioMedioR1 = r1.getPunteggioMedio();
        System.out.println("Punteggio medio di Ristorante 1: " + punteggioMedioR1);

        // ristoranti preferiti di U2
        List<Ristorante> ristorantiPreferitiU2 = u2.getRistorantiPreferiti();
        for (Ristorante ristorante : ristorantiPreferitiU2) {
            System.out.println("Lista dei ristoranti preferiti di Utente 2: " + ristorante.getNome());
        }
    }
}
