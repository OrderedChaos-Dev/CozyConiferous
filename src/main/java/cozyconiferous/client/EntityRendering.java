package cozyconiferous.client;

import cozyconiferous.client.renderers.CCBoatRenderer;
import cozyconiferous.init.CCEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityRendering {

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(CCEntities.CC_BOAT, CCBoatRenderer::new);
	}
}
