import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import opennlp.tools.stemmer.PorterStemmer;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        
        PorterStemmer s = new PorterStemmer();
        System.out.println(s.stem("jumping"));
    }
}
