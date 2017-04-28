package dalapo.autoutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AutoUtilBlock extends Block {

	final String name;
	
	protected AutoUtilBlock(Material mtl, String name) {
		super(mtl);
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}