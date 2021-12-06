import java.util.Scanner;
import java.io.*;

public class leggi1 {
    public static void main(String[] args) {
        try {
        FileReader fl=new FileReader("input.txt");
        Scanner sc=new Scanner(fl);
        while (sc.hasNext()) {
            String linea=sc.next();
            System.out.println(linea);
        }
        sc.close();
        fl.close();
        } catch (Exception e) {
            System.out.println("File non trovato!");
        }
        
    }
}
