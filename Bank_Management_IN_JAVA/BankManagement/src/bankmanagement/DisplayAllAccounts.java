
package bankmanagement;

/**
 *
 * @author raani
 */


import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.*;

public class DisplayAllAccounts extends JFrame implements ActionListener{
    JTable table;
    JButton search,back;
    Choice caccount;
    
    DisplayAllAccounts(){
        
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        
        JLabel text = new JLabel("All Accounts");
        text.setBounds(170,15,200,30);
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(text);
        
        
        JLabel lblaccount = new JLabel("Account No");
        lblaccount.setBounds(40,60,80,20);
        add(lblaccount);
        
        caccount= new Choice();
        
        try {
            Conn conn = new Conn();
            
            String query = "select * from account";
            
            ResultSet rs = conn.s.executeQuery(query);
            
            while(rs.next()){
                caccount.add(rs.getString("account_no"));
                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        caccount.setBounds(140,60,170,25);
        add(caccount);
        
        search = new JButton("Search");
        search.setForeground(Color.BLACK);
        search.setBackground(Color.WHITE);
        search.setBounds(320,60,100,30);
        search.addActionListener(this);
        add(search);
        
        JLabel l1=new JLabel("Name");
        l1.setBounds(40,100,100,20);
        add(l1);
        
        JLabel l2= new JLabel("Account No");
        l2.setBounds(150,100,100,20);
        add(l2);
        
        JLabel l3= new JLabel("Account Type");
        l3.setBounds(290,100,100,20);
        add(l3);
        
        JLabel l4= new JLabel("Balance");
        l4.setBounds(430,100,100,20);
        add(l4);
        
        table= new JTable();
        table.setBounds(0,130,550,300);
        table.setBackground(Color.PINK);
        add(table);
        
        try{
            Conn c =new Conn();
            ResultSet rs= c.s.executeQuery("select * from account");
            table.setModel(DbUtils.resultSetToTableModel(rs)); 
        }catch(Exception e){
            e.printStackTrace();
        }
        
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        
        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(220,440,120,30);
        back.addActionListener(this);
        add(back);
        
        setBounds(300,200,550,510);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){
            try {
            String query = "select * from account where account_no = '"+caccount.getSelectedItem()+"'";
            
            Conn c = new Conn();
            ResultSet rs2;
            
            rs2 = c.s.executeQuery(query);
            
            table.setModel(DbUtils.resultSetToTableModel(rs2));
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == back){
        setVisible(false);
        new BankManagement();
        }
    }
    
    public static void main(String[] args){
        new DisplayAllAccounts();
    }
    
}
