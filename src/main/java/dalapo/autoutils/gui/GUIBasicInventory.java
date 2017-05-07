package dalapo.autoutils.gui;

import cpw.mods.fml.relauncher.SideOnly;
import dalapo.autoutils.helper.TextureRegistryHelper;
import dalapo.autoutils.tileentity.TileEntityBasicInventory;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GUIBasicInventory extends GuiContainer {

	int invRows;
	int invCols;
	String texName;
	IInventory playerInv;
	private TileEntityBasicInventory te;
	
	public GUIBasicInventory(ContainerBasicInventory container, IInventory player, String texName, TileEntityBasicInventory te) {
		super(container);
		invRows = container.getRows();
		invCols = container.getCols();
		// TODO: Stop hardcoding this
		this.xSize = 176;
		this.ySize = 166;
		this.te = te;
		this.texName = texName;
		this.playerInv = player;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y)
	{
		this.mc.getTextureManager().bindTexture(new ResourceLocation(TextureRegistryHelper.formatGuiName(texName)));
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String str = this.te.getDisplayName();
		fontRendererObj.drawString(str, 88 - fontRendererObj.getStringWidth(str) / 2, 6, 0x404040);
	}
}