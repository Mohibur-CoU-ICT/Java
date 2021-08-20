package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class MapGenerator {

    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) (dimension.getWidth() * 0.6);
    int height = (int) (dimension.getHeight() * 0.8);
    private final int offset = 100;
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = 1;
            }
        }
        brickWidth = (width - 2 * offset) / col;
        brickHeight = (height / 3) / row;
    }

    public void setBrick(int value, int row, int col) {
        map[row][col] = value;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.white);
                    g.fillRect(j * brickWidth + offset, i * brickHeight + offset, brickWidth, brickHeight);

                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(1));
                    g.drawRect(j * brickWidth + offset, i * brickHeight + offset, brickWidth, brickHeight);
                }
            }
        }
    }

}
