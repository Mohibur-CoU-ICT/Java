package quizapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutDeveloper extends JFrame implements ActionListener {

    JButton closeButton;

    AboutDeveloper() {
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(500, 100, 450, 410);
        setTitle("About Developer");
        setAlwaysOnTop(true);
        setResizable(false);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("quizapp/icons/mohibur.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(125, 10, 200, 200);
        add(l1);

        JLabel l3 = new JLabel("<html>"
                + "Mohibur Rahman<br>"
                + "Department of ICT, Comilla University.<br>"
                + "Competitive Programmer (C++),<br>"
                + "Problem Solver,<br>"
                + "Java Developer."
                + "</html>");
        l3.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l3.setBounds(110, 220, 250, 100);
        l3.setBackground(Color.yellow);
        add(l3);

        closeButton = new JButton("Close");
        closeButton.setBounds(180, 330, 70, 25);
        closeButton.addActionListener(this);
        closeButton.setFocusable(false);
        add(closeButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Close")) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new AboutDeveloper().setVisible(true);
    }

}
