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
import dalapo.autoutils.logging.Logger;
import dalapo.autoutils.reference.*;
import dalapo.autoutils.registry.*;

@Mod(modid=NameList.MODID, name=NameList.MODNAME, version=NameList.VERSION)
public class AutoUtils
{
	public static Block[] blocks;
	public static Item[] items;
	@SidedProxy(clientSide="dalapo.autoutils.ClientProxy", serverSide="dalapo.autoutils.ServerProxy")
	public static CommonProxy proxy;
	
	@Instance(NameList.MODID)
	public static AutoUtils instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Logger.info("Entered AutoUtils.preInit");
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Logger.info("Entered AutoUtils.init");
		proxy.init(event);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		Logger.info("Entered AutoUtils.postInit");
		proxy.postInit(event);
	}
}