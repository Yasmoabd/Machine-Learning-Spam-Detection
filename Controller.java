import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import com.opencsv.exceptions.CsvValidationException;

public class Controller {
    private ClassifierModel classifierModel;
    private GUI gui;
    private TestingModel testingModel;

    public Controller(GUI g) throws CsvValidationException, IOException{
        gui = g;
    }

    public ArrayList<String> buildClassiferModel() throws CsvValidationException, IOException{
        classifierModel = new ClassifierModel();
        TestingModel tm = new TestingModel(classifierModel);
        testingModel = tm;
        tm.test();
        HashMap<String, Double> result = tm.getResults();
        ArrayList<String> resultString = new ArrayList<>();
        for(String key: result.keySet()){
            resultString.add(key+": "+Double.toString(result.get(key)));
        }
        return resultString;
    }

    public HashMap<String,Integer> getConfusionMatrix(){
        HashMap<String,Integer> result = testingModel.getConfusionMatrix();
        return result;
    }

    public ArrayList<String> getNewEmail() throws GeneralSecurityException, IOException, InterruptedException{
        String result = GmailAPI.fetchNewEmail();
        String[] parts = result.split("EmailSplit");
        ArrayList<String> resultString = new ArrayList<>();
        for(String part: parts){
            resultString.add(part);
        }
        return resultString;
    }

    public String classify(String email){
        
        boolean result = classifierModel.classify(email);
        if(result){
            return "Spam";
        }
        return "Not spam";
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException, InterruptedException {
        GmailAPI.fetchNewEmail();
    }
}
