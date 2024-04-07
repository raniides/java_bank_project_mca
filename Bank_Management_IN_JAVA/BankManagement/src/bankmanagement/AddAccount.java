
package bankmanagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

/**
 *
 * @author raani
 */
public class AddAccount extends JFrame implements ActionListener{
    
    JTextField tfname,tfaccount,tfbalance;
    JComboBox comboactype;
    JButton back,add;
    
    AddAccount(){
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        
        JLabel text = new JLabel("Add Account");
        text.setBounds(100,20,200,30);
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(text);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35,80,100,20);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(180,80,170,20);
        tfname.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(tfname);
        
        JLabel lblaccount = new JLabel("Account No");
        lblaccount.setBounds(35,130,100,20);
        lblaccount.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(lblaccount);
        
        tfaccount = new JTextField();
        tfaccount.setBounds(180,130,170,20);
        tfaccount.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(tfaccount);
        
        JLabel lblactype = new JLabel("Account Type");
        lblactype.setBounds(35,180,100,20);
        lblactype.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(lblactype);
        
        String idOption[] = { "Savings Account","Current Account"};
        comboactype = new JComboBox(idOption);
        comboactype.setBounds(180,180,170,25);
        comboactype.setBackground(Color.WHITE);
        add(comboactype);
        
        JLabel lblbalance = new JLabel("Balance");
        lblbalance.setBounds(35,230,100,20);
        lblbalance.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(lblbalance);
        
        tfbalance = new JTextField();
        tfbalance.setBounds(180,230,170,25);
        add(tfbalance);
        
        add = new JButton("Add Account");
        add.setForeground(Color.BLACK);
        add.setBackground(Color.WHITE);
        add.setBounds(70,280,120,30);
        add.addActionListener(this);
        add(add);
        
        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(200,280,80,30);
        back.addActionListener(this);
        add(back);
        
        setBounds(400,200,400,350);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
            try {
                Conn c = new Conn();
                
                String name = tfname.getText();
                String accountno = tfaccount.getText();
                String accounttype = (String) comboactype.getSelectedItem();
                String balance = tfbalance.getText();
                
                String query = "insert into account values('"+name+"','"+accountno+"','"+accounttype+"','"+balance+"')";
                
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Account added successfully");
                
                setVisible(false); 
                new BankManagement();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == back){
            setVisible(false);
            new BankManagement();
        }
    }
    
    public static void main(String[] args) {
        new AddAccount();
    }
}
