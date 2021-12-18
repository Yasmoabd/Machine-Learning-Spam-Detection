import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import opennlp.tools.stemmer.PorterStemmer;

public class test {
    
    public static void main(String[] args) throws FileNotFoundException {
        String testemail = "hello   my name is  yasir";
        String[] testwords = testemail.trim().split("\\s+");
        for(String s: testwords){
            System.out.println(s);
        }
        PorterStemmer s = new PorterStemmer();
        System.out.println(s.stem("walks"));
       
    }
}

