package edu.csuft.hjx.go;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 棋子
 * 
 * @author hjx
 *
 */
public class Piece {
	int x;
	int y;
	boolean isBlack = true;
	static int size = 40;
	static int cellSize = 50;
	
	/**
	 * 落子：创建一个棋子
	 * 
	 * @param x
	 * @param y
	 */
	public Piece(int x, int y) {
		this.x = x/cellSize*cellSize+cellSize/2;
		this.y = y/cellSize*cellSize+cellSize/2;
	}
	
	/**
	 * 绘制（显示在画布中）
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		g.setColor(isBlack? Color.BLACK : Color.WHITE);
		g.fillOval(x-size/2, y-size/2, size, size);
	}
}
