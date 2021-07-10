package library.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

    private final JPanel contentPane;
    private final JTextField username;
    private final JPasswordField password;
    private final JButton loginButton, signupButton, forgotButton, helpButton;
    private final int frame_length = 800, frame_height = 600, no_of_field = 7;
    private final int label_length = 100, field_length = 250, field_width = 25, y_gap = 50;
    private final int x_start = (frame_length - label_length - field_length) / 2;
    private final int y_start = (frame_height - no_of_field * y_gap - 30) / 2; // title bar height = 30

    Login() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Login");
        setSize(frame_length, frame_height);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.PINK);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel usernameJLabel = new JLabel("Username");
        createJLabel(usernameJLabel, x_start, y_start, label_length, field_width);

        username = new JTextField();
        username.setBounds(x_start + label_length, y_start, field_length, field_width);
        username.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(username);

        JLabel passwordJLabel = new JLabel("Password");
        createJLabel(passwordJLabel, x_start, y_start + y_gap, label_length, field_width);

        password = new JPasswordField();
        password.setBounds(x_start + label_length, y_start + y_gap, field_length, field_width);
        password.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(password);

        loginButton = new JButton("Login");
        createButton(loginButton, frame_length / 2, y_start + 2 * y_gap, label_length, field_width);

        JLabel noAccount = new JLabel("Don't have an a/c?");
        createJLabel(noAccount, x_start, y_start + 4 * y_gap, field_length, field_width);

        signupButton = new JButton("Sign up");
        createButton(signupButton, frame_length / 2, y_start + 4 * y_gap, label_length, field_width);

        JLabel troubleInLogin = new JLabel("Trouble in Login?");
        createJLabel(troubleInLogin, x_start, y_start + 5 * y_gap, field_length, field_width);

        forgotButton = new JButton("Forgot");
        createButton(forgotButton, frame_length / 2, y_start + 5 * y_gap, label_length, field_width);

        JLabel howToUse = new JLabel("How to use?");
        createJLabel(howToUse, x_start, y_start + 6 * y_gap, field_length, field_width);

        helpButton = new JButton("Help");
        createButton(helpButton, frame_length / 2, y_start + 6 * y_gap, label_length, field_width);
    }

    private void createJLabel(JLabel label, int a, int b, int c, int d) {
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setForeground(new Color(44, 22, 11));
        label.setBounds(a, b, c, d);
        contentPane.add(label);
    }

    private void createButton(JButton btn, int a, int b, int c, int d) {
        btn.addActionListener(this);
        btn.setBackground(Color.ORANGE);
        btn.setForeground(Color.WHITE);
        btn.setBounds(a, b, c, d);
        btn.setFocusable(false);
        contentPane.add(btn);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            if (username.getText().length() == 0 || password.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Empty username or password", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Conn con = new Conn();
                    String query = "select * from account where username = ? and password = ?;";
                    PreparedStatement st = con.c.prepareStatement(query);

                    st.setString(1, username.getText());
                    st.setString(2, password.getText());

                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        new Loading(rs.getString("userid")).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Wrong username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (ae.getSource() == signupButton) {
            dispose();
            new Signup().setVisible(true);
        } else if (ae.getSource() == forgotButton) {
            dispose();
            new Forgot().setVisible(true);
        } else if (ae.getSource() == helpButton) {
            new HowToUse().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
