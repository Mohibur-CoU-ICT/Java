package library.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ReturnBook extends JFrame implements ActionListener {

    private final JPanel contentPane;
    private final JTextField book_id, book_name, writer, publisher, isbn, edition, page;
    private final JTextField student_id, student_name, father_name, faculty, department, session, year, semester, date_of_return;
    private final JButton bookSearchButton, studentSearchButton, returnButton, backButton;
    private final int frame_length = 800, frame_height = 600, no_of_field = 10;
    private final int label_length = 100, field_length = 200, field_width = 25, y_gap = 40;
    private final int x_start = (frame_length - 3 * label_length - 2 * field_length) / 2;
    private final int y_start = (frame_height - no_of_field * y_gap) / 2; // title bar height = 30
    String useridString;

    ReturnBook(String useridString) {
        this.useridString = useridString;
//        System.out.println(x_start + " " + y_start);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Return Book");
        setResizable(false);
        setSize(frame_length, frame_height);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel book_l1, book_l2, book_l3, book_l4, book_l5, book_l6, book_l7;

        book_l1 = new JLabel("Book Id");
        createJLabel(book_l1, x_start, y_start, label_length, field_width);

        book_id = new JTextField();
        createJTextField(book_id, x_start + label_length, y_start, field_length / 2, field_width);
        book_id.setEditable(true);

        bookSearchButton = new JButton("Search");
        createButton(bookSearchButton, x_start + label_length + field_length / 2, y_start, label_length, field_width);

        book_l2 = new JLabel("Book Name");
        createJLabel(book_l2, x_start, y_start + y_gap, label_length, field_width);

        book_name = new JTextField();
        createJTextField(book_name, x_start + label_length, y_start + y_gap, field_length, field_width);

        book_l3 = new JLabel("Writer");
        createJLabel(book_l3, x_start, y_start + 2 * y_gap, label_length, field_width);

        writer = new JTextField();
        createJTextField(writer, x_start + label_length, y_start + 2 * y_gap, field_length, field_width);

        book_l4 = new JLabel("Publisher");
        createJLabel(book_l4, x_start, y_start + 3 * y_gap, label_length, field_width);

        publisher = new JTextField();
        createJTextField(publisher, x_start + label_length, y_start + 3 * y_gap, field_length, field_width);

        book_l5 = new JLabel("ISBN");
        createJLabel(book_l5, x_start, y_start + 4 * y_gap, label_length, field_width);

        isbn = new JTextField();
        createJTextField(isbn, x_start + label_length, y_start + 4 * y_gap, field_length, field_width);

        book_l6 = new JLabel("Edition");
        createJLabel(book_l6, x_start, y_start + 5 * y_gap, label_length, field_width);

        edition = new JTextField();
        createJTextField(edition, x_start + label_length, y_start + 5 * y_gap, field_length, field_width);

        book_l7 = new JLabel("Page");
        createJLabel(book_l7, x_start, y_start + 6 * y_gap, label_length, field_width);

        page = new JTextField();
        createJTextField(page, x_start + label_length, y_start + 6 * y_gap, field_length, field_width);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(90, 180, 45), 2),
                "Book Details",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Serif", Font.TRUETYPE_FONT, 14),
                new Color(110, 60, 30)));
        panel.setBackground(Color.WHITE);
        panel.setBounds(x_start / 2, y_start / 2, label_length + field_length + x_start, (int) (9.5 * y_gap));
        contentPane.add(panel);

        JLabel stu_l1, stu_l2, stu_l3, stu_l4, stu_l5, stu_l6, stu_l7, stu_l8;

        stu_l1 = new JLabel("Student Id");
        createJLabel(stu_l1, x_start + 2 * label_length + field_length, y_start, label_length, field_width);

        student_id = new JTextField();
        createJTextField(student_id, 3 * x_start + 2 * label_length + field_length, y_start, field_length / 2, field_width);
        student_id.setEditable(true);

        studentSearchButton = new JButton("Search");
        createButton(studentSearchButton, (int) (3 * x_start + 2 * label_length + 1.5 * field_length), y_start, label_length, field_width);

        stu_l2 = new JLabel("Student Name");
        createJLabel(stu_l2, x_start + 2 * label_length + field_length, y_start + y_gap, label_length, field_width);

        student_name = new JTextField();
        createJTextField(student_name, 3 * x_start + 2 * label_length + field_length, y_start + y_gap, field_length, field_width);

        stu_l3 = new JLabel("Father Name");
        createJLabel(stu_l3, x_start + 2 * label_length + field_length, y_start + 2 * y_gap, label_length, field_width);

        father_name = new JTextField();
        createJTextField(father_name, 3 * x_start + 2 * label_length + field_length, y_start + 2 * y_gap, field_length, field_width);

        stu_l4 = new JLabel("Faculty");
        createJLabel(stu_l4, x_start + 2 * label_length + field_length, y_start + 3 * y_gap, label_length, field_width);

        faculty = new JTextField();
        createJTextField(faculty, 3 * x_start + 2 * label_length + field_length, y_start + 3 * y_gap, field_length, field_width);

        stu_l5 = new JLabel("Department");
        createJLabel(stu_l5, x_start + 2 * label_length + field_length, y_start + 4 * y_gap, label_length, field_width);

        department = new JTextField();
        createJTextField(department, 3 * x_start + 2 * label_length + field_length, y_start + 4 * y_gap, field_length, field_width);

        stu_l6 = new JLabel("Session");
        createJLabel(stu_l6, x_start + 2 * label_length + field_length, y_start + 5 * y_gap, label_length, field_width);

        session = new JTextField();
        createJTextField(session, 3 * x_start + 2 * label_length + field_length, y_start + 5 * y_gap, field_length, field_width);

        stu_l7 = new JLabel("Year");
        createJLabel(stu_l7, x_start + 2 * label_length + field_length, y_start + 6 * y_gap, field_length, field_width);

        year = new JTextField();
        createJTextField(year, 3 * x_start + 2 * label_length + field_length, y_start + 6 * y_gap, field_length, field_width);

        stu_l8 = new JLabel("Semester");
        createJLabel(stu_l8, x_start + 2 * label_length + field_length, y_start + 7 * y_gap, label_length, field_width);

        semester = new JTextField();
        createJTextField(semester, 3 * x_start + 2 * label_length + field_length, y_start + 7 * y_gap, field_length, field_width);

        JPanel panel2 = new JPanel();
        panel2.setBorder(new TitledBorder(new LineBorder(new Color(90, 180, 45), 2),
                "Student Details",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Serif", Font.TRUETYPE_FONT, 14),
                new Color(110, 60, 30)));
        panel2.setBounds((int) (2.5 * x_start + label_length + field_length), y_start / 2, label_length + field_length + x_start, (int) (9.5 * y_gap));
        panel2.setBackground(Color.WHITE);
        contentPane.add(panel2);

        JLabel doi = new JLabel("Date of Return");
        createJLabel(doi, x_start, y_start + 9 * y_gap, label_length, field_width);

        date_of_return = new JTextField();
        createJTextField(date_of_return, x_start + label_length, y_start + 9 * y_gap, field_length, field_width);
        date_of_return.setText(getTodaysDate());
        date_of_return.setHorizontalAlignment(SwingConstants.CENTER);

        returnButton = new JButton("Return");
        createButton(returnButton, x_start + 2 * label_length + field_length, y_start + 9 * y_gap, label_length, field_width);

        backButton = new JButton("Back");
        createButton(backButton, (x_start + 2 * label_length + 2 * field_length), y_start + 9 * y_gap, label_length, field_width);

        JPanel panel3 = new JPanel();
        panel3.setBorder(new LineBorder(new Color(90, 180, 45), 2));
        panel3.setBounds(x_start / 2, (int) (y_start + 8.5 * y_gap), frame_length - x_start, (int) (1.5 * y_gap));
        panel3.setBackground(Color.WHITE);
        contentPane.add(panel3);
    }

    private void createJLabel(JLabel label, int a, int b, int c, int d) {
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setForeground(new Color(44, 22, 11));
        label.setBounds(a, b, c, d);
        contentPane.add(label);
    }

    private void createJTextField(JTextField txtFld, int a, int b, int c, int d) {
        txtFld.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        txtFld.setEditable(false);
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

    private String getTodaysDate() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        int len = 11;
        if (date.toLocaleString().charAt(6) == ',') {
            len++;
        }
        String todaysDate = date.toLocaleString().substring(0, len);
        return todaysDate;
    }

    private boolean checkBookField() {
        return book_id.getText().length() != 0
                && book_name.getText().length() != 0
                && writer.getText().length() != 0
                && publisher.getText().length() != 0
                && isbn.getText().length() != 0
                && page.getText().length() != 0;
    }

    private boolean checkStudentField() {
        return student_id.getText().length() != 0
                && student_name.getText().length() != 0
                && father_name.getText().length() != 0
                && session.getText().length() != 0
                && faculty.getText().length() != 0
                && department.getText().length() != 0
                && year.getText().length() != 0
                && semester.getText().length() != 0;
    }

    private void resetBookField() {
//        book_id.setText("");
        book_name.setText("");
        writer.setText("");
        publisher.setText("");
        isbn.setText("");
        edition.setText("");
        page.setText("");
    }

    private void resetStudentField() {
//        student_id.setText("");
        student_name.setText("");
        father_name.setText("");
        session.setText("");
        faculty.setText("");
        department.setText("");
        year.setText("");
        semester.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Object source = ae.getSource();

            if (source.equals(bookSearchButton)) {

                if (book_id.getText().length() == 0) {
                    JOptionPane.showMessageDialog(this, "Enter book id", "Return Book", JOptionPane.ERROR_MESSAGE);

                } else if (book_id.getText().length() != 4) {
                    JOptionPane.showMessageDialog(this, "Book id must be a 4 digit number", "Return Book", JOptionPane.ERROR_MESSAGE);
                    resetBookField();
                } else {
                    int b_id = Integer.parseInt(book_id.getText());
                    Conn con = new Conn();
                    String sql = "select * from book where book_id = ?;";
                    PreparedStatement st = con.c.prepareStatement(sql);
                    st.setInt(1, b_id);
                    ResultSet rs = st.executeQuery();

                    if (rs.next()) {
                        book_name.setText(rs.getString("book_name"));
                        writer.setText(rs.getString("writer"));
                        publisher.setText(rs.getString("publisher"));
                        isbn.setText(rs.getString("isbn"));
                        edition.setText(rs.getString("edition"));
                        page.setText(rs.getString("page"));
                    } else {
                        JOptionPane.showMessageDialog(this, "Book not found", "Return Book", JOptionPane.ERROR_MESSAGE);
                        resetBookField();
                    }
                    rs.close();
                    st.close();
                    con.c.close();
                }
            } else if (source.equals(studentSearchButton)) {

                if (student_id.getText().length() == 0) {
                    JOptionPane.showMessageDialog(this, "Enter student id", "Return Book", JOptionPane.ERROR_MESSAGE);

                } else if (student_id.getText().length() != 8) {
                    JOptionPane.showMessageDialog(this, "Student id must be a 8 digit number", "Return Book", JOptionPane.ERROR_MESSAGE);
                    resetStudentField();
                } else {
                    int stu_id = Integer.parseInt(student_id.getText());
                    Conn con = new Conn();
                    String sql = "select * from student where student_id = ?;";
                    PreparedStatement st = con.c.prepareStatement(sql);
                    st.setInt(1, stu_id);
                    ResultSet rs = st.executeQuery();

                    if (rs.next()) {
                        student_name.setText(rs.getString("student_name"));
                        father_name.setText(rs.getString("father_name"));
                        faculty.setText(rs.getString("faculty"));
                        department.setText(rs.getString("department"));
                        session.setText(rs.getString("session"));
                        year.setText(rs.getString("year"));
                        semester.setText(rs.getString("semester"));
                    } else {
                        JOptionPane.showMessageDialog(this, "Student not found", "Return Book", JOptionPane.ERROR_MESSAGE);
                        resetStudentField();
                    }
                    rs.close();
                    st.close();
                    con.c.close();
                }

            } else if (source.equals(returnButton)) {
                if (book_id.getText().length() == 0 || student_id.getText().length() == 0) {
                    JOptionPane.showMessageDialog(this, "Search book and student first", "Return Book", JOptionPane.ERROR_MESSAGE);

                } else if (!checkBookField() || !checkStudentField()) {
                    JOptionPane.showMessageDialog(this, "Search book and student first", "Return Book", JOptionPane.ERROR_MESSAGE);
                } else {
                    int b_id = Integer.parseInt(book_id.getText());
                    int stu_id = Integer.parseInt(student_id.getText());
                    Conn con = new Conn();
                    String sql1 = "select * from issue_book where book_id = ? and student_id = ?;";
                    PreparedStatement st1 = con.c.prepareStatement(sql1);
                    st1.setInt(1, b_id);
                    st1.setInt(2, stu_id);
                    ResultSet r1 = st1.executeQuery();

                    if (r1.next()) {
                        String sql2 = "delete from issue_book where book_id = ? and student_id = ?";
                        PreparedStatement st2 = con.c.prepareStatement(sql2);
                        st2.setInt(1, b_id);
                        st2.setInt(2, stu_id);
                        int r2 = st2.executeUpdate();

                        if (r2 == 1) {
//                            JOptionPane.showMessageDialog(this, "Book removed successfully", "Return Book", JOptionPane.INFORMATION_MESSAGE);
                            String sql3 = "insert into return_book (book_id, student_id, date_of_return) values(?, ?, ?);";
                            PreparedStatement st3 = con.c.prepareStatement(sql3);
                            st3.setInt(1, b_id);
                            st3.setInt(2, stu_id);
                            st3.setString(3, date_of_return.getText());
                            int r3 = st3.executeUpdate();

                            if (r3 == 1) {
                                JOptionPane.showMessageDialog(this, "Book returned successfully", "Return Book", JOptionPane.INFORMATION_MESSAGE);
                                resetBookField();
                                resetStudentField();
                            } else {
                                JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Return Book", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Return Book", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, student_name.getText() + " didn't borrow this book", "Return Book", JOptionPane.ERROR_MESSAGE);
                    }
                    r1.close();
                    st1.close();
                    con.c.close();
                }

            } else if (source.equals(backButton)) {
                dispose();
                new Home(useridString).setVisible(true);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Book Id and Student Id must be a number", "Add Book", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Add Book", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ReturnBook("").setVisible(true);
    }

}
