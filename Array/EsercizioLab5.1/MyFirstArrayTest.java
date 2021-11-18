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

        System.out.println("");
        System.out.println("Array modificato dopo il metodo statico \"incrementAll()\"");
        //passo il mio array e lo vado ad utilizzare nel metodo statico
        incrementAll(primoArray);

        for (int i = 0; i < primoArray.length; i++) {
            System.out.print(primoArray[i]+" | ");
            
        }
    }
    //il mio array verra cambiato in a[] 
    public static void incrementAll(int[] a) {
        
        for (int i = 0; i < a.length; i++) {
            a[i]=a[i]+1;
        }

    }

    //la parte c, nel main non verrà cambiato perché gli int sono tipi primitivi e quindi vengono cambiati solo nel metodo statico
}