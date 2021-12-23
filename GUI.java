import javax.swing.*;

import com.google.api.client.util.Data;
import com.opencsv.exceptions.CsvValidationException;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;    

public class GUI {
    private JFrame myFrame;
    private JTextArea textField;
    private JButton[] buttons = new JButton[4];
    private JLabel statusLabel;
    private Controller controller;
    JPanel statusJPanel;


    public GUI() throws CsvValidationException, IOException{
        controller = new Controller(this);
        myFrame = new JFrame("Email Spam Detector");
        myFrame.setSize(1000,600); 
        myFrame.setLayout(null);
        myFrame.setDefaultCloseOperation(3);
        myFrame.setResizable(false);
        addLeftPanel();
        addTextPanel();
        addStatusPane();
        myFrame.setVisible(true);
    }

    public void addStatusPane(){
        JPanel statusPanel = new JPanel();
        statusJPanel=statusPanel;
        statusPanel.setLayout(new FlowLayout());
        statusPanel.setBounds(120, 420, 800, 50);
        JLabel l = new JLabel("Press Build Model to begin");
        statusLabel = l;
        statusPanel.add(l);
        myFrame.add(statusPanel);
    }

    public void addTextPanel(){
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBounds(120, 10, 800, 400);
        JTextArea textArea = new JTextArea("hello world!");
        textField = textArea;
        JScrollPane pane = new JScrollPane(textField,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(new Dimension(800,400));
        
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(800,2000));
        textArea.setLineWrap(true);
        bottomPanel.add(pane);
        myFrame.add(bottomPanel);
        
    }

    public void addLeftPanel(){
        JPanel leftPanel = new JPanel();
        GridLayout layout = new GridLayout(0,1);
        layout.setVgap(50);
        
        leftPanel.setLayout(layout);
        leftPanel.setBounds(10, 10, 100, 400);
      
        JButton b1 = new JButton("Build Model");
        buttons[0] = b1;
        b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long startTime = System.currentTimeMillis();
                    ArrayList<String> stringResults = controller.buildClassiferModel();
                    long duration = (System.currentTimeMillis()-startTime);
                    clearTextField();
                    updateTextField("Classifier was trained and tested in: " + Long.toString(duration) +" (Milliseconds)");
                    addNewLineTextField(2);
                    printConfusionMatrix(controller.getConfusionMatrix());
                    addNewLineTextField(3);
                    updateTextField(stringResults);
                } catch (CsvValidationException | IOException e1) {
                    e1.printStackTrace();
                }
                statusLabel.setText("Press Input email to continue");
                makeNextButtonPressable(0);
            }
            
        });
        
        
        JButton b2 = new JButton("Input email");
        buttons[1] = b2;

        b2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Press Done when you have sent the email.");
                clearTextField();
                updateTextField("Please Forward email to machinelearningspamdetector@gmail.com");
                JButton sentButton = new JButton("Done");
                statusJPanel.add(sentButton);
                sentButton.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            clearTextField();
                            updateTextField(controller.getNewEmail());
                            sentButton.setVisible(false);
                            statusLabel.setText("Press Classify email to continue.");
                            makeNextButtonPressable(1);
                            
                        } catch (GeneralSecurityException | IOException | InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        
                    }
                    
                });
                
            }
            
        });


        b2.setEnabled(false);
        JButton b3 = new JButton("Classify email");
        buttons[2] = b3;
        b3.setEnabled(false);
        JButton b4 = new JButton("Restart");
        buttons[3] = b4;

        leftPanel.add(b1);
        leftPanel.add(b2);
        leftPanel.add(b3);
        leftPanel.add(b4);
        myFrame.add(leftPanel);
    }

    public void printConfusionMatrix(HashMap<String,Integer> result){
        updateTextField("True Positives: "+Integer.toString(result.get("True Positives")));
        updateTextField("True Negatives: "+Integer.toString(result.get("True Negatives")));
        updateTextField("False Positives: "+Integer.toString(result.get("False Positives")));
        updateTextField("False Negatives: "+Integer.toString(result.get("False Negatives")));
    }

    public void updateTextField(ArrayList<String> texts){
        for(String text: texts){
            textField.append(text+ "\n");
        }
    }

    public void updateTextField(String text){
        textField.append(text+ "\n");
    }

    public void clearTextField(){
        textField.setText(null);
    }

    public void addNewLineTextField(int n){
        for(int i =0;i<n;i++){
            textField.append("\n");
        }
    }

    public void makeNextButtonPressable(int buttonPressed){
        buttons[buttonPressed].setEnabled(false);
        buttons[buttonPressed+1].setEnabled(true);
    }
    
    public static void main(String[] args) throws CsvValidationException, IOException {
        
        GUI g = new GUI();
    }
}
