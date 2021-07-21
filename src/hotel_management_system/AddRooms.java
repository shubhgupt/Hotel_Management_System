
package hotel_management_system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class AddRooms extends JFrame implements ActionListener{
    
    JTextField t1,t2;
    JComboBox c1,c2,c3;
    JButton b1,b2;
    
    
    AddRooms(){
        super("Add Rooms");
//        JLabel l1 = new JLabel("Add Rooms");
//        l1.setFont(new Font("Tahoma", Font.BOLD, 17));
//        l1.setBounds(350, 10, 100, 30);
//        add(l1);
        
        JLabel room = new JLabel("Room Number");
        room.setBounds(50, 40, 120, 25);
        room.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(room);
        t1 = new JTextField();
        t1.setBounds(190, 40, 170, 25);
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t1);
        
        JLabel available = new JLabel("Availablity");
        available.setBounds(50, 90, 120, 25);
        available.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(available);
        c1 = new JComboBox(new String[]{"Available", "Occupied" });
        c1.setBackground(Color.white);
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c1.setBounds(190, 90, 170, 25);
        add(c1);
        
        JLabel status = new JLabel("Cleaning Status");
        status.setBounds(50, 140, 120, 25);
        status.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(status);
        c2 = new JComboBox(new String[]{"Cleaned", "Uncleaned" });
        c2.setBackground(Color.white);
        c2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c2.setBounds(190, 140, 170, 25);
        add(c2);
        
        JLabel price = new JLabel("Price");
        price.setBounds(50, 190, 120, 25);
        price.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(price);
        t2 = new JTextField();
        t2.setBounds(190, 190, 170, 25);
        t2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t2);
        
        JLabel type = new JLabel("Bed Type");
        type.setBounds(50, 240, 120, 25);
        type.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(type);
        c3 = new JComboBox(new String[]{"Single Bed", "Double Bed" });
        c3.setBackground(Color.white);
        c3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c3.setBounds(190, 240, 170, 25);
        add(c3);
        
        b1 = new JButton("Add Room");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(50, 300, 145, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b1.addActionListener(this);
        b1.setFocusPainted(false);
        add(b1);
        
        b2 = new JButton("Cancel");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(220, 300, 145, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b2.addActionListener(this);
        b2.setFocusPainted(false);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/twelve.jpg"));
        Image I = i1.getImage().getScaledInstance(430, 300, Image.SCALE_DEFAULT);
        i1 = new ImageIcon(I);
        JLabel image = new JLabel(i1);
        image.setBounds(395, 40, 430, 300);
        add(image);
        
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/door.png"));
        Image Ic = ic.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        setIconImage(Ic);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(600, 250, 870, 425 );
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void clearAllFields(){
        t1.setText("");
        t2.setText("");
        c1.setSelectedIndex(0);
        c2.setSelectedIndex(0);
        c3.setSelectedIndex(0);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            try{
                int room = Integer.parseInt(t1.getText());
                String available = c1.getSelectedItem().toString();
                String status = c2.getSelectedItem().toString();
                double price = Double.parseDouble(t2.getText());
                String type = c3.getSelectedItem().toString();
                
                Conn c = new Conn();
                String query1 = "Select * from rooms where roomnumber = '"+room+"'";
                ResultSet rs = c.st.executeQuery(query1);
                if(rs.next()){
                    JOptionPane.showMessageDialog(rootPane, "Room Already Exist!", "Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    String query = "Insert into rooms values( ?, ?, ?, ?, ?)";
                    PreparedStatement pt = c.con.prepareStatement(query);
                    pt.setInt(1, room);
                    pt.setString(2, available);
                    pt.setString(3, status);
                    pt.setDouble(4, price);
                    pt.setString(5, type);

                    int surity = JOptionPane.showConfirmDialog(rootPane, "Please Confirm!");
                    if (surity == JOptionPane.YES_OPTION) {
                        int result = pt.executeUpdate();
                        if (result == 1) {
                            JOptionPane.showMessageDialog(rootPane, "Room Added SuccessFully!", "Success", JOptionPane.PLAIN_MESSAGE);
                            clearAllFields();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
                
                
            }catch(NumberFormatException e1){
                JOptionPane.showMessageDialog(rootPane, "Invalid Number Input!", "Error", JOptionPane.ERROR_MESSAGE);
            }catch(Exception e2){
                System.out.println(e2.getMessage());
                JOptionPane.showMessageDialog(rootPane, "Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            dispose();
        }
    }
    
    public static void main(String[] args) {
        new AddRooms();
    }
    
}
