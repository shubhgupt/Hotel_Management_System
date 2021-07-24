
package hotel_management_system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener{
    JMenuBar mb;
    JMenu m1,m2;
    JMenuItem i1,i2,i3,i4;
    
    Dashboard(){
        super("Hotel Management System");
        
        mb = new JMenuBar();
        mb.setBounds(0, 0, 1950, 30);
        mb.setBackground(Color.white);
        add(mb);
        
        m1 = new JMenu("Home");
        m1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ImageIcon ic6 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/home2.png"));
        Image Ic6 = ic6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        m1.setIcon(new ImageIcon(Ic6));
        mb.add(m1);
        
        m2 = new JMenu("Admin");
        m2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ImageIcon ic7 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/admin.png"));
        Image Ic7 = ic7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        m2.setIcon(new ImageIcon(Ic7));
        mb.add(m2);
        
        i1 = new JMenuItem("Reception");
        i1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        i1.setBackground(Color.white);
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/reception.png"));
        Image Ic1 = ic.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        i1.setIcon(new ImageIcon(Ic1));
        i1.addActionListener(this);
        m1.add(i1);
        
        i2 = new JMenuItem("Add Employee");
        i2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        i2.setBackground(Color.white);
        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/employee.png"));
        Image Ic2 = ic2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        i2.setIcon(new ImageIcon(Ic2));
        i2.addActionListener(this);
        m2.add(i2);
        
        i3 = new JMenuItem("Add Rooms");
        i3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        i3.setBackground(Color.white);
        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/beds.png"));
        Image Ic3 = ic3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        i3.setIcon(new ImageIcon(Ic3));
        i3.addActionListener(this);
        m2.add(i3);
        
        i4 = new JMenuItem("Add Drivers");
        i4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        i4.setBackground(Color.white);
        ImageIcon ic4 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/car.png"));
        Image Ic4 = ic4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        i4.setIcon(new ImageIcon(Ic4));
        i4.addActionListener(this);
        m2.add(i4);
        
        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/third.jpg"));
        Image I2 = I1.getImage().getScaledInstance(1950, 1000, Image.SCALE_DEFAULT);
        I1 = new ImageIcon(I2);
        JLabel l1 = new JLabel(I1);
        l1.setBounds(0,0,1950, 1000);
        add(l1);
        
        JLabel l2 = new JLabel("The Taj Group Welcomes You");
        l2.setBounds(600, 60, 1000, 60);
        l2.setForeground(Color.white);
        l2.setFont(new Font("Tahoma", Font.PLAIN, 50));
        l1.add(l2);
        
        ImageIcon ic5 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/home.png"));
        Image Ic5 = ic5.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        setIconImage(Ic5);
        setLayout(null);
        setBounds(0, 0, 1950, 1000);
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Reception")){
            new Reception();
        }
        else if(e.getActionCommand().equals("Add Employee")){
            new AddEmployee();
        }else if(e.getActionCommand().equals("Add Rooms")){
            new AddRooms();
        }else if(e.getActionCommand().equals("Add Drivers")){
            new AddDriver();
        }
    }
    
    public static void main(String[] args) {
        new Dashboard();
    }
}
