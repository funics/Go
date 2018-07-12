package edu.csuft.hjx.go;

/**
 * 游戏的模型（逻辑）
 * 
 * @author hjx
 *
 */
public class GameModel {
	private int[][] data;
	
	public GameModel(int n) {
		data = new int[n][n];
	}

	public int[][] getData() {
		return data;
	}
	
	public void show() {
		System.out.println("++++++++++++++++++");
		for (int[] row : data) {
			for (int e : row) {
				System.out.print(e + "\t");
			}
		System.out.println();	
		}
		System.out.println("++++++++++++++++++");
	}
	
	/**
	 * 在model中添加棋子
	 */
	public boolean update(Piece piece) {
		int x = piece.x/Piece.cellSize;
		int y = piece.y/Piece.cellSize;
		data[y][x] = piece.isBlack? 1:2;
		
		return isWin(x, y);
	}
	
	/**
	 * 输赢判断
	 * 
	 * @param x
	 * @param y
	 */
	public boolean isWin(int x, int y) {
		int count = 1;
		// 横向
		for (int i = x - 1; (i >= 0) && (data[y][i] == data[y][x]); i--) {
			count++;
		}
		for (int i = x + 1; (i < data.length) && (data[y][i] == data[y][x]); i++) {
			count++;
		}
		if (count >= 5) {
			System.out.println(data[y][x] + "获胜");
			return true;
		}

		// 纵向
		count = 1;
		for (int i = y - 1; (i >= 0) && (data[i][x] == data[y][x]); i--) {
			count++;
		}
		for (int i = y + 1; (i < data.length) && (data[i][x] == data[y][x]); i++) {
			count++;
		}
		if (count >= 5) {
			System.out.println(data[y][x] + "获胜");
			return true;
		}
		
		//左斜
		count = 1;
		int n = x+y;
		for (int i = x+1; (i < data.length) && (n-i>=0) && (data[n-i][i] == data[y][x]); i++) {
			count++;
		}
		for (int i = x-1; (i >= 0) && (n-i<9) && (data[n-i][i] == data[y][x]); i--) {
			count++;
		}
		if (count >= 5) {
			System.out.println(data[y][x] + "获胜");
			return true;
		}
		
		
		//右斜
		count = 1;
		n = y - x;
		for (int i = x+1; (i < data.length) && (n+i<data.length) && (data[n+i][i] == data[y][x]); i++) {
			count++;
		}
		for (int i = x-1; (i >= 0) && (n+i>=0) && (data[n+i][i] == data[y][x]); i--) {
			count++;
		}
		if (count >= 5) {
			System.out.println(data[y][x] + "获胜");
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * 检查棋子是否已存在
	 * @param pieceX
	 * @param pieceY
	 * @return
	 */
	public boolean check(int pieceX, int pieceY) {
		int x = pieceX/Piece.cellSize;
		int y = pieceY/Piece.cellSize;
		if(data[y][x]==0)
			return false;
		return true;
	} 
	
	/**
	 * 清空棋盘模型
	 */
	public void clear() {
		for(int i=0; i<data.length; i++) {
			for(int j=0; j<data[i].length; j++) {
				data[i][j]=0;
			}
		}
	}

}
