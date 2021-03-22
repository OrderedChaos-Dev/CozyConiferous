package cozyconiferous.init;

import cozyconiferous.core.CozyConiferous;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class CCConfiguredSurfaceBuilders {

	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> REDWOODS = register("cc_redwoods", CCSurfaceBuilders.REDWOODS.func_242929_a(SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG));
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> ALPINE_HEIGHTS = register("cc_alpine_heights", CCSurfaceBuilders.ALPINE_HEIGHTS.func_242929_a(SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG));
	
	private static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SC> register(String name,
			ConfiguredSurfaceBuilder<SC> builder) {
		return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(CozyConiferous.MOD_ID, name), builder);
	}
}
