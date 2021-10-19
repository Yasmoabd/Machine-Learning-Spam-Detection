import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        
        File myfile = new File("./src/main/resources/ham");
        File[] myfiles = myfile.listFiles();
        int i = 0;
        for(File f: myfiles){
            if(!f.isDirectory()){
                i++;
                Scanner s = new Scanner(f);
                System.out.println(s.nextLine());
                if(i>5){
                    break;
                }
            }
        } 
    }
}
