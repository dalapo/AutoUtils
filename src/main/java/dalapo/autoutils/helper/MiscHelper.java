package dalapo.autoutils.helper;

import net.minecraftforge.common.util.ForgeDirection;

public class MiscHelper {
	private MiscHelper() {}
	
	public static boolean isInRange(int x, int min, int max)
	{
		return x >= min && x <= max;
	}
	
	public static int sum(int[] arr)
	{
		int acc = 0;
		for (int i=0; i<arr.length; i++)
		{
			acc += arr[i];
		}
		return acc;
	}
	
	public static int getOpposite(int dir)
	{
		return ForgeDirection.getOrientation(dir).getOpposite().ordinal();
	}
}