package javaapplication2;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
public class Server  implements ActionListener {
    JTextField t1;//Global declaration
     static JPanel p2;
     static Box vertical=Box.createVerticalBox();
     static JFrame f=new JFrame();
     static DataOutputStream dout;
    Server(){
        f.setLayout(null);
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("ChatIcons/icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(25,25,0); //image class is under awt package
        ImageIcon i3=new ImageIcon(i2);
        JLabel back=new JLabel (i3);
        back.setBounds(5,20,25,25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e){
           System.exit(0) ; } } );
        
        
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("ChatIcons/icons/my profile photo.jpg"));
        Image i5=i4.getImage().getScaledInstance(30,50,0);
        ImageIcon i6=new ImageIcon(i5);
        JLabel profil=new JLabel (i6);
        profil.setBounds(40,10,55,55);
        p1.add(profil);
        
         ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("ChatIcons/icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT); 
        ImageIcon i9=new ImageIcon(i8);
        JLabel video=new JLabel (i9);
        video.setBounds(300,10,55,55);
        p1.add(video);
     
         ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("ChatIcons/icons/phone.png"));
        Image i11=i10.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT); 
        ImageIcon i12=new ImageIcon(i11);
        JLabel phone=new JLabel (i12);
        phone.setBounds(350,10,55,55);
        p1.add(phone);
        
         ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("ChatIcons/icons/3icon.png"));
        Image i14=i13.getImage().getScaledInstance(9,25,Image.SCALE_DEFAULT); 
        ImageIcon i15=new ImageIcon(i14);
        JLabel threedot=new JLabel (i15);
        threedot.setBounds(390,10,55,55);
        p1.add(threedot);
        
        JLabel name=new JLabel("Vaibhav");
        name.setBounds(100,19,80,20);
        name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        name.setForeground(Color.WHITE);
        p1.add(name);
        
          JLabel status=new JLabel("online");
        status.setBounds(100,35,80,20);
        status.setFont(new Font("Tahoma", Font.PLAIN, 10));
        status.setForeground(Color.WHITE);
        p1.add(status);
        
        p2=new JPanel();//messanger box panel
        p2.setBounds(5,75,445,550);
        f.add(p2);
        
        t1=new JTextField();
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,13));
        t1.setBounds(5,630,358,40);
        f.add(t1);
        
        JButton send=new JButton("Send");
        send.setBounds(365,630,85,39);
        send.setForeground(Color.WHITE);
        send.setBackground(new Color(1,94,84));
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,15));
        f.add(send);
        send.addActionListener(this);
}
    public void actionPerformed(ActionEvent e){
        try{
        String out =t1.getText();
        JPanel p3=formatLabel(out);
        p2.setLayout(new BorderLayout());
        
        JPanel right =new JPanel(new BorderLayout());
        right.add(p3,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(12));
        
        p2.add(vertical,BorderLayout.PAGE_START);
        t1.setText("");
        dout.writeUTF(out);
        f.repaint();
        f.invalidate();
        f.validate();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    
      public static JPanel formatLabel(String out) {
        JPanel panel10 = new JPanel();
        panel10.setLayout(new BoxLayout(panel10, BoxLayout.Y_AXIS));
//        panel11.set(new BoxLayout(panel11,BoxLayout.X_AXIS))
        
//        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        JLabel output = new JLabel(out);
        output.setFont(new Font("Tahoma", Font.PLAIN, 15));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        
        panel10.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel10.add(time);
        
        return panel10;
    }
    public static void main(String[] ary){
         Server s1=new Server();
        f.setSize(470,710);
        f.setLocation(150,50);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
        
        try {
            ServerSocket skt = new ServerSocket(6001);
            while(true) {
            Socket s = skt.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
                
                while(true) {
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);
                     
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                  vertical.add(left);
                  f.validate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
