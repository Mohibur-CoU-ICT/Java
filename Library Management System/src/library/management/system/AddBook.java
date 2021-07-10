package library.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

public class AddBook extends JFrame implements ActionListener {

    private final JPanel contentPane;
    private final JTextField book_id, book_name, writer, publisher, isbn, page;
    private final JComboBox edition;
    private final JButton addButton, backButton;
    private final int frame_length = 800, frame_height = 600, no_of_field = 8;
    private final int label_length = 100, field_length = 300, field_width = 25, y_gap = 40;
    private final int x_start = (frame_length - label_length - field_length) / 2;
    private final int y_start = (frame_height - no_of_field * y_gap - 30) / 2; // title bar height = 30
    String useridString;

    AddBook(String useridString) {
        this.useridString = useridString;
//        System.out.println(x_start + " " + y_start);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add Book");
        setResizable(false);
        setSize(frame_length, frame_height);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel l1 = new JLabel("Book Id");
        createJLabel(l1, x_start, y_start, label_length, field_width);

        book_id = new JTextField();
        createJTextField(book_id, x_start + label_length, y_start, field_length, field_width);
        book_id.setText(gen_random());

        JLabel l2 = new JLabel("Book Name");
        createJLabel(l2, x_start, y_start + y_gap, label_length, field_width);

        book_name = new JTextField();
        createJTextField(book_name, x_start + label_length, y_start + y_gap, field_length, field_width);

        JLabel l3 = new JLabel("Writer");
        createJLabel(l3, x_start, y_start + 2 * y_gap, label_length, 25);

        writer = new JTextField();
        createJTextField(writer, x_start + label_length, y_start + 2 * y_gap, field_length, field_width);

        JLabel l4 = new JLabel("Publisher");
        createJLabel(l4, x_start, y_start + 3 * y_gap, label_length, field_width);

        publisher = new JTextField();
        createJTextField(publisher, x_start + label_length, y_start + 3 * y_gap, field_length, field_width);

        JLabel l5 = new JLabel("ISBN");
        createJLabel(l5, x_start, y_start + 4 * y_gap, label_length, field_width);

        isbn = new JTextField();
        createJTextField(isbn, x_start + label_length, y_start + 4 * y_gap, field_length, field_width);

        JLabel l6 = new JLabel("Edition");
        createJLabel(l6, x_start, y_start + 5 * y_gap, label_length, field_width);

        edition = new JComboBox();
        edition.setModel(new DefaultComboBoxModel(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}));
        edition.setBounds(x_start + label_length, y_start + 5 * y_gap, field_length, field_width);
        edition.setBackground(Color.WHITE);
        edition.setSelectedIndex(-1);
        contentPane.add(edition);

        JLabel l8 = new JLabel("Page");
        createJLabel(l8, x_start, y_start + 6 * y_gap, label_length, field_width);

        page = new JTextField();
        createJTextField(page, x_start + label_length, y_start + 6 * y_gap, field_length, field_width);

        backButton = new JButton("Back");
        createButton(backButton, x_start, (int) (y_start + 7.5 * y_gap), label_length, field_width);

        addButton = new JButton("Add");
        createButton(addButton, x_start + 3 * label_length, (int) (y_start + 7.5 * y_gap), label_length, field_width);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(90, 180, 45), 2),
                "Add Book",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Serif", Font.TRUETYPE_FONT, 14),
                new Color(120, 60, 30)));
        panel.setBounds(x_start / 2, y_start / 2, (label_length + field_length + x_start), no_of_field * y_gap + y_start);
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
//        txtFld.setBorder(BorderFactory.createEmptyBorder());
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

    private String gen_random() {
        Random r = new Random();
        int ran_num = r.nextInt(9000) + 1000;
        return "" + ran_num;
    }

    private boolean checkField() {
        return book_id.getText().length() != 0
                && book_name.getText().length() != 0
                && writer.getText().length() != 0
                && publisher.getText().length() != 0
                && isbn.getText().length() != 0
                && edition.getSelectedIndex() != -1
                && page.getText().length() != 0;
    }

    private void resetField() {
        book_id.setText(gen_random());
        book_name.setText("");
        writer.setText("");
        publisher.setText("");
        isbn.setText("");
        page.setText("");
        edition.setSelectedIndex(-1);
    }

    private boolean checkISBN(String isbn) {
        final String pattern1 = "\\d{10}";
        final String pattern2 = "\\d{13}";
        Pattern pat1 = Pattern.compile(pattern1);
        Pattern pat2 = Pattern.compile(pattern2);
        Matcher mat1 = pat1.matcher(isbn);
        Matcher mat2 = pat2.matcher(isbn);
        return mat1.matches() || mat2.matches();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            if (checkField() == false) {
                JOptionPane.showMessageDialog(this, "Please fill up the fields", "Add Book", JOptionPane.WARNING_MESSAGE);

            } else if (book_id.getText().length() != 4) {
                JOptionPane.showMessageDialog(this, "Book id must be a 4 digit number", "Add Book", JOptionPane.ERROR_MESSAGE);

            } else if (checkISBN(isbn.getText()) == false) {
                JOptionPane.showMessageDialog(this, "ISBN should be 10 or 13 digits number", "Add Book", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int b_id = Integer.parseInt(book_id.getText());
                    int pg = Integer.parseInt(page.getText());

                    Conn con = new Conn();
                    String sql = "insert into book(book_id, book_name, writer, publisher, isbn, edition, page) values(?, ?, ?, ?, ?, ?, ?);";
                    PreparedStatement st = con.c.prepareStatement(sql);

                    st.setInt(1, b_id);
                    st.setString(2, book_name.getText());
                    st.setString(3, writer.getText());
                    st.setString(4, publisher.getText());
                    st.setString(5, isbn.getText());
                    st.setInt(6, (int) edition.getSelectedItem());
                    st.setInt(7, pg);

                    if (st.executeUpdate() == 1) {
                        JOptionPane.showMessageDialog(this, "Successfully Added", "Add Book", JOptionPane.INFORMATION_MESSAGE);
                        resetField();
                    } else {
                        JOptionPane.showMessageDialog(this, "Book could not be added", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    st.close();
                    con.c.close();

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Book Id and page must be number", "Add Book", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Couldn't connect to the server", "Add Book", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (ae.getSource() == backButton) {
            dispose();
            new Home(useridString).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new AddBook("").setVisible(true);
    }

}
