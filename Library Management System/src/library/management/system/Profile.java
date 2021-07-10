package library.management.system;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Profile extends JFrame implements ActionListener {

    private final JPanel contentPane;
    private final JTextField userId, userName, name, securityAnswer;
    private final JDateChooser dateOfBirth;
    private final JPasswordField password;
    private final JComboBox securityQuestion;
    private final JButton saveButton, editButton, deleteButton, backButton;
    private final int frame_length = 800, frame_height = 600, no_of_field = 8;
    private final int label_length = 120, field_length = 300, field_width = 25, y_gap = 50;
    private final int x_start = (frame_length - label_length - field_length) / 2;
    private final int y_start = (frame_height - no_of_field * y_gap - 30) / 2; // title bar height = 30
    String useridString;

    Profile(String useridString) {
        this.useridString = useridString;
//        System.out.println("Profile : " + useridString);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Profile");
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
        password.setBackground(Color.WHITE);
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
        contentPane.add(securityQuestion);

        JLabel l6 = new JLabel("Answer");
        createJLabel(l6, x_start, y_start + 6 * y_gap, label_length, field_width);

        securityAnswer = new JTextField();
        createJTextField(securityAnswer, x_start + label_length, y_start + 6 * y_gap, field_length, field_width);

        backButton = new JButton("Back");
        createButton(backButton, field_width, field_width, label_length, field_width);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/back.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        backButton.setIcon(i3);

        deleteButton = new JButton("Delete");
        createButton(deleteButton, x_start, (int) (y_start + 7.5 * y_gap), label_length, field_width);

        editButton = new JButton("Edit");
        createButton(editButton, (int) (x_start + 1.25 * label_length), (int) (y_start + 7.5 * y_gap), label_length, field_width);

        saveButton = new JButton("Save");
        createButton(saveButton, (int) (x_start + 2.5 * label_length), (int) (y_start + 7.5 * y_gap), label_length, field_width);

        setField();
        disableField();
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
        txtFld.setBackground(Color.WHITE);
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

    private boolean checkField() {
        return userId.getText().length() != 0
                && userName.getText().length() != 0
                && name.getText().length() != 0
                && ((JTextField) dateOfBirth.getDateEditor().getUiComponent()).getText().length() != 0
                && password.getText().length() != 0
                && securityQuestion.getSelectedIndex() != -1
                && securityAnswer.getText().length() != 0;
    }

    private boolean checkPassword(String password) {
        String ragex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+=_-])(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(ragex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private void setField() {
        try {
            Conn con = new Conn();
            String sql = "select * from account where userid = ?;";
            PreparedStatement st = con.c.prepareStatement(sql);
            st.setString(1, useridString);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                userId.setText(rs.getString("userid"));
                userName.setText(rs.getString("username"));
                name.setText(rs.getString("name"));
                password.setText(rs.getString("password"));
                securityQuestion.setSelectedItem(rs.getString("sec_qus"));
                securityAnswer.setText(rs.getString("sec_ans"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
                Date date = dateFormat.parse(rs.getString("date_of_birth"));
                dateOfBirth.setDate(date);
            } else {
                securityQuestion.setSelectedIndex(-1);
            }
            rs.close();
            st.close();
            con.c.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Profile", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enableField() {
//        userId.setEditable(true);
        userName.setEditable(true);
        name.setEditable(true);
        dateOfBirth.setEnabled(true);
        password.setEditable(true);
        securityQuestion.enable();
        securityAnswer.setEditable(true);
        saveButton.setEnabled(true);
    }

    private void disableField() {
        userId.setEditable(false);
        userName.setEditable(false);
        name.setEditable(false);
        dateOfBirth.setEnabled(false);
        password.setEditable(false);
        securityQuestion.disable();
        securityAnswer.setEditable(false);
        saveButton.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            dispose();
            new Home(useridString).setVisible(true);

        } else if (ae.getSource() == editButton) {
            enableField();

        } else if (ae.getSource() == deleteButton) {
            if (checkField()) {
                int response = JOptionPane.showConfirmDialog(this, "Do you really want to delete your account?", "Profile", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    try {
                        Conn con = new Conn();
                        String sql = "delete from account where userid = ?;";
                        PreparedStatement st = con.c.prepareStatement(sql);
                        st.setString(1, userId.getText());
                        int rs = st.executeUpdate();
                        if (rs == 1) {
                            JOptionPane.showMessageDialog(this, "Account deleted successfully", "Profile", JOptionPane.PLAIN_MESSAGE);
                            dispose();
                            new Login().setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(this, "Account couldn't deleted", "Profile", JOptionPane.ERROR_MESSAGE);
                        }
                        st.close();
                        con.c.close();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Couldn't connect to the server", "Profile", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } else if (ae.getSource() == saveButton) {
            if (checkField() == false) {
                JOptionPane.showMessageDialog(this, "Please fill up the form", "Incomplete Information", JOptionPane.ERROR_MESSAGE);

            } else if (checkPassword(password.getText()) == false) {
                JOptionPane.showMessageDialog(this, "Password must contain:\n1. At least one number.\n2. At least one lower case letter.\n3. At least one upper case letter.\n4. At least one special character from [!@#$%^&*()+=_-].\n5. No whitespace.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Conn con = new Conn();
                    String sql = "select * from account where username = ?;";
                    PreparedStatement st = con.c.prepareStatement(sql);
                    st.setString(1, userName.getText());
                    ResultSet rs = st.executeQuery();
//                    rs.last();
//                    int r = rs.getRow();
                    int r = 0;
                    while (rs.next()) {
                        r++;
                    }
//                    System.out.println("r = " + r);

                    if (r > 1) {
                        JOptionPane.showMessageDialog(this, "Username already taken", "Signup", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        sql = "update account set username=?, name=?, password=?, date_of_birth=?, sec_qus=?, sec_ans=? where userid=?;";
                        st = con.c.prepareStatement(sql);

                        st.setString(1, userName.getText());
                        st.setString(2, name.getText());
                        st.setString(3, password.getText());
                        st.setString(4, ((JTextField) dateOfBirth.getDateEditor().getUiComponent()).getText());
                        st.setString(5, (String) securityQuestion.getSelectedItem());
                        st.setString(6, securityAnswer.getText());
                        st.setString(7, userId.getText());
//                        System.out.println("I am here");

                        if (st.executeUpdate() == 1) {
//                            System.out.println("I am here2");
                            JOptionPane.showMessageDialog(this, "Profile Successfully Updated", "Profile", JOptionPane.INFORMATION_MESSAGE);
                            this.useridString = userName.getText();
                            disableField();
                        } else {
                            JOptionPane.showMessageDialog(this, "Profile couldn't Updated", "Error", JOptionPane.ERROR_MESSAGE);
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
        new Profile("").setVisible(true);
    }

}
