package dalapo.autoutils.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemSubtypeBlock extends ItemBlockWithMetadata {
	public ItemSubtypeBlock(Block block)
	{
		super(block, block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
}