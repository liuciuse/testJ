package saolei;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * �� ��-1 �հף�0 ���֣�1-8
 * 
 * @author ��
 * 
 */
public class Chessboard
{

	private int[][] boardArray;
	private static Chessboard singObj;
	private ArrayList<Index> containerFlag;
	private Iclick clickDelegate;
	

	private Queue<Index> queue;
	private ArrayList<Index> blankDealedFlag;//����հ�ȥ��
	private ArrayList<Index> leiInitFlag;//��ʼ���������ȥ��
	private ArrayList<Index> numDealedFlag;//�������ȥ��
	
	public static final int FLAG_NUM_LEI = -1;
	public static final int FLAG_NUM_BLANK = 0;
	
	
	/**
	 * �հ����
	 */
	public Chessboard()
	{
		if (boardArray == null)
		{
			boardArray = new int[Constance.ROW_NUM][Constance.COL_NUM];
			for (int i = 0; i < Constance.ROW_NUM; i++)
			{
				for (int j = 0; j < Constance.COL_NUM; j++)
				{
					boardArray[i][j] = 0;
				}
			}
		}
		if (containerFlag == null)
		{
			containerFlag = new ArrayList<Index>()
			{
			};
		}

		if (queue == null)
		{
			queue = new LinkedList<Index>();
		}
		if (blankDealedFlag == null)
		{
			blankDealedFlag = new ArrayList<Index>();
		}
		if (leiInitFlag == null)
		{
			leiInitFlag = new ArrayList<Index>();
		}
		if (numDealedFlag == null)
		{
			numDealedFlag = new ArrayList<Index>();
		}

	}

	// ��ȡ�������
	public static Chessboard sharedInstance()
	{
		if (null == singObj)
		{
			synchronized (Chessboard.class)
			{
				if (null == singObj)
					singObj = new Chessboard();
			}
		}
		return singObj;
	}

	// ��ʼ����
	public void initlei()
	{
		int j = 0;//����
		leiInitFlag.clear();
		for (int i = 0; j < Constance.LEI_NUM; i++)
		{
			Random random = new Random();
			int row = Math.abs(random.nextInt()) % Constance.ROW_NUM;
			int col = Math.abs(random.nextInt()) % Constance.COL_NUM;

			if (!leiInitFlag.contains(new Index(row, col)))
			{
				j++;
				leiInitFlag.add(new Index(row, col));
				boardArray[row][col] = -1;
			}
		}
		
	}

	/**
	 * ��ʼ������
	 */
	public void initNums()
	{
		initCornerNums();
		initLineNums();
		initCenterNums();
	}

	// ��ʼ����������
	private void initCornerNums()
	{
		if (!isLEI(0, 0))
		{
			int i = 0;
			if (isLEI(0, 1))
			{
				i++;
			}
			if (isLEI(1, 0))
			{
				i++;
			}
			if (isLEI(1, 1))
			{
				i++;
			}
			boardArray[0][0] = i;
		}
		if (!isLEI(0, Constance.COL_NUM - 1))
		{
			int i = 0;
			if (isLEI(0, Constance.COL_NUM - 2))
			{
				i++;
			}
			if (isLEI(1, Constance.COL_NUM - 2))
			{
				i++;
			}
			if (isLEI(1, Constance.COL_NUM - 1))
			{
				i++;
			}
			boardArray[0][Constance.COL_NUM - 1] = i;
		}
		if (!isLEI(Constance.ROW_NUM - 1, 0))
		{
			int i = 0;
			if (isLEI(Constance.ROW_NUM - 2, 0))
			{
				i++;
			}
			if (isLEI(Constance.ROW_NUM - 2, 1))
			{
				i++;
			}
			if (isLEI(Constance.ROW_NUM - 1, 1))
			{
				i++;
			}
			boardArray[Constance.ROW_NUM - 1][0] = i;
		}
		if (!isLEI(Constance.ROW_NUM - 1, Constance.COL_NUM - 1))
		{
			int i = 0;
			if (isLEI(Constance.ROW_NUM - 2, Constance.COL_NUM - 2))
			{
				i++;
			}
			if (isLEI(Constance.ROW_NUM - 1, Constance.COL_NUM - 2))
			{
				i++;
			}
			if (isLEI(Constance.ROW_NUM - 2, Constance.COL_NUM - 1))
			{
				i++;
			}
			boardArray[Constance.ROW_NUM - 1][Constance.COL_NUM - 1] = i;
		}
	}

