package hotel_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class UpdateDepartments extends JFrame implements ActionListener{
    
    private JComboBox c;
    private JTextField t, editor;
    private JButton b1,b2;
    private Conn cn;
    private boolean isDataPresent = false;
//    import com.mxrck.autocompleter.TextAutoCompleter;
    
    UpdateDepartments(){
        
        JPanel main = new JPanel();
        main.setBounds(0, 80, 780, 300);
        main.setBackground(Color.white);
        main.setLayout(null);
        add(main);
        
        JPanel labels = new JPanel();
        labels.setBounds(0,0, 200, 100);
        labels.setBackground(Color.white);
        labels.setLayout(null);
        main.add(labels);
        
        JLabel id = new JLabel("Department Name:");
        id.setFont(new Font("Tahoma", Font.PLAIN, 17));
        id.setBounds(50,0, 150, 25);
        labels.add(id);
        
        JLabel room = new JLabel("Budget:");
        room.setFont(new Font("Tahoma", Font.PLAIN, 17));
        room.setBounds(50,50, 150, 25);
        labels.add(room);
        
        
        JPanel fields = new JPanel();
        fields.setBounds(210,0, 180, 100);
        fields.setBackground(Color.white);
        fields.setLayout(null);
        main.add(fields);
        
        t = new JTextField();
        t.setBounds(0, 50, 180, 25);
        t.setBackground(Color.white);
        t.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t.setEditable(false);
        fields.add(t);
        
        c =new JComboBox();
        c.setBackground(Color.white);
        c.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c.setBounds(0,0, 180, 25);
        c.setEditable(true);
        addElementsToChoice();
        AutoCompleteDecorator.decorate(c);
        fields.add(c);
        editor = (JTextField) c.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent evt){
                if(evt.getKeyCode() == 10){
                    String department = editor.getText();
                    showBudget(department);
                }
            }
        });
        
        ImageIcon searchIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/search.png"));
        Image searchImage = searchIcon.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        JLabel searchLabel = new JLabel(new ImageIcon(searchImage));
        searchLabel.setBounds(392, 0, 25, 25);
        main.add(searchLabel);
        searchLabel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                String department = editor.getText();
                showBudget(department);
            }
        });
        
        ImageIcon editable = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/edit.png"));
        Image edit = editable.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        JLabel editIcon = new JLabel(new ImageIcon(edit));
        editIcon.setBounds(392, 50, 25, 25);
        main.add(editIcon);
        editIcon.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                String password = JOptionPane.showInputDialog(rootPane, "Enter Admin Password!", "Confirm Admin", HEIGHT);
                if(password != null)
                    validatePassword(password);
                
            }
        });
        
        b1 =new JButton("Update");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(60, 120, 145, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b1.addActionListener(this);
        b1.setFocusPainted(false);
        main.add(b1);
        
        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(225, 120, 145, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b2.addActionListener(this);
        b2.setFocusPainted(false);
        main.add(b2);
        
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/departments.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(500, 175, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(440, 0, 500, 175);
        main.add(imageLabel);
        
        ImageIcon frameicon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/updateDepartment.png"));
        Image frameImage = frameicon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        JLabel frameimage = new JLabel(new ImageIcon(frameImage));
        frameimage.setBounds(5,5,30,30);
        add(frameimage);
        
        JLabel title = new JLabel("Update Departments Info");
        title.setBounds(40, 5, 250, 25);
        title.setFont(new Font("SERIF", Font.PLAIN, 18));
        add(title);
        
        setUndecorated(true);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(550, 300, 800, 310);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(!isDataPresent){
            JOptionPane.showMessageDialog(rootPane, "No Departments Present!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void validatePassword(String password){
        try{
         
            cn = new Conn();
            String query = "Select * from login where password ='" + password+"'";
            ResultSet rs = cn.st.executeQuery(query);
            if(rs.next()){
                t.setEditable(true);
            }else{
                JOptionPane.showMessageDialog(rootPane,"Wrong Password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane,"Technical Issue!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showBudget(String department){
        try{
            
            cn = new Conn();
            String query = "Select budget from departments where department_name = '"+department+"'";
            ResultSet rs = cn.st.executeQuery(query);
            if(rs.next()){
                t.setText(rs.getString(1));
                
            }else{
                throw new Exception("Department Does Not Exist!");
            }
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addElementsToChoice(){
        try{
            
            cn = new Conn();
            String query = "Select department_name from departments  Order By department_name";
            ResultSet rs = cn.st.executeQuery(query);
            while(rs.next()){
                isDataPresent = true;
                c.addItem(rs.getString(1));
            }
            showBudget((String) c.getSelectedItem());
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(rootPane,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            
            try{
                String department = editor.getText();
                String budget = t.getText();
                cn =new Conn();
                if(!budget.matches("^[0-9,.]{1,15}$")){
                    throw new Exception("Invalid Budget!");
                }
                
                String query = "Update departments Set budget= ? where department_name = ?";
                PreparedStatement pt = cn.con.prepareStatement(query);
                pt.setString(1,budget);
                pt.setString(2, department);
                int result = pt.executeUpdate();
                
                if(result == 1){
                    JOptionPane.showMessageDialog(rootPane, "Data Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    throw new Exception("Unable to Update");
                }
                
                
            }catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            dispose();
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new UpdateDepartments();
    }
    
}

