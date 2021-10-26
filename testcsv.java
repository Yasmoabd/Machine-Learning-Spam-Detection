import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class testcsv {
    public static void main(String[] args) throws CsvValidationException, IOException {
        CSVReader csvr = new CSVReader(new BufferedReader(new FileReader("./src/main/resources/completeSpamAssassin.csv")));
        int spam =0;
        int ham = 0;
        int bad = 0;
        String[] s = csvr.readNext();
        try {
            while(s!=null){
                if(s.length==3){
                    if(s[2].equals("0")){
                        System.out.println(s[0]);
                        ham+=1;
                    }
                    else if(s[2].equals("1")){
                       System.out.println(s[0]); 
                        spam+=1;
                    }
                }
                s=csvr.readNext();
            }
        } catch (Exception e) {
            System.out.println(73763763);
        }
       
        
        System.out.println("Num of ham " + ham);
        System.out.println("Num of spam " + spam);
        System.out.println(bad);
    }
}
