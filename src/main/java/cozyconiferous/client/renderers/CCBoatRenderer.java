package cozyconiferous.client.renderers;

import cozyconiferous.common.entities.CCBoatEntity;
import cozyconiferous.core.CozyConiferous;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CCBoatRenderer extends BoatRenderer {
	private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[] {
			new ResourceLocation(CozyConiferous.MOD_ID, "textures/entity/boat/fir.png"),
			new ResourceLocation(CozyConiferous.MOD_ID, "textures/entity/boat/pine.png"),
			new ResourceLocation(CozyConiferous.MOD_ID, "textures/entity/boat/redwood.png") };

	public CCBoatRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public ResourceLocation getEntityTexture(BoatEntity entity) {
		return BOAT_TEXTURES[((CCBoatEntity)entity).getCCBoatType().ordinal()];
	}
}
