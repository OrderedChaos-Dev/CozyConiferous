package cozyconiferous.client;

import cozyconiferous.init.CCBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockRendering {

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderers() {
		RenderType cutout = RenderType.cutout();
		RenderType cutout_mipped = RenderType.cutoutMipped();
		
		RenderTypeLookup.setRenderLayer(CCBlocks.fir_door, cutout);
		RenderTypeLookup.setRenderLayer(CCBlocks.fir_trapdoor, cutout);
		RenderTypeLookup.setRenderLayer(CCBlocks.pine_door, cutout);
		RenderTypeLookup.setRenderLayer(CCBlocks.pine_trapdoor, cutout);
		RenderTypeLookup.setRenderLayer(CCBlocks.redwood_door, cutout);
		RenderTypeLookup.setRenderLayer(CCBlocks.redwood_trapdoor, cutout);

		RenderTypeLookup.setRenderLayer(CCBlocks.fir_sapling, cutout_mipped);
		RenderTypeLookup.setRenderLayer(CCBlocks.pine_sapling, cutout_mipped);
		RenderTypeLookup.setRenderLayer(CCBlocks.redwood_sapling, cutout_mipped);
		
		RenderTypeLookup.setRenderLayer(CCBlocks.potted_fir_sapling, cutout);
		RenderTypeLookup.setRenderLayer(CCBlocks.potted_pine_sapling, cutout);
		RenderTypeLookup.setRenderLayer(CCBlocks.potted_redwood_sapling, cutout);
	}
}
