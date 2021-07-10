package library.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutApp extends JFrame implements ActionListener {

    JButton closeButton;

    AboutApp() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("About App");
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(450, 410);
        setLocationRelativeTo(null);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        
        JLabel l1 = new JLabel("Library Management System");
        l1.setBounds(50, 10, 500, 30);
        l1.setFont(new Font("Tahoma", Font.BOLD, 25));
        l1.setForeground(new Color(55, 110, 220));
        contentPane.add(l1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/library.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 160, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(75, 75, 300, 100);
        contentPane.add(l2);
        
        JLabel l3 = new JLabel("<html>A library management system enhances the efficiency of both the librarians and the library users. It also enables librarians to easily catalog books and keep proper records of books issued, reissued, and those not returned.</html>");
        l3.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l3.setBounds(50, 200, 350, 100);
        contentPane.add(l3);

        closeButton = new JButton("Close");
        closeButton.setBounds(180, 330, 70, 25);
        closeButton.setBackground(Color.WHITE);
        closeButton.addActionListener(this);
        closeButton.setFocusable(false);
        contentPane.add(closeButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Close")) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new AboutApp().setVisible(true);
    }

}
