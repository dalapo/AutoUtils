package dalapo.autoutils.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import dalapo.autoutils.reference.BlockIDList;
import dalapo.autoutils.reference.ItemIDList;
import dalapo.autoutils.reference.NameList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeRegistry {
	public static void init()
	{
		GameRegistry.addRecipe(new ItemStack(ModRegistry.blocks.get(BlockIDList.STACK_MOVER)), new Object[] {
				"WHW", "WPW", "WDW", 'W', Blocks.planks, 'H', Blocks.hopper, 'P', Blocks.piston, 'D', Blocks.dropper});
		GameRegistry.addRecipe(new ItemStack(ModRegistry.blocks.get(BlockIDList.FILTER_STACK_MOVER)), new Object[] {
				"QLQ", "QMQ", "QLQ", 'Q', Items.quartz, 'L', new ItemStack(Items.dye, 1, 4), 'M', ModRegistry.blocks.get(BlockIDList.STACK_MOVER)});
		GameRegistry.addRecipe(new ItemStack(ModRegistry.blocks.get(BlockIDList.REDSTONE_NOTIFIER)),
				" T ", "WQW", "WWW", 'T', Blocks.redstone_torch, 'W', Blocks.planks, 'Q', Blocks.quartz_block);
		GameRegistry.addRecipe(new ItemStack(ModRegistry.items.get(ItemIDList.WATCHER)),
				"QTQ", "SSS", 'Q', Items.quartz, 'T', Blocks.redstone_torch, 'S', new ItemStack(Blocks.stone_slab, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModRegistry.blocks.get(BlockIDList.SEQUENCE_PLACER)),
				"III", "CDT", "III", 'I', Blocks.planks, 'C', Blocks.chest, 'D', Items.diamond, 'T', Blocks.dispenser);
		GameRegistry.addRecipe(new ItemStack(ModRegistry.blocks.get(BlockIDList.REDSTONE_PANEL)),
				"RR", "SS", "SS", 'R', Items.redstone, 'S', Blocks.stone_slab);
		GameRegistry.addRecipe(new ItemStack(ModRegistry.blocks.get(BlockIDList.ITEM_REDIS)),
				"WHW", "RQG", "WLW", 'W', Blocks.planks, 'H', Blocks.hopper, 'R', Items.redstone, 'Q', Blocks.quartz_block, 'L', new ItemStack(Items.dye, 1, 4), 'G', Items.glowstone_dust);
	}
}