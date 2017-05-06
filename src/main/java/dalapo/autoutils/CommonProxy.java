package dalapo.autoutils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import dalapo.autoutils.gui.AutoUtilsGuiHandler;
import dalapo.autoutils.logging.Logger;
import dalapo.autoutils.registry.ModRegistry;
import dalapo.autoutils.registry.RecipeRegistry;

public class CommonProxy {
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		ModRegistry.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e)
	{
		Logger.info("Entered CommonProxy.init");
		NetworkRegistry.INSTANCE.registerGuiHandler(AutoUtils.instance, new AutoUtilsGuiHandler());
		RecipeRegistry.init();
	}
	
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
	}
}