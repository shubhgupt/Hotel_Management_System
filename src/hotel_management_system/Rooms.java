
package hotel_management_system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class Rooms extends JFrame implements ActionListener{
    
    JButton b1,b2;
    JTable t;
    
    Rooms(){
        JPanel p1 = new JPanel();
        p1.setBounds(0,40, 620, 600);
        p1.setBackground(Color.white);
        p1.setLayout(null);
        add(p1);
        
        t = new JTable();
        t.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t.getTableHeader().setBackground(Color.white);
        t.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
        
        JScrollPane sp = new JScrollPane(t);
        sp.setBounds(0,0,620,500);
        sp.getViewport().setBackground(Color.white);
        p1.add(sp);
        
        b1 = new JButton("Load Data");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(150, 540, 150, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b1.setFocusPainted(false);
        b1.addActionListener(this);
        p1.add(b1);

        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(320, 540, 150, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b2.setFocusPainted(false);
        b2.addActionListener(this);
        p1.add(b2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/eight.jpg"));
        Image I1 = i1.getImage().getScaledInstance(580, 560, Image.SCALE_DEFAULT);
        i1 = new ImageIcon(I1);
        JLabel image = new JLabel(i1);
        image.setBounds(630, 40, 580, 560);
        add(image);
        
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/beds.png"));
        Image Ic = ic.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        JLabel frameIcon = new JLabel(new ImageIcon(Ic));
        frameIcon.setBounds(0,0, 30, 30);
        add(frameIcon);
        
        JLabel title = new JLabel("Rooms Information");
        title.setFont(new Font("serif", Font.PLAIN, 18 ));
        title.setBounds(40, 0, 200, 30);
        add(title);
        
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);
        setBounds(400, 150, 1240, 650);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            try{
                
                Conn c = new Conn();
                String query = "Select * from rooms Order By RoomNumber";
                ResultSet rs = c.st.executeQuery(query);
                if(rs.next()){
                    rs = c.st.executeQuery(query);
                    t.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    t.setModel(DbUtils.resultSetToTableModel(rs));
                    
                }else{
                    JOptionPane.showMessageDialog(rootPane,"Unable to fetch Data!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(rootPane,"Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            dispose();
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new Rooms();
    }
}
