package hotel_management_system;

/**
 *
 * @author kanha
 */
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import javax.swing.*;

public class UpdateRoomInfo extends JFrame{
    
    Choice c1,c2,c3,c4;
    Conn c;
    String roomQuery1 = "SELECT roomnumber from rooms where roomnumber Not In (Select roomNumber from customers)";
    String roomQuery2 = "Select RoomNumber from customers where customerId =";
    
    UpdateRoomInfo(){
        JPanel p1 = new JPanel();
        p1.setBounds(0,50, 200, 200);
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
        
        JPanel p2 = new JPanel();
        p2.setBounds(200,50, 180, 200);
        p2.setLayout(null);
        p2.setBackground(Color.white);
        add(p2);
        
        c2 = new Choice();
        c2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c2.setBackground(Color.white);
        c2.setBounds(0, 50, 180, 25);
        c2.setFocusable(false);
        p2.add(c2);
        c2.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                
            }
        });
        
        c3 = new Choice();
        c3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c3.setBackground(Color.white);
        c3.setBounds(0, 50, 180, 25);
        c3.setFocusable(false);
        c3.add("Available");
        c3.add("Occupied");
        p2.add(c3);
        
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
        
        
        
        
//        setUndecorated(true);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setBounds(600, 250, 900, 500 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void addElementsToC2(String query){
        
        try{
            c = new Conn();
            ResultSet rs = c.st.executeQuery(query);
            c2.removeAll();
            while(rs.next()){
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
    
    public static void main(String[] args) {
        new UpdateRoomInfo();
    }
}
