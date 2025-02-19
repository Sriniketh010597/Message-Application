package message.application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class TheClient implements ActionListener {
    
    JTextField messagefield;
    static JPanel chatpanel;
    static Box verticalscale = Box.createVerticalBox();
    static JFrame frm= new JFrame(); 
    
    static DataOutputStream dout;
    
        TheClient() {
        
        frm.setLayout(null);
        
        JPanel headerpanel = new JPanel();
        headerpanel.setBackground(new Color(7, 94, 84));
        headerpanel.setBounds(0, 0, 450, 70);
        headerpanel.setLayout(null);
        frm.add(headerpanel);
        
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
        
        JLabel username = new JLabel("Sriniketh");
        username.setBounds(110, 15, 100, 18);
        username.setForeground(Color.WHITE);
        username.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        headerpanel.add(username);
        
        JLabel statusindicator = new JLabel("Online");
        statusindicator.setBounds(110, 35, 100, 18);
        statusindicator.setForeground(Color.WHITE);
        statusindicator.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        headerpanel.add(statusindicator);
        
        chatpanel = new JPanel();
        chatpanel.setBounds(5, 75, 440, 570);
        frm.add(chatpanel);
        
        messagefield = new JTextField();
        messagefield.setBounds(5, 655, 310, 40);
        messagefield.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        frm.add(messagefield);
        
        JButton sendmessage = new JButton("Send");
        sendmessage.setBounds(320, 655, 123, 40);
        sendmessage.setBackground(new Color(7, 94, 84));
        sendmessage.setForeground(Color.WHITE);
        sendmessage.addActionListener(this);
        sendmessage.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        frm.add(sendmessage);
        
        frm.setSize(450, 700);
        frm.setLocation(800, 50);
        frm.setUndecorated(true);
        frm.getContentPane().setBackground(Color.WHITE);
        
        frm.setVisible(true);
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

            dout.writeUTF(out);

            messagefield.setText("");

            frm.repaint();
            frm.invalidate();
            frm.validate();
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
        new TheClient();

        try {
            Socket sk = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(sk.getInputStream());
            dout = new DataOutputStream(sk.getOutputStream());
            
            while(true) {
                chatpanel.setLayout(new BorderLayout());
                String chat = din.readUTF();
                JPanel panel = formatLabel(chat);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                verticalscale.add(left);
                
                verticalscale.add(Box.createVerticalStrut(15));
                chatpanel.add(verticalscale, BorderLayout.PAGE_START);
                
                frm.validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
}
