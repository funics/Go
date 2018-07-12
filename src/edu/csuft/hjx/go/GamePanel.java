package edu.csuft.hjx.go;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * ���
 * 
 * @author hjx
 *
 */
public class GamePanel extends JPanel{
	static boolean canAI=true;
	GameModel model;
	Image bg;
	ArrayList<Piece> pieceList = new ArrayList<>();
	AI ai;
	
	/**
	 * ���̵��������
	 */
	MouseAdapter listener = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if (canAI) {
				if (model.check(e.getX(), e.getY()))
					return;
				addPiece(e.getX(), e.getY());
				repaint();
				Location l = ai.explore();
				int aiX = l.getY() * Piece.cellSize + Piece.cellSize / 2;
				int aiY = l.getX() * Piece.cellSize + Piece.cellSize / 2;
				if (model.check(aiX, aiY))
					return;
				addPiece(aiX, aiY);
				repaint();
			}
			else {
				if (model.check(e.getX(), e.getY()))
					return;
				addPiece(e.getX(), e.getY());
				repaint();
			}
		};
	};

	/**
	 * ������Ϸ���
	 * 
	 * @param model
	 */
	public GamePanel(GameModel model, String imageName) {
		this.model = model;
		this.ai = new AI(model);
		
		try {
			bg = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addMouseListener(listener);
	}
	
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		
		//���� 2D ͼ����Ⱦ�Ŀ����ѡ��
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//����
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
//		g.drawLine(0, 0, getWidth(), getHeight());
//		System.out.println(getWidth()+" "+getHeight());
		
		//����
		for (Piece piece : pieceList) {
			piece.paint(graphics);
		}
	}
	
	/**
	 * �������
	 * @param x
	 * @param y
	 */
	public void addPiece(int x, int y) {
		Piece piece = new Piece(x, y);
		piece.isBlack = (pieceList.size() % 2 == 0);
		pieceList.add(piece);
		
		if(model.update(piece))
			JOptionPane.showMessageDialog(this, (piece.isBlack? "BLACK ":"WHITE ")+"WIN!");
		model.show();
	}

	/**
	 * �������
	 */
	public void clearPanel() {
		model.clear();
		pieceList.clear();
		repaint();
	}
}
