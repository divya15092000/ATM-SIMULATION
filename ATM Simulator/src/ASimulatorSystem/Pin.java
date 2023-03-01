
package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Pin extends JFrame implements ActionListener{
    
    JPasswordField t1,t2;
    JButton b1,b2;                               
    JLabel l1,l2,l3;
    String pin;
    Pin(String pin){
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 780, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l4 = new JLabel(i3);
        l4.setBounds(0, 0, 700, 750);
        add(l4);
        
        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.PLAIN, 12));
        l1.setForeground(Color.WHITE);
        
        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.PLAIN, 12));
        l2.setForeground(Color.WHITE);
        
        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.PLAIN, 12));
        l3.setForeground(Color.WHITE);
        
        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 12));
        
        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 12));
        
        b1 = new JButton("CHANGE");
        b2 = new JButton("BACK");
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setLayout(null);
        
        l1.setBounds(210,250,700,25);
        l4.add(l1);
        
        l2.setBounds(130,280,100,25);
        l4.add(l2);
        
        l3.setBounds(130, 340, 150, 20);
        l4.add(l3);
        
        t1.setBounds(130,310,150,20);
        l4.add(t1);
        
        t2.setBounds(130, 370, 150, 20);
        l4.add(t2);
        
        b1.setBounds(300,400,100,20);
        b1.setFont(new Font("System", Font.PLAIN, 12));
        l4.add(b1);
        
        b2.setBounds(300,430,100,20);
        b2.setFont(new Font("System", Font.PLAIN, 12));
        l4.add(b2);
        
        setSize(800,800);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String npin = t1.getText();
            String rpin = t2.getText();
            
            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }
            
            if(ae.getSource()==b1){
                if (t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                }
                if (t2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                }
                
                Conn c1 = new Conn();
                String q1 = "update bank set pin_no = '"+rpin+"' where pin_no = '"+pin+"' ";
                String q2 = "update login set pin_no = '"+rpin+"' where pin_no = '"+pin+"' ";
                String q3 = "update signupthree set pin_no = '"+rpin+"' where pin_no = '"+pin+"' ";

                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);
                c1.s.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);
            
            }else if(ae.getSource()==b2){
                new Transactions(pin).setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Pin("").setVisible(true);
    }
}
