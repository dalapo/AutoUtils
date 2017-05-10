package dalapo.autoutils.reference;

import net.minecraftforge.common.util.ForgeDirection;

public class UsefulLists {
	private UsefulLists() {}
	
	public static final ForgeDirection[] directions = {ForgeDirection.DOWN, ForgeDirection.UP, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.WEST, ForgeDirection.EAST};
	public static final ForgeDirection[] directionsMinusUp = {ForgeDirection.DOWN, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.WEST, ForgeDirection.EAST};
}