	// ��ʼ����������
	private void initLineNums()
	{
		// �к�i,�к�j.��ʱ��K.

		// ��һ��
		int i = 0;
		int j = 0;
		int k = 0;
		for (int ln = 1; ln < Constance.COL_NUM - 1; ln++)
		{
			if (isLEI(i,ln))
			{
				continue;
			}
			k = 0;
			if (isLEI(0, ln - 1))
			{
				k++;
			}
			if (isLEI(1, ln - 1))
			{
				k++;
			}
			if (isLEI(1, ln))
			{
				k++;
			}
			if (isLEI(1, ln + 1))
			{
				k++;
			}
			if (isLEI(0, ln + 1))
			{
				k++;
			}
			boardArray[0][ln] = k;
		}

		// ���һ��
		i = Constance.ROW_NUM - 1;
		j = 0;
		k = 0;
		for (int ln = 1; ln < Constance.COL_NUM - 1; ln++)
		{
			if (isLEI(i,ln))
			{
				continue;
			}
			
			k = 0;
			if (isLEI(i - 1, ln - 1))
			{
				k++;
			}
			if (isLEI(i - 1, ln))
			{
				k++;
			}
			if (isLEI(i - 1, ln + 1))
			{
				k++;
			}
			if (isLEI(i, ln - 1))
			{
				k++;
			}
			if (isLEI(i, ln + 1))
			{
				k++;
			}
			boardArray[i][ln] = k;
		}

		// ��һ��
		i = 0;
		j = 0;
		k = 0;
		for (int ln = 1; ln < Constance.ROW_NUM - 1; ln++)
		{
			if (isLEI(ln,j))
			{
				continue;
			}
			k = 0;
			if (isLEI(ln - 1, j))
			{
				k++;
			}
			if (isLEI(ln - 1, j + 1))
			{
				k++;
			}
			if (isLEI(ln, j + 1))
			{
				k++;
			}
			if (isLEI(ln + 1, j + 1))
			{
				k++;
			}
			if (isLEI(ln + 1, j))
			{
				k++;
			}
			boardArray[ln][j] = k;
		}

		// ���һ��
		i = 0;
		j = Constance.COL_NUM - 1;
		k = 0;
		for (int ln = 1; ln < Constance.ROW_NUM - 1; ln++)
		{
			if (isLEI(ln, j))
			{
				continue;
			}
			k = 0;
			if (isLEI(ln - 1, j - 1))
			{
				k++;
			}
			if (isLEI(ln - 1, j))
			{
				k++;
			}
			if (isLEI(ln, j - 1))
			{
				k++;
			}
			if (isLEI(ln + 1, j - 1))
			{
				k++;
			}
			if (isLEI(ln + 1, j))
			{
				k++;
			}
			boardArray[ln][j] = k;
		}
	}

	// ��ʼ���м�����
	private void initCenterNums()
	{
		int k = 0;
		for (int i = 1; i < Constance.ROW_NUM - 1; i++)
		{
			for (int j = 1; j < Constance.COL_NUM - 1; j++)
			{
				if (isLEI(i, j))
				{
					continue;
				}
				
				k = 0;
				for (int ln = i - 1; ln < i + 2; ln++)
				{
					for (int jn = j - 1; jn < j + 2; jn++)
					{
						if (!(ln == i && jn == j) && isLEI(ln, jn))
						{
							k++;
						}
					}
				}
				boardArray[i][j] = k;
			}
		}
	}

