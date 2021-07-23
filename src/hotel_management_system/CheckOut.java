package hotel_management_system;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class CheckOut extends JFrame implements ActionListener{
    
    private JComboBox c;
    private JTextField t, editor;
    private JButton b1,b2;
    private Conn cn;
    private boolean isDataPresent = false;
//    import com.mxrck.autocompleter.TextAutoCompleter;
    
    CheckOut(){
        
        JPanel main = new JPanel();
        main.setBounds(0, 80, 780, 300);
        main.setBackground(Color.white);
        main.setLayout(null);
        add(main);
        
        JPanel labels = new JPanel();
        labels.setBounds(0,0, 200, 100);
        labels.setBackground(Color.white);
        labels.setLayout(null);
        main.add(labels);
        
        JLabel id = new JLabel("Guest Id:");
        id.setFont(new Font("Tahoma", Font.PLAIN, 17));
        id.setBounds(50,0, 150, 25);
        labels.add(id);
        
        JLabel room = new JLabel("Room Number:");
        room.setFont(new Font("Tahoma", Font.PLAIN, 17));
        room.setBounds(50,50, 150, 25);
        labels.add(room);
        
        
        JPanel fields = new JPanel();
        fields.setBounds(200,0, 180, 100);
        fields.setBackground(Color.white);
        fields.setLayout(null);
        main.add(fields);
        
        t = new JTextField();
        t.setBounds(0, 50, 180, 25);
        t.setBackground(Color.white);
        t.setFont(new Font("Tahoma", Font.PLAIN, 15));
        t.setEditable(false);
        fields.add(t);
        
        c =new JComboBox();
        c.setBackground(Color.white);
        c.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c.setBounds(0,0, 180, 25);
        c.setEditable(true);
        addElementsToChoice();
        AutoCompleteDecorator.decorate(c);
        fields.add(c);
        editor = (JTextField) c.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent evt){
                if(evt.getKeyCode() == 10){
                    int id = Integer.parseInt(editor.getText());
                    showRoomNumber(id);
                }
            }
        });
        
        ImageIcon searchIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/search.png"));
        Image searchImage = searchIcon.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        JLabel searchLabel = new JLabel(new ImageIcon(searchImage));
        searchLabel.setBounds(382, 0, 25, 25);
        main.add(searchLabel);
        searchLabel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                int id = Integer.parseInt(editor.getText());
                showRoomNumber(id);
            }
        });
        
        b1 =new JButton("Update");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(60, 120, 145, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b1.addActionListener(this);
        b1.setFocusPainted(false);
        main.add(b1);
        
        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(225, 120, 145, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b2.addActionListener(this);
        b2.setFocusPainted(false);
        main.add(b2);
        
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/sixth.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(400, 175, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(440, 0, 400, 175);
        main.add(imageLabel);
        
        ImageIcon frameicon = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/checkout.png"));
        Image frameImage = frameicon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        JLabel frameimage = new JLabel(new ImageIcon(frameImage));
        frameimage.setBounds(5,5,30,30);
        add(frameimage);
        
        JLabel title = new JLabel("Check Out");
        title.setBounds(40, 10, 250, 25);
        title.setFont(new Font("SERIF", Font.PLAIN, 18));
        add(title);
        
        setUndecorated(true);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(550, 300, 800, 310);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(!isDataPresent){
            JOptionPane.showMessageDialog(rootPane, "No Guest Present to Check Out!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showRoomNumber(int id){
        try{
            
            cn = new Conn();
            String query = "Select roomNumber from customers where check_In ='Yes' and customerId = "+id;
            ResultSet rs = cn.st.executeQuery(query);
            if(rs.next()){
                t.setText(rs.getInt(1)+"");
                
            }else{
                throw new Exception("Invalid Guest Id!");
            }
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addElementsToChoice(){
        try{
            
            cn = new Conn();
            String query = "Select customerId from customers where check_in = 'Yes'";
            ResultSet rs = cn.st.executeQuery(query);
            while(rs.next()){
                isDataPresent = true;
                c.addItem(rs.getInt(1));
            }
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(rootPane, "Something Went Wrong!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            
            try{
                int id = Integer.parseInt(editor.getText());
                cn =new Conn();
                if(t.getText().equals("")){
                    throw new Exception("Empty Room Number!");
                }
                String query = "Select * from customers where check_in = 'Yes' and customerId ="+id;
                ResultSet rs = cn.st.executeQuery(query);
                if(rs.next()){
                    double dueAmount = rs.getDouble("dueAmount");
                    if(dueAmount != 0.0){
                        throw new Exception("Some Amount is Due!");
                    }else{
                        
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                        String today = formatter.format(date);
                        
                        String query2 = "Insert Into guestsHistory values(?, ?, ?, ?, ?, ?)";
                        PreparedStatement pt = cn.con.prepareStatement(query2);
                        pt.setString(1, rs.getString("Name"));
                        pt.setString(2, rs.getString("Id_Proof"));
                        pt.setString(3, rs.getString("id_Number"));
                        pt.setInt(4, rs.getInt("RoomNumber"));
                        pt.setString(5, rs.getString("Checked_On"));
                        pt.setString(6, today);
                        
                        int selectedOption = JOptionPane.showConfirmDialog(rootPane, "Are You Sure?");
                        if (selectedOption == JOptionPane.YES_OPTION) {
                            String query3 = "Delete from customers where customerId =" + id;
                            int result1 = cn.st.executeUpdate(query3);
                            if (result1 == 1) {
                                int result2 = pt.executeUpdate();
                                if (result2 == 1) {
                                    JOptionPane.showMessageDialog(rootPane, "Thank You For Visiting!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    dispose();
                                    new Reception();
                                }else{
                                    throw new Exception("Data can't be inserted in History!");
                                }
                            }else{
                                throw new Exception("Erro in Deleting Data!");
                            }
                        }
                    }
                    
                }else{
                    throw new Exception("Invalid Guest Id!");
                }
                
            }catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            dispose();
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new CheckOut();
    }
    
}
