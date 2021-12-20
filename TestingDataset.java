import java.util.ArrayList;

public class TestingDataset {
    private Dataset spamDataset;
    private Dataset hamDataset;
    private ArrayList<TestingInstance> testingEmails;

    public TestingDataset(Dataset sd, Dataset hd){
        testingEmails = new ArrayList<>();
        spamDataset = sd;
        hamDataset = hd;
        build();
    }

    public void build(){
        int spamIndex = spamDataset.getNumberOfEmails();
        int spamlength = spamDataset.getSpamEmails().size();
        int hamIndex = hamDataset.getNumberOfEmails();
        int hamLength = hamDataset.getHamEmails().size();

        for(int i = spamIndex;i<spamlength;i++){
            TestingInstance t = new TestingInstance(true, spamDataset.getSpamEmails().get(i));
            testingEmails.add(t);
        }

        for(int i = hamIndex;i<hamLength;i++){
            TestingInstance t = new TestingInstance(false, hamDataset.getHamEmails().get(i));
            testingEmails.add(t);
        }
    }

    public ArrayList<TestingInstance> getEmails(){
        return testingEmails;
    }
    
}
