package dalapo.autoutils.reference;

import java.util.List;
import java.util.ArrayList;

import net.minecraft.item.Item;

public class ItemRegistry {
	public static List<Item> items = new ArrayList<Item>();
	
	static {
		// Item entries go here
	}
	
	public static int getSize()
	{
		return items.size();
	}
}