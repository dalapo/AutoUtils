package dalapo.autoutils.item;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dalapo.autoutils.block.BlockRSNotifier;
import dalapo.autoutils.event.RSToggleEvent;
import dalapo.autoutils.helper.TextureRegistryHelper;
import dalapo.autoutils.logging.Logger;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemRedstoneWatcher extends AutoUtilItem {

		IIcon inactive;
		IIcon active;
		
		public ItemRedstoneWatcher(String name)
		{
			super(name);
			setHasSubtypes(true);
			setMaxDamage(0);
			setMaxStackSize(1);
			setCreativeTab(CreativeTabs.tabRedstone);
		}
		
		@SideOnly(Side.CLIENT)
		public IIcon getIconFromDamage(int dmg)
		{
			if (dmg == 0) return inactive;
			else return active;
		}
		
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister register)
		{
			inactive = TextureRegistryHelper.registerTexture("watcher_off", register);
			active = TextureRegistryHelper.registerTexture("watcher_on", register);
		}
		
		/*
		@Override
		public int getDamage(ItemStack itemstack)
		{
			boolean isActive = itemstack.getTagCompound().getBoolean("Active");
			return isActive ? 1 : 0;
		}
		*/
		
		@Override
		public void onUpdate(ItemStack itemstack, World world, Entity ep, int i, boolean b)
		{
			super.onUpdate(itemstack, world, ep, i, b);
			if (!itemstack.hasTagCompound()) return;
			int x = itemstack.getTagCompound().getInteger("bound_x");
			int y = itemstack.getTagCompound().getInteger("bound_y");
			int z = itemstack.getTagCompound().getInteger("bound_z");
			if (world.getBlock(x, y, z) instanceof BlockRSNotifier)
			{
				if (world.isBlockIndirectlyGettingPowered(x, y, z))
				{
					itemstack.setItemDamage(1);
				}
				else itemstack.setItemDamage(0);
			}
		}
		
		@Override
		public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer ep)
		{
			if (!world.isRemote)
			{
				if (!itemstack.hasTagCompound())
				{
					Logger.info("Unlinked.");
					return itemstack;
				}
				int x = itemstack.getTagCompound().getInteger("bound_x");
				int y = itemstack.getTagCompound().getInteger("bound_y");
				int z = itemstack.getTagCompound().getInteger("bound_z");
				Logger.info(world.isBlockIndirectlyGettingPowered(x, y, z) ? "Powered" : "Unpowered");
			}
			return itemstack;
		}
		@Override
		public boolean onItemUse(ItemStack itemstack, EntityPlayer ep, World world, int x, int y, int z, int side, float pX, float pY, float pZ)
		{
			if (world.isRemote) return false;
			if (!(world.getBlock(x, y, z) instanceof BlockRSNotifier)) return false;
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("bound_x", x);
			nbt.setInteger("bound_y", y);
			nbt.setInteger("bound_z", z);
			itemstack.setTagCompound(nbt);
			return true;
		}
}