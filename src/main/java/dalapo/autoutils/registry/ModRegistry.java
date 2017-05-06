package dalapo.autoutils.registry;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import dalapo.autoutils.block.AutoUtilBlock;
import dalapo.autoutils.block.BlockStackMover;
import dalapo.autoutils.block.ItemSubtypeBlock;
import dalapo.autoutils.item.AutoUtilItem;
import dalapo.autoutils.tileentity.TileEntityStackMover;
import dalapo.autoutils.reference.NameList;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

@GameRegistry.ObjectHolder(NameList.MODID)
public class ModRegistry {
	
	public static List<AutoUtilBlock> blocks = new ArrayList<AutoUtilBlock>();
	public static List<AutoUtilItem> items = new ArrayList<AutoUtilItem>();
	public static List<Class<? extends TileEntity>> tiles = new ArrayList<Class<? extends TileEntity>>();
	
	public static void initBlocks()
	{
		blocks.add(new BlockStackMover(Material.wood, "stackmover", false));
		blocks.add(new BlockStackMover(Material.iron, "filtermover", true));
		tiles.add(TileEntityStackMover.class);
		for (AutoUtilBlock b : blocks)
		{
			GameRegistry.registerBlock(b, b.getName());
		}
	}
	
	public static void initItems()
	{
		for (AutoUtilItem i : items)
		{
			GameRegistry.registerItem(i, i.getName());
		}
	}
	
	public static void initTiles()
	{
		for (Class c : tiles)
		{
			GameRegistry.registerTileEntity(c, "dummygi");
		}
	}
	public static void init()
	{
		initBlocks();
		initItems();
		initTiles();
		
	}
	public static int getSize()
	{
		return blocks.size();
	}
}