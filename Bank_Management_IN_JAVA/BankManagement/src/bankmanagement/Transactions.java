
package bankmanagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

/**
 *
 * @author raani
 */
public class Transactions extends JFrame implements ActionListener{
    
    JButton withdraw,deposit,back;
    
    Transactions(){
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        JLabel heading = new JLabel("Transactions");
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        heading.setBounds(80,20,250,35);
        add(heading);
        
        withdraw = new JButton("Withdraw");
        withdraw.setBounds(40,80,200,40);
        withdraw.setBackground(Color.WHITE);
        withdraw.setForeground(Color.BLACK);
        withdraw.addActionListener(this);
        add(withdraw);
        
        deposit = new JButton("Deposit");
        deposit.setBounds(40,140,200,40);
        deposit.setBackground(Color.WHITE);
        deposit.setForeground(Color.BLACK);
        deposit.addActionListener(this);
        add(deposit);
        
        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(100,200,80,30);
        back.addActionListener(this);
        add(back);
        
        
        setBounds(400,200,280,270);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == withdraw){
            setVisible(false);
            new Withdraw();
        }else if(ae.getSource() == deposit){
            setVisible(false);
            new Deposit();
        }else if(ae.getSource() == back){
            setVisible(false);
            new BankManagement();
        }
    }
    
    public static void main(String[] args) {
        new Transactions();
    }
}
