package edu.csuft.hjx.go;

import java.awt.Color;
import java.awt.Graphics;

/**
 * ����
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
	 * ���ӣ�����һ������
	 * 
	 * @param x
	 * @param y
	 */
	public Piece(int x, int y) {
		this.x = x/cellSize*cellSize+cellSize/2;
		this.y = y/cellSize*cellSize+cellSize/2;
	}
	
	/**
	 * ���ƣ���ʾ�ڻ����У�
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		g.setColor(isBlack? Color.BLACK : Color.WHITE);
		g.fillOval(x-size/2, y-size/2, size, size);
	}
}
