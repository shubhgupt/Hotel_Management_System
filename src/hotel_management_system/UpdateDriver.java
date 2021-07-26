package hotel_management_system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class UpdateDriver extends JFrame implements ActionListener{
    
    private JComboBox c1;
    private Choice c2;
    private JTextField t1,t2,t3,t4, t5,editor;
    private JButton b1,b2;
    private Conn c;
    private boolean isDataPresent=false;
    
    UpdateDriver(){
        
        ImageIcon frameicon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/wheel.png"));
        Image frameImage = frameicon.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        JLabel frameimage = new JLabel(new ImageIcon(frameImage));
        frameimage.setBounds(0,0,30,30);
        add(frameimage);
        
        JLabel title = new JLabel("Update Driver Information");
        title.setBounds(35, 3, 250, 25);
        title.setFont(new Font("SERIF", Font.PLAIN, 18));
        add(title);
        
        JPanel main = new JPanel();
        main.setBounds(0,30, 930, 500);
        main.setLayout(null);
        main.setBackground(Color.white);
        add(main);
        
        JPanel labels = new JPanel();
        labels.setBounds(0,30, 200, 350);
        labels.setLayout(null);
        labels.setBackground(Color.white);
        main.add(labels);
        
        JPanel fields = new JPanel();
        fields.setBounds(200,30, 180, 350);
        fields.setLayout(null);
        fields.setBackground(Color.white);
        main.add(fields);
        
        JLabel l1 = new JLabel("Id:");
        l1.setBounds(50,0, 150, 25 );
        l1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labels.add(l1);
        
        JLabel l2 = new JLabel("Name:");
        l2.setBounds(50,50, 150, 25 );
        l2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labels.add(l2);
        
        JLabel l3 = new JLabel("Contact:");
        l3.setBounds(50, 100, 150, 25 );
        l3.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labels.add(l3);
        
        JLabel l4 = new JLabel("Car Company:");
        l4.setBounds(50, 150, 150, 25 );
        l4.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labels.add(l4);
        
        JLabel l5 = new JLabel("Car Model:");
        l5.setBounds(50, 200, 150, 25 );
        l5.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labels.add(l5);
        
        JLabel l6 = new JLabel("Availability:");
        l6.setBounds(50,250, 150, 25 );
        l6.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labels.add(l6);
        
        JLabel l7 = new JLabel("Location:");
        l7.setBounds(50, 300, 150, 25 );
        l7.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labels.add(l7);
        
        t1 =new JTextField();
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t1.setBackground(Color.white);
        t1.setEditable(false);
        t1.setBounds(0,50, 180, 25);
        fields.add(t1);
        
        t2 =new JTextField();
        t2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t2.setBackground(Color.white);
        t2.setBounds(0,100, 180, 25);
        fields.add(t2);
        
        t3 =new JTextField();
        t3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t3.setBackground(Color.white);
        t3.setBounds(0, 150, 180, 25);
        fields.add(t3);
        
        t4 =new JTextField();
        t4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t4.setBackground(Color.white);
        t4.setBounds(0, 200, 180, 25);
        fields.add(t4);
        
        c2 = new Choice();
        c2.setBackground(Color.white);
        c2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c2.setBounds(0,250, 180, 25);
        c2.setFocusable(false);
        c2.add("Available");
        c2.add("Busy");
        fields.add(c2);
        
        
        t5 =new JTextField();
        t5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t5.setBackground(Color.white);
        t5.setBounds(0,300, 180, 25);
        fields.add(t5);
        
        c1 = new JComboBox();
        c1.setBackground(Color.white);
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c1.setEditable(true);
        c1.setBounds(0,0, 180, 25);
        addElementsToC1();
        AutoCompleteDecorator.decorate(c1);
        editor = (JTextField) c1.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent evt){
                if(evt.getKeyCode() == 10){
                    int id = Integer.parseInt(editor.getText());
                    displayAll(id);
                }
            }
        });
        fields.add(c1);
        
        ImageIcon searchIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/search.png"));
        Image searchImage = searchIcon.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        JLabel searchLabel = new JLabel(new ImageIcon(searchImage));
        searchLabel.setBounds(382, 30, 25, 25);
        main.add(searchLabel);
        searchLabel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                int id = Integer.parseInt(editor.getText());
                displayAll(id);
            }
        });
        
        
        b1 = new JButton("Update");
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.setBounds(60, 400, 145, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b1.addActionListener(this);
        b1.setFocusPainted(false);
        main.add(b1);
        
        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(225, 400, 145, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b2.addActionListener(this);
        b2.setFocusPainted(false);
        main.add(b2);
        
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/updateDriver.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(500, 430, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(440, 0, 500, 430);
        main.add(imageLabel);
        
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(505,210, 930, 520);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        if(!isDataPresent){
            JOptionPane.showMessageDialog(rootPane, "No Driver Present!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void displayAll(int id){
        try{
         
            c = new Conn();
            String query = "Select * from drivers where id= "+id;
            ResultSet rs = c.st.executeQuery(query);
            if(rs.next()){
                
                t1.setText(rs.getString("name"));
                t2.setText(rs.getString("Contact"));
                t3.setText(rs.getString("CarCompany"));
                t4.setText(rs.getString("model"));
                c2.select(rs.getString("availability"));
                t5.setText(rs.getString("location"));
            }else{
                throw new Exception("Invalid Id!");
            }
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addElementsToC1(){
        try{
            
            c = new Conn();
            String query = "Select id from drivers order by id";
            ResultSet rs = c.st.executeQuery(query);
            while(rs.next()){
                isDataPresent = true;
                c1.addItem(rs.getInt(1));
            }
            if(isDataPresent){
                displayAll((Integer) c1.getSelectedItem());
            }
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == b1){
            
            try {
                
                int id = Integer.parseInt(editor.getText());
                String contact = t2.getText();
                String company = t3.getText();
                String model = t4.getText();
                String location = t5.getText();
                String availability = c2.getSelectedItem();
                
                if(company.equals("") || model.equals("") || location.equals("")){
                    throw new Exception("Fields Are Empty!");
                }
                if(!contact.matches("^[0-9]{10}$")){
                    throw new Exception("Invalid contact Number!");
                }
                
                c = new Conn();
                String query = "Update drivers Set contact = ?, carCompany = ?, model = ?, availability = ?, location= ? where id = ?";

                PreparedStatement pt = c.con.prepareStatement(query);
                pt.setString(1, contact);
                pt.setString(2, company);
                pt.setString(3, model);
                pt.setString(4, availability);
                pt.setString(5, location);
                pt.setInt(6, id);
                
                int result = pt.executeUpdate();
                if(result == 1){
                    JOptionPane.showMessageDialog(rootPane, "Data Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new Reception();
                }else{
                    throw new Exception("Invalid Driver Id!");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        else{
            dispose();
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new UpdateDriver();
    }
}
