package hotel_management_system;


import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.Date;

public class UpdateCheckIn extends JFrame implements ActionListener{
    
    private JTextField t1,t2,t3,t4,t5;
    private Choice c1,c2;
    private Conn c;
    private JButton b1, b2;
    
    UpdateCheckIn(){
        
        JPanel main = new JPanel();
        main.setBounds(0, 70, 1000, 400);
        main.setBackground(Color.white);
        main.setLayout(null);
        add(main);
        
        
        JPanel label = new JPanel();
        label.setBounds(0,0, 200, 350);
        label.setBackground(Color.white);
        label.setLayout(null);
        main.add(label);
        
        JLabel id = new  JLabel("Guest Id");
        id.setBounds(50, 0, 150, 25);
        id.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(id);
        
        JLabel room = new  JLabel("Room Number");
        room.setBounds(50, 50, 150, 25);
        room.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(room);
        
        JLabel name = new  JLabel("Guest Name");
        name.setBounds(50, 100, 150, 25);
        name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(name);
        
        JLabel status = new  JLabel("Checked In");
        status.setBounds(50, 150, 150, 25);
        status.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(status);
        
        JLabel paid = new  JLabel("Amount Paid(Rs)");
        paid.setBounds(50, 200, 150, 25);
        paid.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(paid);
        
        JLabel due = new  JLabel("Amount Due(Rs)");
        due.setBounds(50, 250, 150, 25);
        due.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(due);
        
        JLabel paying = new  JLabel("Depositing(Rs)");
        paying.setBounds(50, 300, 150, 25);
        paying.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(paying);
        
        JPanel fields = new JPanel();
        fields.setBounds(230,0, 180, 350);
        fields.setBackground(Color.white);
        fields.setLayout(null);
        main.add(fields);
        
        t1 = new JTextField();
        t1.setBounds(0, 50, 180, 25);
        t1.setBackground(Color.white);
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t1.setEditable(false);
        fields.add(t1);
        
        t2 = new JTextField();
        t2.setBounds(0, 100, 180, 25);
        t2.setBackground(Color.white);
        t2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t2.setEditable(false);
        fields.add(t2);
        
        t3 = new JTextField();
        t3.setBounds(0, 200, 180, 25);
        t3.setBackground(Color.white);
        t3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t3.setEditable(false);
        fields.add(t3);
        
        t4 = new JTextField();
        t4.setBounds(0, 250, 180, 25);
        t4.setBackground(Color.white);
        t4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t4.setEditable(false);
        fields.add(t4);
        
        t5 = new JTextField();
        t5.setBounds(0, 300, 180, 25);
        t5.setBackground(Color.white);
        t5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        fields.add(t5);
        
        c2 = new Choice();
        c2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c2.setBackground(Color.white);
        c2.setBounds(0, 150, 180, 25);
        c2.setFocusable(false);
        c2.add("Yes");
        c2.add("No");
        fields.add(c2);
        
        c1 = new Choice();
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c1.setBackground(Color.white);
        c1.setBounds(0, 0, 180, 25);
        c1.setFocusable(false);
        addElementsToC1();
        fields.add(c1);
        c1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                int customerId = Integer.parseInt((String) e.getItem());
                fillAllFields(customerId);
            }
        });
        
        b1 =new JButton("Update");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(75, 360, 145, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b1.addActionListener(this);
        b1.setFocusPainted(false);
        main.add(b1);
        
        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(245, 360, 145, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b2.addActionListener(this);
        b2.setFocusPainted(false);
        main.add(b2);
        
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/nine.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(420, -20, 580, 390);
        main.add(imageLabel);
        
        ImageIcon frameicon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/customer.png"));
        Image frameImage = frameicon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        JLabel frameimage = new JLabel(new ImageIcon(frameImage));
        frameimage.setBounds(5,5,30,30);
        add(frameimage);
        
        JLabel title = new JLabel("Update Customer Information");
        title.setBounds(40, 10, 250, 25);
        title.setFont(new Font("SERIF", Font.PLAIN, 18));
        add(title);
        
        setUndecorated(true);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setBounds(550, 250, 1000, 500 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void fillAllFields(int customerId){
        try{
            
            c =new Conn();
            String query = "Select * from customers where customerId = "+customerId;
            ResultSet rs = c.st.executeQuery(query);
            rs.next();
            t1.setText(""+rs.getInt("RoomNumber"));
            t2.setText(rs.getString("Name"));
            c2.select(rs.getString("Check_In"));
            t3.setText(""+rs.getDouble("DepositAmount"));
            t4.setText(""+rs.getDouble("DueAmount"));
            if(rs.getDouble("DueAmount") == 0.0){
                t5.setEditable(false);
            }else{
                t5.setEditable(true);
            }
            if(rs.getString("Check_In").equals("Yes")){
                c2.setEnabled(false);
            }else{
                c2.setEnabled(true);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addElementsToC1(){
        try{
            c = new Conn();
            String query = "SELECT customerId from customers";
            ResultSet rs = c.st.executeQuery(query);
            boolean flag = true;
            int id= 0;
            while(rs.next()){
                if(flag){
                    id = rs.getInt(1);
                    flag = false;
                }
                c1.add(""+rs.getInt(1));
            }
            if(id == 0){
                JOptionPane.showMessageDialog(rootPane,"No Guests are Present!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                fillAllFields(id);
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane,"Technical Issue!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            
            try{
                int customerId = Integer.parseInt(c1.getSelectedItem());
                String status = c2.getSelectedItem();
                Double depositAmount = Double.parseDouble(t3.getText());
                Double dueAmount = Double.parseDouble(t4.getText());
                if (dueAmount != 0.0) {
                    Double depositing = Double.parseDouble(t5.getText());
                    depositAmount += depositing;
                    dueAmount -= depositing;
                }
                String today = "";
                if(dueAmount < 0){
                    throw new NumberFormatException();
                }
                if(status.equals("Yes")){
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    today = formatter.format(date);
                }
                
                c = new Conn();
                String query = "Update customers Set check_in = ?, depositAmount = ?, dueAmount = ?, Checked_On = ? where customerid= ?";
                PreparedStatement pt = c.con.prepareStatement(query);
                pt.setString(1, status);
                pt.setDouble(2, depositAmount);
                pt.setDouble(3, dueAmount);
                pt.setString(4, today);
                pt.setInt(5, customerId);
                
                int selectedOption = JOptionPane.showConfirmDialog(rootPane, "Are You Sure?");
                    if(selectedOption == JOptionPane.YES_OPTION){
                        int result = pt.executeUpdate();
                        if (result == 1) {
                            JOptionPane.showMessageDialog(rootPane, "Guest Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            new UpdateCheckIn();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(rootPane, "Invalid Deposit Amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "Technical Issue!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            
        }else{
            dispose();
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new UpdateCheckIn();
    }
    
}
