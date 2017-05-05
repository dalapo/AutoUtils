package dalapo.autoutils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import dalapo.autoutils.block.AutoUtilBlock;
import dalapo.autoutils.item.AutoUtilItem;
import dalapo.autoutils.reference.*;

@Mod(modid=NameList.MODID, name=NameList.MODNAME, version=NameList.VERSION)
public class AutoUtils
{
	public static Block[] blocks;
	public static Item[] items;
	@SidedProxy(clientSide="dalapo.autoutils.ClientProxy", serverSide="dalapo.autoutils.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("autoutils")
	public static AutoUtils instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		blocks = new AutoUtilBlock[ModRegistry.blocks.size()];
		items = new AutoUtilItem[ModRegistry.items.size()];
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		ModRegistry.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}