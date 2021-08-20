package brickbreaker;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainClass {

    static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = (int) (dimension.getWidth() * 0.6);
    static int height = (int) (dimension.getHeight() * 0.8);

    public static void main(String[] args) {
//        System.out.println(width + " " + height);
        JFrame f = new JFrame();
        f.setTitle("Brick Breaker");
        f.setSize(width, height);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);

        GamePlay gamePlay = new GamePlay();
        f.add(gamePlay);
        f.setVisible(true);

    }

}
