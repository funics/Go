package edu.csuft.hjx.go;

import java.util.ArrayList;
import java.util.List;

/**
 * �˻�AI
 * @author hjx
 *
 */
public class AI {
	GameModel model;
	int[][] chess;
	
	public AI(GameModel m) {
		model = m;
		chess = m.getData();
	}
	
	/**
	 * AI�����̷������������ĺ��ķ���
	 * ���㷨�������λ�ö�˫�����������
	 * @return
	 */
	public Location explore() {
		// �õ�����λ�õļ���
		List<Location> allMayLocation = getAllMayLocation();
		
		// ���е÷��������ͬ��λ��
		//���ʱ���ܴ��ڷ�����ͬ��λ�ã������λ�ñ��������������
		List<Location> allMaxLocation = new ArrayList<Location>();
		
		// ��ÿ�������ӵĿ�λ�������
		int max = 0;//������
		
		//��������λ�ü���
		for (Location location : allMayLocation) {
			
			//����λ�õ÷ֲ�����λ�÷���
			int score = getScore(location.getX(), location.getY());
			location.setScore(score);
			
			//�ж�
			if (score > max) max = score;
			
			//���socre�ǵ�ǰ���ֵ�Ҳ���0
			//���allMaxLocation�����е�һ��Ԫ��ֵС��max
			//����գ�Ȼ������Ӹ�λ��
			//����ֱ����Ӹ�λ��
			if (max != 0 && score == max) {
				if (allMaxLocation.size() > 0)
					if (allMaxLocation.get(0).getScore() < max)
						allMaxLocation.clear();
				allMaxLocation.add(location);
			}
			
			System.out.println("x=" + location.getX() + " y=" + location.getY() + " score=" + score);
		}
		
		//����߷ּ����������ȡһ��λ��
		Location pos = allMaxLocation.get((int) (Math.random() * allMaxLocation.size()));

		System.out.println("��������:�У�" + (pos.getX() + 1) + " ��:" + (pos.getY() + 1));
		
		//���ط�����λ��
		return new Location(pos.getX(), pos.getY());
	}

	/**
	 * ��ȡ����λ�ü���
	 * ��ÿ���ǿ�λ�ã��������ܵ�λ����ӵ�������
	 * ע��ȥ���ظ���λ��
	 * @return λ�ü���
	 */
	private List<Location> getAllMayLocation() {
		
		List<Location> allMayLocation = new ArrayList<Location>();
		
		// �������̻�ȡ������ĵ�
		for (int i = 0; i < chess.length; i++)
			for (int j = 0; j < chess.length; j++) {
				if (chess[i][j] != 0) {
					if (j != 0 && chess[i][j - 1] == 0)
						addToList(allMayLocation, i, j - 1);
					if (j != (chess.length - 1) && chess[i][j + 1] == 0)
						addToList(allMayLocation, i, j + 1);
					if (i != 0 && j != 0 && chess[i - 1][j - 1] == 0)
						addToList(allMayLocation, i - 1, j - 1);
					if (i != 0 && chess[i - 1][j] == 0)
						addToList(allMayLocation, i - 1, j);
					if (i != 0 && j != (chess.length - 1) && chess[i - 1][j + 1] == 0)
						addToList(allMayLocation, i - 1, j + 1);
					if (i != (chess.length - 1) && j != 0 && chess[i + 1][j - 1] == 0)
						addToList(allMayLocation, i + 1, j - 1);
					if (i != (chess.length - 1) && chess[i + 1][j] == 0)
						addToList(allMayLocation, i + 1, j);
					if (i != (chess.length - 1) && j != (chess.length - 1) && chess[i + 1][j + 1] == 0)
						addToList(allMayLocation, i + 1, j + 1);
				}
			}
		return allMayLocation;
	}

