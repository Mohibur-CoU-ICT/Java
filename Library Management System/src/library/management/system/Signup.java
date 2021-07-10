package library.management.system;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Signup extends JFrame implements ActionListener {

    private final JPanel contentPane;
    private final JTextField userId, userName, name, securityAnswer;
    private final JDateChooser dateOfBirth;
    private final JPasswordField password;
    private final JComboBox securityQuestion;
    private final JButton createButton, backButton;
    private final int frame_length = 800, frame_height = 600, no_of_field = 8;
    private final int label_length = 120, field_length = 300, field_width = 25, y_gap = 50;
    private final int x_start = (frame_length - label_length - field_length) / 2;
    private final int y_start = (frame_height - no_of_field * y_gap - 30) / 2; // title bar height = 30

    Signup() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Signup");
        setSize(frame_length, frame_height);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.PINK);
        setContentPane(contentPane);

        JLabel u_id = new JLabel("User Id");
        createJLabel(u_id, x_start, y_start, label_length, field_width);

        userId = new JTextField();
        createJTextField(userId, x_start + label_length, y_start, field_length, field_width);
        userId.setEditable(false);
        userId.setText(getUserId());

        JLabel l1 = new JLabel("Username");
        createJLabel(l1, x_start, y_start + y_gap, label_length, field_width);

        userName = new JTextField();
        createJTextField(userName, x_start + label_length, y_start + y_gap, field_length, field_width);

        JLabel l2 = new JLabel("Name");
        createJLabel(l2, x_start, y_start + 2 * y_gap, label_length, field_width);

        name = new JTextField();
        createJTextField(name, x_start + label_length, y_start + 2 * y_gap, field_length, field_width);

        JLabel l3 = new JLabel("Password");
        createJLabel(l3, x_start, y_start + 3 * y_gap, label_length, field_width);

        password = new JPasswordField();
        password.setBounds(x_start + label_length, y_start + 3 * y_gap, field_length, field_width);
        password.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(password);

        JLabel l4 = new JLabel("Date of Birth");
        createJLabel(l4, x_start, y_start + 4 * y_gap, label_length, field_width);

        dateOfBirth = new JDateChooser();
        dateOfBirth.setBounds(x_start + label_length, y_start + 4 * y_gap, field_length, field_width);
        dateOfBirth.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(dateOfBirth);

        JLabel l5 = new JLabel("Security Question");
        createJLabel(l5, x_start, y_start + 5 * y_gap, label_length, field_width);

        securityQuestion = new JComboBox();
        securityQuestion.setBounds(x_start + label_length, y_start + 5 * y_gap, field_length, field_width);
        String sq[] = {"Your Nick Name?", "Your Lucky Number?", "Your Favourite Teacher?", "Your Favourite Person?"};
        securityQuestion.setModel(new DefaultComboBoxModel(sq));
        securityQuestion.setBackground(Color.WHITE);
        securityQuestion.setSelectedIndex(-1);
        contentPane.add(securityQuestion);

        JLabel l6 = new JLabel("Answer");
        createJLabel(l6, x_start, y_start + 6 * y_gap, label_length, field_width);

        securityAnswer = new JTextField();
        createJTextField(securityAnswer, x_start + label_length, y_start + 6 * y_gap, field_length, field_width);

        backButton = new JButton("Back");
        createButton(backButton, x_start, (int) (y_start + 7.5 * y_gap), label_length, field_width);

        createButton = new JButton("Create");
        createButton(createButton, (int) (x_start + 2.5 * label_length), (int) (y_start + 7.5 * y_gap), label_length, field_width);
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
        btn.addActionListener(this);
        btn.setBackground(Color.ORANGE);
        btn.setForeground(Color.WHITE);
        btn.setBounds(a, b, c, d);
        btn.setFocusable(false);
        contentPane.add(btn);
    }

    private String getUserId() {
        String id = "";
        try {
            Conn con = new Conn();
            PreparedStatement st;
            String sql = "select * from account where user_id = ?;";
            ResultSet rs;
            while (true) {
                id = gen_random();
                st = con.c.prepareStatement(sql);
                st.setString(1, id);
                rs = st.executeQuery();
                if (rs.next() == false) {
                    break;
                }
            }
            rs.close();
            st.close();
            con.c.close();
        } catch (Exception e) {

        }
        return id;
    }

    private String gen_random() {
        Random r = new Random();
        int ran_num = r.nextInt(90000000) + 10000000;
        return "" + ran_num;
    }

    private boolean checkField() {
        return userName.getText().length() != 0
                && name.getText().length() != 0
                && password.getText().length() != 0
                && ((JTextField) dateOfBirth.getDateEditor().getUiComponent()).getText().length() != 0
                && securityQuestion.getSelectedIndex() != -1
                && securityAnswer.getText().length() != 0;
    }

    private void resetField() {
        userId.setText(getUserId());
        userName.setText("");
        name.setText("");
        password.setText("");
        ((JTextField) dateOfBirth.getDateEditor().getUiComponent()).setText("");
        securityQuestion.setSelectedIndex(-1);
        securityAnswer.setText("");
    }

    private void printFieldValue() {
        System.out.println(userId.getText());
        System.out.println(userName.getText());
        System.out.println(name.getText());
        System.out.println(password.getText());
        System.out.println("Date of birth : " + ((JTextField) dateOfBirth.getDateEditor().getUiComponent()).getText());
        System.out.println(securityQuestion.getSelectedItem());
        System.out.println(securityAnswer.getText());
    }

    private boolean checkPassword(String password) {
        String ragex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+=_-])(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(ragex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            dispose();
            new Login().setVisible(true);

        } else if (ae.getSource() == createButton) {
            if (checkField() == false) {
                JOptionPane.showMessageDialog(this, "Please fill up the form", "Incomplete Information", JOptionPane.ERROR_MESSAGE);

            } else if (checkPassword(password.getText()) == false) {
                JOptionPane.showMessageDialog(this, "Password must contain:\n1. At least one number.\n2. At least one lower case letter.\n3. At least one upper case letter.\n4. At least one special character from [!@#$%^&*()+=_-].\n5. No whitespace.\n6. Length between 8-20.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Conn con = new Conn();
                    String sql = "select * from account where username = ?;";
                    PreparedStatement st = con.c.prepareStatement(sql);
                    st.setString(1, userName.getText());
                    ResultSet rs = st.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Username already taken", "Signup", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        sql = "insert into account(userid, username, name, password, date_of_birth, sec_qus, sec_ans) values(?, ?, ?, ?, ?, ?, ?);";
                        st = con.c.prepareStatement(sql);

                        st.setString(1, userId.getText());
                        st.setString(2, userName.getText());
                        st.setString(3, name.getText());
                        st.setString(4, password.getText());
                        st.setString(5, ((JTextField) dateOfBirth.getDateEditor().getUiComponent()).getText());
                        st.setString(6, (String) securityQuestion.getSelectedItem());
                        st.setString(7, securityAnswer.getText());
//                        printFieldValue();

                        int r = st.executeUpdate();
//                        System.out.println("r = " + r);
                        if (r == 1) {
                            JOptionPane.showMessageDialog(this, "Successfully Created", "Signup", JOptionPane.INFORMATION_MESSAGE);
                            resetField();
                        } else {
                            JOptionPane.showMessageDialog(this, "Account can't created", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    con.c.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Couldn't connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Signup().setVisible(true);
    }

}
