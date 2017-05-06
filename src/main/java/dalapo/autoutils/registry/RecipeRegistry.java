package dalapo.autoutils.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import dalapo.autoutils.reference.BlockIDList;
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
	}
}