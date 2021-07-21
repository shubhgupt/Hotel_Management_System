
package hotel_management_system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.PreparedStatement;
import javax.swing.*;


public class AddEmployee extends JFrame implements ActionListener{
    
    JTextField t1,t2,t3,t4,t5,t6;
    JRadioButton r1,r2;
    JComboBox c1;
    JButton b, b2;
    ButtonGroup bg;
    
    AddEmployee(){
        super("Add Employee");
        JLabel name = new JLabel("Name");
        name.setBounds(50, 40, 80, 25);
        name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(name);
        t1 = new JTextField();
        t1.setBounds(150, 40, 180, 25);
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t1);
        
        JLabel age = new JLabel("Age");
        age.setBounds(50, 80, 80, 25);
        age.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(age);
        t2 = new JTextField();
        t2.setBounds(150, 80, 180, 25);
        t2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t2);
        
        JLabel gender = new JLabel("Gender");
        gender.setBounds(50, 120, 80, 25);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(gender);
        r1 = new JRadioButton("Male");
        r1.setBounds(150, 120, 90, 25);
        r1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        r1.setBackground(Color.white);
        r2 = new JRadioButton("Female");
        r2.setBounds(240, 120, 90, 25);
        r2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        r2.setBackground(Color.white);
        
        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        add(r1);
        add(r2);
        
        
        JLabel job = new JLabel("Job");
        job.setBounds(50, 160, 80, 25);
        job.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(job);
        
        String []listItems = {"Front Desk Clerks","Forters","Housekeeping", "Kitchen Staff", "Room Service", "Waiter/Waitress", "Manager", "Accountant", "Chef"};
        c1 = new JComboBox(listItems);
        c1.setBounds(150, 160, 180, 25);
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c1.setBackground(Color.white);
        add(c1);
        
        JLabel salary = new JLabel("Salary");
        salary.setBounds(50, 200, 80, 25);
        salary.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(salary);
        t3 = new JTextField();
        t3.setBounds(150, 200, 180, 25);
        t3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t3);
        
        JLabel phone = new JLabel("Contact");
        phone.setBounds(50, 240, 80, 25);
        phone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(phone);
        t4 = new JTextField();
        t4.setBounds(150, 240, 180, 25);
        t4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t4);
        
        JLabel adhar = new JLabel("Adhaar");
        adhar.setBounds(50, 280, 80, 25);
        adhar.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(adhar);
        t5 = new JTextField();
        t5.setBounds(150, 280, 180, 25);
        t5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t5);
        
        JLabel email = new JLabel("Email");
        email.setBounds(50, 320, 80, 25);
        email.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(email);
        t6 = new JTextField();
        t6.setBounds(150, 320, 180, 25);
        t6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t6);
        
        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/tenth.jpg"));
        Image i = I1.getImage().getScaledInstance(500, 500 , Image.SCALE_DEFAULT );
        I1 = new ImageIcon(i);
        JLabel image = new JLabel(I1);
        image.setBounds(350, -30, 500, 500);
        add(image);
        
        b = new JButton("Add Employee");
        b.setBackground(Color.black);
        b.setForeground(Color.white);
        b.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b.setBounds(50, 380, 130, 30);
        b.addActionListener(this);
        b.setFocusPainted(false);
        add(b);
        
        b2 =new JButton("Cancel");
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b2.setBounds(200, 380, 130, 30);
        b2.addActionListener(this);
        b2.setFocusPainted(false);
        add(b2);
        
        
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/employee.png"));
        Image Ic = ic.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        setIconImage(Ic);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(600, 250, 850, 510 );
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
        if(ae.getSource() == b){
            
            try{
                String name = t1.getText();
                int age = Integer.parseInt(t2.getText());
                String gender= "";
                if(r1.isSelected()){
                    gender = "Male";
                }else if(r2.isSelected()){
                    gender = "Female";
                }
                String job = c1.getSelectedItem().toString();
                double salary = Double.parseDouble(t3.getText());
                long  contact = Long.parseLong(t4.getText());
                long adhaar = Long.parseLong(t5.getText());
                String email = t6.getText();
                EmailValidator emailValidator = new EmailValidator();
                
                if(name.equals("")){
                    JOptionPane.showMessageDialog(rootPane,"Name Field is Empty!", "Error", JOptionPane.ERROR_MESSAGE);
                }else if(age < 18 || age > 70){
                    JOptionPane.showMessageDialog(rootPane,"Age is Invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                }else if(gender.equals("")){
                    JOptionPane.showMessageDialog(rootPane,"Select Gender!", "Error", JOptionPane.ERROR_MESSAGE);
                }else if(("" + contact).length() != 10){
                    JOptionPane.showMessageDialog(rootPane,"Invalid Contact!", "Error", JOptionPane.ERROR_MESSAGE);
                }else if(("" + adhaar).length() != 12){
                    JOptionPane.showMessageDialog(rootPane,"Invalid Adhaar Number!", "Error", JOptionPane.ERROR_MESSAGE);
                }else if(!emailValidator.validate(email)){
                    JOptionPane.showMessageDialog(rootPane,"Invalid Email!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    Conn c = new Conn();
                    String query = "Insert into employee( name, age, gender, job, salary, contact, adhaar, email) values(?, ?, ?, ?, ?, ?, ? , ?)";
                    PreparedStatement pt = c.con.prepareStatement(query);
                    pt.setString(1, name);
                    pt.setInt(2, age);
                    pt.setString(3, gender);
                    pt.setString(4, job);
                    pt.setDouble(5, salary);
                    pt.setLong(6, contact);
                    pt.setLong(7, adhaar);
                    pt.setString(8, email);
                    
                    
                    int selectedOption = JOptionPane.showConfirmDialog(rootPane, "Are You Sure?");
                    if(selectedOption == JOptionPane.YES_OPTION){
                        int result = pt.executeUpdate();
                        if (result == 1) {
                            JOptionPane.showMessageDialog(rootPane, "Employee Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            clearAllFields();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(rootPane,"Invalid Number Input!", "Error", JOptionPane.ERROR_MESSAGE);
            }catch(Exception e){
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(rootPane,"Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new AddEmployee();
    }
}
