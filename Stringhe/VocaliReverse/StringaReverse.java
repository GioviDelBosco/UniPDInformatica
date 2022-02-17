import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * StringaReverse
 */
public class StringaReverse {
    public static void main(String[] args) {
        String s="";

        Scanner sc=new Scanner(System.in);

        System.out.println("Inserisci una stringa");
        s=sc.nextLine();
        System.out.println(concatenazione(s));
        System.out.println("----------------");
        System.out.println(concatenazioneInversa(s));

        sc.close();
    }

    public static String concatenazione(String s){

        if(s.equals("")){
            return s;
        }    

        char c=s.charAt(0);

        if((c=='a')||(c=='e')||(c=='i')||(c=='o')||(c=='u')||(c=='A')||(c=='E')||(c=='I')||(c=='O')||(c=='U')){
            return c+(concatenazione(s.substring(1)));
        }else{
            return concatenazione(s.substring(1));
        }
    }

    public static String concatenazioneInversa(String s){
        if(s.equals("")){
            return s;
        }    

        char c=s.charAt(0);

        if((c=='a')||(c=='e')||(c=='i')||(c=='o')||(c=='u')||(c=='A')||(c=='E')||(c=='I')||(c=='O')||(c=='U')){
            return (concatenazioneInversa(s.substring(1))+c);
        }else{
            return concatenazioneInversa(s.substring(1));
        }
    }
}