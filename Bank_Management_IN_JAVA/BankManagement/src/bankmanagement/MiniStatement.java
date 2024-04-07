
package bankmanagement;

/**
 *
 * @author raani
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;
import javax.swing.table.DefaultTableCellRenderer; 

public class MiniStatement extends JFrame implements ActionListener{
    
    JLabel lblnamee,lblacno,lblactype,lblbal;
    Choice caccount;
    JButton check,showMiniStatement,back;
    JTable table;

    
    MiniStatement(){
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        
        JLabel heading = new JLabel("Mini Statement");
        heading.setBounds(320,20,200,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        add(heading);
        
        JLabel lblaccount = new JLabel("Account No");
        lblaccount.setBounds(220, 80, 100, 30);
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
        
        caccount.setBounds(340,80,170,25);
        add(caccount);
        
        check = new JButton("Check");
        check.setForeground(Color.BLACK);
        check.setBackground(Color.WHITE);
        check.setBounds(520,80,50,20);
        check.addActionListener(this);
        add(check);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(220, 130, 100, 30);
        add(lblname);
        
        lblnamee =new JLabel();
        lblnamee.setBounds(340,130,170,30);
        add(lblnamee);
        
        JLabel lbltype = new JLabel("Account Type");
        lbltype.setBounds(220, 180, 100, 30);
        add(lbltype);
        
        lblactype =new JLabel();
        lblactype.setBounds(340,180,170,30);
        add(lblactype);
        
        JLabel lblbalance = new JLabel("Balance");
        lblbalance.setBounds(220, 230, 100, 30);
        add(lblbalance);
        
        lblbal =new JLabel();
        lblbal.setBounds(340,230,170,30);
        add(lblbal);
        
        showMiniStatement = new JButton("Show Mini Statement");
        showMiniStatement.setForeground(Color.BLACK);
        showMiniStatement.setBackground(Color.WHITE);
        showMiniStatement.setBounds(230,280,160,30);
        showMiniStatement.addActionListener(this);
        add(showMiniStatement);
        
        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(450,280,80,30);
        back.addActionListener(this);
        add(back);
        
        
        table= new JTable();
        table.setBounds(0,330,850,530);
        table.setBackground(Color.PINK);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setEnabled(false); 
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 330, 850, 430);
        scrollPane.getViewport().setBackground(Color.PINK);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        table.getTableHeader().setBackground(Color.PINK);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        add(scrollPane);
        
        setBounds(400,100,850,900);
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
                    lblactype.setText(rs.getString("account_type"));
                    lblbal.setText(rs.getString("balance"));
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
                
        }else if(ae.getSource() == showMiniStatement){
            try{
                String accountno = caccount.getSelectedItem();
                
                Conn c = new Conn();
                
                String query1 = "select * from transactions where account_no = '"+accountno+"' ";
                
                ResultSet rs2;
                
                rs2 = c.s.executeQuery(query1);
                
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
        new MiniStatement();
    }
    
}
