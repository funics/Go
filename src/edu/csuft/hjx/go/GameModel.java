package edu.csuft.hjx.go;

/**
 * ��Ϸ��ģ�ͣ��߼���
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
	 * ��model���������
	 */
	public boolean update(Piece piece) {
		int x = piece.x/Piece.cellSize;
		int y = piece.y/Piece.cellSize;
		data[y][x] = piece.isBlack? 1:2;
		
		return isWin(x, y);
	}
	
	/**
	 * ��Ӯ�ж�
	 * 
	 * @param x
	 * @param y
	 */
	public boolean isWin(int x, int y) {
		int count = 1;
		// ����
		for (int i = x - 1; (i >= 0) && (data[y][i] == data[y][x]); i--) {
			count++;
		}
		for (int i = x + 1; (i < data.length) && (data[y][i] == data[y][x]); i++) {
			count++;
		}
		if (count >= 5) {
			System.out.println(data[y][x] + "��ʤ");
			return true;
		}

		// ����
		count = 1;
		for (int i = y - 1; (i >= 0) && (data[i][x] == data[y][x]); i--) {
			count++;
		}
		for (int i = y + 1; (i < data.length) && (data[i][x] == data[y][x]); i++) {
			count++;
		}
		if (count >= 5) {
			System.out.println(data[y][x] + "��ʤ");
			return true;
		}
		
		//��б
		count = 1;
		int n = x+y;
		for (int i = x+1; (i < data.length) && (n-i>=0) && (data[n-i][i] == data[y][x]); i++) {
			count++;
		}
		for (int i = x-1; (i >= 0) && (n-i<9) && (data[n-i][i] == data[y][x]); i--) {
			count++;
		}
		if (count >= 5) {
			System.out.println(data[y][x] + "��ʤ");
			return true;
		}
		
		
		//��б
		count = 1;
		n = y - x;
		for (int i = x+1; (i < data.length) && (n+i<data.length) && (data[n+i][i] == data[y][x]); i++) {
			count++;
		}
		for (int i = x-1; (i >= 0) && (n+i>=0) && (data[n+i][i] == data[y][x]); i--) {
			count++;
		}
		if (count >= 5) {
			System.out.println(data[y][x] + "��ʤ");
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * ��������Ƿ��Ѵ���
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
	 * �������ģ��
	 */
	public void clear() {
		for(int i=0; i<data.length; i++) {
			for(int j=0; j<data[i].length; j++) {
				data[i][j]=0;
			}
		}
	}

}
