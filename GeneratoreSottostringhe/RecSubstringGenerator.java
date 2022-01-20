import java.io.*;
import java.util.*;
import java.lang.*;

public class RecSubstringGenerator {
    public static void main(String[] args) {
        System.out.println("All substring of abbc are:");
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                System.out.println(str.substring(i, j));

            }
        }
    }
}
