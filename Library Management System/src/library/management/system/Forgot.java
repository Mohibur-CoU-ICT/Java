package library.management.system;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Forgot extends JFrame implements ActionListener {

    private final JPanel contentPane;
    private final JTextField username, name, dateOfBirth, securityQuestion, securityAnswer, password;
    private final JButton searchButton, retrieveButton, backButton;
    private final int frame_length = 800, frame_height = 600, no_of_field = 7;
    private final int label_length = 120, field_length = 300, field_width = 25, y_gap = 50;
    private final int x_start = (frame_length - 2 * label_length - 20 - field_length) / 2;
    private final int y_start = (frame_height - no_of_field * y_gap - 30) / 2; // title bar height = 30

    Forgot() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Forgot Password");
        setResizable(false);
        setSize(frame_length, frame_height);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.PINK);

        JLabel l1 = new JLabel("Username");
        createJLabel(l1, x_start, y_start, label_length, field_width);

        username = new JTextField();
        createJTextField(username, x_start + label_length, y_start, field_length, field_width);

        searchButton = new JButton("Search");
        createButton(searchButton, x_start + label_length + field_length + 20, y_start, label_length, field_width);

        JLabel l2 = new JLabel("Name");
        createJLabel(l2, x_start, y_start + y_gap, label_length, field_width);

        name = new JTextField();
        createJTextField(name, x_start + label_length, y_start + y_gap, field_length, field_width);
        name.setEditable(false);

        JLabel l3 = new JLabel("Date of Birth");
        createJLabel(l3, x_start, y_start + 2 * y_gap, label_length, field_width);

        dateOfBirth = new JTextField();
        createJTextField(dateOfBirth, x_start + label_length, y_start + 2 * y_gap, field_length, field_width);
        dateOfBirth.setEditable(false);

        JLabel l4 = new JLabel("Security Question");
        createJLabel(l4, x_start, y_start + 3 * y_gap, label_length, field_width);

        securityQuestion = new JTextField();
        createJTextField(securityQuestion, x_start + label_length, y_start + 3 * y_gap, field_length, field_width);
        securityQuestion.setEditable(false);

        JLabel l5 = new JLabel("Security Answer");
        createJLabel(l5, x_start, y_start + 4 * y_gap, label_length, field_width);

        securityAnswer = new JTextField();
        createJTextField(securityAnswer, x_start + label_length, y_start + 4 * y_gap, field_length, field_width);

        retrieveButton = new JButton("Retrieve");
        createButton(retrieveButton, x_start + label_length + field_length + 20, y_start + 4 * y_gap, label_length, field_width);

        JLabel l6 = new JLabel("Password");
        createJLabel(l6, x_start, y_start + 5 * y_gap, label_length, field_width);

        password = new JTextField();
        createJTextField(password, x_start + label_length, y_start + 5 * y_gap, field_length, field_width);
        password.setEditable(false);

        backButton = new JButton("Back");
        createButton(backButton, x_start, (int) (y_start + 6.5 * y_gap), label_length, field_width);
    }

    private void createJLabel(JLabel label, int a, int b, int c, int d) {
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setForeground(new Color(44, 22, 11));
        label.setBounds(a, b, c, d);
        contentPane.add(label);
    }

    private void createJTextField(JTextField txtFld, int a, int b, int c, int d) {
        txtFld.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        txtFld.setBorder(BorderFactory.createEmptyBorder());
        txtFld.setBounds(a, b, c, d);
        contentPane.add(txtFld);
    }

    private void createButton(JButton btn, int a, int b, int c, int d) {
        btn.setBackground(Color.ORANGE);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        btn.setBounds(a, b, c, d);
        btn.setFocusable(false);
        contentPane.add(btn);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {

            if (ae.getSource() == backButton) {
                dispose();
                new Login().setVisible(true);

            } else if (ae.getSource() == searchButton) {

                if (username.getText().length() == 0) {
                    JOptionPane.showMessageDialog(this, "Enter user name", "Forgot", JOptionPane.ERROR_MESSAGE);
                } else {
                    Conn con = new Conn();
                    String sql = "select * from account where username = ?;";
                    PreparedStatement st = con.c.prepareStatement(sql);
                    st.setString(1, username.getText());
                    ResultSet rs = st.executeQuery();

                    if (rs.next()) {
                        name.setText(rs.getString("name"));
                        dateOfBirth.setText(rs.getString("date_of_birth"));
                        securityQuestion.setText(rs.getString("sec_qus"));
                    } else {
                        JOptionPane.showMessageDialog(this, "Username not found", "Forgot", JOptionPane.ERROR_MESSAGE);
                    }
                    con.c.close();
                }

            } else if (ae.getSource() == retrieveButton) {
                if (username.getText().length() == 0 || name.getText().length() == 0 || securityQuestion.getText().length() == 0) {
                    JOptionPane.showMessageDialog(this, "Retrieve security question first", "Forgot", JOptionPane.INFORMATION_MESSAGE);

                } else if (securityAnswer.getText().length() == 0) {
                    JOptionPane.showMessageDialog(this, "Enter security answer", "Forgot", JOptionPane.ERROR_MESSAGE);

                } else {
                    String sql = "select * from account where username = ? and sec_ans = ?;";
                    Conn con = new Conn();
                    PreparedStatement st = con.c.prepareStatement(sql);
                    st.setString(1, username.getText());
                    st.setString(2, securityAnswer.getText());
                    ResultSet rs = st.executeQuery();

                    if (rs.next()) {
                        password.setText(rs.getString("password"));
                    } else {
                        JOptionPane.showMessageDialog(this, "Security answer doesn't match", "Forgot", JOptionPane.ERROR_MESSAGE);
                        password.setText("");
                    }
                    con.c.close();
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Forgot", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Forgot().setVisible(true);
    }

}
