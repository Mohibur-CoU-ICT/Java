package library.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AddStudent extends JFrame implements ActionListener {

    private final JPanel contentPane;
    private final JButton addButton, backButton;
    private final JTextField student_id, student_name, father_name, session;
    private final JComboBox faculty, department, year, semester;
    private final int frame_length = 800, frame_height = 600, no_of_field = 9;
    private final int label_length = 100, field_length = 300, field_width = 25, y_gap = 40;
    private final int x_start = (frame_length - label_length - field_length) / 2;
    private final int y_start = (frame_height - no_of_field * y_gap - 30) / 2; // title bar height = 30
    String useridString;
    
    AddStudent(String useridString) {
        this.useridString = useridString;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add Student");
        setResizable(false);
        setSize(frame_length, frame_height);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel stu_l1, stu_l2, stu_l3, stu_l4, stu_l5, stu_l6, stu_l7, stu_l8;

        stu_l1 = new JLabel("Student Id");
        createJLabel(stu_l1, x_start, y_start, label_length, field_width);

        student_id = new JTextField();
        createJTextField(student_id, x_start + label_length, y_start, field_length, field_width);
        student_id.setText(gen_random());

        stu_l2 = new JLabel("Student Name");
        createJLabel(stu_l2, x_start, y_start + y_gap, label_length, field_width);

        student_name = new JTextField();
        createJTextField(student_name, x_start + label_length, y_start + y_gap, field_length, field_width);

        stu_l3 = new JLabel("Father Name");
        createJLabel(stu_l3, x_start, y_start + 2 * y_gap, label_length, field_width);

        father_name = new JTextField();
        createJTextField(father_name, x_start + label_length, y_start + 2 * y_gap, field_length, field_width);

        stu_l4 = new JLabel("Faculty");
        createJLabel(stu_l4, x_start, y_start + 3 * y_gap, label_length, field_width);

        faculty = new JComboBox();
        faculty.setBounds(x_start + label_length, y_start + 3 * y_gap, field_length, field_width);
        faculty.setBackground(Color.WHITE);
        String facString[] = {"Science", "Arts and Humanities", "Social Science", "Business Studies", "Engineering", "Law"};
        Arrays.sort(facString);
        faculty.setModel(new DefaultComboBoxModel(facString));
        faculty.setSelectedIndex(-1);
        contentPane.add(faculty);

        stu_l5 = new JLabel("Department");
        createJLabel(stu_l5, x_start, y_start + 4 * y_gap, label_length, field_width);

        department = new JComboBox();
        department.setBounds(x_start + label_length, y_start + 4 * y_gap, field_length, field_width);
        department.setBackground(Color.WHITE);
        String deptString[] = {
            "Mathematics", "Physics", "Statistics", "Chemistry", "Pharmacy",
            "English", "Bangla", "Archaelogy", "Economics", "Public Administration", "Anthropology", "Mass Communication and Journalism",
            "Management Studies", "Accounting and Information Systems", "Marketing", "Finance and Banking",
            "Computer Science and Engineering", "Information and Communication Technology",
            "Law"};
        Arrays.sort(deptString);
        department.setModel(new DefaultComboBoxModel(deptString));
        department.setSelectedIndex(-1);
        contentPane.add(department);

        stu_l6 = new JLabel("Session");
        createJLabel(stu_l6, x_start, y_start + 5 * y_gap, label_length, field_width);

        session = new JTextField();
        createJTextField(session, x_start + label_length, y_start + 5 * y_gap, field_length, field_width);

        stu_l7 = new JLabel("Year");
        createJLabel(stu_l7, x_start, y_start + 6 * y_gap, field_length, field_width);

        year = new JComboBox();
        year.setBounds(x_start + label_length, y_start + 6 * y_gap, field_length, field_width);
        year.setBackground(Color.WHITE);
        year.setModel(new DefaultComboBoxModel(new String[]{"1st", "2nd", "3rd", "4th", "5th"}));
        year.setSelectedIndex(-1);
        contentPane.add(year);

        stu_l8 = new JLabel("Semester");
        createJLabel(stu_l8, x_start, y_start + 7 * y_gap, label_length, field_width);

        semester = new JComboBox();
        semester.setBounds(x_start + label_length, y_start + 7 * y_gap, field_length, field_width);
        semester.setBackground(Color.WHITE);
        semester.setModel(new DefaultComboBoxModel(new String[]{"1st", "2nd"}));
        semester.setSelectedIndex(-1);
        contentPane.add(semester);

        backButton = new JButton("Back");
        createButton(backButton, x_start, (int) (y_start + 8.5 * y_gap), label_length, field_width);

        addButton = new JButton("Add");
        createButton(addButton, x_start + 3 * label_length, (int) (y_start + 8.5 * y_gap), label_length, field_width);

        JPanel panel = new JPanel();
        panel.setBounds(x_start / 2, y_start / 2, (label_length + field_length + x_start), no_of_field * y_gap + y_start);
        panel.setBorder(new TitledBorder(
                new LineBorder(new Color(65, 130, 65), 2),
                "Add Student",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Vardana", Font.PLAIN, 14),
                new Color(170, 120, 170)));
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
    }

    private void createJLabel(JLabel label, int a, int b, int c, int d) {
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setForeground(new Color(44, 22, 11));
        label.setBounds(a, b, c, d);
        contentPane.add(label);
    }

    private void createJTextField(JTextField txtFld, int a, int b, int c, int d) {
        txtFld.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
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

    private boolean checkField() {
        return student_id.getText().length() != 0
                && student_name.getText().length() != 0
                && father_name.getText().length() != 0
                && session.getText().length() != 0
                && faculty.getSelectedIndex() != -1
                && department.getSelectedIndex() != -1
                && year.getSelectedIndex() != -1
                && semester.getSelectedIndex() != -1;
    }

    private void resetField() {
        student_id.setText(gen_random());
        student_name.setText("");
        father_name.setText("");
        session.setText("");
        faculty.setSelectedIndex(-1);
        department.setSelectedIndex(-1);
        year.setSelectedIndex(-1);
        semester.setSelectedIndex(-1);
    }

    private String gen_random() {
        Random r = new Random();
        int ran_num = r.nextInt(90000000) + 10000000;
        return "" + ran_num;
    }

    private boolean check_session(String session) {
        if (session.length() != 7) {
            return false;
        } else {
            int session_start = Integer.parseInt(session.substring(2, 4));
            int session_end = Integer.parseInt(session.substring(5, 7));
            return session.charAt(4) == '-' && ((session_start + 1 == session_end) || (session_start - 99 == session_end));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {

            if (checkField() == false) {
                JOptionPane.showMessageDialog(this, "Please fill up the fields", "Add Student", JOptionPane.WARNING_MESSAGE);

            } else if (student_id.getText().length() != 8) {
                JOptionPane.showMessageDialog(this, "Student id must be a 8 digit number", "Add Student", JOptionPane.ERROR_MESSAGE);

            } else if (check_session(session.getText()) == false) {
                JOptionPane.showMessageDialog(this, "Session must be in yyyy-yy format", "Add Student", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int stu_id = Integer.parseInt(student_id.getText());
                    Conn con = new Conn();
                    String sql = "insert into student (student_id, student_name, father_name, faculty, department, session, year, semester) values(?, ?, ?, ?, ?, ?, ?, ?);";
                    PreparedStatement st = con.c.prepareStatement(sql);
                    
                    st.setString(1, student_id.getText());
                    st.setString(2, student_name.getText());
                    st.setString(3, father_name.getText());
                    st.setString(4, (String) faculty.getSelectedItem());
                    st.setString(5, (String) department.getSelectedItem());
                    st.setString(6, session.getText());
                    st.setString(7, (String) year.getSelectedItem());
                    st.setString(8, (String) semester.getSelectedItem());

                    if (st.executeUpdate() == 1) {
                        JOptionPane.showMessageDialog(this, "Student added successfully", "Add Student", JOptionPane.INFORMATION_MESSAGE);
                        resetField();
                    } else {
                        JOptionPane.showMessageDialog(this, "Student can't added", "Add Student", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Student Id must be a integer number", "Add Student", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Couldn't connect to the server", "Add Student", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (ae.getSource() == backButton) {
            dispose();
            new Home(useridString).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new AddStudent("").setVisible(true);
    }

}
