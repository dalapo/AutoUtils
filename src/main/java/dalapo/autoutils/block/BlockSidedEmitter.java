package dalapo.autoutils.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dalapo.autoutils.helper.TextureRegistryHelper;
import dalapo.autoutils.logging.Logger;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSidedEmitter extends BlockDirectional {
	
	public BlockSidedEmitter(Material mtl, String name) {
		super(mtl, name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		front = TextureRegistryHelper.registerTexture("emitter_front", register);
		back = register.registerIcon("furnace_top");
		side = register.registerIcon("furnace_top");
//		side = TextureRegistryHelper.registerTexture("emitter_side", register);
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return true;
	}
	@Override
	public boolean canProvidePower()
	{
		return true;
	}
	
	@Override
	public int isProvidingStrongPower(IBlockAccess access, int x, int y, int z, int s)
	{
		return 0;
//		return access.getBlockMetadata(x, y, z) == ForgeDirection.OPPOSITES[s] ? 15 : 0;
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess access, int x, int y, int z, int s)
	{
		return access.getBlockMetadata(x, y, z) == ForgeDirection.OPPOSITES[s] ? 15 : 0;
	}
}