	/**
	 * ������������
	 * �����õ�ĵ÷�
	 * @param x ����x
	 * @param y ����y
	 * @return ����
	 */
	public int getScore(int x, int y) {
		//ʹ�û�λ˼��˼��
		//�Լ������ӺͶԷ�����ģ�����Ӽ��������
		int xScore = getXScore(x, y, 1) + getXScore(x, y, 2);
		int yScore = getYScore(x, y, 1) + getYScore(x, y, 2);
		int skewScore1 = getSkewScore1(x, y, 1) + getSkewScore1(x, y, 2);
		int skewScore2 = getSkewScore2(x, y, 1) + getSkewScore2(x, y, 2);
//		int xScore = getXScore(x, y, 1);
//		int yScore = getYScore(x, y, 1);
//		int skewScore1 = getSkewScore1(x, y, 1);
//		int skewScore2 = getSkewScore2(x, y, 1);
		return xScore + yScore + skewScore1 + skewScore2;
	}

	/**
	 * �������ͼ���÷�
	 * @param count1 ���Ӹ���
	 * @param leftStatus �������� 1:��λ��2���Է���ǽ
	 * @param rightStatus �Ҳ������ 1:��λ��2���Է���ǽ
	 * @return ����
	 */
	private int getScoreBySituation(int count1, int leftStatus, int rightStatus) {
		int score = 0;
		
		// �������
		if (count1 >= 5)
			score += 200000;// Ӯ��

		// �������
		if (count1 == 4) {
			if (leftStatus == 1 && rightStatus == 1)
				score += 50000;
			if ((leftStatus == 2 && rightStatus == 1) || (leftStatus == 1 && rightStatus == 2))
				score += 3000;
			if (leftStatus == 2 && rightStatus == 2)
				score += 1000;
		}

		//�������
		if (count1 == 3) {
			if (leftStatus == 1 && rightStatus == 1)
				score += 3000;
			if ((leftStatus == 2 && rightStatus == 1) || (leftStatus == 1 && rightStatus == 2))
				score += 1000;
			if (leftStatus == 2 && rightStatus == 2)
				score += 500;
		}
		
		//�������
		if (count1 == 2) {
			if (leftStatus == 1 && rightStatus == 1)
				score += 500;
			if ((leftStatus == 2 && rightStatus == 1) || (leftStatus == 1 && rightStatus == 2))
				score += 200;
			if (leftStatus == 2 && rightStatus == 2)
				score += 100;
		}
		
		//һ�����
		if (count1 == 1) {
			if (leftStatus == 1 && rightStatus == 1)
				score += 100;
			if ((leftStatus == 2 && rightStatus == 1) || (leftStatus == 1 && rightStatus == 2))
				score += 50;
			if (leftStatus == 2 && rightStatus == 2)
				score += 30;
		}
		
		return score;
	}

	/**
	 * ��ȡ�ÿ�λ�ں����ϵĵ÷�
	 * @param x λ�ú�����
	 * @param y λ��������
	 * @return ����
	 */
	public int getXScore(int x, int y, int cur) {
		int other;// �Է�����
		
		if (cur == 1) other = 2;
		else other = 1;
		
		// ģ������
		chess[x][y] = cur;
		
		//��ࡢ�Ҳ��״̬��������¼����
		int leftStatus = 0;
		int rightStatus = 0;
		int j,count1 = 0;//count1���������Ӹ���
		
		//ɨ���¼����
		for (j = y; j < chess.length; j++) {
			if (chess[x][j] == cur)
				count1++;
			else {
				if (chess[x][j] == 0)
					rightStatus = 1;// �Ҳ�Ϊ��
				if (chess[x][j] == other)
					rightStatus = 2;// �Ҳ౻�Է���ס
				break;
			}
		}
		for (j = y - 1; j >= 0; j--) {
			if (chess[x][j] == cur)
				count1++;
			else {
				if (chess[x][j] == 0)
					leftStatus = 1;// ���Ϊ��
				if (chess[x][j] == other)
					leftStatus = 2;// ��౻�Է���ס
				break;
			}
		}
		
		// �ָ�
		chess[x][y] = 0;
		
		//�������ͼ����λ����
		return getScoreBySituation(count1, leftStatus, rightStatus);
	}

