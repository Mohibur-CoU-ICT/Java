package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe extends JFrame implements ActionListener {

    Random random = new Random();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton replay;
    boolean player1_turn;
    boolean draw;
    boolean game_end;

    TicTacToe() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 750);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(50, 50, 50));
        setLayout(new BorderLayout());
        setTitle("Tic-Tac-Toe");

        title_panel.setLayout(new GridLayout(1, 2));
        title_panel.setBounds(0, 0, 750, 75);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 40));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setOpaque(true);
        title_panel.add(textfield);

        replay = new JButton("Replay");
        replay.setFocusPainted(false);
        replay.addActionListener(this);
        replay.setOpaque(true);
        title_panel.add(replay);

        add(title_panel, BorderLayout.NORTH);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 100));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        add(button_panel);

        game_end = false;
        first_turn();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        for (int i = 0; i < 9; i++) {
            if (ae.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O's turn");
                        checkWinner();
                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X's turn");
                        checkWinner();
                    }
                }
            }
        }
        if (game_end) {
            stop_game();
            if (ae.getSource() == replay) {
                restart_game();
            }
        }

    }

    private void first_turn() {
        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X's turn");
        } else {
            player1_turn = false;
            textfield.setText("O's turn");
        }
    }

    public void checkWinner() {
        // check if X wins
        if (buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("X")) {
            xWins(0, 1, 2);
        } else if (buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("X")) {
            xWins(3, 4, 5);
        } else if (buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X")) {
            xWins(6, 7, 8);
        } else if (buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("X")) {
            xWins(0, 3, 6);
        } else if (buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("X")) {
            xWins(1, 4, 7);
        } else if (buttons[2].getText().equals("X") && buttons[5].getText().equals("X") && buttons[8].getText().equals("X")) {
            xWins(2, 5, 8);
        } else if (buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X")) {
            xWins(0, 4, 8);
        } else if (buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X")) {
            xWins(2, 4, 6);
        } // check if O wins
        else if (buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("O")) {
            oWins(0, 1, 2);
        } else if (buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("O")) {
            oWins(3, 4, 5);
        } else if (buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O")) {
            oWins(6, 7, 8);
        } else if (buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("O")) {
            oWins(0, 3, 6);
        } else if (buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("O")) {
            oWins(1, 4, 7);
        } else if (buttons[2].getText().equals("O") && buttons[5].getText().equals("O") && buttons[8].getText().equals("O")) {
            oWins(2, 5, 8);
        } else if (buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O")) {
            oWins(0, 4, 8);
        } else if (buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O")) {
            oWins(2, 4, 6);
        } // check if game is a draw
        else {
            draw = true;
            for (int i = 0; i < 9; i++) {
                if (buttons[i].getText().length() == 0) {
                    draw = false;
                    break;
                }
            }
            if (draw) {
                draw();
            }
        }
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        textfield.setText("X wins");
        game_end = true;
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        textfield.setText("O wins");
        game_end = true;
    }

    public void draw() {
        textfield.setText("Game Draw");
        game_end = true;
    }

    public void stop_game() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }

    public void restart_game() {
        game_end = false;
        first_turn();
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
            buttons[i].setText("");
            buttons[i].setBackground(new JButton().getBackground());
        }
    }

    public static void main(String[] args) {
        new TicTacToe().setVisible(true);
    }

}
