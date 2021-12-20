import java.util.ArrayList;

public class TestingModel {
    private ClassifierModel classifier;
    private TestingDataset testingSet;
    private int truePositives,falsePositives,trueNegatives,falseNegatives = 0;

    public TestingModel(ClassifierModel cm){
        classifier = cm;
        testingSet = new TestingDataset(classifier.getSpamDataset(), classifier.getHamDataset());
    }

    public void test(){
        ArrayList<TestingInstance> emails = testingSet.getEmails();
        
        for(TestingInstance email: emails){
            boolean type = email.getType();
            boolean result = classifier.classify(email.getEmail());

            if(type==result){
                if(type==true){
                    truePositives+=1;
                }
                else{
                    trueNegatives+=1;
                }
            }
            else{
                if(type==true){
                    falsePositives+=1;
                }
                else{
                    falseNegatives+=1;
                }
            }
        }
    }

    public int getAccuracy(){
        System.out.println("TESTING SIZE: " + testingSet.getEmails().size());
        return trueNegatives+truePositives;
    }
}
