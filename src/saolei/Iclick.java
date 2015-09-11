package saolei;

public interface Iclick
{
	public static final int FLAG_LEI = 0x00;
	public static final int FLAG_NUM = 0x01;
	public static final int FLAT_BLANK = 0x02;
	
	
	void show(int x,int y,int flag);//œ‘ æ[x,y]…œ
}
