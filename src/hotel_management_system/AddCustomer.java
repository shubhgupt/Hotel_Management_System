package hotel_management_system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener{
    
    
    JTextField t1,t2,t3,t4,t5,t6;
    JRadioButton r1,r2;
    ButtonGroup bg;
    JButton b1,b2;
    Choice ch,c1,c2;
    Conn c;
    
    AddCustomer(){
        JPanel p1 = new JPanel();
        p1.setBounds(0,50, 200, 450);
        p1.setLayout(null);
        p1.setBackground(Color.white);
        add(p1);
        
        JLabel id = new  JLabel("Id");
        id.setBounds(50, 0, 150, 25);
        id.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(id);
        
        JLabel number = new  JLabel("Number");
        number.setBounds(50, 50, 150, 25);
        number.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(number);
        
        JLabel name = new  JLabel("Name");
        name.setBounds(50, 100, 150, 25);
        name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(name);
        
        JLabel Gender = new  JLabel("Gender");
        Gender.setBounds(50, 150, 150, 25);
        Gender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(Gender);
        
        JLabel Country = new  JLabel("Country");
        Country.setBounds(50, 200, 150, 25);
        Country.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(Country);
        
        JLabel room = new  JLabel("Room Number");
        room.setBounds(50, 250, 150, 25);
        room.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(room);
        
        JLabel price = new JLabel("Price");
        price.setBounds(50, 300, 150, 25);
        price.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(price);
        
        JLabel Checkin = new  JLabel("Checked-In");
        Checkin.setBounds(50, 350, 150, 25);
        Checkin.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(Checkin);
        
        JLabel Deposit = new  JLabel("Deposit");
        Deposit.setBounds(50, 400, 150, 25);
        Deposit.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(Deposit);
        
        
        JPanel p2 = new JPanel();
        p2.setBounds(200, 50, 180, 450);
        p2.setBackground(Color.white);
        p2.setLayout(null);
        add(p2);
        
        c1 =new Choice();
        addItemsToValidate();
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c1.setBackground(Color.white);
        c1.setFocusable(false);
        c1.setBounds(0,0, 180, 25);
        p2.add(c1);
        
        t1 = new JTextField();
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t1.setBounds(0, 50, 180, 25);
        p2.add(t1);
        
        t2 = new JTextField();
        t2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t2.setBounds(0, 100, 180, 25);
        p2.add(t2);
        
        r1 =new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        r1.setBackground(Color.white);
        r1.setBounds(0, 150, 90, 25);
        p2.add(r1);
        
        r2 =new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        r2.setBackground(Color.white);
        r2.setBounds(90, 150, 90, 25);
        p2.add(r2);
        
        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        
        t3 = new JTextField();
        t3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t3.setBounds(0, 200, 180, 25);
        p2.add(t3);
        
        t4 = new JTextField();
        t4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t4.setEditable(false);
        t4.setBounds(0, 300, 180, 25);
        t4.setBackground(Color.white);
        p2.add(t4);
        
        ch = new Choice();
        ch.setBounds(0, 250, 180, 25);
        ch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addElementsToChoice();
        ch.setFocusable(false);
        ch.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                addPrice();
            }
        });
        p2.add(ch);
        
        
        c2 = new Choice();
        c2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c2.setBounds(0, 350, 180, 25);
        c2.setFocusable(false);
        c2.add("Yes");
        c2.add("No");
        p2.add(c2);
        
        t6 = new JTextField();
        t6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t6.setBounds(0, 400, 180, 25);
        p2.add(t6);
        
        b1 =new JButton("Add Customer");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(55, 510, 145, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b1.addActionListener(this);
        b1.setFocusPainted(false);
        add(b1);
        
        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(225, 510, 145, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b2.addActionListener(this);
        b2.setFocusPainted(false);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/fifth.png"));
        Image I1 = i1.getImage().getScaledInstance(400, 360, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(I1));
        image.setBounds(400, 80, 400, 360);
        add(image);
        
        ImageIcon frameicon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/customer.png"));
        Image frameImage = frameicon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        JLabel frameimage = new JLabel(new ImageIcon(frameImage));
        frameimage.setBounds(5,5,30,30);
        add(frameimage);
        
        JLabel title = new JLabel("Add Customer");
        title.setBounds(40, 10, 150, 25);
        title.setFont(new Font("SERIF", Font.PLAIN, 18));
        add(title);
        
        setUndecorated(true);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setBounds(600, 250, 800, 600 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    private void addPrice(){
        try{
            c = new Conn();
            String roomNumber = ch.getSelectedItem();
//            System.out.println(roomNumber);
            String query = "Select * from rooms where RoomNumber = '"+roomNumber+"'";
            ResultSet rs = c.st.executeQuery(query);
            rs.next();
            Double price = rs.getDouble("Price");
            t4.setText(price.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,"Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    
    private void addItemsToValidate(){
        try{
            
            c = new Conn();
            String query = "Select * from validate";
            ResultSet rs = c.st.executeQuery(query);
            while(rs.next()){
                c1.add(rs.getString("ids"));
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,"Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    private void addElementsToChoice(){
        try{
            
            c = new Conn();
            String query = "Select * from rooms where availability = 'Available'";
            ResultSet rs = c.st.executeQuery(query);
            int value;
            boolean flag = true;
            while(rs.next()){
                if(flag){
                    String price = rs.getString("price");
                    t4.setText(""+price);
                    flag = false;
                }
                value = rs.getInt("roomnumber"); 
                ch.add(""+value);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,"Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }
    
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            try{
                String idProof = c1.getSelectedItem();
                String idNumber = t1.getText();
                String name = t2.getText();
                String gender = "";
                if(r1.isSelected()){
                    gender = "Male";
                }else if(r2.isSelected()){
                    gender = "Female";
                }
                String country = t3.getText();
                int room = Integer.parseInt(ch.getSelectedItem());
                double price = Double.parseDouble(t4.getText());
                String checkIn = c2.getSelectedItem();
                double deposit = Double.parseDouble(t6.getText());
                double dueAmount = price - deposit;
                String today = "";
                if(idNumber.isEmpty() || name.isEmpty() || country.isEmpty() || gender.isEmpty()){
                    JOptionPane.showMessageDialog(rootPane, "Fill All Fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(idProof.equalsIgnoreCase("Adhaar Id") && idNumber.length() != 12){
                    JOptionPane.showMessageDialog(rootPane, "Icorrect Adhaar Number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    
                    if(checkIn == "Yes"){
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date date =new Date();
                        today = formatter.format(date);
                    }
                    
                    
                    c = new Conn();
                    String generatedKeys[] = {"ID"};
                    String query = "Insert into customers(id_Proof, id_Number, name, gender, country, roomNumber, check_in, price, depositAmount, Due"
                            + "Amount, checked_on) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pt = c.con.prepareStatement(query, generatedKeys);
                    pt.setString(1, idProof);
                    pt.setString(2, idNumber);
                    pt.setString(3, name);
                    pt.setString(4, gender);
                    pt.setString(5, country);
                    pt.setInt(6, room);
                    pt.setString(7, checkIn);
                    pt.setDouble(8, price);
                    pt.setDouble(9, deposit);
                    pt.setDouble(10, dueAmount);
                    pt.setString(11, today);
                    
                    pt.executeUpdate();
                    ResultSet rs = pt.getGeneratedKeys();
                    String query2 = "Update rooms Set Availability = 'Occpied' where roomnumber =" + room;
                    int result = c.st.executeUpdate(query2);
                    if(rs.next() && result == 1){
                        long id = rs.getLong(1);
                        JOptionPane.showMessageDialog(rootPane," Customer Added Successfully!\n Customer Id is "+id, "Information", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new AddCustomer();
                    }
                    else{
                        JOptionPane.showMessageDialog(rootPane,"Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(rootPane,"Invalid Number Input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch(Exception e){
//                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(rootPane,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            dispose();
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new AddCustomer();
    }
    
}
