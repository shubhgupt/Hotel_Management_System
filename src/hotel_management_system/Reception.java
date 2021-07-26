
package hotel_management_system;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

public class Reception extends JFrame implements ActionListener{
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12,  b13, b14;
    
    Reception(){
        super("Reception");
        
        b1 = new JButton("New Guest Form");
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.setFocusPainted(false);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b1.setBounds(10, 30, 200, 30);
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Search Room");
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b2.setFocusPainted(false);
        b2.setBounds(10, 70, 200, 30);
        b2.addActionListener(this);
        add(b2);
        
        b3 = new JButton("Pick Up Service");
        b3.setBackground(Color.black);
        b3.setForeground(Color.white);
        b3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b3.setFocusPainted(false);
        b3.setBounds(10, 110, 200, 30);
        b3.addActionListener(this);
        add(b3);
        
        b4 = new JButton("Check Out");
        b4.setBackground(Color.black);
        b4.setForeground(Color.white);
        b4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b4.setFocusPainted(false);
        b4.setBounds(10, 150, 200, 30);
        b4.addActionListener(this);
        add(b4);
        
        b5 = new JButton("Update Check Status");
        b5.setBackground(Color.black);
        b5.setForeground(Color.white);
        b5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b5.setFocusPainted(false);
        b5.setBounds(10, 190, 200, 30);
        b5.addActionListener(this);
        add(b5);
        
        b6 = new JButton("Updata Room Status");
        b6.setBackground(Color.black);
        b6.setForeground(Color.white);
        b6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b6.setBounds(10, 230, 200, 30);
        b6.setFocusPainted(false);
        b6.addActionListener(this);
        add(b6);
        
        b7 = new JButton("Update Driver Info");
        b7.setBackground(Color.black);
        b7.setForeground(Color.white);
        b7.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b7.setBounds(10, 270, 200, 30);
        b7.setFocusPainted(false);
        b7.addActionListener(this);
        add(b7);
        
        b8 = new JButton("Update Department Info");
        b8.setBackground(Color.black);
        b8.setForeground(Color.white);
        b8.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b8.setBounds(10, 310, 200, 30);
        b8.setFocusPainted(false);
        b8.addActionListener(this);
        add(b8);
        
        b9 = new JButton("Guests Info");
        b9.setBackground(Color.black);
        b9.setForeground(Color.white);
        b9.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b9.setBounds(10, 350, 200, 30);
        b9.setFocusPainted(false);
        b9.addActionListener(this);
        add(b9);
        
        b10 = new JButton("Rooms Info");
        b10.setBackground(Color.black);
        b10.setForeground(Color.white);
        b10.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b10.setBounds(10, 390, 200, 30);
        b10.setFocusPainted(false);
        b10.addActionListener(this);
        add(b10);
        
        b11 = new JButton("Managers Info");
        b11.setBackground(Color.black);
        b11.setForeground(Color.white);
        b11.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b11.setBounds(10, 430, 200, 30);
        b11.setFocusPainted(false);
        b11.addActionListener(this);
        add(b11);
        
        b12 = new JButton("All Employees Info");
        b12.setBackground(Color.black);
        b12.setForeground(Color.white);
        b12.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b12.setBounds(10, 470, 200, 30);
        b12.setFocusPainted(false);
        b12.addActionListener(this);
        add(b12);
        
        b13 = new JButton("Department Info");
        b13.setBackground(Color.black);
        b13.setForeground(Color.white);
        b13.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b13.setBounds(10, 510, 200, 30);
        b13.setFocusPainted(false);
        b13.addActionListener(this);
        add(b13);
        
        b14 = new JButton("Guests History");
        b14.setBackground(Color.black);
        b14.setForeground(Color.white);
        b14.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b14.setBounds(10, 550, 200, 30);
        b14.setFocusPainted(false);
        b14.addActionListener(this);
        add(b14);
        
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/fourth.jpg"));
        Image I = i.getImage().getScaledInstance(590, 550, Image.SCALE_DEFAULT);
        i = new ImageIcon(I);
        JLabel image = new JLabel(i);
        image.setBounds(235, 30, 590, 550);
        add(image);
        
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/reception.png"));
        Image Ic = ic.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        setIconImage(Ic);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(520, 200, 870, 660 );
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            new AddCustomer();
        }
        else if(ae.getSource() == b2){
            new SearchRoom();
        }
        else if(ae.getSource() == b3){
            new PickUpService();
        }
        else if(ae.getSource() == b4){
            new CheckOut();
        }
        else if(ae.getSource() == b5){
            new UpdateCheckIn();
        }
        else if(ae.getSource() == b6){
            new UpdateRoomInfo();
        }
        else if(ae.getSource() == b7){
            new UpdateDriver();
        }
        else if(ae.getSource() == b8){
            new UpdateDepartments();
        }
        else if(ae.getSource() == b9){
            new CustomerInfo();
        }
        else if(ae.getSource() == b10){
            new Rooms();
        }
        else if(ae.getSource() == b11){
            new ManagerInfo();
        }
        else if(ae.getSource() == b12){
            new EmployeeInfo();
        }
        else if(ae.getSource() == b13){
            new Departments();
        }
        else if(ae.getSource() == b14){
            new GuestsHistory();
        }
        dispose();
    }
    
    public static void main(String[] args) {
        new Reception();
    }
}
