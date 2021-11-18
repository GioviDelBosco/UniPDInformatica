/**
 * MyFirstArrayTest
 */

public class MyFirstArrayTest {
    public static void main(String[] args) {
        int primoArray[]= new int[10];
        
        //mostrare a schermo quanto lungo è l'array
        System.out.println("L'array e' lungo: "+primoArray.length);
        System.out.println();

        System.out.println("Array in ordine crescente:");

        //riempie e stampa a schermo l'array
        for (int i = 0; i < primoArray.length; i++) {
            primoArray[i]=i;
            System.out.print(primoArray[i]+" | ");
            
        }
        
        System.out.println();
        //stampo dall'ultimo elemento al primo l'array
        System.out.println("Array in ordine decrescente:");
        for (int i = primoArray.length-1; i >= 0; i--) {
            System.out.print(primoArray[i]+" | ");
        }
    }
}