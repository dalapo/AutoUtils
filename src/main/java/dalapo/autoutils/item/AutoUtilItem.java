package dalapo.autoutils.item;

import net.minecraft.item.Item;

public class AutoUtilItem extends Item {
	private final String name;
	
	public AutoUtilItem(String name)
	{
		super();
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}