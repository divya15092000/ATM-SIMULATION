
package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener{
    
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    String pin;
    Deposit(String pin){
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 780, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 700, 750);
        add(l3);
         l2 = new JLabel("MAXIMUM DEPOSIT IS RS.10,00,000");
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.PLAIN, 12));
        
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.PLAIN, 12));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 12));
        
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("BACK");
        
        setLayout(null);
        l2.setBounds(140,230,500,25);
        l3.add(l2);
        
        l1.setBounds(140,250,500,25);
        l3.add(l1);
        
        t1.setBounds(140,290,220,25);
        l3.add(t1);
        
        b1.setBounds(260,390,100,25);
        b1.setFont(new Font("System",Font.PLAIN,10));
        l3.add(b1);
        
        b2.setBounds(260,420,100,25);
          b2.setFont(new Font("System",Font.PLAIN,10));
        l3.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        getContentPane().setBackground(Color.GRAY);
        setSize(960,1080);
        setUndecorated(true);
        setLocation(300,0);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String amount = t1.getText();
            Date date = new Date();
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
                }else{
                    Conn c1 = new Conn();
                    c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Deposit', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Deposited Successfully");
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            }else if(ae.getSource()==b2){
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
    
    public static void main(String[] args){
        new Deposit("").setVisible(true);
    }
}