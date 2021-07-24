package hotel_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class Departments extends JFrame implements ActionListener{
    
    JTextField t1, t2;
    JButton b1, b2, b3;
    JTable t;
    private Conn c;
    private boolean isAdmin = false;
    
    Departments(){
        
        JPanel main = new JPanel();
        main.setBounds(0,30, 900, 500);
        main.setBackground(Color.white);
        main.setLayout(null);
        add(main);
        
        JPanel input = new JPanel();
        input.setBounds(0,30, 900, 30);
        input.setBackground(Color.white);
        input.setLayout(null);
        main.add(input);
        
        JLabel department = new JLabel("New Department:");
        department.setBounds(50, 0, 150, 25);
        department.setFont(new Font("Tahoma", Font.PLAIN, 17));
        input.add(department);
        
        t1 = new JTextField();
        t1.setBounds(200, 0, 180, 25);
        t1.setBackground(Color.white);
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        input.add(t1);
        
        JLabel budget = new JLabel("Budget:");
        budget.setBounds(450, 0, 70, 25);
        budget.setFont(new Font("Tahoma", Font.PLAIN, 17));
        input.add(budget);
        
        t2 = new JTextField();
        t2.setBounds(520, 0, 180, 25);
        t2.setBackground(Color.white);
        t2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        input.add(t2);
        
        t = new JTable();
        t.getTableHeader().setBackground(Color.white);
        t.getTableHeader().setFont(new Font("Sans-Serif", Font.BOLD, 17));
        t.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JScrollPane tableView = new JScrollPane(t);
        tableView.getViewport().setBackground(Color.white);
        tableView.setBounds(0, 95, 900, 295);
        main.add(tableView);
        
        b1 = new JButton("Add");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(740, 0, 140, 25);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b1.setFocusPainted(false);
        b1.addActionListener(this);
        input.add(b1);
        
        b2 = new JButton("Load Data");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(290, 420, 150, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b2.setFocusPainted(false);
        b2.addActionListener(this);
        main.add(b2);

        b3 = new JButton("Back");
        b3.setBackground(Color.black);
        b3.setForeground(Color.WHITE);
        b3.setBounds(460, 420, 150, 30);
        b3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b3.setFocusPainted(false);
        b3.addActionListener(this);
        main.add(b3);
        
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/department.png"));
        Image Ic = ic.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        JLabel frameIcon = new JLabel(new ImageIcon(Ic));
        frameIcon.setBounds(0,0, 30, 30);
        add(frameIcon);
        
        JLabel title = new JLabel("Departments Information");
        title.setFont(new Font("serif", Font.PLAIN, 18 ));
        title.setBounds(40, 0, 200, 30);
        add(title);
        
        setUndecorated(true);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setBounds(510, 200, 901, 530 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void clearAllFields(){
        t1.setText("");
        t2.setText("");
    }
    
    private void addNewDepartment(){
        try{
            
            String name = t1.getText();
            String budget = t2.getText();
            if(name.equals("") || budget.equals("") || !budget.matches("^[0-9,.]+$")){
                throw new Exception("Invalid Input!");
            }else{
                
                c = new Conn();
                String query = "Insert into departments(department_name, budget) values(?, ?)";
                PreparedStatement pt = c.con.prepareStatement(query);
                pt.setString(1, name);
                pt.setString(2, budget);
                
                int result = pt.executeUpdate();
                if(result == 1){
                    JOptionPane.showMessageDialog(rootPane,"Record Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearAllFields();
                }else{
                    throw new Exception("Something Went Wrong!");
                }
            }
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane,"Invalid Budget!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            
            try{
                
                if(!isAdmin){
                    
                    String password = JOptionPane.showInputDialog(rootPane, "Enter Admin Password?", "Admin Confirm", HEIGHT);
                    if (password != null) {

                        c = new Conn();
                        String query = "Select * from login where password = '" + password + "'";
                        ResultSet rs = c.st.executeQuery(query);
                        if (rs.next()) {
                            isAdmin = true;
                            addNewDepartment();
                        } else {
                            throw new Exception("Invalid Password!");
                        }
                    }
                }else{
                    addNewDepartment();
                }
                
                
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }else if(ae.getSource() == b2){
            
            try{
             
                c = new Conn();
                String query = "Select Department_Name, Budget from departments Order By Department_name";
                ResultSet rs = c.st.executeQuery(query);
                if(rs.next()){
                    rs = c.st.executeQuery(query);
                    t.setModel(DbUtils.resultSetToTableModel(rs));
                }else{
                    throw new Exception("No Department Info!");
                }
                
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            dispose();
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new Departments();
    }
}
