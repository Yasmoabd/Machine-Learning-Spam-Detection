import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.exceptions.CsvValidationException;

public class SpamDataset extends Dataset {

    private int datatsetSize;

    public SpamDataset() throws IOException, CsvValidationException {
        super();
        datatsetSize = 1731;
        build();
    }

    public void build() throws CsvValidationException, IOException{
        ArrayList<String> myEmails = new ArrayList<>();
        ArrayList<String> spamEmails = getSpamEmails();

        for(int i = 0;i<=datatsetSize-1;i++){
            myEmails.add(spamEmails.get(i));
        }

        setEmails(myEmails);
        clean();
    }

    public static void main(String[] args) throws CsvValidationException, IOException {
        SpamDataset sd = new SpamDataset();
        System.out.println(sd.getNumberOfEmails());
        System.out.println(sd.getNumberofWords());
    }
      
}
