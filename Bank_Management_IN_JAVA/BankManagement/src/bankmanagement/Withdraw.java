
package bankmanagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author raani
 */
public class Withdraw  extends JFrame implements ActionListener{
    
    JLabel lblnamee,lblbal,transactiontime;
    JTextField tfmoney;
    JButton check,back,withdraw;
    Choice caccount;
    JTextArea statementArea;
    
    Withdraw(){
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        
        JLabel text = new JLabel("Withdraw");
        text.setBounds(120,20,200,30);
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(text);
        
        JLabel lblaccount = new JLabel("Account No");
        lblaccount.setBounds(35,80,100,20);
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
        
        caccount.setBounds(150,80,170,25);
        add(caccount);
        
        check = new JButton("Check");
        check.setForeground(Color.BLACK);
        check.setBackground(Color.WHITE);
        check.setBounds(330,80,50,20);
        check.addActionListener(this);
        add(check);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35,130,110,20);
        add(lblname);
        
        lblnamee = new JLabel();
        lblnamee.setBounds(160,130,170,20);
        add(lblnamee);
        
        JLabel lblbalance = new JLabel("Balance");
        lblbalance.setBounds(35,180,110,20);
        add(lblbalance);
        
        lblbal = new JLabel();
        lblbal.setBounds(160,180,170,20);
        add(lblbal);
        
        JLabel lblmoney = new JLabel("Withdraw Balance");
        lblmoney.setBounds(35,230,110,20);
        add(lblmoney);
        
        tfmoney = new JTextField();
        tfmoney.setBounds(160,230,170,20);
        add(tfmoney);
        
        JLabel lbltime = new JLabel("Transaction Time");
        lbltime.setBounds(35,280,150,20);
        add(lbltime);
        
        java.util.Date date = new java.util.Date();
        
        transactiontime = new JLabel("" + date);
        transactiontime.setBounds(160,280,170,20);
        transactiontime.setFont(new Font("Tahoma",Font.PLAIN,14));
        add(transactiontime);
        
        
        withdraw = new JButton("Withdraw");
        withdraw.setForeground(Color.BLACK);
        withdraw.setBackground(Color.WHITE);
        withdraw.setBounds(150,330,100,30);
        withdraw.addActionListener(this);
        add(withdraw);
        
        statementArea = new JTextArea();
        statementArea.setBounds(30, 380, 300, 80);
        statementArea.setEditable(false);
        statementArea.setBackground(Color.PINK);
        add(statementArea);
        
        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(150,490,80,30);
        back.addActionListener(this);
        add(back);
        
        setBounds(400,100,400,570);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            try{
                String accountno = caccount.getSelectedItem();
                
                Conn c = new Conn();
                
                String query = "select * from account where account_no = '"+accountno+"' ";
                
                ResultSet rs2;
                
                rs2 = c.s.executeQuery(query);
                
                while(rs2.next()){
                    lblnamee.setText(rs2.getString("name"));
                    lblbal.setText(rs2.getString("balance"));
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
                
        }else if(ae.getSource() == withdraw){
           try {
                String name = lblnamee.getText();
                String accountno = caccount.getSelectedItem();
                
                Conn c = new Conn();
                
                String query = "select * from account where name = '"+name+"' and account_no = '"+accountno+"' ";
                
                ResultSet rs3;
                
                rs3 = c.s.executeQuery(query);
                
                while(rs3.next()){
                    String balance = lblbal.getText();
                    String time = transactiontime.getText();
                    String withdraww = tfmoney.getText();
                    int withdrawmoney = Integer.parseInt(balance) - Integer.parseInt(withdraww);
                    
                    
                    String query2 = "update account set balance = '"+withdrawmoney+"' where name = '"+name+"' and account_no = '"+accountno+"'  ";
                
                     c.s.executeUpdate(query2);
                     
                     String query3 = "insert into transactions values('"+accountno+"','withdraw','"+time+"','-"+withdraww+"','"+withdrawmoney+"')";
                
                     c.s.executeUpdate(query3);
                     
                     JOptionPane.showMessageDialog(null,"Money Withdrawed successfully");
                     
                     statementArea.append("Account No: " + accountno + " \n"
                             +"Name: " + name + "\n"
                                     +"Money Withdrawed:"+withdraww +" \n"
                                             +"Total Balance: " + withdrawmoney + "\n");
                
                }
                
           }catch(Exception e){
               e.printStackTrace();
           }
           
        }else if(ae.getSource() == back){
            setVisible(false);
            new Transactions();
        }
    }
    
    public static void main(String[] args){
        new Withdraw();
    }
}
