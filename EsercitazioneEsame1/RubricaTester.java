// Giovanni Sgaravatto, matricola, data, numero della postazione

import java.lang.*;
import java.security.Key;
import java.text.RuleBasedCollator;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class RubricaTester {
    public static void main(String[] args) {

        // controllo che inserisco i due file da riga di commando
        if (args.length == 2) {

            // creo due oggetti rubrica
            Rubrica rubrica1 = new Rubrica();
            Rubrica rubrica2 = new Rubrica();

            // creo il file reader e il printwriter
            FileReader fileReader = null;
            PrintWriter printWriter = null;

            // attribuisco fileReader e printWriter ai due input da riga di comando
            try {
                fileReader = new FileReader(args[0]);
                printWriter = new PrintWriter(args[1]);
            } catch (FileNotFoundException e) {
                System.out.println("File non trovato " + e);
            }

            // fill rubrica1
            Scanner fileInputScanner = new Scanner(fileReader);
            while (fileInputScanner.hasNext()) {
                Scanner rowScanner = new Scanner(fileInputScanner.nextLine());
                String name = rowScanner.next();
                rowScanner.next(); // skippo il ':' del file perché c'è uno spazio
                long number = Long.parseLong(rowScanner.next());
                rubrica1.insert(name, number);
                System.out.println("Inserito " + name + " : " + number + " in rubrica 1");
            }
            // metto in output tutta rubrica1
            System.out.println(rubrica1.toString());
            fileInputScanner.close();

            // ricerca rimozione libera
            boolean active = true;
            Scanner userInput = new Scanner(System.in);
            while (active) {

                System.out.println(rubrica1.toString());
                System.out.println(rubrica2.toString());
                System.out.println("Che numero vuoi portare nella rubrica 2?");
                String input = userInput.next();
                if (input.equalsIgnoreCase("Q")) {
                    active = false;
                    break;
                } else {
                    long num = (Long) rubrica1.find(input);
                    rubrica2.insert(input, num);
                    System.out.println("Inserito " + input + " : " + num + " in rubrica 2");
                    rubrica1.remove(input);
                }

            }

            // scrittura su file
            printWriter.write(rubrica2.toString());
            userInput.close();
            printWriter.close();

        } else {
            System.out.println("Esegui il programma come Rubricatester file1 file2");
        }
    }
}

class Rubrica implements Map {
    private Pair arrayRubrica[];
    private int arraySize;

    public Rubrica() {
        arraySize = 0;
        arrayRubrica = new Pair[500000];
    }

    public boolean isEmpty() {
        return arraySize == 0;
    }

    public void makeEmpty() {
        arraySize = 0;
    }

    public void remove(Comparable key) {
        int pos = binarySearch(key, arraySize, arrayRubrica);
        if (pos != -1) {
            for (int i = pos; i < arraySize; i++)
                arrayRubrica[i] = arrayRubrica[i + 1];
            arraySize--;
        }

    }

    public void insert(Comparable key, Object value) {
        if (key == null || !(value instanceof Long))
            throw new IllegalArgumentException();
        int pos = binarySearch(key, arraySize, arrayRubrica);
        if (pos == -1) {
            int i = arraySize;
            while (i > 0 && ((arrayRubrica[i - 1].getName()).compareTo((String) key) > 0)) {
                arrayRubrica[i] = arrayRubrica[i - 1];
                i--;
            }
            arrayRubrica[i] = new Pair((String) key, (Long) value);
            arraySize++;
            System.out.println(toString());
        } else {
            arrayRubrica[pos] = new Pair(arrayRubrica[pos].getName(), (Long) value);
        }
    }

    private int binarySearch(Comparable key, int arraySize, Pair arrayRubrica[]) {
        if (key == null)
            throw new IllegalArgumentException();
        int first = 0;
        int last = arraySize - 1;
        while (first <= last) {
            int mid = (first + last) / 2;
            if (((Comparable) arrayRubrica[mid].getName()).compareTo(key) < 0) {
                first = mid + 1;
            } else if (((Comparable) arrayRubrica[mid].getName()).compareTo(key) > 0) {
                last = mid - 1;
            } else {
                System.out.println("POSIZIONE " + mid);
                return mid;
            }
        }
        System.out.println("POSIZIONE -1");
        return -1;
    }

    public Object find(Comparable key) {
        int pos = binarySearch(key, arraySize, arrayRubrica);
        if (pos == -1)
            throw new MapItemNotFoundException();
        return arrayRubrica[pos].getPhone();
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < arraySize; i++) {
            str += arrayRubrica[i].toString() + "\n";
        }
        return str;
    }

    // mi trova l'elemento dell'array, dopo averlo trovato,
    // mi scala da destra verso sinistra tutti gli elementi, rimuovendo l'elemento
    // trovato

    // campi di esemplare di Rubrica ..... da completare ......

    // classe privata Pair: non modificare!!
    private class Pair {
        public Pair(String aName, long aPhone) {
            name = aName;
            phone = aPhone;
        }

        public String getName() {
            return name;
        }

        public long getPhone() {
            return phone;
        }

        /*
         * Restituisce una stringa contenente
         * - la nome, "name"
         * - un carattere di separazione ( : )
         * - il numero telefonico, "phone"
         */
        public String toString() {
            return name + " : " + phone;
        }

        // campi di esemplare
        private String name;
        private long phone;
    }
}

interface Map {
    /*
     * verifica se la mappa contiene almeno una coppia chiave/valore
     */
    boolean isEmpty();

    /*
     * Map
     * svuota la mappa
     */
    void makeEmpty();

    /*
     * Inserisce un elemento nella mappa. L'inserimento va sempre a buon fine.
     * Se la chiave non esiste la coppia key/value viene aggiunta alla mappa;
     * se la chiave esiste gia' il valore ad essa associato viene sovrascritto
     * con il nuovo valore; se key e` null viene lanciata IllegalArgumentException
     */
    void insert(Comparable key, Object value);

    /*
     * Rimuove dalla mappa l'elemento specificato dalla chiave key
     * Se la chiave non esiste viene lanciata MapItemNotFoundException
     */
    void remove(Comparable key);

    /*
     * Cerca nella mappa l'elemento specificato dalla chiave key
     * La ricerca per chiave restituisce soltanto il valore ad essa associato
     * Se la chiave non esiste viene lanciata MapItemNotFoundException
     */
    Object find(Comparable key);
}

class MapItemNotFoundException extends RuntimeException {
}
