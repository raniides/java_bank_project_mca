
package bankmanagement;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author raani
 */
public class BankManagement extends JFrame implements ActionListener{

     JButton addaccount,transactions,displayallaccounts,ministatment,deleteaccount;

    
    BankManagement(){
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        
        JLabel heading = new JLabel("Bank Management System");
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        heading.setBounds(200,20,250,35);
        add(heading);
        
        addaccount = new JButton("Add Account");
        addaccount.setBounds(10,70,200,30);
        addaccount.setBackground(Color.WHITE);
        addaccount.setForeground(Color.BLACK);
        addaccount.addActionListener(this);
        add(addaccount);
        
        transactions = new JButton("Transactions");
        transactions.setBounds(10,110,200,30);
        transactions.setBackground(Color.WHITE);
        transactions.setForeground(Color.BLACK);
        transactions.addActionListener(this);
        add(transactions);
        
        displayallaccounts = new JButton("Display All Accounts");
        displayallaccounts.setBounds(10,150,200,30);
        displayallaccounts.setBackground(Color.WHITE);
        displayallaccounts.setForeground(Color.BLACK);
        displayallaccounts.addActionListener(this);
        add(displayallaccounts);
        
        ministatment = new JButton("Mini Statment");
        ministatment.setBounds(10,190,200,30);
        ministatment.setBackground(Color.WHITE);
        ministatment.setForeground(Color.BLACK);
        ministatment.addActionListener(this);
        add(ministatment);
        
        deleteaccount = new JButton("Delete Account");
        deleteaccount.setBounds(10,230,200,30);
        deleteaccount.setBackground(Color.WHITE);
        deleteaccount.setForeground(Color.BLACK);
        deleteaccount.addActionListener(this);
        add(deleteaccount);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/b1.png"));
         Image i2 = i1.getImage().getScaledInstance(400,220,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(250,60,400,220);
        add(image);
        
        setBounds(400,200,700,320);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == addaccount){
            setVisible(false);
            new AddAccount();
        }else if(ae.getSource() == transactions){
            setVisible(false);
            new Transactions();
        }else if(ae.getSource() == displayallaccounts){
            setVisible(false);
            new DisplayAllAccounts();
        }else if(ae.getSource() == ministatment){
            setVisible(false);
            new MiniStatement();
        }else if(ae.getSource() == deleteaccount){
            setVisible(false);
            new DeleteAccount();
        }
    }
    
    public static void main(String[] args) {
        new BankManagement();
    }
    
}
