 
package hotel_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

import net.proteanit.sql.*;

public class EmployeeInfo extends JFrame implements ActionListener{
    
    JTable t;
    JButton b1, b2;
    
    EmployeeInfo(){
        t = new JTable();
        t.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
        t.getTableHeader().setBackground(Color.white);
        
        JScrollPane sp = new JScrollPane(t);
        sp.setVisible(true);
        sp.setBounds(0,40,1200, 500);
        sp.getViewport().setBackground(Color.white);
//        sp.setBorder(BorderFactory.createEmptyBorder());
        add(sp);

        b1 = new JButton("Load Data");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(450, 590, 150, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b1.setFocusPainted(false);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(620, 590, 150, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b2.setFocusPainted(false);
        b2.addActionListener(this);
        add(b2);

        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/info.jpg"));
        Image Ic = ic.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        JLabel frameIcon = new JLabel(new ImageIcon(Ic));
        frameIcon.setBounds(0,0, 30, 30);
        add(frameIcon);
        
        JLabel title = new JLabel("Employee Information");
        title.setFont(new Font("serif", Font.PLAIN, 18 ));
        title.setBounds(40, 0, 200, 30);
        add(title);
        
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setBounds(400, 150, 1201, 650);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            try{
                
                Conn c = new Conn();
                String query = "Select * from Employee";
                ResultSet rs = c.st.executeQuery(query);
                if(rs.next()){
                    rs = c.st.executeQuery(query);
                    t.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    t.setModel(DbUtils.resultSetToTableModel(rs));
                    
                }else{
                    throw new Exception("No Employee Info Available!");
                }
                
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(rootPane,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if(ae.getSource() == b2){
            dispose();
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new EmployeeInfo();
    }
    
}
