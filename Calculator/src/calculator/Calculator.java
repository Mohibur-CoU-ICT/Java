package calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener {

    JTextField result;
    JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint;
    JButton btnPlus, btnMinus, btnMuliply, btnDivide, btnEqual, btnMod, btnBack, btnClear, btn00;

    Calculator() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(600, 100, 305, 370);
        setTitle("Calculator");
        setResizable(false);
        setLayout(null);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.WHITE);
        setJMenuBar(menubar);

        JMenu about = new JMenu("About");
        menubar.add(about);

        JMenuItem aboutDeveloper = new JMenuItem("About Developer");
        aboutDeveloper.addActionListener(this);
        about.add(aboutDeveloper);

        result = new JTextField();
        result.setBounds(10, 10, 270, 40);
        result.setFont(new Font("Times new roman", Font.BOLD, 18));
        result.setBackground(Color.WHITE);
        result.setEditable(false);
        add(result);

        btnBack = new JButton();
        setButton(btnBack, "B", 2, 1, 60, 40, 10);

        btnClear = new JButton();
        setButton(btnClear, "C", 2, 2, 60, 40, 10);

        btn00 = new JButton();
        setButton(btn00, "00", 2, 3, 60, 40, 10);

        btnPlus = new JButton();
        setButton(btnPlus, "+", 2, 4, 60, 40, 10);

        btn7 = new JButton();
        setButton(btn7, "7", 3, 1, 60, 40, 10);

        btn8 = new JButton();
        setButton(btn8, "8", 3, 2, 60, 40, 10);

        btn9 = new JButton();
        setButton(btn9, "9", 3, 3, 60, 40, 10);

        btnMinus = new JButton();
        setButton(btnMinus, "-", 3, 4, 60, 40, 10);

        btn4 = new JButton();
        setButton(btn4, "4", 4, 1, 60, 40, 10);

        btn5 = new JButton();
        setButton(btn5, "5", 4, 2, 60, 40, 10);

        btn6 = new JButton();
        setButton(btn6, "6", 4, 3, 60, 40, 10);

        btnMuliply = new JButton();
        setButton(btnMuliply, "*", 4, 4, 60, 40, 10);

        btn1 = new JButton();
        setButton(btn1, "1", 5, 1, 60, 40, 10);

        btn2 = new JButton();
        setButton(btn2, "2", 5, 2, 60, 40, 10);

        btn3 = new JButton();
        setButton(btn3, "3", 5, 3, 60, 40, 10);

        btnDivide = new JButton();
        setButton(btnDivide, "/", 5, 4, 60, 40, 10);

        btn0 = new JButton();
        setButton(btn0, "0", 6, 1, 60, 40, 10);

        btnPoint = new JButton();
        setButton(btnPoint, ".", 6, 2, 60, 40, 10);

        btnEqual = new JButton();
        setButton(btnEqual, "=", 6, 3, 60, 40, 10);

        btnMod = new JButton();
        setButton(btnMod, "%", 6, 4, 60, 40, 10);
    }

    void setButton(JButton btn, String btnName, int row, int col, int width, int height, int gap) {
        btn.setBounds(gap + (col - 1) * (gap + width), gap + (row - 1) * (gap + height), width, height);
//        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn.setBackground(Color.WHITE);
        btn.addActionListener(this);
        btn.setFocusable(false);
        btn.setText(btnName);
        add(btn);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String s = ae.getActionCommand();
//        System.out.println("Button pressed : " + s);
        String res = result.getText();
        int resLen = res.length();
//        System.out.println("Reselt length : " + resLen);
        if (isNumber(s)) {
            result.setText(res + s);
        } else if (s.equals("B")) {
            if (resLen > 0) {
                result.setText(res.substring(0, resLen - 1));
            }
        } else if (s.equals("C")) {
            result.setText("");
        } else if (s.equals("0") || s.equals("00")) {
            if (resLen > 0) {
                char last = res.charAt(resLen - 1);
                if (isOperator(last) == false) {
                    result.setText(res + s);
                }
            }
        } else if (s.equals(".")) {
            if (res.contains(".") == false || (hasOperator(res) && countDot(res) < 2)) {
                result.setText(res + s);
            }
        } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%")) {
            if (resLen > 0) {
                char last = res.charAt(resLen - 1);
                if (isOperator(last)) {
                    result.setText(res.substring(0, resLen - 1) + s);
                } else {
                    result.setText(res + s);
                }
            }
        } else if (s.equals("=")) {
            int opIndex = -1;
            char operator = '#';
            for (int i = 0; i < resLen; i++) {
                char c = res.charAt(i);
                if (isOperator(c)) {
                    opIndex = i;
                    operator = c;
                }
            }
//            System.out.println("Operator index : " + opIndex);
            if (opIndex != -1 && resLen > opIndex + 1) {
                double num1 = Double.parseDouble(res.substring(0, opIndex));
                double num2 = Double.parseDouble(res.substring(opIndex + 1));
                double ans = 0;
                if (operator == '+') {
                    ans = num1 + num2;
                } else if (operator == '-') {
                    ans = num1 - num2;
                } else if (operator == '*') {
                    ans = num1 * num2;
                } else if (operator == '/') {
                    ans = num1 / num2;
                } else if (operator == '%') {
                    ans = num1 % num2;
                }
                String s2 = Double.toString(ans);
                if (s2.endsWith(".0")) {
                    s2 = String.format("%.0f", ans);
                }
                result.setText(s2);
            }
        } else if (s.equals("About Developer")) {
            new AboutDeveloper().setVisible(true);
        }
    }

    private boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '%');
    }

    private boolean hasOperator(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isOperator(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private int countDot(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                ans++;
            }
        }
        return ans;
    }

    private boolean isNumber(String s) {
        char c = s.charAt(0);
        return (c >= '1' && c <= '9');
    }

    public static void main(String[] args) {
        new Calculator().setVisible(true);
    }

}
