import java.lang.*;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.print.event.PrintJobAdapter;

import java.util.NoSuchElementException;
// nome e cognome del candidato, matricola, data, numero della postazione

public class AgendaTester {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner sc = new Scanner(System.in);
        boolean active = true;
        while (active) {
            System.out.println("Cosa vuoi fare? I-Inserisci R- Rimuovi L-Leggi Q-Esci");
            String userInput = sc.nextLine();
            switch (userInput) {
                case "I":
                    // inserisce un impegno
                    String task = sc.nextLine();
                    Scanner taskScanner = new Scanner(task);
                    int prio = Integer.parseInt(taskScanner.next());
                    String text = taskScanner.nextLine();
                    text = text.substring(1, text.length());
                    agenda.insert(prio, text);
                    System.out.println("Hai inserito: \"" + text + "\" con priorita' " + prio);
                    taskScanner.close();
                    break;
                case "R":
                    System.out.println("Rimosso: " + agenda.removeMin());
                    break;
                case "L":
                    System.out.println("Il tuo impegno con priorita' massimo e':");
                    System.out.println(agenda.getMin());
                    break;
                case "Q":
                    active = false;
                    break;
                default:
                    System.out.println("Inserisci un comando valido");
                    break;
            }
        }
    }
}

class Agenda implements PriorityQueue {
    private Impegno[] impegno;
    private int size;

    // costruttori e metodi pubblici ......da completare ......
    public Agenda() {
        impegno = new Impegno[50];
        size = 0;
    }

    public void makeEmpty() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int key, Object value) {
        if (!(value instanceof String))
            throw new IllegalArgumentException();
        impegno[size] = new Impegno(key, (String) value);
        size++;
    }

    public Object removeMin() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        int minPos = 0;
        for (int i = 0; i < size; i++) {
            if (impegno[i].getPriority() < impegno[minPos].getPriority()) {
                minPos = i;
            }
        }
        Impegno removed = impegno[minPos];
        for (int i = 0; i < size; i++) {
            impegno[i] = impegno[i + 1];
        }
        size--;
        return removed.getMemo();
    }

    public Object getMin() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        int minPos = 0;
        for (int i = 0; i < size; i++) {
            if (impegno[i].getPriority() < impegno[minPos].getPriority()) {
                minPos = i;
            }
        }
        Impegno removed = impegno[minPos];
        return removed.getMemo();

    }

    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            str += impegno[i] + "\n";
        }
        return str;
    } // ..... da completare .........

    // campi di esemplare ..... da completare ......

    /*
     * classe privata Impegno: rappresenta gli elementi della classe Agenda ed
     * e` costituita da coppie "chiave valore" in cui il campo chiave e` di
     * tipo int e rappresenta la priorita` dell'impegno, e il campo valore e`
     * una stringa contenente un promemoria dell'impegno. Si considerano 4
     * livelli di priorita`, numerati da 0 a 3. Conseguentemente il campo
     * chiave puo` assumere valori solo in questo intervallo, dove il valore 0
     * significa "massima priorita`" e il valore 3 significa "minima priorita`"
     */
    private class Impegno // non modificare!!
    {
        public Impegno(int priority, String memo) {
            if (priority > 3 || priority < 0)
                throw new IllegalArgumentException();
            this.priority = priority;
            this.memo = memo;
        }

        // metodi (pubblici) di accesso
        public int getPriority() {
            return priority;
        }

        public Object getMemo() {
            return memo;
        }

        // metodo toString sovrascritto
        public String toString() {
            return priority + " " + memo;
        }

        // campi di esemplare (privati) della classe Impegno
        private int priority; // priorita` dell'impegno (da 0 a 3)
        private String memo; // promemoria dell'impegno
    }
}

/*
 * Interfaccia PriorityQueue (non modificare!!).
 * - Questo tipo di dato astratto definisce un contenitore di coppie
 * "chiave valore", dove il campo chiave e` un numero in formato int che
 * specifica il livello di priorita` della coppia mentre il campo valore
 * rappresenta il dato della coppia.
 * - Si assume che date due chiavi k1 e k2 tali che k1 < k2, allora k1 ha
 * priorita` piu` alta di k2.
 * - Naturalmente possono essere presenti nel contenitore coppie diverse con
 * chiavi uguali, cioe` con uguale priorita`
 */
interface PriorityQueue // non modificare!!
{ /*
   * svuota la coda di priorita`
   */
    void makeEmpty();

    /*
     * restituisce true se la coda e' vuota, false altrimenti
     */
    boolean isEmpty();

    /*
     * Metodo di inserimento
     * Inserisce la coppia "chiave valore" nella coda di priorita`. Notare che
     * la coda di priorita` puo` contenere piu` coppie con la stessa chiave.
     * Questo perche` il campo chiave non serve ad identificare univocamente
     * un elemento (come nel caso di un dizionario), ma serve invece a definire
     * la priorita` di un elemento. E` ovvio che piu` elementi possono avere
     * la stessa priorita`.
     */
    void insert(int key, Object value);

    /*
     * Metodo di rimozione
     * Rimuove dalla coda la coppia con chiave minima, e restituisce un
     * riferimento al suo campo value. Se sono presenti piu` coppie con chiave
     * minima, effettua la rimozione di una qualsiasi delle coppie con chiave
     * minima (ad es. la prima coppia con chiave minima che e` stata trovata)
     * Lancia EmptyQueueException se la coda di priorita` e` vuota
     */
    Object removeMin() throws EmptyQueueException;

    /*
     * Metodo di ispezione
     * Restituisce un riferimento al campo value della coppia con chiave minima
     * (ma *non* rimuove la coppia dalla coda). Se sono presenti piu` coppie
     * con chiave minima, restituisce il campo value di una qualsiasi delle
     * coppie con chiave minima (ad esempio la prima coppia con chiave minima
     * che e` stata trovata). Lancia EmptyQueueException se la coda e` vuota
     */
    Object getMin() throws EmptyQueueException;
}

/*
 * Eccezione lanciata dai metodi removeMin e getMin di PriorityQueue quando
 * la coda di priorita` e` vuota
 */
class EmptyQueueException extends RuntimeException {
}
