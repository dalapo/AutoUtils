package dalapo.autoutils.reference;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import dalapo.autoutils.block.AutoUtilBlock;
import dalapo.autoutils.block.BlockStackMover;
import dalapo.autoutils.tileentity.TileEntityStackMover;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public class BlockRegistry {
	
	public static List<AutoUtilBlock> blocks = new ArrayList<AutoUtilBlock>();
	public static List<Class<? extends TileEntity>> tiles = new ArrayList<Class<? extends TileEntity>>();
	
	static {
		blocks.add(new BlockStackMover(Material.rock, "StackMover"));
		
		tiles.add(TileEntityStackMover.class);
	}
	
	public static void init()
	{
		for (AutoUtilBlock b : blocks)
		{
			GameRegistry.registerBlock(b, b.getName());
		}
		for (Class c : tiles)
		{
			GameRegistry.registerTileEntity(c, "dummy");
		}
	}
	public static int getSize()
	{
		return blocks.size();
	}
}