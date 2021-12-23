import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;
import opennlp.tools.stemmer.PorterStemmer;

public class test {
    
    public static void main(String[] args) throws GeneralSecurityException, IOException, InterruptedException {
        GmailAPI.fetchNewEmail();
       
    }
}

