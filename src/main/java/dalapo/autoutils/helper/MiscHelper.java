package dalapo.autoutils.helper;

import net.minecraftforge.common.util.ForgeDirection;

public class MiscHelper {
	private MiscHelper() {}
	
	public static boolean isInRange(int x, int min, int max)
	{
		return x >= min && x <= max;
	}
	
	public static int getOpposite(int dir)
	{
		return ForgeDirection.getOrientation(dir).getOpposite().ordinal();
	}
}