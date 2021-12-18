import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import opennlp.tools.stemmer.PorterStemmer;

public class Dataset {
    ArrayList<String> emails;
    ArrayList<String> words;

    private static List<String> stopWords;

    //2720 for ham
    //1330 for spam

    //testing 1164 ham
    //566 spam
    public Dataset() throws IOException{
        emails = new ArrayList<>();
        words = new ArrayList<>();
        loadStopwords();
    }

    public void clean(){
        PorterStemmer stemmer = new PorterStemmer();
        for(String email: emails){
            String[] wordsInEmail = email.trim().split("\\s+");
            for(String word: wordsInEmail){
                String result = word.replaceAll("\\p{Punct}", "");
                if(result.trim().isEmpty()==false){
                    words.add(stemmer.stem(result));
                }
            }
        }
        words.removeAll(stopWords);
    }

    public ArrayList<String> loadAllEmails() throws CsvValidationException, IOException{
        CSVReader csvr = new CSVReader(new BufferedReader(new FileReader("./src/main/resources/completeSpamAssassin.csv")));
        String[] csvEntry = csvr.readNext();

        ArrayList<String> allEmails = new ArrayList<>();
        try {
            while(csvEntry!=null){
                if(csvEntry.length==3){
                    allEmails.add(csvEntry[1]);
                }
                csvEntry=csvr.readNext();
            }
        } catch (Exception e) {
            System.out.println(73763763);
        }
        //FOR TESTTING MAKE EMAILS = ALLEMAILS
        
        return allEmails;  
    }

    public int getNumberOfEmails(){
        return emails.size();
    }

    public int getNumberofWords(){
        return words.size();
    }

    public static void loadStopwords() throws IOException {
        List<String> stopwords = Files.readAllLines(Paths.get("./src/main/resources/english_stopwords.txt"));
        PorterStemmer stemmer = new PorterStemmer();

        for(int i = 0;i<stopwords.size();i++){
            stopwords.set(i, stemmer.stem(stopwords.get(i)));
        }
        stopWords = stopwords;
    }

    public static void main(String[] args) throws IOException, CsvValidationException {
        Dataset test = new Dataset();
        test.loadAllEmails();
        test.clean();
        System.out.println(test.getNumberofWords());
        
    }
}
