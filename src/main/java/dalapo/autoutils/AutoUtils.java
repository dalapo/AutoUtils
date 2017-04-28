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
import dalapo.autoutils.reference.*;

@Mod(modid=NameList.MODID, name=NameList.MODNAME, version=NameList.VERSION)
public class AutoUtils
{
	public static Block[] blocks;
	public static Item[] items;
	@SidedProxy(clientSide="dalapo.autoutils.ClientProxy", serverSide="dalapo.autoutils.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("AutoUtils")
	public static AutoUtils instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		blocks = new Block[BlockRegistry.getSize()];
		items = new Item[ItemRegistry.getSize()];
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		BlockRegistry.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}