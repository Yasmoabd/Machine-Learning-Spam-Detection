import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;  
public class testView {
    public static void main(String[] args) {
        JPanel home = new JPanel();
        home.setSize(400,500);//400 width and 500 height  
        home.setLayout(null);//using no layout managers  
        home.setVisible(true);//making the frame visible
        JLabel homLabel = new JLabel();
        homLabel.setText("Home screen");
        homLabel.setBounds(0, 50, 100, 50);
        home.add(homLabel);

        JPanel second = new JPanel();
        second.setSize(1000,1000);//400 width and 500 height  
        second.setLayout(null);//using no layout managers  
        second.setVisible(true);//making the frame visible
        JLabel secondJLabel = new JLabel();
        secondJLabel.setText("second screen");
        secondJLabel.setBounds(50, 50, 100, 300);
        second.add(secondJLabel);

        JFrame myFrame = new JFrame();
        myFrame.add(home);
        myFrame.add(second);
        myFrame.setSize(1000,600);//400 width and 500 height  
        myFrame.setLayout(null);//using no layout managers  
        myFrame.setVisible(true);//making the frame visible
        myFrame.setDefaultCloseOperation(3);
        myFrame.setContentPane(second);
        myFrame.setResizable(false);
        




        
    }
}
