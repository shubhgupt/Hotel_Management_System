
package hotel_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author shubh
 */
public class Hotel_Management_System extends JFrame implements ActionListener{
    
    public Hotel_Management_System(){
        super("Hotel Management System");
        
        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/first.jpg"));
        JLabel l1 = new JLabel(I1);
        l1.setBounds(0,0,1366, 565);
        add(l1);
        
        JLabel l2 = new JLabel("Hotel Management System");
        l2.setBounds(20,450, 800, 80);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("serif", Font.PLAIN, 70));
        l1.add(l2);
        
        JButton b1 = new JButton("Next");
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        b1.setBounds(1150, 480, 150, 40 );
        b1.setFocusPainted(false);
        b1.addActionListener(this);
        l1.add(b1);
        
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/hotel.png"));
        Image Ic = ic.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        setIconImage(Ic);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        // This function sets location and size both simultaneously.
        setBounds(300, 150, 1366, 585);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while(true){
            l2.setVisible(false);
            try{
                Thread.sleep(500);
            }catch(Exception e){}
            l2.setVisible(true);
            try{
                Thread.sleep(500);
            }catch(Exception e){}
        }
    }
    
    public void actionPerformed(ActionEvent e){
        new Login();
        dispose();
    }
    
    public static void main(String[] args) {
        new Hotel_Management_System();
    }
    
}
