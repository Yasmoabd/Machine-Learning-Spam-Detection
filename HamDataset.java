import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.exceptions.CsvValidationException;

public class HamDataset extends Dataset {
    private int datatsetSize;

    public HamDataset() throws CsvValidationException, IOException{
        super();
        datatsetSize=2720;
        build();
    }

    public void build(){
        ArrayList<String> myEmails = new ArrayList<>();
        ArrayList<String> hamEmails = getHamEmails();
        
        for(int i=0; i<=datatsetSize-1; i++){
            myEmails.add(hamEmails.get(i));
        }

        setEmails(myEmails);
        clean();
    }

    public static void main(String[] args) throws CsvValidationException, IOException {
        HamDataset hd = new HamDataset();
        System.out.println(hd.getNumberOfEmails());
        System.out.println(hd.getNumberofWords());
    }

}
