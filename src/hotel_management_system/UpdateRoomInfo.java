package hotel_management_system;

/**
 *
 * @author kanha
 */
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class UpdateRoomInfo extends JFrame implements ActionListener{
    
    private Choice c1,c2,c3,c4;
    private Conn c;
    private JTextField t1;
    private JButton b1,b2;
    private String roomQuery1 = "SELECT roomnumber from rooms where roomnumber Not In (Select roomNumber from customers)";
    private String roomQuery2 = "Select RoomNumber from customers where customerId =";
    
    UpdateRoomInfo(){
        JPanel p1 = new JPanel();
        p1.setBounds(0,50, 200, 250);
        p1.setLayout(null);
        p1.setBackground(Color.white);
        add(p1);
        
        JLabel id = new  JLabel("Guest Id");
        id.setBounds(50, 0, 150, 25);
        id.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(id);
        
        JLabel number = new  JLabel("Room Number");
        number.setBounds(50, 50, 150, 25);
        number.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(number);
        
        JLabel available = new  JLabel("Availability");
        available.setBounds(50, 100, 150, 25);
        available.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(available);
        
        JLabel status = new  JLabel("Clean Status");
        status.setBounds(50, 150, 150, 25);
        status.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(status);
        
        JLabel price = new  JLabel("Price");
        price.setBounds(50, 200, 100, 25);
        price.setFont(new Font("Tahoma", Font.PLAIN, 17));
        p1.add(price);
        
        JPanel p2 = new JPanel();
        p2.setBounds(200,50, 180, 250);
        p2.setLayout(null);
        p2.setBackground(Color.white);
        add(p2);
        
        t1 = new JTextField();
        t1.setBounds(0, 200, 180, 25);
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t1.setBackground(Color.white);
        t1.setEditable(false);
        p2.add(t1);
        
        c4 = new Choice();
        c4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c4.setBackground(Color.white);
        c4.setBounds(0, 150, 180, 25);
        c4.setFocusable(false);
        c4.add("Cleaned");
        c4.add("Uncleaned");
        p2.add(c4);
        
        c3 = new Choice();
        c3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c3.setBackground(Color.white);
        c3.setBounds(0, 100, 180, 25);
        c3.setFocusable(false);
        c3.add("Available");
        c3.add("Occupied");
        p2.add(c3);
        
        c2 = new Choice();
        c2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c2.setBackground(Color.white);
        c2.setBounds(0, 50, 180, 25);
        c2.setFocusable(false);
        p2.add(c2);
        c2.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                int roomNumber = Integer.parseInt((String) e.getItem()) ;
                selectElementFromC3C4AndT1(roomNumber);
            }
        });
        
        
        c1 = new Choice();
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c1.setBackground(Color.white);
        c1.setBounds(0, 0, 180, 25);
        c1.setFocusable(false);
        addElementsToC1();
        p2.add(c1);
        c1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                String item = (String) e.getItem();
                if(item.equals("Update Rooms Only")){
                    addElementsToC2(roomQuery1);
                }
                else{
                    addElementsToC2(roomQuery2+ Integer.parseInt(item));
                }
            }
        });
        
        ImageIcon editable = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/edit.png"));
        Image edit = editable.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        JLabel editIcon = new JLabel(new ImageIcon(edit));
        editIcon.setBounds(173, 200, 25, 25);
        p1.add(editIcon);
        editIcon.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                if(c1.getSelectedItem().equals("Update Rooms Only")){
                    String password = JOptionPane.showInputDialog(rootPane, "Enter Admin Password!", "Confirm Admin", HEIGHT);
                    if(password != null)
                        validatePassword(password);
                }
            }
        });
        
        b1 =new JButton("Update");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(60, 310, 145, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b1.addActionListener(this);
        b1.setFocusPainted(false);
        add(b1);
        
        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(230, 310, 145, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b2.addActionListener(this);
        b2.setFocusPainted(false);
        add(b2);
        
//        setUndecorated(true);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setBounds(600, 250, 900, 450 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void validatePassword(String password){
        try{
         
            c = new Conn();
            String query = "Select * from login where password ='" + password+"'";
            ResultSet rs = c.st.executeQuery(query);
            if(rs.next()){
                t1.setEditable(true);
            }else{
                JOptionPane.showMessageDialog(rootPane,"Wrong Password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane,"Technical Issue!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void selectElementFromC3C4AndT1(int roomNumber){
        try{
            
            c = new Conn();
            String query = "Select availability, status, price from rooms where roomNumber ="+roomNumber;
            ResultSet rs = c.st.executeQuery(query);
            rs.next();
            c3.select(rs.getString(1));
            c4.select(rs.getString(2));
            t1.setText(""+rs.getDouble(3));
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane,"Technical Issue!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addElementsToC2(String query){
        
        try{
            c = new Conn();
            ResultSet rs = c.st.executeQuery(query);
            c2.removeAll();
            boolean flag = true;
            while(rs.next()){
                if(flag){
                    selectElementFromC3C4AndT1(rs.getInt(1));
                    flag = false;
                }
                c2.add(""+rs.getInt(1));
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane,"Technical Issue!", "Error", JOptionPane.ERROR_MESSAGE);
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
            c1.add("Update Rooms Only");
            
            if(id == 0){
                addElementsToC2(roomQuery1);
            }else{
                addElementsToC2(roomQuery2+id);
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
                String available = c3.getSelectedItem();
                String status = c4.getSelectedItem();
                Double price = Double.parseDouble(t1.getText());
                int room = Integer.parseInt(c2.getSelectedItem());
                c = new Conn();
                String query = "Update rooms Set Availability = ?, Status = ?, price = ? where roomNumber = ?";
                PreparedStatement pt = c.con.prepareStatement(query);
                pt.setString(1,available);
                pt.setString(2, status);
                pt.setDouble(3, price);
                pt.setInt(4, room);
                
                int result = pt.executeUpdate();
                if(result == 0){
                    throw new Exception();
                }else{
                    JOptionPane.showMessageDialog(rootPane,"Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new UpdateRoomInfo();
                }
                
                
            }catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane,"Technical Issue!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            dispose();
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new UpdateRoomInfo();
    }
}
