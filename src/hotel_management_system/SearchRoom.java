package hotel_management_system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class SearchRoom extends JFrame implements ActionListener{
    
    private JTable t;
    private JTextField t1;
    private Choice c1;
    private JButton b1,b2;
    private Conn c;
    private JLabel checkLabel, UncheckLabel;
    
    SearchRoom(){
        
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
        
        JLabel l1 = new JLabel("Bed Type:");
        l1.setBounds(20, 0, 100, 25);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        search.add(l1);
        
        c1 =new Choice();
        c1.setBackground(Color.white);
        c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        c1.setFocusable(false);
        c1.setBounds(120, 0, 180, 25);
        c1.add("Single Bed");
        c1.add("Double Bed");
        search.add(c1);
        
        ImageIcon checkIcon =new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/check.png"));
        Image checkImage = checkIcon.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        checkLabel = new JLabel(new ImageIcon(checkImage));
        checkLabel.setBounds(360, 0, 25,25 );
        checkLabel.setVisible(false);
        search.add(checkLabel);
        checkLabel.addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent me){
               checkLabelClicked();
           } 
        });
        
        ImageIcon uncheckIcon =new ImageIcon(ClassLoader.getSystemResource("hotel_management_system/icons/uncheck.png"));
        Image uncheckImage = uncheckIcon.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        UncheckLabel = new JLabel(new ImageIcon(uncheckImage));
        UncheckLabel.setBounds(360, 0, 23,23 );
        UncheckLabel.setVisible(true);
        search.add(UncheckLabel);
        UncheckLabel.addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent me){
               uncheckLabelClicked();
           } 
        });
        
        
        
        JLabel l2 = new JLabel("Only Display Available");
        l2.setBounds(390, 0, 200, 25);
        l2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        search.add(l2);
        
        
        
        JLabel l3 = new JLabel("Price Less Than:");
        l3.setBounds(630, 0, 130, 25);
        l3.setFont(new Font("Tahoma", Font.PLAIN, 17));
        search.add(l3);
        
        t1 = new JTextField();
        t1.setBounds(770, 0, 180, 25 );
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
        setBounds(380, 200, 970, 685);
        
    }
    public void checkLabelClicked(){
        checkLabel.setVisible(false);
        UncheckLabel.setVisible(true);
    }
    public void uncheckLabelClicked(){
        UncheckLabel.setVisible(false);
        checkLabel.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            
        }else{
            dispose();
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new SearchRoom();
    }
}
