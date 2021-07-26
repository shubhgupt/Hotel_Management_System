package hotel_management_system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class PickUpService extends JFrame implements ActionListener{
    
    private Choice c1;
    private JTextField t1;
    private JTable t;
    private JButton b1,b2;
    private Conn c;
    private boolean isDriverAvailable = false;
    
    PickUpService(){
        JPanel main = new JPanel();
        main.setBounds(0, 30, 970, 685);
        main.setLayout(null);
        main.setBackground(Color.white);
        add(main);
        
        t = new JTable();
        t.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
        t.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t.getTableHeader().setBackground(Color.white);

        JScrollPane sp = new JScrollPane(t);
        sp.setBounds(0, 85, 970, 500);
        sp.getViewport().setBackground(Color.white);
        main.add(sp);
        
        JPanel search = new JPanel();
        search.setBounds(0, 30, 970, 25);
        search.setLayout(null);
        search.setBackground(Color.white);
        main.add(search);
        
        JLabel l1 = new JLabel("Car Company:");
        l1.setBounds(20, 0, 130, 25);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        search.add(l1);
        
        c1 =new Choice();
        c1.setBackground(Color.white);
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c1.setFocusable(false);
        c1.setBounds(150, 0, 180, 25);
        c1.add("All");
        addElementsToChoice();
        search.add(c1);
        
        
        JLabel l2 = new JLabel("Location:");
        l2.setBounds(390, 0, 90, 25);
        l2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        search.add(l2);
        
        t1 = new JTextField();
        t1.setBounds(480, 0, 180, 25 );
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        search.add(t1);        

        b1 = new JButton("Load Data");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(325, 605, 150, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b1.setFocusPainted(false);
        b1.addActionListener(this);
        main.add(b1);

        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(495, 605, 150, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b2.setFocusPainted(false);
        b2.addActionListener(this);
        main.add(b2);

        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/pickup.png"));
        Image Ic = ic.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        JLabel frameIcon = new JLabel(new ImageIcon(Ic));
        frameIcon.setBounds(0, 0, 30, 30);
        add(frameIcon);

        JLabel title = new JLabel("Pick Up Service");
        title.setFont(new Font("serif", Font.BOLD, 18));
        title.setBounds(40, 0, 200, 30);
        add(title);

        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setBounds(475, 200, 970, 685);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(!isDriverAvailable){
            JOptionPane.showMessageDialog(rootPane, " Sorry! \n No Drivers Available!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addElementsToChoice(){
        try{
            
            c = new Conn();
            String query = "Select Distinct carCompany from drivers order by carcompany";
            ResultSet rs  = c.st.executeQuery(query);
            while(rs.next()){
                c1.add(rs.getString(1));
                isDriverAvailable = true;
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            
            try{
                
                String carCompany = c1.getSelectedItem();
                String location = t1.getText();
                String query = "";
                
                if(location.equals("")){
                    if(carCompany.equals("All")){
                        query = "Select * from drivers order by id";
                    }
                    else{
                        query = "Select * from drivers where carcompany = '"+carCompany+"' order by id";
                    }
                }else{
                    if(carCompany.equals("All")){
                        query = "Select * from drivers where location= '"+location+"' order by id";
                    }
                    else{
                        query = "Select * from drivers where location= '"+location+"' and carcompany = '"+carCompany+"' order by id";
                    }
                }
                
                c =new Conn();
                ResultSet rs = c.st.executeQuery(query);
                if(rs.next()){
                    rs = c.st.executeQuery(query);
                    t.setModel(DbUtils.resultSetToTableModel(rs));
                }else{
                    throw new Exception(" Sorry! \n No Drivers Present!");
                }
                
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            dispose();
            new Reception();
        }
    }
    public static void main(String[] args) {
        new PickUpService();
    }
    
}