	/**
	 * [x,y]��������
	 */
	public boolean isLEI(int x, int y)
	{
		return isLEI(new Index(x, y));
	}

	private boolean isLEI(Index index)
	{
		return boardArray[index.x][index.y] == FLAG_NUM_LEI;
	}

	/**
	 * [x,y]�����ǰװ�
	 */
	public boolean isBlank(int x, int y)
	{
		return isBlank(new Index(x, y));
	}

	private boolean isBlank(Index index)
	{
		return boardArray[index.x][index.y] == FLAG_NUM_BLANK;
	}

	/**
	 * [x,y]����������
	 */
	public boolean isNum(int x, int y)
	{
		return isNum(new Index(x, y));
	}

	private boolean isNum(Index index)
	{
		return boardArray[index.x][index.y] > 0;
	}
	
	/**
	 * ��ȡ����
	 */
	public int getNum(int x,int y)
	{
		return boardArray[x][y];
	}

	/**
	 * ���[x,y]���괥���¼�
	 */
	public void click(int x, int y)
	{
		System.out.println("click");
		if (clickDelegate != null)
		{
			if (isLEI(x, y))
			{
				clickDelegate.show(x, y, Iclick.FLAG_LEI);
			}

			if (isNum(x, y))
			{
				clickDelegate.show(x, y, Iclick.FLAG_NUM);
			}

			if (isBlank(x, y))
			{
				queue.clear();
				blankDealedFlag.clear();
				numDealedFlag.clear();
				
				Index base = new Index(x, y);
				clickDelegate.show(x, y, Iclick.FLAT_BLANK);
				queue.add(base);
				blankDealedFlag.add(base);

				while (!queue.isEmpty())
				{
					Index task = queue.poll();

					for (int i = task.x - 1; i < task.x + 2; i++)
					{
						for (int j = task.y - 1; j < task.y + 2; j++)
						{
							if (i > -1 && i < Constance.ROW_NUM && j > -1
									&& j < Constance.COL_NUM)
							{
								if (isNum(i, j))
								{
									if (!numDealedFlag.contains(new Index(i, j)))
									{
										clickDelegate.show(i, j, Iclick.FLAG_NUM);
										numDealedFlag.add(new Index(i, j));
									}
								} else
								{
									Index index = new Index(i,j);
									if (!blankDealedFlag.contains(index))
									{
										clickDelegate.show(i, j, Iclick.FLAT_BLANK);
										queue.add(index);
										blankDealedFlag.add(index);
									}
								}
							}
						}
					}

				}

			}
		}
	}

	
	/**
	 * ��ʾ���
	 * @return
	 */
	public void showChessBoard()
	{
		if (clickDelegate == null)
		{
			return;
		}
		for (int i = 0; i < Constance.ROW_NUM; i++)
		{
			for (int j = 0; j < Constance.COL_NUM; j++)
			{
				clickDelegate.show(i, j, getFlag(i, j));
			}
		}
	}
	
	
	/**
	 * ��ȡflag
	 */
	public int getFlag(int x,int y)
	{
		if (boardArray[x][y] == -1)
		{
			return Iclick.FLAG_LEI;
		}
		else if (boardArray[x][y] == 0)
		{
			return Iclick.FLAT_BLANK;
		}
		else 
		{
			return Iclick.FLAG_NUM;
		}
	}
	
	/**
	 * ��ȡ��ǩ
	 * @return
	 */
	
	public Iclick getClickDelegate()
	{
		return clickDelegate;
	}

	public void setClickDelegate(Iclick clickDelegate)
	{
		this.clickDelegate = clickDelegate;
	}
	
}

/**
 * λ������
 */
class Index
{
	public int x;
	public int y;

	public Index(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object obj) 
	{
		if(this == obj) return true;
		if (obj instanceof Index)
		{
			Index index = (Index) obj;
			if (x == index.x && y == index.y)
			{
				return true;
			}
		}
		else 
		{
			return false;
		}
		return false;
	}
}
