package dalapo.autoutils.event;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class RSToggleEvent extends BlockEvent {

	boolean isPowered;
	
	public RSToggleEvent(int x, int y, int z, World world, Block block, int blockMetadata, boolean power) {
		super(x, y, z, world, block, blockMetadata);
		isPowered = power;
	}
}