import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.opencsv.exceptions.CsvValidationException;

public class ClassifierModel {
    private Dataset spamDataset;
    private Dataset hamDataset;
    private int alpha;
    private HashMap<String,Integer> spamVocabulary;
    private HashMap<String,Integer> hamVocabulary;

    public ClassifierModel() throws CsvValidationException, IOException{
        spamDataset = new SpamDataset();
        hamDataset = new HamDataset();
        spamVocabulary = new HashMap<>();
        hamVocabulary = new HashMap<>();
        alpha = 1;
        build();
    }

    public void build(){
        buildSpamVocabulary();
        buildHamVocabulary();
    }

    public void buildSpamVocabulary(){
        for(String word: spamDataset.getWords()){
            if(spamVocabulary.containsKey(word)){
                int oldVal = spamVocabulary.get(word);
                spamVocabulary.put(word, oldVal+1);
            }
            else{
                spamVocabulary.put(word, alpha);
            }
            if(!(hamVocabulary.containsKey(word))){
                hamVocabulary.put(word, alpha); //adding alpha to other map so there is no 0 occurences.
            }
        }
    }

    public void buildHamVocabulary(){
        for(String word: hamDataset.getWords()){
            if(hamVocabulary.containsKey(word)){
                int oldVal = hamVocabulary.get(word);
                hamVocabulary.put(word, oldVal+1);
            }
            else{
                hamVocabulary.put(word, alpha);
            }
            if(!(spamVocabulary.containsKey(word))){
                spamVocabulary.put(word, alpha);
            }
        }
    }
    
    public boolean classify(String email){
        ArrayList<String> wordsInEmail = Dataset.cleanEmail(email);

        double spam = pSpam();
        double ham = pHam();

        for(String word: wordsInEmail){
            double spamProbability = pWordInSpam(word)*1000;
            double hamProbability = pWordInHam(word)*1000;

            spam*=spamProbability;
            ham*=hamProbability;
        }

        return spam>ham;
    }

    private double pSpam(){
        int spamEmails = spamDataset.getNumberOfEmails();
        int hamEmails = hamDataset.getNumberOfEmails();
        int totalEmails = spamEmails+hamEmails;

        return spamEmails/(double)totalEmails;
    }

    private double pHam(){
        int spamEmails = spamDataset.getNumberOfEmails();
        int hamEmails = hamDataset.getNumberOfEmails();
        int totalEmails = spamEmails+hamEmails;

        return hamEmails/(double)totalEmails;
    }

    private double pWordInSpam(String word){
        int totalSpamWords = spamDataset.getNumberofWords();
        if(spamVocabulary.containsKey(word)){
            int occurences = spamVocabulary.get(word);
            return occurences/(double)totalSpamWords;
        }
        return 1;
    }

    private double pWordInHam(String word){
        int totalHamWords = hamDataset.getNumberofWords();
        if(hamVocabulary.containsKey(word)){
            int occurences = hamVocabulary.get(word);
            return occurences/(double)totalHamWords;
        }
        return 1;
    }

    public Dataset getSpamDataset(){
        return spamDataset;
    }

    public Dataset getHamDataset(){
        return hamDataset;
    }

    public static void main(String[] args) throws CsvValidationException, IOException {
        ClassifierModel cm = new ClassifierModel();
        System.out.println(cm.classify("exam on tuesday cancelled"));
        TestingModel tm = new TestingModel(cm);
        tm.test();
        tm.PrintResults();
        System.out.println(tm.getAccuracy());
    }
    


}
