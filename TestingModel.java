import java.util.ArrayList;
import java.util.HashMap;

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
                    falseNegatives+=1;
                }
                else{
                    falsePositives+=1;
                }
            }
        }
    }

    public int getNumberofTestCases(){
        return testingSet.getEmails().size();
    }

    public int getTruePositives(){
        return truePositives;
    }

    public int getTrueNegatives(){
        return trueNegatives;
    }

    public int getFalsePositives(){
        return falsePositives;
    }

    public int getFalseNegatives(){
        return falseNegatives;
    }

    public double getAccuracy(){
        return (truePositives+trueNegatives)/(double)testingSet.getEmails().size();
    }

    public double getPrecision(){
        return truePositives/(double)(truePositives+falsePositives);
    }

    public double getRecall(){
        return truePositives/(double)(truePositives+falseNegatives);
    }

    public double getSpecificity(){
        return trueNegatives/(double)(trueNegatives+falsePositives);
    }

    public double getF1Score(){
        double score = (2*(getPrecision()*getRecall()))/(getPrecision()+getRecall());
        return score;
    }

    public void PrintResults(){
        System.out.println("TESTING SIZE: " + testingSet.getEmails().size());
        System.out.println("TRUE POSITIVES" + truePositives);
        System.out.println("TRUE negatives" + trueNegatives);
        System.out.println("false negatives" + falseNegatives);
        System.out.println("false postives" + falsePositives);
        System.out.println(trueNegatives+truePositives);
    }

    public HashMap<String,Integer> getConfusionMatrix(){
        HashMap<String,Integer> result = new HashMap<>();
        
        result.put("True Positives", getTruePositives());
        result.put("False Positives", getFalsePositives());
        result.put("True Negatives", getTrueNegatives());
        result.put("False Negatives", getFalseNegatives());

        return result;
    }

    public HashMap<String, Double> getResults(){
        HashMap<String,Double> result = new HashMap<>();
        result.put("Acurracy", getAccuracy());
        result.put("Precision", getPrecision());
        result.put("Recall", getRecall());
        result.put("Specificity", getSpecificity());
        result.put("F1-Score", getF1Score());

        return result;
    }
}
