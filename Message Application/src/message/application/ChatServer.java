package message.application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;



public class ChatServer extends JFrame implements ActionListener {
    
    JTextField messagefield;
    JPanel chatpanel;
    static Box verticalscale = Box.createVerticalBox();
    
    ChatServer() {
        
        setLayout(null);
        
        JPanel headerpanel = new JPanel();
        headerpanel.setBackground(new Color(7, 94, 84));
        headerpanel.setBounds(0, 0, 450, 70);
        headerpanel.setLayout(null);
        add(headerpanel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel backbutton = new JLabel(i3);
        backbutton.setBounds(5, 20, 25, 25);
        headerpanel.add(backbutton);
        
        backbutton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/circle.jpg"));
        Image i5 = i4.getImage().getScaledInstance(53, 47, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profilepic = new JLabel(i6);
        profilepic.setBounds(40, 10, 50, 50);
        headerpanel.add(profilepic);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel videocall = new JLabel(i9);
        videocall.setBounds(300, 20, 30, 30);
        headerpanel.add(videocall);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel telephone = new JLabel(i12);
        telephone.setBounds(360, 20, 35, 30);
        headerpanel.add(telephone);
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel mrevert = new JLabel(i15);
        mrevert.setBounds(420, 20, 10, 25);
        headerpanel.add(mrevert);
        
        JLabel theusername = new JLabel("Vaibhav");
        theusername.setBounds(110, 15, 100, 18);
        theusername.setForeground(Color.WHITE);
        theusername.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        headerpanel.add(theusername);
        
        JLabel indicator = new JLabel("Active Now");
        indicator.setBounds(110, 35, 100, 18);
        indicator.setForeground(Color.WHITE);
        indicator.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        headerpanel.add(indicator);
        
        chatpanel = new JPanel();
        chatpanel.setBounds(5, 75, 440, 570);
        add(chatpanel);
        
        messagefield = new JTextField();
        messagefield.setBounds(5, 655, 310, 40);
        messagefield.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(messagefield);
        
        JButton sendmessage = new JButton("Send");
        sendmessage.setBounds(320, 655, 123, 40);
        sendmessage.setBackground(new Color(7, 94, 84));
        sendmessage.setForeground(Color.WHITE);
        sendmessage.addActionListener(this);
        sendmessage.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(sendmessage);
        
        setSize(450, 700);
        setLocation(200, 50);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            String out = messagefield.getText();

            JPanel panel2 = formatLabel(out);

            chatpanel.setLayout(new BorderLayout());

            JPanel onright = new JPanel(new BorderLayout());
            onright.add(panel2, BorderLayout.LINE_END);
            verticalscale.add(onright);
            verticalscale.add(Box.createVerticalStrut(15));

            chatpanel.add(verticalscale, BorderLayout.PAGE_START);

            

            messagefield.setText("");

            repaint();
            invalidate();
            validate();   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        
        panel.add(output);
        
        Calendar thecalender = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("HH:mm");
        
        JLabel timeclock = new JLabel();
        timeclock.setText(date.format(thecalender.getTime()));
        
        panel.add(timeclock);
        return panel;
        
    }
        
    public static void main(String[] args) {
        new ChatServer();
        new TheClient();
    }   
        
        
    }
   
