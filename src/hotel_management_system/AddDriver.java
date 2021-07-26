
package hotel_management_system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class AddDriver extends JFrame implements ActionListener{
    
    JTextField t1,t2,t3, t4, t5,t6;
    JRadioButton r1,r2;
    ButtonGroup bg;
    JComboBox c1;
    JButton b1,b2;
    
    AddDriver(){
        super("Add Driver");
        JLabel name = new JLabel("Name");
        name.setBounds(50, 40, 110, 25 );
        name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(name);
        t1 = new JTextField();
        t1.setBounds(190, 40, 170, 25);
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t1);
        
        JLabel age = new JLabel("Age");
        age.setBounds(50, 90, 110, 25 );
        age.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(age);
        t2 = new JTextField();
        t2.setBounds(190, 90, 170, 25);
        t2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t2);
        
        JLabel gender = new JLabel("Gender");
        gender.setBounds(50, 140, 110, 25 );
        gender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(gender);
        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        r1.setBounds(190, 140, 85, 25);
        r1.setBackground(Color.white);
        
        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        r2.setBounds(275, 140, 85, 25);
        r2.setBackground(Color.white);
        
        bg = new ButtonGroup();
        bg.add(r1);bg.add(r2);
        add(r1);
        add(r2);
        
        JLabel contact = new JLabel("Contact");
        contact.setBounds(50, 190, 110, 25 );
        contact.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(contact);
        t6 = new JTextField();
        t6.setBounds(190, 190, 170, 25);
        t6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t6);
        
        JLabel CarCompany = new JLabel("Car Company");
        CarCompany.setBounds(50, 240, 110, 25 );
        CarCompany.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(CarCompany);
        t3 = new JTextField();
        t3.setBounds(190, 240, 170, 25);
        t3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t3);
        
        JLabel model = new JLabel("Model Number");
        model.setBounds(50, 290, 110, 25 );
        model.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(model);
        t4 = new JTextField();
        t4.setBounds(190, 290, 170, 25);
        t4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t4);
        
        JLabel available = new JLabel("Availability");
        available.setBounds(50, 340, 110, 25 );
        available.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(available);
        c1 = new JComboBox(new String[]{"Available", "Busy"});
        c1.setBackground(Color.white);
        c1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        c1.setBounds(190, 340, 170, 25);
        c1.setFocusable(false);
        add(c1);
        
        
        JLabel location = new JLabel("Location");
        location.setBounds(50, 390, 110, 25 );
        location.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(location);
        t5 = new JTextField();
        t5.setBounds(190, 390, 170, 25);
        t5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t5);
        
        b1 = new JButton("Add Driver");
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.setBounds(50, 450, 145, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b1.addActionListener(this);
        b1.setFocusPainted(false);
        add(b1);
        
        b2 = new JButton("Cancel");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(215, 450, 145, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b2.addActionListener(this);
        b2.setFocusPainted(false);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/eleven.jpg"));
        Image I = i1.getImage().getScaledInstance(500, 440, Image.SCALE_DEFAULT);
        i1 = new ImageIcon(I);
        JLabel image = new JLabel(i1);
        image.setBounds(390, 40, 500, 440);
        add(image);
        
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/car.png"));
        Image Ic = ic.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        setIconImage(Ic);
        
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(505,210, 930, 570);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    public void clearAllFields(){
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        c1.setSelectedIndex(0);
        bg.clearSelection();
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            
            try{
                String name = t1.getText();
                int age = Integer.parseInt(t2.getText());
                String gender="";
                if(r1.isSelected()){
                    gender = "Male";
                }else if(r2.isSelected()){
                    gender = "Female";
                }
                String company = t3.getText();
                String model = t4.getText();
                String available = c1.getSelectedItem().toString();
                String location = t5.getText();
                String contact = t6.getText();
                
                if( name.equals("") || gender.equals("") || company.equals("") || model.equals("")
                        || location.equals("") ){
                    throw new Exception("All Fields Required!");
                }else if(age < 18 || age > 80){
                    throw new Exception("Invalid Age! \n ( 18 <= Age <= 80)");
                }else if(!contact.matches("^[0-9]{10}$")){
                    throw new Exception("Invalid Contact Number!");
                }
                else {

                    Conn c = new Conn();
                    String query = "Insert into drivers(name, age, gender,contact, CarCompany, model, availability, location) values( ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pt = c.con.prepareStatement(query);
                    pt.setString(1, name);
                    pt.setInt(2, age);
                    pt.setString(3, gender);
                    pt.setString(4, contact);
                    pt.setString(5, company);
                    pt.setString(6, model);
                    pt.setString(7, available);
                    pt.setString(8, location);

                    int surity = JOptionPane.showConfirmDialog(rootPane, "Please Confirm!");
                    if (surity == JOptionPane.YES_OPTION) {
                        int result = pt.executeUpdate();
                        if (result == 1) {
                            JOptionPane.showMessageDialog(rootPane, "Driver Added SuccessFully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            clearAllFields();
                        } else {
                            throw new Exception("Database Related Error");
                        }

                    }  
                }
                
                
            }catch(NumberFormatException e1){
                JOptionPane.showMessageDialog(rootPane, "Invalid Number Input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch(Exception e2){
                System.out.println(e2.getMessage());
                JOptionPane.showMessageDialog(rootPane, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            dispose();
        }
    }
    
    public static void main(String[] args) {
        new AddDriver();
    }
    
}
