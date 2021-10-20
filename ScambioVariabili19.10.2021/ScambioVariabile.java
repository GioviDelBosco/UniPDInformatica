//importo la classe Scanner
import java.util.Scanner;
 
public class ScambioVariabile {
    public static void main(String[] args) {
        int x,y;
        
        //creo lo Scanner
        Scanner sc=new Scanner(System.in);
        
        //lascio decidere all'utente che valori attribuire
        System.out.println("Attribuisci il valore a X (Intero)");
        x=sc.nextInt();
        System.out.println("Attribuisci il valore a Y (Intero)");
        y=sc.nextInt();
        
        //li scambio senza variabile d'appoggio
        x=x+y;
        y=x-y;
        x=x-y;
        
        //stampo a schermo il risultato
        System.out.println("Scambio effettuato!");
        System.out.println("");
        System.out.println("Il nuovo valore di X è: "+x);
        System.out.println("");
        System.out.println("Il nuovo valore di Y è: "+y);
        
    }
}