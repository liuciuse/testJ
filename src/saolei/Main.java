package saolei;

public class Main implements Iclick
{
	private Chessboard chessboard;
	
	public Chessboard getChessboard()
	{
		return chessboard;
	}

	public void setChessboard(Chessboard chessboard)
	{
		this.chessboard = chessboard;
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		Chessboard chessboard = new Chessboard();
		chessboard.setClickDelegate(main);
		main.setChessboard(chessboard);
		chessboard.initlei();
		chessboard.initNums();
		chessboard.showChessBoard();
		chessboard.click(2, 2);
	}

	@Override
	public void show(int x, int y, int flag)
	{
		int num = chessboard.getNum(x, y);
		System.out.println("["+x+","+y+"]="+num);
	}

}
