
// nome e cognome del candidato, matricola, data,       numero postazione

import java.lang.*;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class StudentiTester {
    public static void main(String[] args) {
        if (args.length == 2) {

            Studenti studenti1 = new Studenti();
            Studenti studenti2 = new Studenti();

            Scanner sc = null;
            try {
                FileReader fr = new FileReader(args[0]);
                sc = new Scanner(fr);
            } catch (FileNotFoundException e) {
                System.out.println("inserisci il nome del file giusto!");
            }
            while (sc.hasNextLine()) {
                Scanner riga = new Scanner(sc.nextLine());
                long matricola = Long.parseLong(riga.next());
                riga.next();
                String nome = "";
                while (riga.hasNext()) {
                    nome += riga.next() + " ";
                }
                studenti1.insert(matricola, nome);
            }
            Scanner input = new Scanner(System.in);
            Long cercaMatr;
            Boolean active = true;

            while (active) {
                System.out.println("Inserisci il numero matricola da cercare!");
                String scelta = input.next();
                if (scelta.equalsIgnoreCase("Q")) {
                    active = false;
                }
                cercaMatr = Long.parseLong(input.next());
                studenti2.insert(cercaMatr, studenti1.find(cercaMatr));
                studenti1.remove(cercaMatr);
            }
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(args[1]);
            } catch (FileNotFoundException e) {
            }
            pw.print(studenti2);

        } else {
            System.out.println("Esegui il programma mettendo file1 e file2");
        }
    }
}

class Studenti implements Dictionary {
    private Pair[] pair;
    private int size;

    public Studenti() {
        pair = new Pair[10];
        makeEmpty();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        size = 0;
    }

    private int binSearch(Comparable key) {
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (((Comparable) pair[mid].getMatr()).equals(key)) {
                return mid;
            } else if (key.compareTo(pair[mid].getMatr()) < 0) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    /*
     * Rimuove dal dizionario l'elemento specificato dalla chiave key
     * Se la chiave non esiste viene lanciata DictionaryItemNotFoundException
     */
    public void remove(Comparable key) {
        if (key == null)
            throw new DictionaryItemNotFoundException();
        int i = binSearch(key);
        for (int j = i; j < size - 1; j++) {
            pair[j] = pair[j + 1];
        }
        size--;
    }

    /*
     * Inserisce un elemento nel dizionario. L'inserimento va sempre a buon fine.
     * Se la chiave non esiste la coppia key/value viene aggiunta al dizionario;
     * se la chiave esiste gia', il valore ad essa associato viene sovrascritto
     * col nuovo valore; se key e` null viene lanciata IllegalArgumentException
     */
    public void insert(Comparable key, Object value) {
        if (!(value instanceof Object))
            throw new IllegalArgumentException();
        try {
            remove(key);
        } catch (DictionaryItemNotFoundException e) {
        }
        int i = size;
        while (i > 0 && pair[i - 1].getMatr() > (long) key) {
            pair[i] = pair[i - 1];
            i--;
        }
        pair[i] = new Pair((Long) key, (String) value);
        size++;

    }

    /*
     * Cerca nel dizionario l'elemento specificato dalla chiave key
     * La ricerca per chiave restituisce soltanto il valore ad essa associato
     * Se la chiave non esiste viene lanciata DictionaryItemNotFoundException
     */
    public Object find(Comparable key) {
        if (!(key instanceof Comparable))
            throw new IllegalArgumentException();
        return pair[binSearch(key)].getName();
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            str += pair[i] + "\n";
        }
        return str;
    }

    // campi di esemplare ..... da completare ......

    // classe privata Pair: non modificare!!
    private class Pair {
        public Pair(Long matr, String name) {
            this.matr = matr;
            this.name = name;
        }

        public long getMatr() {
            return matr;
        }

        public String getName() {
            return name;
        }

        /*
         * Restituisce una stringa contenente
         * - il numero di matricola, (numero long contenuto in "matr")
         * - un carattere di separazione ( : )
         * - il nome (stringa di una o piu` parole contenuta in "name")
         */
        public String toString() {
            return matr + " : " + name;
        }

        // campi di esemplare
        private long matr;
        private String name;
    }
}

interface Dictionary {
    /*
     * verifica se il dizionario contiene almeno una coppia chiave/valore
     */
    boolean isEmpty();

    /*
     * svuota il dizionario
     */
    void makeEmpty();

    /*
     * Inserisce un elemento nel dizionario. L'inserimento va sempre a buon fine.
     * Se la chiave non esiste la coppia key/value viene aggiunta al dizionario;
     * se la chiave esiste gia', il valore ad essa associato viene sovrascritto
     * col nuovo valore; se key e` null viene lanciata IllegalArgumentException
     */
    void insert(Comparable key, Object value);

    /*
     * Rimuove dal dizionario l'elemento specificato dalla chiave key
     * Se la chiave non esiste viene lanciata DictionaryItemNotFoundException
     */
    void remove(Comparable key);

    /*
     * Cerca nel dizionario l'elemento specificato dalla chiave key
     * La ricerca per chiave restituisce soltanto il valore ad essa associato
     * Se la chiave non esiste viene lanciata DictionaryItemNotFoundException
     */
    Object find(Comparable key);
}

class DictionaryItemNotFoundException extends RuntimeException {
}
