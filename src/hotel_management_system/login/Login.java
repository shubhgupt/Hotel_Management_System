package hotel_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class Login extends JFrame implements ActionListener{
    
    private JLabel l1, l2;
    private JTextField t1;
    private JPasswordField p1;
    private JButton b1, b2;
    private JLabel visibleLabel, invisibleLabel;
    
    Login(){
        super("Login");
        l1 = new JLabel("UserName: ");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        l1.setBounds(40, 20, 100, 25);
        add(l1);
        
        l2 = new JLabel("Password: ");
        l2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        l2.setBounds(40, 70, 100, 25);
        add(l2);
        
        t1 = new JTextField();
        t1.setBounds(160, 20, 160, 25);
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(t1);
        
        p1 = new JPasswordField();
        p1.setBounds(160, 70, 160, 25);
        add(p1);
        
        ImageIcon visible = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/visible.png"));
        Image visibleImage = visible.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT );
        visible = new ImageIcon(visibleImage);
        visibleLabel = new JLabel(visible);
        visibleLabel.setBounds(135, 70, 25, 25);
        add(visibleLabel);
        visibleLabel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                visibleLabelPressed(evt);
            }
        });

        
        ImageIcon invisible = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/invisible.png"));
        Image invisibleImage = invisible.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT );
        invisible = new ImageIcon(invisibleImage);
        invisibleLabel = new JLabel(invisible);
        invisibleLabel.setBounds(135, 70, 25, 25);
        invisibleLabel.setVisible(false);
        add(invisibleLabel);
        invisibleLabel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                invisibleLabelPressed(evt);
            }
        });
        
        b1 = new JButton("Login");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(40, 150, 130, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b1.addActionListener(this);
        add(b1);
        
        b2 = new JButton("Cancel");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(190, 150, 130, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b2.addActionListener(this);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(340, 20, 200, 180);
        add(l3);
        
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/login.png"));
        Image Ic = ic.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        setIconImage(Ic);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(600, 250, 550, 300);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void invisibleLabelPressed(MouseEvent evt){
        invisibleLabel.setVisible(false);
        visibleLabel.setVisible(true);
        p1.setEchoChar('*');
        p1.setFont(new Font("Tahoma", Font.PLAIN, 15));
    }
    
    public void visibleLabelPressed(MouseEvent evt){
        visibleLabel.setVisible(false);
        invisibleLabel.setVisible(true);
        p1.setEchoChar((char)0);
        p1.setFont(new Font("Tahoma", Font.PLAIN, 15));
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            String username = t1.getText();
            String password = p1.getText();
            if(username.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(rootPane,"Please Fill The Username and Passoword First", "Error", JOptionPane.ERROR_MESSAGE );
            }else{
                Conn c = new Conn();
                
//                String query = "Select * from Login where username = '"+username+"' and password = '"+ password+"'";
                String query = "Select * from login where username = ? and password = ?";
                
                try{
                    PreparedStatement pt = c.con.prepareStatement(query);
                    pt.setString(1, username);
                    pt.setString(2, password);
                    ResultSet rs = pt.executeQuery();
                    
                    if(rs.next()){
                        new Dashboard().setVisible(true);
                        this.setVisible(false);
                        
                    }else{
                        JOptionPane.showMessageDialog(rootPane,"Invalid Username and Password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            
        }else{
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
}
