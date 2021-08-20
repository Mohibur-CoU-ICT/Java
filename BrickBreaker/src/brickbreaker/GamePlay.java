package brickbreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener, KeyListener {

    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int SCREEN_WIDTH = (int) (dimension.getWidth() * 0.6);
    int SCREEN_HEIGHT = (int) (dimension.getHeight() * 0.8);
    private boolean play = false;
    private int score = 0;
    private int row = 1;
    private int col = 1;
    private int totalBrick = row * col;
    private final Timer timer;
    private final int delay = 1;
    private final int offset = 100;
    private int ballXpos = 160;
    private int ballYpos = 400;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private final int ballRadius = 20;
    private int playerX = 350;
    private final int playerY = SCREEN_HEIGHT - 48;
    private final int paddleWidth = 100;
    private final int paddleHeight = 10;
    private MapGenerator map;

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(delay, this);
        timer.start();

        map = new MapGenerator(row, col);
    }

    @Override
    public void paint(Graphics g) {
        // black background
        g.setColor(Color.black);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // border
        g.setColor(Color.yellow);
        g.fillRect(0, 0, SCREEN_WIDTH, 3); // upper border
        g.fillRect(0, 3, 3, SCREEN_HEIGHT); // left border
        g.fillRect(SCREEN_WIDTH - 19, 3, 3, SCREEN_HEIGHT); // right border

        // paddle
        g.setColor(Color.green);
        g.fillRect(playerX, playerY, paddleWidth, paddleHeight);

        // bricks
        map.draw((Graphics2D) g);

        // ball
        g.setColor(Color.red);
        g.fillOval(ballXpos, ballYpos, ballRadius, ballRadius);

        // score
        g.setColor(Color.green);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Level : " + row, SCREEN_WIDTH - 230, 20);
        g.drawString("Score : " + score, SCREEN_WIDTH - 130, 20);

        // gameover
        if (ballYpos >= SCREEN_HEIGHT - ballRadius - paddleHeight) {
            play = false;
            row = 1;
            col = 1;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over!! Score : " + score, 300, 250);

            g.drawString("Press Enter to Restart!!", 280, 300);
        }

        // won
        if (totalBrick <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won!! Score : " + score, 300, 250);

            g.drawString("Press Enter to play next level!!", 250, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (play) {
            if (ballXpos <= 3) {
                ballXdir = -ballXdir;
            }
            if (ballXpos >= SCREEN_WIDTH - ballRadius - 18) {
                ballXdir = -ballXdir;
            }
            if (ballYpos <= 5) {
                ballYdir = -ballYdir;
            }
            Rectangle ballRect = new Rectangle(ballXpos, ballYpos, ballRadius, ballRadius);
            Rectangle paddleRect = new Rectangle(playerX, playerY, paddleWidth, paddleHeight);
            if (ballRect.intersects(paddleRect)) {
                ballYdir = -ballYdir;
            }

            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int width = map.brickWidth;
                        int height = map.brickHeight;
                        int brickXpos = offset + j * width;
                        int brickYpos = offset + i * height;
                        Rectangle brickRectangle = new Rectangle(brickXpos, brickYpos, width, height);

                        if (ballRect.intersects(brickRectangle)) {
                            map.setBrick(0, i, j);
                            totalBrick--;
                            score += row;
                            if (ballXpos + ballRadius - 1 <= brickXpos || ballXpos + 1 >= brickXpos + width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballXpos += ballXdir;
            ballYpos += ballYdir;
        }
        repaint();
    }

    private void moveLeft() {
        play = true;
        if (play && totalBrick > 0 && ballYpos < SCREEN_HEIGHT - ballRadius - paddleHeight) {
            playerX -= 20;
        }
    }

    private void moveRight() {
        play = true;
        if (play && totalBrick > 0 && ballYpos < SCREEN_HEIGHT - ballRadius - paddleHeight) {
            playerX += 20;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 0) {
                playerX = 0;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= SCREEN_WIDTH - paddleWidth - 20) {
                playerX = SCREEN_WIDTH - paddleWidth - 20;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                if (totalBrick == 0) {
                    ballXpos = 160;
                    ballYpos = 400;
                    ballXdir = -1;
                    ballYdir = -2;
                    row++;
                    col++;
                    totalBrick = row * col;
                    map = new MapGenerator(row, col);
                }
                if (ballYpos >= SCREEN_HEIGHT - ballRadius - paddleHeight) {
                    score = 0;
                    ballXpos = 160;
                    ballYpos = 400;
                    ballXdir = -1;
                    ballYdir = -2;
                    row = 1;
                    col = 1;
                    totalBrick = row * col;
                    map = new MapGenerator(row, col);
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            play = !play;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
