package pgmi;

import javax.swing.*;

public class Main { 
    public static void main(String[] args) { 
    	
        JFrame jf = new JFrame();
        jf.setBounds(0, 10, 360, 800);
        jf.setTitle("Main");
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        jf.add(new Login());
        jf.setVisible(true);
       
    }
}

