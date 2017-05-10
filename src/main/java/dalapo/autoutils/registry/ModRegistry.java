package dalapo.autoutils.registry;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import dalapo.autoutils.block.AutoUtilBlock;
import dalapo.autoutils.block.BlockItemRedis;
import dalapo.autoutils.block.BlockRSNotifier;
import dalapo.autoutils.block.BlockSequencePlacer;
import dalapo.autoutils.block.BlockStackMover;
import dalapo.autoutils.block.ItemSubtypeBlock;
import dalapo.autoutils.item.AutoUtilItem;
import dalapo.autoutils.item.ItemRedstoneWatcher;
import dalapo.autoutils.tileentity.TileEntityItemRedis;
import dalapo.autoutils.tileentity.TileEntitySequencePlacer;
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
		blocks.add(new BlockRSNotifier(Material.wood, "rednotifier"));
		blocks.add(new BlockSequencePlacer(Material.rock, "sequenceplacer"));
		blocks.add(new BlockItemRedis(Material.wood, "itemredis"));

		for (AutoUtilBlock b : blocks)
		{
			GameRegistry.registerBlock(b, b.getName());
		}
	}
	
	public static void initItems()
	{
		items.add(new ItemRedstoneWatcher("watcher"));
		for (AutoUtilItem i : items)
		{
			GameRegistry.registerItem(i, i.getName());
		}
	}
	
	public static void initTiles()
	{
		tiles.add(TileEntityStackMover.class);
		tiles.add(TileEntitySequencePlacer.class);
		tiles.add(TileEntityItemRedis.class);
		for (Class c : tiles)
		{
			GameRegistry.registerTileEntity(c, c.getName());
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