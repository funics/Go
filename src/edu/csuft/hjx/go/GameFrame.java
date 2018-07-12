package edu.csuft.hjx.go;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * 游戏窗口
 * 
 * @author hjx
 *
 */
public class GameFrame extends JFrame{
	GamePanel gamePanel;
	GameModel model;
	
	JMenuItem restartMItem;
	JRadioButtonMenuItem easyRBItem;
	JRadioButtonMenuItem normalRBItem;
	JRadioButtonMenuItem hardRBItem;
	JRadioButtonMenuItem whiteRBItem;
	JRadioButtonMenuItem blackRBItem;
	

	public GameFrame(GameModel model) {
		this.model = model;
		
		setTitle("五子棋 - 黄季秀作品");
		setSize(466, 514);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		gamePanel = new GamePanel(model, "res/Blank_Go_board_9x9.png");
		setContentPane(gamePanel);
		
		initMenu();
		
		setVisible(true);
	}

	private void initMenu() {
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
		JMenu startMenu = new JMenu("开始");
		menubar.add(startMenu);
		restartMItem = new JMenuItem("重新开始");
		startMenu.add(restartMItem);
		
		JMenu pieceMenu = new JMenu("棋子");
		menubar.add(pieceMenu);
		whiteRBItem = new JRadioButtonMenuItem("白子");
		blackRBItem = new JRadioButtonMenuItem("黑子", true);
		pieceMenu.add(blackRBItem);
		pieceMenu.add(whiteRBItem);
		ButtonGroup pieceBG = new ButtonGroup();
		pieceBG.add(whiteRBItem);
		pieceBG.add(blackRBItem);
		
		JMenu modeMenu = new JMenu("模式");
		menubar.add(modeMenu);
		easyRBItem = new JRadioButtonMenuItem("简单", true);
		normalRBItem = new JRadioButtonMenuItem("普通");
		hardRBItem = new JRadioButtonMenuItem("困难");
		modeMenu.add(easyRBItem);
		modeMenu.add(normalRBItem);
		modeMenu.add(hardRBItem);
		ButtonGroup modeBG = new ButtonGroup();
		modeBG.add(easyRBItem);
		modeBG.add(normalRBItem);
		modeBG.add(hardRBItem);
		
		JMenu battleMenu = new JMenu("对战");
		menubar.add(battleMenu);
		JRadioButtonMenuItem pveRBItem = new JRadioButtonMenuItem("人机", true);
		JRadioButtonMenuItem pvpRBItem = new JRadioButtonMenuItem("人人");
		battleMenu.add(pveRBItem);
		battleMenu.add(pvpRBItem);
		ButtonGroup battleBG = new ButtonGroup();
		battleBG.add(pveRBItem);
		battleBG.add(pvpRBItem);
		
		
		//"开始"监听器
		restartMItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gamePanel.clearPanel();
			}
		});
		
		//"棋子"监听器
		ActionListener pieceAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.clearPanel();
				
				if(e.getSource()==whiteRBItem) {
					gamePanel.addPiece(getWidth()/2, getHeight()/2);
					gamePanel.repaint();
				}
			}
		};
		
		blackRBItem.addActionListener(pieceAL);
		whiteRBItem.addActionListener(pieceAL);
		
		//"模式"监听器
		ActionListener modeAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==easyRBItem) {
					blackRBItem.setSelected(true);
					reChessSize(466, 514);
					setLocationRelativeTo(null);
					model = new GameModel(9);
					gamePanel = new GamePanel(model, "res/Blank_Go_board_9x9.png");
					setContentPane(gamePanel);
					Piece.size = 40;
					Piece.cellSize = 50;
				}
				if(e.getSource()==normalRBItem) {
					blackRBItem.setSelected(true);
					reChessSize(536, 584);
					setLocationRelativeTo(null);
					model = new GameModel(13);
					gamePanel = new GamePanel(model, "res/Blank_Go_board_13x13.png");
					setContentPane(gamePanel);
					Piece.size = 35;
					Piece.cellSize = 40;
				}
				if(e.getSource()==hardRBItem) {
					blackRBItem.setSelected(true);
					reChessSize(586, 634);
					setLocationRelativeTo(null);
					model = new GameModel(19);
					gamePanel = new GamePanel(model, "res/Blank_Go_board_19x19.png");
					setContentPane(gamePanel);
					Piece.size = 25;
					Piece.cellSize = 30;
				}
				
			}
		};

		easyRBItem.addActionListener(modeAL);
		normalRBItem.addActionListener(modeAL);
		hardRBItem.addActionListener(modeAL);
		
		//对战监听器
		ActionListener battleAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.clearPanel();
				if(e.getSource()==pvpRBItem)
					GamePanel.canAI = false;
				if(e.getSource()==pveRBItem)
					GamePanel.canAI = true;
			}
		};
		
		pvpRBItem.addActionListener(battleAL);
		pveRBItem.addActionListener(battleAL);
	}
	
	/**
	 * 重设棋盘大小
	 * @param x
	 * @param y
	 */
	public void reChessSize(int x, int y){
		setSize(x, y);
	}
}

