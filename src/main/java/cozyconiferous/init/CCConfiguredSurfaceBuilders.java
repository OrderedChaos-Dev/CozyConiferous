package cozyconiferous.init;

import cozyconiferous.core.CozyConiferous;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class CCConfiguredSurfaceBuilders {

	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> REDWOODS = register("cc_redwoods", CCSurfaceBuilders.REDWOODS.configured(SurfaceBuilder.CONFIG_GRASS));
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> ALPINE_HEIGHTS = register("cc_alpine_heights", CCSurfaceBuilders.ALPINE_HEIGHTS.configured(SurfaceBuilder.CONFIG_GRASS));
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PINE_MEADOWS = register("cc_pine_meadows", CCSurfaceBuilders.PINE_MEADOWS.configured(SurfaceBuilder.CONFIG_GRASS));
	
	private static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SC> register(String name,
			ConfiguredSurfaceBuilder<SC> builder) {
		return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(CozyConiferous.MOD_ID, name), builder);
	}
}
