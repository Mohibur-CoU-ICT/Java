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

public class AboutDeveloper extends JFrame implements ActionListener {

    JButton closeButton;

    AboutDeveloper() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("About Developer");
        setAlwaysOnTop(rootPaneCheckingEnabled);
        setResizable(false);
        setSize(450, 410);
        setLocationRelativeTo(null);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/mohibur.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(125, 10, 200, 200);
        contentPane.add(l1);

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
        contentPane.add(l3);

        closeButton = new JButton("Close");
        closeButton.setBounds(180, 330, 70, 25);
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
        new AboutDeveloper().setVisible(true);
    }

}
