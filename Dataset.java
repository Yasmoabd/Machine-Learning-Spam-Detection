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
    private ArrayList<String> emails;
    private ArrayList<String> words;
    private ArrayList<String> spamEmails;
    private ArrayList<String> hamEmails;

    private static List<String> stopWords;
    //total 5780
    //3884 ham
    //1896 spam


    //2720 for ham
    //1330 for spam

    //testing 1164 ham
    //566 spam
    public Dataset() throws IOException, CsvValidationException{
        emails = new ArrayList<>();
        words = new ArrayList<>();
        spamEmails = new ArrayList<>();
        hamEmails = new ArrayList<>();
        loadStopwords();
        loadAllEmails();
    }

    public void clean(){
        PorterStemmer stemmer = new PorterStemmer();
        for(String email: emails){
            String[] wordsInEmail = email.trim().split("\\s+");
            for(String word: wordsInEmail){
                String result = word.replaceAll("\\p{Punct}", "");
                if(result.trim().isEmpty()==false){
                    words.add(result);
                }
            }
        }
        
    }

    public void loadAllEmails() throws CsvValidationException, IOException{
        CSVReader csvr = new CSVReader(new BufferedReader(new FileReader("./src/main/resources/completeSpamAssassin.csv")));
        String[] csvEntry = csvr.readNext();

        try {
            while(csvEntry!=null){
                if(csvEntry.length==3){
                    if(csvEntry[2].equals("0")){
                        hamEmails.add(csvEntry[1]);
                    }
                    else if(csvEntry[2].equals("1")){
                        spamEmails.add(csvEntry[1]);
                    }
                }
                csvEntry=csvr.readNext();
            }
        } catch (Exception e) {
            return;
        } 
    }

    public static ArrayList<String> cleanEmail(String email){
        PorterStemmer stemmer = new PorterStemmer();
        ArrayList<String> cleanWords = new ArrayList<>();
        String[] wordsInEmail = email.trim().split("\\s+");
        for(String word: wordsInEmail){
            String result = word.replaceAll("\\p{Punct}", "");
            if(result.trim().isEmpty()==false){
                cleanWords.add(result);
            }
        }
        return cleanWords;
    }

    public int getNumberOfEmails(){
        return emails.size();
    }

    public int getNumberofWords(){
        return words.size();
    }

    public ArrayList<String> getHamEmails(){
        return hamEmails;
    }

    public ArrayList<String> getSpamEmails(){
        return spamEmails;
    }

    public ArrayList<String> getWords(){
        return words;
    }

    public void setEmails(ArrayList<String> emls){
        emails = emls;
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
