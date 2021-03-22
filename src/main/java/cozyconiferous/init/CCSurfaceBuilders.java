package cozyconiferous.init;

import java.util.ArrayList;
import java.util.List;

import cozyconiferous.common.worldgen.surfacebuilders.AlpineHeightsSurfaceBuilder;
import cozyconiferous.common.worldgen.surfacebuilders.RedwoodsSurfaceBuilder;
import cozyconiferous.core.CozyConiferous;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = CozyConiferous.MOD_ID)
public class CCSurfaceBuilders {

	public static final List<SurfaceBuilder<?>> SURFACE_BUILDER = new ArrayList<SurfaceBuilder<?>>();
	
	public static final SurfaceBuilder<SurfaceBuilderConfig> REDWOODS = register(new RedwoodsSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_), "redwoods");
	public static final SurfaceBuilder<SurfaceBuilderConfig> ALPINE_HEIGHTS = register(new AlpineHeightsSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_), "alpine_heights");
	
	public static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(F builder, String name) {
		builder.setRegistryName(new ResourceLocation(CozyConiferous.MOD_ID, name));
		ForgeRegistries.SURFACE_BUILDERS.register(builder);
		return builder;
	}
}
