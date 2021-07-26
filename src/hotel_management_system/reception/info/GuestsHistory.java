package hotel_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class GuestsHistory extends JFrame implements ActionListener {

    private JTable t;
    private JButton b1, b2;
    private JLabel l1, l2,l3;
    private JTextField t1, t2, t3;
    private Conn c;
    private ResultSet rs;
    
    GuestsHistory() {
        t = new JTable();
        t.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
        t.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t.getTableHeader().setBackground(Color.white);

        JScrollPane sp = new JScrollPane(t);
        sp.setBounds(0, 115, 1160, 500);
        sp.getViewport().setBackground(Color.white);
        add(sp);
        
        JPanel search = new JPanel();
        search.setBounds(0, 30, 1160, 85);
        search.setLayout(null);
        search.setBackground(Color.white);
        add(search);
        
        l1 = new JLabel("Search By Name:");
        l1.setBounds(10, 30, 150, 25);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        search.add(l1);
        
        t1 = new JTextField();
        t1.setBounds(160, 30, 180, 25 );
        t1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        search.add(t1);
        
        ImageIcon searchIcon1 = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/search.png"));
        Image searchImage1 = searchIcon1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        JLabel searchLabel1 = new JLabel(new ImageIcon(searchImage1));
        searchLabel1.setBounds(342, 30, 25, 25);
        search.add(searchLabel1);
        searchLabel1.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                searchByName();
            }
        });
        
        l2 = new JLabel("Search By IdProof:");
        l2.setBounds(390, 30, 150, 25);
        l2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        search.add(l2);
        
        t2 = new JTextField();
        t2.setBounds(550, 30, 180, 25 );
        t2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        search.add(t2);
        
        JLabel searchLabel2 = new JLabel(new ImageIcon(searchImage1));
        searchLabel2.setBounds(732, 30, 25, 25);
        search.add(searchLabel2);
        searchLabel2.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                searchById();
            }
        });
        
        l3 = new JLabel("Search By Room:");
        l3.setBounds(780, 30, 150, 25);
        l3.setFont(new Font("Tahoma", Font.PLAIN, 17));
        search.add(l3);
        
        t3 = new JTextField();
        t3.setBounds(940, 30, 180, 25 );
        t3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        search.add(t3);
        
        JLabel searchLabel3 = new JLabel(new ImageIcon(searchImage1));
        searchLabel3.setBounds(1122, 30, 25, 25);
        search.add(searchLabel3);
        searchLabel3.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                searchByRoom();
            }
        });

        b1 = new JButton("Load Data");
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.setBounds(410, 655, 150, 30);
        b1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b1.setFocusPainted(false);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.setBounds(580, 655, 150, 30);
        b2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        b2.setFocusPainted(false);
        b2.addActionListener(this);
        add(b2);

        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/history.png"));
        Image Ic = ic.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        JLabel frameIcon = new JLabel(new ImageIcon(Ic));
        frameIcon.setBounds(0, 0, 30, 30);
        add(frameIcon);

        JLabel title = new JLabel("Guests History");
        title.setFont(new Font("serif", Font.PLAIN, 18));
        title.setBounds(35, 0, 200, 30);
        add(title);

        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setBounds(380, 200, 1161, 715);
    }

    private void searchByName(){
        try{
            
            String name = t1.getText();
            c = new Conn();
            String query = "Select * from guestshistory where GuestName ='"+name+"'";
            rs = c.st.executeQuery(query);
            if(rs.next()){
                rs = c.st.executeQuery(query);
                t.setModel(DbUtils.resultSetToTableModel(rs));
                
            }else{
                throw new Exception("No Such Guest Visited!");
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        t1.setText("");
    }

    private void searchById(){
        try{
            
            String id = t2.getText();
            c = new Conn();
            String query = "Select * from guestshistory where GuestIdProof ='"+id+"'";
            rs = c.st.executeQuery(query);
            if(rs.next()){
                rs = c.st.executeQuery(query);
                t.setModel(DbUtils.resultSetToTableModel(rs));
                
            }else{
                throw new Exception("No Such Guest Visited!");
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        t2.setText("");
    }

    private void searchByRoom(){
        try{
            
            String room = t3.getText();
            c = new Conn();
            String query = "Select * from guestshistory where RoomNumber ="+room;
            rs = c.st.executeQuery(query);
            if(rs.next()){
                rs = c.st.executeQuery(query);
                t.setModel(DbUtils.resultSetToTableModel(rs));
                
            }else{
                throw new Exception("No Such Guest Visited!");
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        t3.setText("");
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {

                c = new Conn();
                String query = "Select * from guestsHistory";
                rs = c.st.executeQuery(query);
                if (rs.next()) {
                    rs = c.st.executeQuery(query);
                    
                    t.setModel(DbUtils.resultSetToTableModel(rs));

                } else {
                    throw new Exception("No History Available!");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == b2) {
            dispose();
            new Reception();
        }
    }

    public static void main(String[] args) {
        new GuestsHistory();
    }
}