	/**
	 * ��ȡ�õ��������ϵĵ÷�
	 * @param x
	 * @param y
	 */
	public int getYScore(int x, int y, int cur) {
		int other;// �Է�����
		
		if (cur == 1) other = 2;
		else other = 1;

		// ģ������
		chess[x][y] = cur;

		//��ࡢ�Ҳ��״̬��������¼����
		int topStatus = 0;
		int bottomStatus = 0;
		int i,count1 = 0;
		
		//ɨ���¼����
		for (i = x; i < chess.length; i++) {
			if (chess[i][y] == cur)
				count1++;
			else {
				if (chess[i][y] == 0)
					bottomStatus = 1;// �²�Ϊ��
				if (chess[i][y] == other)
					bottomStatus = 2;// �²౻�Է���ס
				break;
			}
		}
		for (i = x - 1; i >= 0; i--) {
			if (chess[i][y] == cur)
				count1++;
			else {
				if (chess[i][y] == 0)
					topStatus = 1;// �ϲ�Ϊ��
				if (chess[i][y] == other)
					topStatus = 2;// �ϲ౻�Է���ס
				break;
			}
		}
		// �ָ�
		chess[x][y] = 0;
		
		return getScoreBySituation(count1, topStatus, bottomStatus);
	}

	/**
	 * ��б��ɨ�����÷�
	 * @param x
	 * @param y
	 */
	public int getSkewScore1(int x, int y, int cur) {
		int other;// �Է�����
		
		if (cur == 1) other = 2;
		else other = 1;

		// ģ������
		chess[x][y] = cur;

		// ����
		int score = 0;

		int topStatus = 0;
		int bottomStatus = 0;
		int i, j,count1 = 0;
		
		for (i = x, j = y; i < chess.length && j < chess.length; i++, j++) {
			if (chess[i][j] == cur)
				count1++;
			else {
				if (chess[i][j] == 0)
					bottomStatus = 1;// �²�Ϊ��
				if (chess[i][j] == other)
					bottomStatus = 2;// �²౻�Է���ס
				break;
			}
		}

		for (i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
			if (chess[i][j] == cur)
				count1++;
			else {
				if (chess[i][j] == 0)
					topStatus = 1;// �ϲ�Ϊ��
				if (chess[i][j] == other)
					topStatus = 2;// �ϲ౻�Է���ס
				break;
			}
		}
		// �ָ�
		chess[x][y] = 0;
		
		return getScoreBySituation(count1, topStatus, bottomStatus);
	}

	/**
	 *
	 * б�ߣ������ϵ�����
	 * ��б��ɨ��
	 * @param x
	 * @param y
	 */
	public int getSkewScore2(int x, int y, int cur) {
		int other;// �Է�����
		if (cur == 1)
			other = 2;
		else
			other = 1;

		// ģ������
		chess[x][y] = cur;

		// ����
		int score = 0;

		int topStatus = 0;
		int bottomStatus = 0;
		int i, j;
		// �����ϵ�����
		int count1 = 0;
		for (i = x, j = y; i < chess.length && j >= 0; i++, j--) {
			if (chess[i][j] == cur)
				count1++;
			else {
				if (chess[i][j] == 0)
					bottomStatus = 1;// �²�Ϊ��
				if (chess[i][j] == other)
					bottomStatus = 2;// �²౻�Է���ס
				break;
			}
		}

		for (i = x - 1, j = y + 1; i >= 0 && j < chess.length; i--, j++) {
			if (chess[i][j] == cur)
				count1++;
			else {
				if (chess[i][j] == 0)
					topStatus = 1;// �ϲ�Ϊ��
				if (chess[i][j] == other)
					topStatus = 2;// �ϲ౻�Է���ס
				break;
			}
		}

		// �ָ�
		chess[x][y] = 0;
//		System.out.println("count: "+count1+"  "+topStatus+"  "+bottomStatus);
		return getScoreBySituation(count1, topStatus, bottomStatus);
	}


	/**
	 * ������ӵ������У����˵��ظ���λ��
	 * @param allMayLocation Ŀ�꼯��
	 * @param x ����x
	 * @param y ����y
	 */
	private void addToList(List<Location> allMayLocation, int x, int y) {
		int sign = 0;
		for (Location location : allMayLocation)
			if (location.getX() == x && location.getY() == y) {
				sign = 1;
				break;
			}
		if (sign == 0) allMayLocation.add(new Location(x, y));
	}

}
