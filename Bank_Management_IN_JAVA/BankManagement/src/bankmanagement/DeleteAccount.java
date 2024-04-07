
package bankmanagement;

/**
 *
 * @author raani
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class DeleteAccount extends JFrame implements ActionListener{
    
    JLabel lblnamee;
    JButton check,back,delete;
    Choice caccount;
    
    DeleteAccount(){
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        
        JLabel text = new JLabel("Delete Account");
        text.setBounds(100,20,200,30);
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
        lblname.setBounds(35,130,100,20);
        add(lblname);
        
        lblnamee = new JLabel();
        lblnamee.setBounds(150,130,170,20);
        add(lblnamee);
        
        delete = new JButton("Delete Account");
        delete.setForeground(Color.BLACK);
        delete.setBackground(Color.WHITE);
        delete.setBounds(60,190,140,30);
        delete.addActionListener(this);
        add(delete);
        
        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(230,190,80,30);
        back.addActionListener(this);
        add(back);
        
        setBounds(400,200,400,290);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            try{
                String accountno = caccount.getSelectedItem();
                
                Conn c = new Conn();
                
                String query = "select * from account where account_no = '"+accountno+"' ";
                
                ResultSet rs;
                
                rs = c.s.executeQuery(query);
                
                while(rs.next()){
                    lblnamee.setText(rs.getString("name"));
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
                
        }else if(ae.getSource() == delete){
           try {
                String name = lblnamee.getText();
                String accountno = caccount.getSelectedItem();
                
                Conn c = new Conn();
                
                String query = "delete from account where name = '"+name+"' and account_no = '"+accountno+"' ";
                
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Account deleted successfully");
                
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
        new DeleteAccount();
    }
    
}
